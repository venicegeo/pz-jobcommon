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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.job.metadata.ResourceMetadata;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class which represents a service registered and managed by Piazza
 * @author mlynum
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)

public class Service {
	
	public enum METHOD_TYPE {
		GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
	};
	
	@ApiModelProperty(value = "HTTP method types")
	private METHOD_TYPE methodType;
	
	@ApiModelProperty(required = false, value = "Provided by the System. The unique Id of the Service")
	private String serviceId;

	@ApiModelProperty(required = true, value = "The URL to the user Service to be executed")
	@NotNull
	@Size(min=1)
	private String url;

	@ApiModelProperty(required = true, value = "URL to the schema or contract to interface with the Service, such as a Swagger file, or documentation")
	@NotNull
	@Size(min=1)
	private String contractUrl;

	@ApiModelProperty(required = true, value = "The HTTP method used to invoke this user Service", dataType = "string", allowableValues = "GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT")
	@NotNull
	@Size(min=1)
	private String method;
	
	@ApiModelProperty(value = "The timeout Piazza waits for a response (defaults to 120 seconds)")
    private Long timeout;
	
	@ApiModelProperty(value = "The frequency in which Piazza sends heartbeat requests to check on the health of the Service  (defaults to 120 seconds)")
    private Long heartbeat;


	@ApiModelProperty(value = "Object of common metadata fields used to describe Data or Services within the Piazza system")
	@NotNull
	@Valid
	private ResourceMetadata resourceMetadata;

	public Long getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(Long heartbeat) {
		this.heartbeat = heartbeat;
	}
	
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
	
	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
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