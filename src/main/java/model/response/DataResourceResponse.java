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
import model.data.DataResource;

/**
 * Represents the response that the Gateway returns to the user when the user
 * has requested to see the information on a Resource currently held by the
 * Piazza system; represented internally using the DataResource object.
 * 
 * @author Patrick.Doody
 * 
 */
public class DataResourceResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Object containing metadata describing the Data", required = true)
	public DataResource data;

	public DataResourceResponse() {
		// Empty constructor required by Jackson
	}

	public DataResourceResponse(DataResource dataResource) {
		this.data = dataResource;
	}
}