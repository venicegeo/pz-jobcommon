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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.venice.piazza.common.hibernate.entity.JobEntity;

/**
 * Implementation for Custom Job Queries
 * 
 * @author Patrick.Doody
 *
 */

@Repository
@Transactional
public class JobDaoCustomImpl implements JobDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	public Page<JobEntity> getJobList(Pageable pageable) {
		Query query = entityManager.createNativeQuery("select * from job order by data ->> 'jobId' desc limit 10", JobEntity.class);
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, pageable, results.size());
	}

}
