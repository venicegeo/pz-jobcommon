package model.job.metadata;


import java.util.Map;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResourceMetadata {
	
	public String name;
	public String serviceID;
	public String description;
	public String url;
	public String networks;
	public String qos;
	public String availability;
	public String tags;
	public String classType;
	@JsonIgnore
	public DateTime termDate;
	public boolean clientCertRequired;
	public boolean credentialsRequired;
	public boolean preAuthRequired;
    public String contacts;
    public String method;
    public String mimeType;
    public String param;
    public Map <String, String> params;
    public String reason;
	

}
