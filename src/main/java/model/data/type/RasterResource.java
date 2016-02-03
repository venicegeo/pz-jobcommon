package model.data.type;

import model.data.FileResource;
import model.data.ResourceType;

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
public class RasterResource extends FileResource implements ResourceType {
	private String type = "raster";

	public RasterResource() {

	};

	public String getType() {
		return type;
	}
}
