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
package model.job.metadata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecuteServiceData {
	@JsonProperty("dataInputs")
	public Map<String, String> dataInputs;	
	public String dataInput;
	public String resourceId;
	
	public Map<String, String> getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(Map<String, String> dataInputs) {
		this.dataInputs = dataInputs;
	}

	public String getDataInput() {
		return dataInput;
	}

	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}		
}
