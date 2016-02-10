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

import java.util.List;

import model.data.DataType;

/**
 * OGC Web Feature Service (WFS) Resource.
 * 
 * @author Patrick.Doody
 * 
 */
public class WfsResource implements DataType {
	public static final String type = "wfs";
	public String url;

	public String version;
	public List<String> featureTypes;

	public WfsResource() {

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

	public List<String> getFeatureTypes() {
		return featureTypes;
	}
}
