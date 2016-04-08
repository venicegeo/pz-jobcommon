package model.request;

/**
 * Represents a File Request to the Gateway made by an end-user posting to the
 * /file Gateway endpoint. The Access component is responsible for making the
 * file available to the user before the file can be downloaded.
 * 
 * @author Patrick.Doody
 * 
 */
public class FileRequest {
	public String userName;
	public String dataId;
}
