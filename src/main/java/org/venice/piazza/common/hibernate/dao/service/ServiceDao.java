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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.ServiceEntity;

/**
 * Data Access Object for Service Entities
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public interface ServiceDao extends CrudRepository<ServiceEntity, Long>, ServiceDaoCustom {
	@Query(value = "select * from user_service where data ->> 'serviceId' = ?1 limit 1", nativeQuery = true)
	ServiceEntity getServiceById(String serviceId);

	/**
	 * Gets all services that are not OFFLINE
	 */
	@Query(value = "select * from user_service where data#>'{resourceMetadata,availability}' != '\"OFFLINE\"'", nativeQuery = true)
	Iterable<ServiceEntity> getAllAvailableServices();

	@Query(value = "select * from user_service where data ->> 'isTaskManaged' = 'true'", nativeQuery = true)
	Iterable<ServiceEntity> getAllTaskManagedServices();
}