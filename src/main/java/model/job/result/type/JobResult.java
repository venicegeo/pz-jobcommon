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
package model.job.result.type;

import model.job.result.ResultType;

/**
 * A Job result for returning a Job held by the internal Piazza stores. This
 * returns the ID of the Job - not the Job information.
 * 
 * This is used when the result of one Job is to spawn another Job. This Result
 * Type is used to link the two Jobs together.
 * 
 * @author Patrick.Doody
 * 
 */
public class JobResult implements ResultType {
	public String type = "job";
	public String jobId;

	public JobResult() {
	}

	public JobResult(String jobId) {
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