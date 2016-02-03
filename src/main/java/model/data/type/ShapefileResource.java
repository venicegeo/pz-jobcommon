package model.data.type;

import model.data.FileResource;
import model.data.ResourceType;

/**
 * Shapefile resource with accompanying files on disk.
 * 
 * @author Patrick.Doody
 * 
 */
public class ShapefileResource extends FileResource implements ResourceType {
	private String type = "shapefile";

	public ShapefileResource() {

	}

	public String getType() {
		return type;
	}
}
