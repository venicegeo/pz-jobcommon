package model.request;

//An AdminResponse represents the data returned from a call to a service's
///admin API.
public class AdminResponse {
	private String starttime;
	private AdminResponseUuidgen uuidgen;
	private AdminResponseLogger logger;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public AdminResponseUuidgen getUuidgen() {
		return uuidgen;
	}
	public void setUuidgen(AdminResponseUuidgen uuidgen) {
		this.uuidgen = uuidgen;
	}
	public AdminResponseLogger getLogger() {
		return logger;
	}
	public void setLogger(AdminResponseLogger logger) {
		this.logger = logger;
	}
	

}
