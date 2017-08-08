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
package org.venice.piazza.common.hibernate.dao.dataresource;

import org.springframework.data.domain.Page;
import org.venice.piazza.common.hibernate.entity.DataResourceEntity;

import model.response.Pagination;

/**
 * Interface for custom DataResource Queries
 * 
 * @author Patrick.Doody & Sonny.Saniev
 *
 */
public interface DataResourceDaoCustom {
	/**
	 * Retrieves DataResource list for a particular user
	 * 
	 * @param userName
	 *            Username of the Job creator.
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	public Page<DataResourceEntity> getDataResourceForUserAndKeyword(String keyword, String userName, Pagination pagination);

	/**
	 * Gets the list of DataResources by a single User
	 * 
	 * @param userName
	 *            The user
	 * @param pagination
	 *            Pagination information
	 * @return List of DataResources
	 */
	Page<DataResourceEntity> getDataResourceListByUser(String userName, Pagination pagination);
	
	/**
	 * Gets the list of DataResources by a keyword filter
	 * 
	 * @param keyword
	 *            The keyword
	 * @param pagination
	 *            Pagination information
	 * @return List of DataResources
	 */
	Page<DataResourceEntity> getDataResourceListByKeyword(String keyword, Pagination pagination);
	
	/**
	 * Gets the list of DataResources by matching createdByJobId
	 * 
	 * @param createdByJobId
	 *            The job id
	 * @param pagination
	 *            Pagination information
	 * @return List of DataResources
	 */
	Page<DataResourceEntity> getDataResourceListByCreatedJobId(String createdByJobId, Pagination pagination);

	/**
	 * Gets paginated list of all DataResource
	 * 
	 * @param pagination
	 *            Pagination information
	 * @return List of DataResources
	 */
	Page<DataResourceEntity> getDataResourceList(Pagination pagination);
}
