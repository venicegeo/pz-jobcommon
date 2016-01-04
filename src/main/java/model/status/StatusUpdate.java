package model.status;

import model.job.JobProgress;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A Status Update message for a Job. This is intended to be fired by Worker
 * components throughout the processing of a Job.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class StatusUpdate {
	public String status;
	public JobProgress progress;

	public StatusUpdate(String status) {
		this.status = status;
	}

	public StatusUpdate(String status, JobProgress progress) {
		this(status);
		this.progress = progress;
	}

	public String getStatus() {
		return status;
	}

	public JobProgress getJobProgress() {
		return progress;
	}
}
