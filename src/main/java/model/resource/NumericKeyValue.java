/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.resource;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class NumericKeyValue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "The pair key", required = true)
	private String key;

	@ApiModelProperty(value = "The value for the key", required = true)
	private Double value;

	public NumericKeyValue() {
		// Empty constructor required by Jackson
	}

	public NumericKeyValue(String key, Double value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}