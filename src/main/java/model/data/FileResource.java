package model.data;

/**
 * Abstract class representing a resource that is a file. Stores information as
 * to how to locate the file (S3, FTP, folder share, etc)
 * 
 * @author Patrick.Doody
 * 
 */
public abstract class FileResource {
	public String filePath;

	public String getFilePath() {
		return filePath;
	}
}
