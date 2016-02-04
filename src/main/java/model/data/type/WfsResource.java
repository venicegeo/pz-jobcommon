package model.data.type;

import java.util.List;

import model.data.ResourceType;

/**
 * OGC Web Feature Service (WFS) Resource.
 * 
 * @author Patrick.Doody
 * 
 */
public class WfsResource implements ResourceType {
	private String type = "wfs";
	public String url;

	public String version;
	public List<String> featureTypes;

	public WfsResource() {

	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getVersion() {
		return version;
	}

	public List<String> getFeatureTypes() {
		return featureTypes;
	}
}
