package model.job.result.type;

import model.job.result.ResultType;

/**
 * Represents a File that can be acquired by accessing a REST endpoint on the
 * Gateway. These files are prepared by the Accessor component.
 * 
 * @author Patrick.Doody
 * 
 */
public class FileResult implements ResultType {
	public final String type = "file";
	public String url;

	/**
	 * Default Constructor
	 */
	public FileResult() {

	}

	/**
	 * 
	 * @param url
	 *            The URL, off of the Gateway, that the file can be acquired
	 *            from.
	 */
	public FileResult(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

}
