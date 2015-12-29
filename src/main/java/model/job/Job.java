package model.job;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

	public String getJobId() {
		return jobId;
	}

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
