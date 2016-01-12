package model.job.type;

import model.job.PiazzaJobType;

public class ListServicesJob implements PiazzaJobType {
	public final String type = "list-service";
	public boolean detailed = false;
	public String getType() {
		
		return type;
	}
	

}
