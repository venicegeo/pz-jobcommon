package model.response;

import model.data.DataResource;

/**
 * Represents the response that the Gateway returns to the user when the user
 * has requested to see the information on a Resource currently held by the
 * Piazza system; represented internally using the DataResource object.
 * 
 * @author Patrick.Doody
 * 
 */
public class DataResourceResponse extends PiazzaResponse {
	private String type = "data";
	public DataResource data;

	public DataResourceResponse() {
	}

	public DataResourceResponse(DataResource dataResource) {
		this.data = dataResource;
	}
}
