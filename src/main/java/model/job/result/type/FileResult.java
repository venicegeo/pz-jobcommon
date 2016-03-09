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
	public String dataId;

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
	 * @param dataId
	 *            The Data ID of the resource.
	 */
	public FileResult(String dataId) {
		this.dataId = dataId;
	}

	public String getType() {
		return type;
	}

	public String getDataId() {
		return dataId;
	}

}
