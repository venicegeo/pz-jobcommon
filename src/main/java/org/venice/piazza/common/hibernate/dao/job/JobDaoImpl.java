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
import org.springframework.data.domain.Sort.Direction;
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

	private static final String JOB_QUERY = "select * from job order by data ->> ?1 %s limit ?2 offset ?3";
	private static final String STATUS_JOB_QUERY = "select * from job where data ->> 'status' = ?1 order by data ->> ?2 %s limit ?3 offset ?4";
	private static final String USERNAME_JOB_QUERY = "select * from job where data ->> 'createdBy' = ?1 order by data ->> ?2 %s limit ?3 offset ?4";
	private static final String USERNAME_AND_STATUS_JOB_QUERY = "select * from job where data ->> 'createdBy' = ?1 and data ->> 'status' = ?2 order by data ->> ?3 %s limit ?4 offset ?5";

	public Page<JobEntity> getJobList(Pagination pagination) {
		String queryString = String.format(JOB_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, JobEntity.class);
		query.setParameter(1, pagination.getSortBy());
		query.setParameter(2, pagination.getPerPage());
		query.setParameter(3, pagination.getPage() * pagination.getPerPage());
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, null, results.size());
	}

	public Page<JobEntity> getJobListForUserAndStatus(String status, String userName, Pagination pagination) {
		String queryString = String.format(USERNAME_AND_STATUS_JOB_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, JobEntity.class);
		query.setParameter(1, userName);
		query.setParameter(2, status);
		query.setParameter(3, pagination.getSortBy());
		query.setParameter(4, pagination.getPerPage());
		query.setParameter(5, pagination.getPage() * pagination.getPerPage());
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, null, results.size());
	}

	public Page<JobEntity> getJobListByUser(String userName, Pagination pagination) {
		String queryString = String.format(USERNAME_JOB_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, JobEntity.class);
		query.setParameter(1, userName);
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, null, results.size());
	}

	public Page<JobEntity> getJobListByStatus(String status, Pagination pagination) {
		String queryString = String.format(STATUS_JOB_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, JobEntity.class);
		query.setParameter(1, status);
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<JobEntity> results = query.getResultList();
		return new PageImpl<JobEntity>(results, null, results.size());
	}

}
