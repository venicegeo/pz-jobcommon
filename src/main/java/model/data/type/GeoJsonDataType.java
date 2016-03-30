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

import model.data.DataType;
import model.data.FileRepresentation;
import model.data.location.FileLocation;

/**
 * Represents a geojson format with accompanying file stored somewhere accessible to Piazza.
 * 
 * @author Sonny.Saniev
 * 
 */
public class GeoJsonDataType implements DataType, FileRepresentation {
	public static final String type = "geojson";
	public FileLocation location;
	public String mimeType;

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public GeoJsonDataType() {

	}

	public String getType() {
		return type;
	}

	/**
	 * Gets the location of the geojson.
	 * 
	 * @return geojson postgis location
	 */
	public FileLocation getLocation() {
		return location;
	}

	/**
	 * Sets the location of the geojson
	 * 
	 * @param location
	 *            The location object
	 */
	public void setLocation(FileLocation location) {
		this.location = location;
	}
}
