package model.data.type;

import model.data.FileResource;
import model.data.DataType;

/**
 * Shapefile resource with accompanying files on disk.
 * 
 * @author Patrick.Doody
 * 
 */
public class ShapefileResource extends FileResource implements DataType {
	public static final String type = "shapefile";

	public ShapefileResource() {

	}

	public String getType() {
		return type;
	}
}
