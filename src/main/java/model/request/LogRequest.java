package model.request;

public class LogRequest {
	String service;
	String address;
	String time;
	String message;
	String severity;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	

}
