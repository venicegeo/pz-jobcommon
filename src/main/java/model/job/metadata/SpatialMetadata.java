package model.job.metadata;

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
	String coordinateReferenceSystem;
	Integer epsgCode;
	Double minX;
	Double minY;
	Double maxX;
	Double maxY;

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

}
