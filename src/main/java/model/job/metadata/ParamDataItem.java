package model.job.metadata;

import java.util.List;

public class ParamDataItem {
	private String name;
	private Integer minOccurs;
	private Integer maxOccurs;
	private InputType inputType;
	private MetadataType metadata;
	private List<Format> formats;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinOccurs() {
		return minOccurs;
	}
	public void setMinOccurs(Integer minOccurs) {
		this.minOccurs = minOccurs;
	}
	public Integer getMaxOccurs() {
		return maxOccurs;
	}
	public void setMaxOccurs(Integer maxOccurs) {
		this.maxOccurs = maxOccurs;
	}
	public InputType getInputType() {
		return inputType;
	}
	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}
	public MetadataType getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataType metadata) {
		this.metadata = metadata;
	}
	public List<Format> getFormats() {
		return formats;
	}
	public void setFormats(List<Format> formats) {
		this.formats = formats;
	}

}
