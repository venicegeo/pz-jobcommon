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
package model.job.result;

import model.data.deployment.Deployment;

/**
 * A Deployment Result. This is the Result of jobs that request GeoServer
 * deployments of resources held within Piazza core.
 * 
 * @author Patrick.Doody
 * 
 */
public class DeploymentResult implements ResultType {
	public final String type = "deployment";
	public Deployment deployment;

	public DeploymentResult() {

	}

	public DeploymentResult(Deployment deployment) {
		this.deployment = deployment;
	}

	public String getType() {
		return type;
	}

	public Deployment getDeployment() {
		return deployment;
	}

	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}

}
