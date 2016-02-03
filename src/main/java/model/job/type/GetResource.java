package model.job.type;

import model.job.PiazzaJobType;

/**
 * Gets Metadata on a Resource that has already been loaded into the Piazza
 * system. This is different from the AccessJob in that it merely returns the
 * information on the Resource as it is currently stored in the MongoDB
 * Resources collection. In order for the user to request an actual deployment
 * of the data, they must use the Access job.
 * 
 * @author Patrick.Doody
 * 
 */
public class GetResource implements PiazzaJobType {
	public String resourceId = null;
	public final String type = "get-resource";

	public GetResource() {
	}

	public GetResource(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getType() {
		return type;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
