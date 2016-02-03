package model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoreResource {
	
	private String type;
	private String address;
	private int host;
	
	@JsonProperty("db-uri")
	private String dburi;
	private String port;
	
	
	public int getHost() {
		return host;
	}
	public void setHost(int host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String host) {
		this.address = host;
	}
	public String getDburi() {
		return dburi;
	}
	public void setDburi(String dburi) {
		this.dburi = dburi;
	}

}