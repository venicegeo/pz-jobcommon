package messages.job;

import model.job.PiazzaJob;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Factory class to abstract the production of Job-related Kafka messages using
 * this projects defined POJO Models.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobMessageFactory {
	/**
	 * Creates a Kafka message for a Piazza Job to be created.
	 * 
	 * @param piazzaJob
	 *            The Job to be created.
	 * @return Kafka message
	 * @throws JsonProcessingException
	 *             The PiazzaJob cannot be serialized to JSON.
	 */
	public static ProducerRecord<String, String> getJobMessage(PiazzaJob piazzaJob) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ProducerRecord<String, String>(piazzaJob.getJobId(), "Job", mapper.writeValueAsString(piazzaJob));
	}
}
