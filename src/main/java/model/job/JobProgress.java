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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the progress of a Job. Job Workers can fire update to the progress
 * of a Job throughout Job Processing to keep users and other components current
 * with the processing.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class JobProgress {
	public Integer percentComplete;
	public String timeRemaining;
	public String timeSpent;

	public JobProgress() {
	}

	public JobProgress(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}

	public Integer getPercentComplete() {
		return percentComplete;
	}
}
