package messaging.job;

import java.io.IOException;

import model.data.DataResource;
import model.job.Job;
import model.job.type.AbortJob;
import model.job.type.IngestJob;
import model.request.PiazzaJobRequest;
import model.status.StatusUpdate;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Factory class to abstract the production of Job-related Kafka messages using
 * this projects defined POJO Models.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobMessageFactory {
	public static final String REQUEST_JOB_TOPIC_NAME = "Request-Job";
	public static final String CREATE_JOB_TOPIC_NAME = "Create-Job";
	public static final String ABORT_JOB_TOPIC_NAME = "Abort-Job";
	public static final String UPDATE_JOB_TOPIC_NAME = "Update-Job";

	/**
	 * Creates a Kafka message for a Piazza Job to be created. This Topic is
	 * listened to solely by the Dispatcher and acts as a simple pass-through
	 * from the outer world to the internal Piazza components.
	 * 
	 * @param piazzaRequest
	 *            The Job to be created.
	 * @param jobId
	 *            The ID of the Job
	 * @return Kafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getRequestJobMessage(PiazzaJobRequest piazzaRequest, String jobId)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(REQUEST_JOB_TOPIC_NAME, jobId,
				mapper.writeValueAsString(piazzaRequest));
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be cancelled; referenced by
	 * Job ID. This message is consumed by the Job Manager, which will update
	 * the Job Tables, and any other components that are required to act on the
	 * Job being cancelled.
	 * 
	 * @param abortJob
	 *            The Job, describing the Job to abort and potential reason for
	 *            requesting the cancellation.
	 * @return Kafka Message
	 * @throws JsonProcessingException
	 */
	public static ProducerRecord<String, String> getAbortJobMessage(AbortJob abortJob) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(ABORT_JOB_TOPIC_NAME, abortJob.getJobId(),
				mapper.writeValueAsString(abortJob));
	}

	/**
	 * Creates a Kafka message that wraps up a Status Update for a Job. This is
	 * intended to be produced by the Worker component that owns this Job.
	 * 
	 * This can also be used to post the result to the Job. If you're setting
	 * the status of the job to complete, you can also set the
	 * StatusUpdate.setResult() method to attach some resulting object or ID
	 * that the job has created.
	 * 
	 * @param jobId
	 *            The ID of the Job being updated
	 * @param statusUpdate
	 *            Status Update information
	 * @return
	 * @throws JsonProcessingException
	 */
	public static ProducerRecord<String, String> getUpdateStatusMessage(String jobId, StatusUpdate statusUpdate)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(UPDATE_JOB_TOPIC_NAME, jobId, mapper.writeValueAsString(statusUpdate));
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be created. This Topic is
	 * designed to be consumed by the Job Manager component. The JobManager will
	 * track this message and use it to commit the job transaction record into
	 * the Job Tables.
	 * 
	 * @param job
	 *            The Job to be indexed by the Job Manager
	 * @return Kafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getJobManagerCreateJobMessage(Job job) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(CREATE_JOB_TOPIC_NAME, job.getJobId(), mapper.writeValueAsString(job));
	}

	/**
	 * Creates a Job Request method for an Ingest Job to ingest the specified
	 * Data Resource, and assigns the specified Job ID to this Ingest job. The
	 * DataResource passed into this first argument MUST have its DataId
	 * specified.
	 * 
	 * This method is only to be used by internal Piazza components who
	 * *understand how the Job Process works*. This method is an abstraction
	 * around creating an IngestJob to the system, where a DataResource is meant
	 * to be ingested.
	 * 
	 * Instead of having the developer create a PiazzaJobRequest, followed up by
	 * an IngestJob, and then finally specifying the DataResource, this method
	 * aims to abstract out those first two steps and require only the
	 * DataResource to be ingested. This method will handle the rest of the
	 * creation and return the Kafka message that can be sent in order to create
	 * the Ingst Job.
	 * 
	 * @param dataResource
	 *            The Data Resource to be ingested
	 * @param jobId
	 *            The Job ID of the Ingest Job that will be created
	 * @param apiKey
	 *            The internal API Key
	 * @return The Kafka message for creating the Ingest Job, that can be Send
	 *         via a producer.
	 */
	public static ProducerRecord<String, String> getIngestJobForDataResource(DataResource dataResource, String jobId,
			String apiKey) throws Exception {
		// Data Resource must have an ID at this point
		if (dataResource.getDataId() == null) {
			throw new Exception("The DataResource object must have a populated ID.");
		}

		// Create the IngestJob
		IngestJob ingestJob = new IngestJob();
		ingestJob.data = dataResource;
		ingestJob.host = true; // This method is only for internal components,
								// so we will always host

		// Create the Job Request and attach the IngestJob
		PiazzaJobRequest jobRequest = new PiazzaJobRequest();
		jobRequest.apiKey = apiKey;
		jobRequest.jobType = ingestJob;
		ProducerRecord<String, String> ingestJobMessage = JobMessageFactory.getRequestJobMessage(jobRequest, jobId);

		// This message will now be handled by the Dispatcher the same as any
		// other Job request
		return ingestJobMessage;
	}

	/**
	 * Creates a Kafka message for a Piazza Job to be created and consumed by
	 * the internal Piazza worker. This topic is different from the above
	 * method, getJobManagerCreateJobMessage, in that it is meant to be consumed
	 * not by the Job Manager, but the internal component that will be
	 * performing the actual work on the job. Thus, the topic for this message
	 * is dynamic based on the JobType's type field.
	 * 
	 * @param job
	 *            The Job to be processed by the internal worker component.
	 * @returnKafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getWorkerJobCreateMessage(Job job) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(job.jobType.getType());
		return new ProducerRecord<String, String>(job.jobType.getType(), job.getJobId(), mapper.writeValueAsString(job));
	}

	/**
	 * Creates a Kafka message for the Piazza Job Status. This Topic is designed
	 * to be consumed by the Piazza Gateway that will return the status to the
	 * end-user.
	 * 
	 * @param topic
	 *            The topic to place the message on.
	 * @param key
	 *            A field representing a name for the JobId.
	 * @param value
	 *            A field representing the JobId value.
	 * 
	 * @return Kafka message
	 */
	public static ProducerRecord<String, String> getJobReturnMessage(String topic, String key, String value) {
		return new ProducerRecord<String, String>(topic, key, value);
	}

	/**
	 * Parses the raw JSON Payload into the PiazzaRest backing models. No value
	 * validation done here, only syntax.
	 * 
	 * @param json
	 *            JSON Payload from POST RequestBody
	 * @return PiazzaRequest object for the JSON Payload.
	 * @throws Exception
	 */
	public static PiazzaJobRequest parseRequestJson(String json) throws IOException, JsonParseException,
			JsonMappingException {
		PiazzaJobRequest request = new ObjectMapper().readValue(json, PiazzaJobRequest.class);
		return request;
	}

}
