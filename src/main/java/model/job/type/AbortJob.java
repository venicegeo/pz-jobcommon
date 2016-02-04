package model.job.type;

import model.job.PiazzaJobType;

/**
 * Describes the request for a user wishing to cancel a job. The Job ID is
 * required, optional is a reason as to why they wish to job to be cancelled.
 * 
 * @author Patrick.Doody
 * 
 */
public class AbortJob implements PiazzaJobType {
	public String jobId = null;
	public final String type = "abort";
	public String reason;

	public AbortJob() {

	}

	public AbortJob(String jobId) {
		this.jobId = jobId;
	}

	public String getType() {
		return type;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

}
