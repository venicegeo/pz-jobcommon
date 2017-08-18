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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.ServiceJobEntity;

/**
 * Data Access Object for Service Job Entities
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public interface ServiceJobDao extends CrudRepository<ServiceJobEntity, Long> {
	/**
	 * Gets the next job in the Service Queue of the service with the specified ID
	 * 
	 * @param serviceId
	 *            The ID of the Service Queue
	 * @return The next job to be processed
	 */
	@Query(value = "select * from service_job where data->> 'serviceId' = ?1 and data-> 'startedOn' = 'null' order by data ->> 'queuedOn' asc limit 1", nativeQuery = true)
	ServiceJobEntity getNextJobInServiceQueue(String serviceId);

	/**
	 * Gets all Service Jobs that have exceeded the timeout
	 * 
	 * @param serviceId
	 *            The ID of the service for Service Jobs queue
	 * @param timeoutThreshold
	 *            The threshold, in epoch ms, that any jobs older than are considered timed out
	 * @return The list of timed out Service Jobs for the specified service
	 */
	@Query(value = "select * from service_job where data->> 'serviceId' = ?1 and data-> 'startedOn' != 'null'  and cast(data->> 'startedOn' as float) < ?2 order by data ->> 'queuedOn'", nativeQuery = true)
	Iterable<ServiceJobEntity> getTimedOutServiceJobs(String serviceId, long timeoutThreshold);

	@Query(value = "select * from service_job where data->> 'serviceId' = ?1 and data->> 'jobId' = ?2 limit 1", nativeQuery = true)
	ServiceJobEntity getServiceJobByServiceAndJobId(String serviceId, String jobId);

	@Query(value = "select * from service_job where data->> 'serviceId' = ?1", nativeQuery = true)
	Iterable<ServiceJobEntity> deleteAllJobsByServiceId(String serviceId);

	@Query(value = "select count(*) from service_job where data->> 'serviceId' = ?1", nativeQuery = true)
	long getServiceJobCountForService(String serviceId);
}