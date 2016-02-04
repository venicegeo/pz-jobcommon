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
	private String result;

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

	/**
	 * Sets the Result of the Job. This result will likely be an ID that points
	 * to a resource in the Resources collection.
	 * 
	 * As a reminder, by nature, it would seem silly to submit the Result for
	 * the Job before the Status of the Job is set to COMPLETE. If you're
	 * attaching the Result to this StatusUpdate object, then it's suggested you
	 * also set the status flag to completed.
	 * 
	 * @param result
	 *            The result of the Job, or a reference to the Result of the
	 *            Job.
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
