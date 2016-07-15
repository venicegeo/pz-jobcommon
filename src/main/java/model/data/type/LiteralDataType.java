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
/**
 * Specifies a literal value to be included in a POST body
 */
package model.data.type;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import model.data.DataType;

public class LiteralDataType implements DataType {

	public enum LITERAL {
		DOUBLE, FLOAT, SHORT, LONG, BYTE, CHAR, BOOLEAN, STRING
	};

	@ApiModelProperty(required = true, value = "The type of data", allowableValues = "literal")
	@NotNull
	public String type;

	@ApiModelProperty(value = "")
	@NotNull
	private LITERAL literalType = LITERAL.STRING;

	@ApiModelProperty(required = true, value = "The content of the data")
	@NotNull
	private String value = "";

	@ApiModelProperty(required = true, value = "The media type of the stored data")
	@NotNull
	public String mimeType;

	public LITERAL getLiteralType() {
		return literalType;
	}

	public void setLiteralType(LITERAL literalType) {
		this.literalType = literalType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMimeType() {
		return null;
	}
}