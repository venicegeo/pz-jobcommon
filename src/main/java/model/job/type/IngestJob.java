package model.job.type;

import model.job.PiazzaJob;

public class IngestJob implements PiazzaJob {
	public String jobId = null;
	public final String type = "ingest";
	public final boolean synchronous = false;

	public IngestJob() {

	}

	public IngestJob(String jobId) {
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

	public boolean getSynchronous() {
		return synchronous;
	}
}
