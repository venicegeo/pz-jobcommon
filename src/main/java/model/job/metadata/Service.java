package model.job.metadata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {
	private String name;
	private ResourceMetadata resourceMetadata;
	private List<ParamDataItem> inputs;
	private List<ParamDataItem> outputs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResourceMetadata getResourceMetadata() {
		return resourceMetadata;
	}
	public void setResourceMetadata(ResourceMetadata resourceMetadata) {
		this.resourceMetadata = resourceMetadata;
	}
	public List<ParamDataItem> getInputs() {
		return inputs;
	}
	public void setInputs(List<ParamDataItem> inputs) {
		this.inputs = inputs;
	}
	public List<ParamDataItem> getOutputs() {
		return outputs;
	}
	public void setOutputs(List<ParamDataItem> outputs) {
		this.outputs = outputs;
	}

}
