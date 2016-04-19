package model.response;

import model.service.metadata.Service;

/**
 * A Response containing information regarding a Piazza Service.
 * 
 * @author Patrick.Doody
 *
 */
public class ServiceResponse extends PiazzaResponse {
	private String type = "service";
	public Service service;
	public String serviceId;

	public ServiceResponse() {
	}

	public ServiceResponse(Service service) {
		this.service = service;
	}

	public ServiceResponse(String serviceId) {
		this.serviceId = serviceId;
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
