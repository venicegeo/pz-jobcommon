package model.job.type;

import model.job.PiazzaJobType;

public class AccessJob implements PiazzaJobType {
	public String resourceId = null;
	public final String type = "access";

	public AccessJob() {
	}

	public AccessJob(String resourceId) {
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
