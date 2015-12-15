package model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents a Standard Response from the Piazza Gateway. Responses contain, at
 * bare minimum, the ID of the Job they correspond with.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class PiazzaResponse {
	public String jobId;

	public PiazzaResponse(String jobId) {
		this.jobId = jobId;
	}
}
