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
package model.data.type;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;

/**
 * Represents simple text that can be stored directly within MongoDB's Resource
 * collection.
 * 
 * @author Patrick.Doody
 * 
 */
public class TextDataType implements DataType {

	@ApiModelProperty(required = true, value = "The type of data", allowableValues = "text")
	@NotNull
	public String type;

	@ApiModelProperty(value = "The text content of the data", required = true)
	@NotNull
	public String content;

	@NotNull
	@ApiModelProperty(value = "The media type of the stored data.  Refer to http://www.iana.org for standard values", required = true)
	public String mimeType;

	public TextDataType() {

	}

	public String getContent() {
		return content;
	}

	public String getMimeType() {
		return mimeType;
	}
}