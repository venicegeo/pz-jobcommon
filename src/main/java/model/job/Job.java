package model.job;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Job {
	public String jobId;
	public PiazzaJobType jobType;
	@JsonIgnore
	public DateTime submitted;
	public String submitterApiKey;
	public String status;
	public JobProgress progress = new JobProgress();
	public List<JobProgress> history = new ArrayList<JobProgress>();
	public Object result = null; // TODO: How to represent this model?

	@ObjectId
	@JsonProperty("_id")
	public String getJobId() {
		return jobId;
	}

	@ObjectId
	@JsonProperty("_id")
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@JsonProperty("submitted")
	public String getSubmittedString() {
		// Defaults to ISO8601
		return submitted.toString();
	}

	@JsonProperty("submitted")
	public void setSubmittedString(String submitted) {
		this.submitted = new DateTime(submitted);
	}
}
