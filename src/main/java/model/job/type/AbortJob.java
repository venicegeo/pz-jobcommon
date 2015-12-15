package model.job.type;

import model.job.PiazzaJob;

public class AbortJob implements PiazzaJob {
	public String jobId = null;
	public final String type = "abort";

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
