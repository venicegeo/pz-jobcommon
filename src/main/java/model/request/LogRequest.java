package model.request;

public class LogRequest {
	
	private String service;
	private String address;
	private String time;
	private String message;
	private String severity;
	
	public LogRequest() {
		
	}
	
	public LogRequest(String service, String address, String time, String message, String severity) {
		setService(service);
		setAddress(address);
		setTime(time);
		setMessage(message);
		setSeverity(severity);
	}
	
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
	
	public String toPrettyString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Service : " + getService() + "\n");
		sb.append("Address : " + getAddress() + "\n");
		sb.append("Time : " + getTime() + "\n");
		sb.append("Message : " + getMessage() + "\n");
		sb.append("Severity : " + getSeverity() + "\n");
		
		return sb.toString();		
	}
}