package model.response;

import io.swagger.annotations.ApiModelProperty;

public class JobResponse extends PiazzaResponse {

	@ApiModelProperty(value = "The ID of the Job referenced in the request.")
	public String jobId;

	public JobResponse(String jobId) {
		this.jobId = jobId;
	}

	public JobResponse() {

	}
}
