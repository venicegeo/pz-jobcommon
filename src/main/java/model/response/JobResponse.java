package model.response;

import io.swagger.annotations.ApiModelProperty;

public class JobResponse extends PiazzaResponse {

	private String type = "job";
	
	@ApiModelProperty(value = "The ID of the Job referenced in the request.")
	public String jobId;

	public JobResponse(String jobId) {
		this.jobId = jobId;
	}
}
