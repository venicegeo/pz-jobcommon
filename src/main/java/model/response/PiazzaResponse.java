package model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a Standard Response from the Piazza Gateway. Responses contain, at
 * bare minimum, the ID of the Job they correspond with.
 * 
 * Each response typically has, at bare minimum, the Job ID for which the
 * request related to. This includes requests such as fetching status, updating
 * metadata, or executing a service. Each request to the Piazza system will have
 * a Job ID generated for it, that can be tracked.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = PiazzaResponse.class, name = "job"), @Type(value = ErrorResponse.class, name = "error"),
		@Type(value = JobStatusResponse.class, name = "status"),
		@Type(value = DataResourceResponse.class, name = "data") })
@JsonInclude(Include.NON_NULL)
public class PiazzaResponse {
	private String type = "job";
	public String jobId;

	public PiazzaResponse() {
	}

	public PiazzaResponse(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the type of this response.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
}
