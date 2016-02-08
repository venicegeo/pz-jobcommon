package model.data.location;

/**
 * Model for the necessary information that is required to access a file on an
 * AWS S3 bucket share.
 * 
 * @author Patrick.Doody
 * 
 */
public class S3FileStore implements FileLocation {
	public static final String type = "s3";
	public String uri;
	public String credentials;

	public String getType() {
		return type;
	}
}
