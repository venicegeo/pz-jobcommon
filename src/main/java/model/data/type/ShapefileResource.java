package model.data.type;

import model.data.DataType;
import model.data.location.FileLocation;

/**
 * Shapefile resource with accompanying files on disk. Shapefiles are typically
 * sent to the Gateway compressed into a single .zip, so as to define the user
 * only send a single file through the POST message. Upon receiving the message,
 * the appropriate components will inflate this zip file as necessary.
 * 
 * @author Patrick.Doody
 * 
 */
public class ShapefileResource implements DataType {
	public static final String type = "shapefile";
	public FileLocation location;

	public ShapefileResource() {

	}

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
