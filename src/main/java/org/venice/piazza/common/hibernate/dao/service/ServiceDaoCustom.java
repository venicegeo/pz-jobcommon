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
package org.venice.piazza.common.hibernate.dao.service;

import org.springframework.data.domain.Page;
import org.venice.piazza.common.hibernate.entity.ServiceEntity;

import model.response.Pagination;

/**
 * Interface for Service queries
 * 
 * @author Patrick.Doody
 *
 */
public interface ServiceDaoCustom {

	/**
	 * Gets the Services based on the user and keyword
	 * 
	 * @param keyword
	 *            The keyword
	 * @param userName
	 *            The user
	 * @param pagination
	 *            Pagination information
	 * @return List of Services
	 */
	Page<ServiceEntity> getServiceListForUserAndKeyword(String keyword, String userName, Pagination pagination);

	/**
	 * Gets paginated list of all Services
	 * 
	 * @param pagination
	 *            Pagination information
	 * @return List of Services
	 */
	Page<ServiceEntity> getServiceList(Pagination pagination);

	/**
	 * Gets the list of Services by a single User
	 * 
	 * @param userName
	 *            The user
	 * @param pagination
	 *            Pagination information
	 * @return List of services
	 */
	Page<ServiceEntity> getServiceListByUser(String userName, Pagination pagination);

	/**
	 * Gets the list of Services by a keyword filter
	 * 
	 * @param keyword
	 *            The keyword
	 * @param pagination
	 *            Pagination information
	 * @return List of Services
	 */
	Page<ServiceEntity> getServiceListByKeyword(String keyword, Pagination pagination);
}
