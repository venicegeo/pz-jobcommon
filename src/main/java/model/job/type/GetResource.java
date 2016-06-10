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
package model.job.type;

import model.job.PiazzaJobType;

/**
 * Gets Metadata on a Resource that has already been loaded into the Piazza
 * system. This is different from the AccessJob in that it merely returns the
 * information on the Resource as it is currently stored in the MongoDB
 * Resources collection. In order for the user to request an actual deployment
 * of the data, they must use the Access job.
 * 
 * @author Patrick.Doody
 * 
 */
public class GetResource implements PiazzaJobType {
	public String resourceId = null;
	public final String type = "get-resource";

	public GetResource() {
	}

	public GetResource(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getType() {
		return type;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}