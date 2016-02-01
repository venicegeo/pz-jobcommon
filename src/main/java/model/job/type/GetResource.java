package model.job.type;

import model.job.PiazzaJobType;

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
