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
package model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a Standard Response from the Piazza Gateway. Responses contain, at
 * bare minimum, the ID of the Job they correspond with.
 * 
 * Each response typically has, at bare minimum, the Job ID for which the
 * request related to. This includes requests such as fetching status, updating
 * metadata, or executing a service. Each request to the Piazza system will have
 * a Job ID generated for it, that can be tracked.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = PiazzaResponse.class, name = "job"), @Type(value = ErrorResponse.class, name = "error"),
		@Type(value = JobStatusResponse.class, name = "status"),
		@Type(value = DataResourceResponse.class, name = "data"),
		@Type(value = DataResourceListResponse.class, name = "data-list"),
		@Type(value = DeploymentResponse.class, name = "deployment"),
		@Type(value = DeploymentListResponse.class, name = "deployment-list"),
		@Type(value = ServiceResponse.class, name = "service"),
		@Type(value = ServiceListResponse.class, name = "service-list") })
@JsonInclude(Include.NON_NULL)
public class PiazzaResponse {
	private String type = "job";
	public String jobId;

	public PiazzaResponse() {
	}

	public PiazzaResponse(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the type of this response.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
}
