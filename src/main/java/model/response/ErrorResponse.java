package model.response;

/**
 * Some internal error occurred in Piazza and should be reported to the user.
 * This response object aims to include sufficient information about the error
 * back to the user.
 * 
 * @author Patrick.Doody
 * 
 */
public class ErrorResponse extends PiazzaResponse {
	private String type = "error";
	public String message;
	public String origin;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String jobId, String message, String origin) {
		super(jobId);
		this.message = message;
		this.origin = origin;
	}
}
