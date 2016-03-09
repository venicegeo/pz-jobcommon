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
package model.service.metadata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.data.DataType;

/**
 * Data passed to service controller for execute service jobs
 * @author bkrasner
 *
 */
public class ExecuteServiceData {
	String serviceId;
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public Map<String, DataType> getDataInputs() {
		return dataInputs;
	}
	public void setDataInputs(Map<String, DataType> dataInputs) {
		this.dataInputs = dataInputs;
	}
	@JsonProperty("dataInputs")
	public Map<String, DataType> dataInputs;	
	
	
}
