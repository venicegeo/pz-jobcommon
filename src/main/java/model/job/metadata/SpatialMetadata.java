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
package model.job.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Spatial metadata fields associated with a Resource. Used to generically
 * specify the bounding box and the spatial reference of a dataset.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class SpatialMetadata {
	private String coordinateReferenceSystem;
	private Integer epsgCode;
	private Double minX;
	private Double minY;
	private Double minZ;
	private Double maxX;
	private Double maxY;
	private Double maxZ;
	private Integer numFeatures;
	
	public SpatialMetadata() {

	}

	/**
	 * Gets the CRS of the data
	 * 
	 * @return CRS of the data
	 */
	public String getCoordinateReferenceSystem() {
		return coordinateReferenceSystem;
	}

	/**
	 * Sets the CRS of the data. This should be the descriptive SRS, such as:
	 * 
	 * GEOGCS["GCS_WGS_1984", DATUM["D_WGS_1984", SPHEROID["WGS_1984",
	 * 6378137.0, 298.257223563]], PRIMEM["Greenwich", 0.0], UNIT["degree",
	 * 0.017453292519943295], AXIS["Longitude", EAST], AXIS["Latitude", NORTH]]
	 * 
	 * 
	 * @param coordinateReferenceSystem
	 *            CRS
	 */
	public void setCoordinateReferenceSystem(String coordinateReferenceSystem) {
		this.coordinateReferenceSystem = coordinateReferenceSystem;
	}

	/**
	 * Sets the EPSG code. Ignores the "EPSG:" prefix, and gets only the number.
	 * 
	 * @return EPSG Code
	 */
	public Integer getEpsgCode() {
		return epsgCode;
	}

	/**
	 * Sets the EPSG Code. Ignore the "EPSG:" prefix, this only accepts the
	 * number
	 * 
	 * @param epsgCode
	 *            EPSG Code
	 */
	public void setEpsgCode(Integer epsgCode) {
		this.epsgCode = epsgCode;
	}

	/**
	 * Returns the EPSG String. Included is the "EPSG:" prefix.
	 * 
	 * @return EPSG String.
	 */
	@JsonIgnore
	public String getEpsgString() {
		return String.format("EPSG:%s", epsgCode);
	}
	
	public Double getMinX() {
		return minX;
	}

	public void setMinX(Double minX) {
		this.minX = minX;
	}

	public Double getMinY() {
		return minY;
	}

	public void setMinY(Double minY) {
		this.minY = minY;
	}

	public Double getMinZ() {
		return minZ;
	}

	public void setMinZ(Double minZ) {
		this.minZ = minZ;
	}

	public Double getMaxX() {
		return maxX;
	}

	public void setMaxX(Double maxX) {
		this.maxX = maxX;
	}

	public Double getMaxY() {
		return maxY;
	}

	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}

	public Double getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(Double maxZ) {
		this.maxZ = maxZ;
	}

	public Integer getNumFeatures() {
		return numFeatures;
	}

	public void setNumFeatures(Integer numFeatures) {
		this.numFeatures = numFeatures;
	}
}