package model.job.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.job.PiazzaJobType;

/**
 * Describes a user Request for Accessing data out of the Piazza core system.
 * This could potentially be for data that is held within the Piazza holdings,
 * or data that is external to Piazza core which has had its Metadata previously
 * loaded.
 * 
 * The two current deployment types are 1) Hosted on a Piazza GeoServer
 * instance, which can allow for Web Coverage Service or Web Feature Service,
 * depending on the type of data to be accessed. 2) Raw file download. This will
 * place the file in a accessible location that the user can access, such as an
 * FTP or an S3 location.
 * 
 * Currently, a single resource ID is provided to denote the Resource in the
 * Piazza holdings to access.
 * 
 * @author Patrick.Doody
 * 
 */
public class AccessJob implements PiazzaJobType {
	@JsonIgnore
	public static final String ACCESS_TYPE_GEOSERVER = "geoserver";
	@JsonIgnore
	public static final String ACCESS_TYPE_FILE = "file";

	public final String type = "access";
	public String resourceId;
	public String deploymentType;

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
