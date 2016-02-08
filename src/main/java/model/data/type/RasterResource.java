package model.data.type;

import model.data.DataType;
import model.data.location.FileLocation;

/**
 * Represents a Raster image with accompanying file stored somewhere accessible
 * to Piazza.
 * 
 * The term Raster is being used as any valid raster file format that can be
 * recognized by GDAL.
 * 
 * @author Patrick.Doody
 * 
 */
public class RasterResource implements DataType {
	public static final String type = "raster";
	public FileLocation location;

	public RasterResource() {

	};

	public String getType() {
		return type;
	}

	public FileLocation getLocation() {
		return location;
	}

	public void setLocation(FileLocation location) {
		this.location = location;
	}
}
