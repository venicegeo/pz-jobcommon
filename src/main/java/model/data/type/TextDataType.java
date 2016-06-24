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

	public static final String TYPE = "text";

	@ApiModelProperty(value = "The type of data.", required = true, allowableValues = "text")
	public final String type = "text";

	@ApiModelProperty(value = "")
	public String content;

	@ApiModelProperty(value = "The media type of the stored data")
	public String mimeType;

	public TextDataType() {

	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public String getMimeType() {
		return mimeType;
	}
}