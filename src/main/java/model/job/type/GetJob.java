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