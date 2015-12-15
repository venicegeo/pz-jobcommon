package model.response;

public class ErrorResponse extends PiazzaResponse {
	public String message;
	public String origin;

	public ErrorResponse(String jobId, String message, String origin) {
		super(jobId);
		this.message = message;
		this.origin = origin;
	}
}
