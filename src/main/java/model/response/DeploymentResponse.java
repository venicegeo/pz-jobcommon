package model.response;

import model.data.deployment.Deployment;

/**
 * A Response containing information regarding a Piazza Deployment.
 * 
 * @author Patrick.Doody
 *
 */
public class DeploymentResponse extends PiazzaResponse {
	private String type = "deployment";
	public Deployment deployment;

	public DeploymentResponse() {
	}

	public DeploymentResponse(Deployment deployment) {
		this.deployment = deployment;
	}

	/**
	 * Gets the type of this response.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
}
