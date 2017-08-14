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
package model.job.result.type;

import io.swagger.annotations.ApiModelProperty;
import model.job.result.ResultType;

/**
 * A Job result for returning a DataResource item held by the internal Piazza
 * stores. This returns the Id of the Resource - not the resource information,
 * as this is weighty and is held elsewhere.
 * 
 * @author Patrick.Doody
 * 
 */
public class DataResult implements ResultType {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "The unique Id of the Data resource", required = true)
	public String dataId;

	public DataResult() {
		// Empty constructor required by Jackson
	}

	public DataResult(String dataId) {
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}