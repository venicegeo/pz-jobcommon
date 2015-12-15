package model.response;

import model.job.JobProgress;

/**
 * Represents the Status and Results of a Piazza Job. This includes the
 * readiness of the Job, and potential Status information. If available, the
 * resulting Resource Object created from the Job is attached as well.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobStatusResponse extends PiazzaResponse {
	public Boolean ready = false;
	public Object result = null;
	public String status;
	public JobProgress progress;

	public JobStatusResponse(String jobId) {
		super(jobId);
	}
}
