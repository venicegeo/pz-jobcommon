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
package model.job.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import model.job.PiazzaJobType;

/**
 * Describes a user Request for Accessing data out of the Piazza core system.
 * This could potentially be for data that is held within the Piazza holdings,
 * or data that is external to Piazza core which has had its Metadata previously
 * loaded.
 * 
 * The two current deployment types are 1) Hosted on a Piazza GeoServer
 * instance, which can allow for Web Coverage Service or Web Feature Service,
 * depending on the type of data to be accessed. 2) Raw file download. This will
 * place the file in a accessible location that the user can access, such as an
 * FTP or an S3 location.
 * 
 * Currently, a single resource ID is provided to denote the Resource in the
 * Piazza holdings to access.
 * 
 * @author Patrick.Doody
 * 
 */
public class AccessJob implements PiazzaJobType {

	@ApiModelProperty(required = true, value = "The type of job.", allowableValues = "access")
	public String type;

	@JsonIgnore
	public static final String ACCESS_TYPE_GEOSERVER = "geoserver";
	@JsonIgnore
	public static final String ACCESS_TYPE_FILE = "file";

	@ApiModelProperty(value = "The ID of the subject data.")
	public String dataId;

	@ApiModelProperty(value = "The type of deployment, either hosted on a Piazza GeoServer instance, or raw file download.")
	public String deploymentType;

	public AccessJob() {
	}

	public AccessJob(String dataId) {
		this.dataId = dataId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * 
	 * @param deploymentType
	 *            Valid ACCESS_TYPE_ static string defined in this Job
	 */
	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

	public String getDeploymentType() {
		return deploymentType;
	}
}