package model.job.type;

import model.job.PiazzaJobType;

/**
 * Gets the Status of a Job in the Piazza system. All Jobs submitted will have
 * an entry in the MongoDB Jobs collection, and this Job will simply query for
 * the specified Job ID on that table and retrieve the status of that Job.
 * 
 * @author Patrick.Doody
 * 
 */
public class GetJob implements PiazzaJobType {
	public String jobId = null;
	public final String type = "get";

	public GetJob() {
	}

	public GetJob(String jobId) {
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
