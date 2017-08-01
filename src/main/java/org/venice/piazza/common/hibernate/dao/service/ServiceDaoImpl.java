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

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.ServiceEntity;

import model.response.Pagination;

/**
 * Implementation for the custom Service Interface
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public class ServiceDaoImpl implements ServiceDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	private static final String SERVICE_QUERY = "select * from service order by data ->> ?1 %s limit ?2 offset ?3";
	private static final String SERVICE_QUERY_COUNT = "select count(*) from service";
	private static final String KEYWORD_SERVICE_QUERY = "select * from service where data->'resourceMetadata'->'name' like ?1 or data->'resourceMetadata'->'description' like ?2 order by data ->> ?3 %s limit ?4 offset ?5";
	private static final String KEYWORD_SERVICE_QUERY_COUNT = "select count(*) from service where data->'resourceMetadata'->'name' like ?1 or data->'resourceMetadata'->'description' like ?2";
	private static final String USERNAME_SERVICE_QUERY = "select * from service where data->'resourceMetadata'->'createdBy' = ?1 order by data ->> ?2 %s limit ?3 offset ?4";
	private static final String USERNAME_SERVICE_QUERY_COUNT = "select count(*) from service where data->'resourceMetadata'->'createdBy' = ?1";
	private static final String USERNAME_AND_KEYWORD_SERVICE_QUERY = "select * from service where data->'resourceMetadata'->'name' like ?1 or data->'resourceMetadata'->'description' like ?2 and where data->'resourceMetadata'->'createdBy' = ?3 order by data ->> ?4 %s limit ?5 offset ?6";
	private static final String USERNAME_AND_KEYWORD_SERVICE_QUERY_COUNT = "select count(*) from service where data->'resourceMetadata'->'name' like ?1 or data->'resourceMetadata'->'description' like ?2 and where data->'resourceMetadata'->'createdBy' = ?3";

	public Page<ServiceEntity> getServiceListForUserAndKeyword(String keyword, String userName, Pagination pagination) {
		// Query
		String queryString = String.format(USERNAME_AND_KEYWORD_SERVICE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, ServiceEntity.class);
		query.setParameter(1, keyword);
		query.setParameter(2, keyword);
		query.setParameter(3, userName);
		query.setParameter(4, pagination.getSortBy());
		query.setParameter(5, pagination.getPerPage());
		query.setParameter(6, pagination.getPage() * pagination.getPerPage());
		List<ServiceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(USERNAME_AND_KEYWORD_SERVICE_QUERY_COUNT);
		query.setParameter(1, keyword);
		query.setParameter(2, keyword);
		query.setParameter(3, userName);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<ServiceEntity>(results, null, count);
	}

	public Page<ServiceEntity> getServiceList(Pagination pagination) {
		// Query
		String queryString = String.format(SERVICE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, ServiceEntity.class);
		query.setParameter(1, pagination.getSortBy());
		query.setParameter(2, pagination.getPerPage());
		query.setParameter(3, pagination.getPage() * pagination.getPerPage());
		List<ServiceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(SERVICE_QUERY_COUNT);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<ServiceEntity>(results, null, count);
	}

	public Page<ServiceEntity> getServiceListByUser(String userName, Pagination pagination) {
		// Query
		String queryString = String.format(USERNAME_SERVICE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, ServiceEntity.class);
		query.setParameter(1, userName);
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<ServiceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(USERNAME_SERVICE_QUERY_COUNT);
		query.setParameter(1, userName);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<ServiceEntity>(results, null, count);
	}

	public Page<ServiceEntity> getServiceListByKeyword(String keyword, Pagination pagination) {
		// Query
		String queryString = String.format(KEYWORD_SERVICE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, ServiceEntity.class);
		query.setParameter(1, keyword);
		query.setParameter(2, keyword);
		query.setParameter(3, pagination.getSortBy());
		query.setParameter(4, pagination.getPerPage());
		query.setParameter(5, pagination.getPage() * pagination.getPerPage());
		List<ServiceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(KEYWORD_SERVICE_QUERY_COUNT);
		query.setParameter(1, keyword);
		query.setParameter(2, keyword);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<ServiceEntity>(results, null, count);
	}

}
