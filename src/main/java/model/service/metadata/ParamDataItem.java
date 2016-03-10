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
package model.service.metadata;

import java.util.List;

import model.data.DataType;
/**
 * Metadata specifying the properties of input to a service
 * @author bkrasner
 *
 */
public class ParamDataItem {
	private String name;
	private Integer minOccurs;
	private Integer maxOccurs;
	private DataType dataType;
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
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
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
