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

import java.util.List;

import org.springframework.data.util.Pair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model for defining a user profile template that can be shared amongst users.
 * 
 * @author Russell Orf
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileTemplate {

	private String profileTemplateId;

	private List<Throttle> throttles;

	private List<Permission> permissions;

	private List<Pair<String, String>> thirdPartyKeys;

	private List<Pair<String, String>> quotas;

	public String getProfileTemplateId() {
		return profileTemplateId;
	}

	public void setProfileTemplateId(String profileTemplateId) {
		this.profileTemplateId = profileTemplateId;
	}

	public List<Throttle> getThrottles() {
		return throttles;
	}

	public void setThrottles(List<Throttle> throttles) {
		this.throttles = throttles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Pair<String, String>> getThirdPartyKeys() {
		return thirdPartyKeys;
	}

	public void setThirdPartyKeys(List<Pair<String, String>> thirdPartyKeys) {
		this.thirdPartyKeys = thirdPartyKeys;
	}

	public List<Pair<String, String>> getQuotas() {
		return quotas;
	}

	public void setQuotas(List<Pair<String, String>> quotas) {
		this.quotas = quotas;
	}
}