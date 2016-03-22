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
package model.job.metadata;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import model.resource.NumericKeyValue;
import model.resource.TextKeyValue;
import model.security.SecurityClassification;

/**
 * Common Metadata fields used to describe Data or Services within the Piazza
 * system. This object should be generic enough to be used to attach common
 * metadata fields to any object stored in the Piazza system.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMetadata {

	public String name;

	public String id;
	public String description;
	public String url;
	public String format;
	public String qos;
	public String availability;
	public String tags;
	public SecurityClassification classType;
	@JsonIgnore
	public DateTime termDate;
	public Boolean clientCertRequired;
	public Boolean credentialsRequired;
	public Boolean preAuthRequired;
	public String networkAvailable;
	public String contacts;
	public String method;
	public String reason;
	public String version;
	
	/*
	 * Need the ability to accommodate arbitrary key/value pairs
	 */
	@Field(type = FieldType.Nested)
	private List<NumericKeyValue> numericKeyValueList;
	@Field(type = FieldType.Nested)
	private List<TextKeyValue> textKeyValueList;

	public List<NumericKeyValue> getNumericKeyValueList() {
		return numericKeyValueList;
	}
	public void setNumericKeyValueList(List<NumericKeyValue> numericKeyValueList) {
		this.numericKeyValueList = numericKeyValueList;
	}
	public List<TextKeyValue> getTextKeyValueList() {
		return textKeyValueList;
	}
	public void setTextKeyValueList(List<TextKeyValue> textKeyValueList) {
		this.textKeyValueList = textKeyValueList;
	}
}
