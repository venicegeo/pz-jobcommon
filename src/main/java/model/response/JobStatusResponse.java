/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * re
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.response;

import io.swagger.annotations.ApiModelProperty;
import model.job.Job;
import model.job.JobProgress;
import model.job.result.ResultType;

/**
 * Represents the Status and Results of a Piazza Job. This includes the
 * readiness of the Job, and potential Status information. If available, the
 * resulting Resource Object created from the Job is attached as well.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobStatusResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Contains the Job Status Information", required = true)
	public JobStatusData data = new JobStatusData();

	public JobStatusResponse() {
	}

	public JobStatusResponse(Job job) {
		data.populateFromJob(job);
	}

	/**
	 * Inner class to wrap the information related to a Job's status.
	 *
	 */
	public class JobStatusData {
		@ApiModelProperty(value = "A reference to the Result of the Job. This could be a Resource Id, or a Service Id, in certain cases. Or perhaps an error if the Job encountered an error during processing", dataType = "model.swagger.SwaggerResultType")
		public ResultType result;

		@ApiModelProperty(value = "The status of the Job. Submitted, Running, Success, Error, or Failure", required = true)
		public String status;

		@ApiModelProperty(value = "Polymorphically describes the information defining what actions this Job should take", required = true)
		public String jobType;

		@ApiModelProperty(value = "The name of the user who submitted the Job", required = true)
		public String createdBy;
		
		@ApiModelProperty(value = "The time the Job was requested.", required = true)
		public String createdOn;

		@ApiModelProperty(value = "Object containing metadata describing the current status of the Job")
		public JobProgress progress;

		@ApiModelProperty(value = "The Id of the Job.")
		public String jobId;

		public void populateFromJob(Job job) {
			jobId = job.getJobId();
			result = job.result;
			status = job.status;
			progress = job.progress;
			jobType = job.getJobType().getClass().getSimpleName();
			createdBy = job.createdBy;
			createdOn = job.createdOn.toString();
		}
	}

}