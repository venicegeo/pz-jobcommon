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
package model.data;

import model.job.metadata.ResourceMetadata;
import model.job.metadata.SpatialMetadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represents a piece of data referened by the Piazza system, internally or
 * externally.
 * 
 * The format and type of the data is described by the DataType object, and has
 * interfaces for describing various formats and types.
 * 
 * Also contains metadata containers for describing the metadata of the data
 * object.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResource {

	@ApiModelProperty(hidden = true)
	public String dataId;

	@ApiModelProperty(value = "Polymorphically defines the information specific to this Data Resource, based on its format.", required = true)
	public DataType dataType;

	@ApiModelProperty(value = "")
	public SpatialMetadata spatialMetadata;

	@ApiModelProperty(value = "")
	public ResourceMetadata metadata;

	public DataResource() {

	}

	public ResourceMetadata getMetadata() {
		return metadata;
	}

	public DataType getDataType() {
		return dataType;
	}

	public SpatialMetadata getSpatialMetadata() {
		return spatialMetadata;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}
}