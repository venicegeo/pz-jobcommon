package model.job.metadata;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common Metadata fields used to describe Data or Services within the Piazza
 * system. This object should be generic enough to be used to attach common
 * metadata fields to any object stored in the Piazza system.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMetadata {

	public String name;

	public String id;
	public String description;
	public String url;
	public String format;
	public String networks;
	public String qos;
	public String availability;
	public String tags;
	public String classType;
	@JsonIgnore
	public DateTime termDate;
	public Boolean clientCertRequired;
	public Boolean credentialsRequired;
	public Boolean preAuthRequired;
	public String contacts;
	public String method;
	public String requestMimeType;
	public String param;
	@JsonProperty("params")
	public List<String> params;
	public String responseMimeType;
	public String reason;

}
