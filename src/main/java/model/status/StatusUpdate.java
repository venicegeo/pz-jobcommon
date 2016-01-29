package model.status;

import model.job.JobProgress;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	public static final String STATUS_SUBMITTED = "Submitted";
	@JsonIgnore
	public static final String STATUS_PENDING = "Pending";
	@JsonIgnore
	public static final String STATUS_RUNNING = "Running";
	@JsonIgnore
	public static final String STATUS_SUCCESS = "Success";
	@JsonIgnore
	public static final String STATUS_CANCELLED = "Cancelled";
	@JsonIgnore
	public static final String STATUS_COMPLETE = "Complete";
	@JsonIgnore
	public static final String STATUS_ERROR = "Error";
	@JsonIgnore
	public static final String STATUS_FAIL = "Fail";

	private String status;
	private JobProgress progress;

	public StatusUpdate() {

	}

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

	public void setStatus(String status) {
		this.status = status;
	}

	public JobProgress getProgress() {
		return progress;
	}

	public void setProgress(JobProgress progress) {
		this.progress = progress;
	}
}
