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

import io.swagger.annotations.ApiModelProperty;

/**
 * Some successful event occurred in Piazza and should be reported to the user.
 * This response object aims to include sufficient information about the success
 * back to the user or the caller.
 * 
 * @author Sonny.Saniev
 * 
 */
public class SuccessResponse extends PiazzaResponse {

	@ApiModelProperty(value = "A description of the successful operation performed.")
	public String message;

	@ApiModelProperty(value = "The Piazza component where the message originated.")
	public String origin;

	public SuccessResponse(String message, String origin) {
		this.message = message;
		this.origin = origin;
	}

	public SuccessResponse() {

	}

	/**
	 * Gets the success message response contains.
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the origin where object came from.
	 * 
	 * @return
	 */
	public String getOrigin() {
		return origin;
	}
}