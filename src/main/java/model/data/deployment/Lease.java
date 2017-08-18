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
package model.data.deployment;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * JSON Database Model, serialized by Jackson, that represents a Lease in the
 * Piazza System.
 * 
 * A Lease represents an amount of time that a Deployed resource is available in
 * the system for. Deployments should be guaranteed to be available as long as
 * they have an active Deployment lease. A Lease is considered active as long as
 * its expiration date has not passed.
 * 
 * If the Expiration date of a lease has passed, then the resource may still be
 * available (perhaps it has not been subject to resource reaping yet) but it
 * will not be guaranteed. Periodically expired leases will be undeployed in
 * order to avoid overtaxing the system with outdated or unused deployments.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lease implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String leaseId;
	private String deploymentId;
	
	@JsonIgnore
	public DateTime expiresOn;

	/**
	 * Creates a new Deployment Lease.
	 */
	public Lease() {
		// Empty constructor required by Jackson
	}

	/**
	 * Creates a new Deployment Lease.
	 * 
	 * @param id
	 *            The Id of this Lease
	 * @param deploymentId
	 *            The Id of the Deployment that this Lease allows access to
	 * @param expiresOn
	 *            The expiration date of this Lease.
	 */
	public Lease(String leaseId, String deploymentId, DateTime expiresOn) {
		this.leaseId = leaseId;
		this.deploymentId = deploymentId;
		this.expiresOn = expiresOn;
	}

	public String getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	@JsonIgnore
	public DateTime getExpiresOn() {
		return expiresOn;
	}

	@JsonIgnore
	public void setExpiresOn(DateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	@JsonProperty("expiresOn")
	public Long getExpiresOnString() {
		if (expiresOn != null) {
			// Defaults to ISO8601
			return expiresOn.getMillis();
		} else {
			return null;
		}
	}

	@JsonProperty("expiresOn")
	public void setExpiresOnString(Long expiresOn) {
		this.expiresOn = new DateTime(expiresOn);
	}
}
