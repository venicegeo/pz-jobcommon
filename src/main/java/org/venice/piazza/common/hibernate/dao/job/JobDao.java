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
package org.venice.piazza.common.hibernate.dao.job;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.JobEntity;

/**
 * Data Access Object for Job Entities
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public interface JobDao extends CrudRepository<JobEntity, Long>, JobDaoCustom {
	@Query(value = "select count(*) from job where data ->> 'status' = ?1", nativeQuery = true)
	Long countJobByStatus(String status);

	@Query(value = "select * from job where data ->> 'jobId' = ?1 limit 1", nativeQuery = true)
	JobEntity getJobByJobId(String jobId);
}