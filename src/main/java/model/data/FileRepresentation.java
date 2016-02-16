package model.data;

import model.data.location.FileLocation;

/**
 * Interface that is implemented by DataTypes that can contain a FileLocation
 * object, that is possibly filled out (possibly not!)
 * 
 * @author Patrick.Doody
 * 
 */
public interface FileRepresentation {
	public FileLocation getLocation();

	public void setLocation(FileLocation location);
}
