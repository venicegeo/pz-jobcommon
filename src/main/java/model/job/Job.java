/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.job;

import java.util.ArrayList;
import java.util.List;

import model.job.result.ResultType;
import model.request.PiazzaJobRequest;
import model.status.StatusUpdate;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines a Job in the Piazza system.
 * 
 * A Job is uniquely identified by a Job ID. This is the primary key used for
 * lookup in the Jobs collection.
 * 
 * Additional Metadata is also stored with a job, such as who submitted the job,
 * what their API key is, and when the Job was submitted.
 * 
 * Job Status and Progress are also tracked and can be reliably used to
 * determine what the current status of the job is as it passes throughout the
 * Piazza system.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	public String jobId;
	public PiazzaJobType jobType;
	@JsonIgnore
	public DateTime submitted;
	public String submitterUserName;
	public String status;
	public JobProgress progress = new JobProgress();
	public List<JobProgress> history = new ArrayList<JobProgress>();
	public ResultType result;

	public Job() {

	}

	public Job(PiazzaJobRequest request, String jobId) {
		this.jobId = jobId;
		this.jobType = request.jobType;
		this.submitterApiKey = request.apiKey;
		this.status = StatusUpdate.STATUS_SUBMITTED;
		this.submitted = new DateTime();
	}

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

	public PiazzaJobType getJobType() {
		return jobType;
	}

	public ResultType getResult() {
		return result;
	}
}
