package model.job;

import model.job.type.AbortJob;
import model.job.type.GetJob;
import model.job.type.IngestJob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = GetJob.class, name = "get"), @Type(value = AbortJob.class, name = "abort"),
		@Type(value = IngestJob.class, name = "ingest") })
@JsonInclude(Include.NON_NULL)
public interface PiazzaJob {
	public String getType();

	public String getJobId();

	public void setJobId(String jobId);

	/**
	 * Determines if this Job must be processed synchronously through REST, or
	 * asynchronously through Kafka.
	 * 
	 * @return
	 */
	public boolean getSynchronous();
}
