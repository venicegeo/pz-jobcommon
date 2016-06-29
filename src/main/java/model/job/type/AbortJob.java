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
package model.job.type;

import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;

/**
 * Describes the request for a user wishing to cancel a job. The Job ID is
 * required, optional is a reason as to why they wish to job to be cancelled.
 * 
 * @author Patrick.Doody
 * 
 */
public class AbortJob implements PiazzaJobType {
	
	@ApiModelProperty(value = "The ID of the subject job.")
	public String jobId = null;
	
	@ApiModelProperty(value = "The type of job.", required = true, allowableValues = "abort")	
	public final String type = "abort";

	@ApiModelProperty(value = "The reason given for aborting the job.")
	public String reason;

	public AbortJob() {

	}

	public AbortJob(String jobId) {
		this.jobId = jobId;
	}

	public AbortJob(String jobId, String reason) {
		this(jobId);
		this.reason = reason;
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