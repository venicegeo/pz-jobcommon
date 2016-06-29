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
import model.data.FileRepresentation;
import model.data.location.FileLocation;

/**
 * Represents a geojson format with accompanying file stored somewhere
 * accessible to Piazza.
 * 
 * @author Sonny.Saniev
 * 
 */
public class GeoJsonDataType implements DataType, FileRepresentation {

	public static final String TYPE = "geojson";

	@ApiModelProperty(value = "The type of data.", required = true, allowableValues = "geojson")
	public final String type = "geojson";

	@ApiModelProperty(value = "The name of the database table holding the data.")
	public String databaseTableName;

	@ApiModelProperty(value = "The location of the data. Used to describe S3 stores, or folder shares, for where the data is located.", dataType = "model.swagger.SwaggerFileLocation")
	public FileLocation location;

	@ApiModelProperty(value = "The geojson content of the data.")
	public String geoJsonContent;

	@ApiModelProperty(value = "The media type of the stored data")
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

	/**
	 * 
	 * @param databaseTableName
	 */
	public void setDatabaseTableName(String databaseTableName) {
		this.databaseTableName = databaseTableName;
	}

	/**
	 * Gets the geojson content string
	 * 
	 * @return
	 */
	public String getGeoJsonContent() {
		return geoJsonContent;
	}

	/**
	 * 
	 * @param geoJsonContent
	 */
	public void setGeoJsonContent(String geoJsonContent) {
		this.geoJsonContent = geoJsonContent;
	}
}