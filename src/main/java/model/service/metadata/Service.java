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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.module.jsonSchema.annotation.JsonHyperSchema;
import com.fasterxml.jackson.module.jsonSchema.annotation.Link;

import model.job.metadata.ResourceMetadata;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonHyperSchema(
	     pathStart = "https://pz-swagger.int.geointservices.io/service/",
	     links = {
	         @Link(href = "", rel = "service", method = "POST", targetSchema = Service.class)
	 })

public class Service {
	
	// If filled in then overwrite entire Service (Delete old service and put in new
	@JsonPropertyDescription("Unique identifier for the user service.")
	private String serviceId;
	
	@JsonPropertyDescription("URL for the user service.")
	private String url;
	
	@JsonPropertyDescription("URL to the contract or schema to the user service (e.g JSON schema, Swagger, etc.)")
	private String contractUrl;
	
	@JsonPropertyDescription("Data associated with the contract.")
	private String contractData;
	
	@JsonPropertyDescription("Additional metadata about the user service.")
	private ResourceMetadata resourceMetadata;
		
	
	public String getContractData() {
		return contractData;
	}
	public void setContractData(String contractData) {
		this.contractData = contractData;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public ResourceMetadata getResourceMetadata() {
		return resourceMetadata;
	}
	public void setResourceMetadata(ResourceMetadata resourceMetadata) {
		this.resourceMetadata = resourceMetadata;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContractUrl() {
		return contractUrl;
	}
	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
	
	

}
