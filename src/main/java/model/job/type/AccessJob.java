package model.job.type;

import model.job.PiazzaJobType;

public class AccessJob implements PiazzaJobType {
	public static final String ACCESS_TYPE_GEOSERVER = "geoserver";
	public static final String ACCESS_TYPE_FILE = "file";

	public String resourceId;
	public final String type = "access";
	public String deploymentType = ACCESS_TYPE_GEOSERVER;

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

	/**
	 * 
	 * @param deploymentType
	 *            Valid ACCESS_TYPE_ static string defined in this Job
	 */
	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

	public String getDeploymentType() {
		return deploymentType;
	}
}
