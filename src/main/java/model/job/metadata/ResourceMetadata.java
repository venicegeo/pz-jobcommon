package model.job.metadata;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ResourceMetadata {

	public String name;

	@JsonProperty("_id")
	public String resourceId;
	public String description;
	public String url;
	public String filePath;
	public String format;
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
	public String requestMimeType;
	public String param;
	@JsonProperty("params")
	public List<String> params;
	public String responseMimeType;
	public String reason;

}
