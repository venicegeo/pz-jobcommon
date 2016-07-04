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


import io.swagger.annotations.ApiModelProperty;

import model.job.metadata.ResourceMetadata;


public class Service {

	@ApiModelProperty(required = true, value = "The unique identifier of the user service.")
	private String serviceId;

	@ApiModelProperty(required = true, value = "The URL to the user service to be executed.")
	private String url;

	@ApiModelProperty(required = false, value = "URL to the schema or contract to interface with the service, such as a Swagger file, or documentation.")
	private String contractUrl;

	@ApiModelProperty(required = true, value = "The HTTP method used to invoke this user service.")
	public String method;

	@ApiModelProperty(value = "Object of common metadata fields used to describe Data or Services within the Piazza system.")
	private ResourceMetadata resourceMetadata;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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