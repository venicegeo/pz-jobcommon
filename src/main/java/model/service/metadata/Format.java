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
/**
 * Actual specification for how an input data item shall be presented to a Service
 * example - For feature, what version of gml should be used
 * @author bkrasner
 *
 */
public class Format {
	String mimeType;
	String encoding;
	String schema;
	Integer maximumMegabytes;
	// Represents the type returned from getType in model.data.type
	String dataType;
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public Integer getMaximumMegabytes() {
		return maximumMegabytes;
	}
	public void setMaximumMegabytes(Integer maximumMegabytes) {
		this.maximumMegabytes = maximumMegabytes;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
