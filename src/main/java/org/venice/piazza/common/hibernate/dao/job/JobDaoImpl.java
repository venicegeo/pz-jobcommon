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
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.JobEntity;

import model.response.Pagination;

/**
 * Implementation for Custom Job Queries
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public class JobDaoImpl implements JobDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	public Page<JobEntity> retrievePageableJobList(Pagination pagination) {
		String queryString = "select * from job order by data ->> '?' ? limit ? offset ?";
		Query query = entityManager.createNativeQuery(queryString, JobEntity.class);
		query.setParameter(1, pagination.getSortBy());
		query.setParameter(2, pagination.getOrder());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, null, results.size());
	}

}
