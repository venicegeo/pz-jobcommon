package model.data.location;

/**
 * Model representing a File accessible via a folder share that is will be
 * accessible to the Piazza Core internal components.
 * 
 * @author Patrick.Doody
 * 
 */
public class FolderShare implements FileLocation {
	public static final String type = "share";
	public String filePath;

	public String getType() {
		return type;
	}
}
