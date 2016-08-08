package model.workflow;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TriggerUpdate {

	@ApiModelProperty(value = "Field that will determine if the trigger fires", required = true)
	@NotNull
	public Boolean enabled;
}
