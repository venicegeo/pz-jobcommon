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

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.DataResourceEntity;

import model.response.Pagination;

/**
 * Implementation for Custom DataResource Queries
 * 
 * @author Patrick.Doody & Sonny.Saniev
 *
 */
@Repository
public class DataResourceDaoImpl implements DataResourceDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	private static final String WILDCARD_STRING_QUERY = "%%%s%%";
	private static final String USERNAME_AND_KEYWORD_DATARESOURCE_QUERY = "select * from data_resource where (data#>>'{metadata,name}' like ?1 or data#>>'{metadata,description}' like ?2) and data#>>'{metadata,createdBy}' like ?3 order by data ->> ?4 %s limit ?5 offset ?6";
	private static final String USERNAME_DATARESOURCE_QUERY = "select * from data_resource where data#>>'{metadata,createdBy}' like ?1 order by data ->> ?2 %s limit ?3 offset ?4";
	private static final String USERNAME_DATARESOURCE_QUERY_COUNT = "select count(*) from data_resource where data#>>'{metadata,createdBy}' like ?1";
	private static final String USERNAME_AND_KEYWORD_DATARESOURCE_QUERY_COUNT = "select count(*) from data_resource where (data#>>'{metadata,name}' like ?1 or data#>>'{metadata,description}' like ?2) and data#>>'{metadata,createdBy}' like ?3";
	private static final String KEYWORD_DATARESOURCE_QUERY = "select * from data_resource where data#>>'{metadata,name}' like ?1 or data#>>'{metadata,description}' like ?2 order by data ->> ?3 %s limit ?4 offset ?5";
	private static final String KEYWORD_DATARESOURCE_QUERY_COUNT = "select count(*) from data_resource where data#>>'{metadata,name}' like ?1 or data#>>'{metadata,description}' like ?2";
	private static final String CREATED_BY_JOB_ID_DATARESOURCE_QUERY = "select * from data_resource where data#>>'{metadata,createdByJobId}' like ?1 order by data ->> ?2 %s limit ?3 offset ?4";
	private static final String CREATED_BY_JOB_ID_DATARESOURCE_QUERY_COUNT = "select count(*) from data_resource where data#>>'{metadata,createdByJobId}' like ?1";
	private static final String DATARESOURCE_QUERY = "select * from data_resource order by data ->> ?1 %s limit ?2 offset ?3";
	private static final String DATARESOURCE_QUERY_COUNT = "select count(*) from data_resource";
	public Page<DataResourceEntity> getDataResourceForUserAndKeyword(String keyword, String userName, Pagination pagination) {
		// Query
		String queryString = String.format(USERNAME_AND_KEYWORD_DATARESOURCE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DataResourceEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(3, String.format(WILDCARD_STRING_QUERY, userName));
		query.setParameter(4, pagination.getSortBy());
		query.setParameter(5, pagination.getPerPage());
		query.setParameter(6, pagination.getPage() * pagination.getPerPage());
		List<DataResourceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(USERNAME_AND_KEYWORD_DATARESOURCE_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(3, String.format(WILDCARD_STRING_QUERY, userName));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<DataResourceEntity>(results, null, count);
	}
	
	public Page<DataResourceEntity> getDataResourceListByUser(String userName, Pagination pagination) {
		// Query
		String queryString = String.format(USERNAME_DATARESOURCE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DataResourceEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, userName));
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<DataResourceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(USERNAME_DATARESOURCE_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, userName));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<DataResourceEntity>(results, null, count);
	}
	
	public Page<DataResourceEntity> getDataResourceListByKeyword(String keyword, Pagination pagination) {
		// Query
		String queryString = String.format(KEYWORD_DATARESOURCE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DataResourceEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(3, pagination.getSortBy());
		query.setParameter(4, pagination.getPerPage());
		query.setParameter(5, pagination.getPage() * pagination.getPerPage());
		List<DataResourceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(KEYWORD_DATARESOURCE_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, String.format(WILDCARD_STRING_QUERY, keyword));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<DataResourceEntity>(results, null, count);
	}
	
	public Page<DataResourceEntity> getDataResourceListByCreatedJobId(String createdByJobId, Pagination pagination) {
		// Query
		String queryString = String.format(CREATED_BY_JOB_ID_DATARESOURCE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DataResourceEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, createdByJobId));
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<DataResourceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(CREATED_BY_JOB_ID_DATARESOURCE_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, createdByJobId));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<DataResourceEntity>(results, null, count);
	}
	
	public Page<DataResourceEntity> getDataResourceList(Pagination pagination) {
		// Query
		String queryString = String.format(DATARESOURCE_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DataResourceEntity.class);
		query.setParameter(1, pagination.getSortBy());
		query.setParameter(2, pagination.getPerPage());
		query.setParameter(3, pagination.getPage() * pagination.getPerPage());
		List<DataResourceEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(DATARESOURCE_QUERY_COUNT);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<DataResourceEntity>(results, null, count);
	}
}
