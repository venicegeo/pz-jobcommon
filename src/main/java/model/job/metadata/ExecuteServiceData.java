package model.job.metadata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecuteServiceData {
	@JsonProperty("dataInputs")
	public Map<String, String> dataInputs;	
	public String dataInput;
	public String resourceId;
	
	public Map<String, String> getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(Map<String, String> dataInputs) {
		this.dataInputs = dataInputs;
	}

	public String getDataInput() {
		return dataInput;
	}

	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}		
}
