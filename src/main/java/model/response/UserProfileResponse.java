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

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.security.authz.UserProfile;

/**
 * Packages a response for a single User Profile
 * 
 * @author Patrick.Doody
 *
 */
@ApiModel(value = "UserProfileResponse")
public class UserProfileResponse extends PiazzaResponse implements Serializable {
	@ApiModelProperty(required = true, value = "The Object containing the User Profile.")
	public UserProfileData data = new UserProfileData();

	public UserProfileResponse(UserProfile userProfile) {
		data.setUserProfile(userProfile);
	}

	public UserProfileResponse() {
	}

	/**
	 * Used to wrap the User Profile in an annotatable class.
	 */
	public class UserProfileData {
		@ApiModelProperty(required = true, value = "The User Profile information.")
		private UserProfile userProfile;

		public UserProfile getUserProfile() {
			return this.userProfile;
		}

		public void setUserProfile(UserProfile userProfile) {
			this.userProfile = userProfile;
		}
	}
}
