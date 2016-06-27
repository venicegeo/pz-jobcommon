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
package model.data.type;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;

/**
 * OGC Web Feature Service (WFS) Resource.
 * 
 * @author Patrick.Doody
 * 
 */
public class WfsDataType implements DataType {

	public static final String TYPE = "wfs";

	@ApiModelProperty(value = "The type of data.", required = true, allowableValues = "wfs")
	public final String type = "wfs";

	@ApiModelProperty(value = "HTTP web address to the WFS endpoint.")
	public String url;

	@ApiModelProperty(value = "The current version of the data.")
	public String version;

	@ApiModelProperty(value = "The type of geospatial feature represented in the data.")
	public String featureType;

	@ApiModelProperty(value = "The media type of the stored data")
	public String mimeType;

	public WfsDataType() {

	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getVersion() {
		return version;
	}

	public String getFeatureType() {
		return featureType;
	}
}