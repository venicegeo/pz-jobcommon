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
package org.venice.piazza.common.hibernate.dao.deployment;

import org.springframework.data.domain.Page;
import org.venice.piazza.common.hibernate.entity.DeploymentEntity;

import model.response.Pagination;

/**
 * Interface for custom Deployment Queries
 * 
 * @author Patrick.Doody & Sonny.Saniev
 *
 */
public interface DeploymentDaoCustom {
	/**
	 * Retrieves Deployment list by deploymentid matching keyword
	 * 
	 * @param keyword
	 *            keyword to match by
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	public Page<DeploymentEntity> getDeploymentListByDeploymentId(String keyword, Pagination pagination);

	/**
	 * Retrieves Deployment list by dataId matching keyword
	 * 
	 * @param keyword
	 *            keyword to match by
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	public Page<DeploymentEntity> getDeploymentListByDataId(String keyword, Pagination pagination);
	
	/**
	 * Retrieves Deployment list by capabilitiesUrl matching keyword
	 * 
	 * @param keyword
	 *            keyword to match by
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	public Page<DeploymentEntity> getDeploymentListByCapabilitiesUrl(String keyword, Pagination pagination);
	
	/**
	 * Gets the list of Deployments
	 * 
	 * @param pagination
	 *            Pagination information
	 * @return List of Deployments
	 */
	Page<DeploymentEntity> getDeploymentList(Pagination pagination);
}
