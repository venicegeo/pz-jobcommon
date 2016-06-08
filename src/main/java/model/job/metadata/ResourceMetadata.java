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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import model.resource.NumericKeyValue;
import model.resource.TextKeyValue;
import model.security.SecurityClassification;

import org.joda.time.DateTime;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

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

	@ApiModelProperty(value = "Human-readable name of the object.")
	public String name;

	@ApiModelProperty(value = "Human-readable description of the object.")
	public String description;

	@ApiModelProperty(hidden = true)
	public String format;

	@ApiModelProperty(hidden = true)
	public String qos;

	@ApiModelProperty(hidden = true)
	public String availability;

	@ApiModelProperty(hidden = true)
	public String tags;

	@ApiModelProperty(hidden = true)
	public SecurityClassification classType;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public DateTime termDate;

	@ApiModelProperty(hidden = true)
	public Boolean clientCertRequired;

	@ApiModelProperty(hidden = true)
	public Boolean credentialsRequired;

	@ApiModelProperty(hidden = true)
	public Boolean preAuthRequired;

	@ApiModelProperty(hidden = true)
	public String networkAvailable;

	@ApiModelProperty(hidden = true)
	public String contacts;

	@ApiModelProperty(hidden = true)
	public String reason;

	@ApiModelProperty(hidden = true)
	public String version;

	@ApiModelProperty(hidden = true)
	public String createdBy;

	@ApiModelProperty(hidden = true)
	public String createdDate;

	@ApiModelProperty(hidden = true)
	public Map<String, String> metadata;

	/*
	 * Need the ability to accommodate arbitrary key/value pairs
	 */
	@ApiModelProperty(hidden = true)
	@Field(type = FieldType.Nested)
	private List<NumericKeyValue> numericKeyValueList;

	@ApiModelProperty(hidden = true)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public SecurityClassification getClassType() {
		return classType;
	}

	public void setClassType(SecurityClassification classType) {
		this.classType = classType;
	}

	public Boolean getClientCertRequired() {
		return clientCertRequired;
	}

	public void setClientCertRequired(Boolean clientCertRequired) {
		this.clientCertRequired = clientCertRequired;
	}

	public Boolean getCredentialsRequired() {
		return credentialsRequired;
	}

	public void setCredentialsRequired(Boolean credentialsRequired) {
		this.credentialsRequired = credentialsRequired;
	}

	public Boolean getPreAuthRequired() {
		return preAuthRequired;
	}

	public void setPreAuthRequired(Boolean preAuthRequired) {
		this.preAuthRequired = preAuthRequired;
	}

	public String getNetworkAvailable() {
		return networkAvailable;
	}

	public void setNetworkAvailable(String networkAvailable) {
		this.networkAvailable = networkAvailable;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * Merges the properties of another ResourceMetadata into this one.
	 * 
	 * <p>
	 * If the other ResourceMetadata specifies properties, then those properties
	 * take precedence. If the other ResourceMetadata contains a null value for
	 * a property that exists in this object, then this object's property is
	 * unchanged unless the overwriteNull flag is set to true.
	 * </p>
	 * 
	 * @param other
	 *            The ResourceMetadata properties to merge
	 * @param overwriteNull
	 *            True if null values in the other ResourceMetadata should
	 *            overwrite values in this object. False if not.
	 */
	public void merge(ResourceMetadata other, boolean overwriteNull) {
		Method[] methods = this.getClass().getMethods();

		for (Method fromMethod : methods) {
			if (fromMethod.getDeclaringClass().equals(this.getClass()) && fromMethod.getName().startsWith("get")) {

				String fromName = fromMethod.getName();
				String toName = fromName.replace("get", "set");

				try {
					Method toMethod = this.getClass().getMethod(toName, fromMethod.getReturnType());
					Object value = fromMethod.invoke(other, (Object[]) null);
					if ((value != null) || (overwriteNull == true)) {
						toMethod.invoke(this, value);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}