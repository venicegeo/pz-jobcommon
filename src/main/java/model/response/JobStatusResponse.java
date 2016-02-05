package model.response;

import model.job.Job;
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
	private String type = "status";
	public Object result;
	public String status;
	public JobProgress progress;

	public JobStatusResponse() {
		super();
	}

	public JobStatusResponse(Job job) {
		super(job.getJobId());
		result = job.result;
		status = job.status;
		progress = job.progress;
	}
}
