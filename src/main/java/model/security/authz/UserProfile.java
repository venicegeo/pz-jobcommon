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
package model.security.authz;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for defining a user profile within the Piazza data store
 * 
 * @author Russell Orf
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile implements Serializable {

	private String username;

	private String distinguishedName;

	@JsonIgnore
	private DateTime createdOn;

	private String createdBy;

	private String profileTemplateId;

	private String adminCode;
	
	private String dutyCode;
	
	private String country;
	
	private boolean isNPE;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public DateTime getCreatedOn() {
		return createdOn;
	}

	@JsonIgnore
	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProfileTemplateId() {
		return profileTemplateId;
	}

	public void setProfileTemplateId(String profileTemplateId) {
		this.profileTemplateId = profileTemplateId;
	}

	@JsonProperty("createdOn")
	public String getCreatedOnString() {
		if (createdOn != null) {
			// Defaults to ISO8601
			return createdOn.toString();
		} else {
			return null;
		}
	}

	@JsonProperty("createdOn")
	public void setCreatedOnString(String createdOn) {
		this.createdOn = new DateTime(createdOn);
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isNPE() {
		return isNPE;
	}

	public void setNPE(boolean isNPE) {
		this.isNPE = isNPE;
	}
}