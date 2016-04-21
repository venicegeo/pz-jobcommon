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

import model.service.metadata.Service;

/**
 * A Response containing information regarding a Piazza Service.
 * 
 * @author Patrick.Doody
 *
 */
public class ServiceResponse extends PiazzaResponse {
	private String type = "service";
	public Service service;
	public String serviceId;

	public ServiceResponse() {
	}

	public ServiceResponse(Service service) {
		this.service = service;
	}

	public ServiceResponse(String serviceId) {
		this.serviceId = serviceId;
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
