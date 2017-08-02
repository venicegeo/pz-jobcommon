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
package org.venice.piazza.common.hibernate.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.AsyncServiceInstanceEntity;

/**
 * Data Access Object for Asynchronous Service Instance Entities
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public interface AsyncServiceInstanceDao extends CrudRepository<AsyncServiceInstanceEntity, Long> {
	@Query(value = "select * from async_service_instance where data ->> 'jobId' = ?1 limit 1", nativeQuery = true)
	AsyncServiceInstanceEntity getInstanceByJobId(String jobId);

	/**
	 * Checks for Instances who have not been checked since the specified time
	 * 
	 * @param thresholdEpoch
	 *            The specified time (epoch) that all instances prior to are considered stale
	 * @return List of stale service instances
	 */
	@Query(value = "select * from async_service_instance where cast(data ->>'lastCheckedOn' as float) < ?1", nativeQuery = true)
	Iterable<AsyncServiceInstanceEntity> getStaleServiceInstances(long thresholdEpoch);

	@Transactional
	@Modifying
	@Query(value = "delete from async_service_instance where data ->> 'jobId' = ?1", nativeQuery = true)
	void deleteInstanceByJobId(String jobId);
}