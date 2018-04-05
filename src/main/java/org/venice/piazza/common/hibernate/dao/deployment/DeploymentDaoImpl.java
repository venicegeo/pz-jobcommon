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
package org.venice.piazza.common.hibernate.dao.deployment;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.DeploymentEntity;
import model.response.Pagination;

/**
 * Implementation for Custom Deployment Queries
 * 
 * @author Patrick.Doody & Sonny.Saniev
 *
 */
@Repository
public class DeploymentDaoImpl implements DeploymentDaoCustom {
	@PersistenceContext
	EntityManager entityManager;

	private static final String WILDCARD_STRING_QUERY = "%%%s%%";
	private static final String KEYWORD_DEPLOYMENT_ID_QUERY = "select * from deployment where data#>>'{deploymentId}' like ?1 order by data #>> regexp_split_to_array(?2, E'\\\\.') %s limit ?3 offset ?4";
	private static final String KEYWORD_DEPLOYMENT_ID_QUERY_COUNT = "select count(*) from deployment where data#>>'{deploymentId}' like ?1";
	private static final String KEYWORD_DATA_ID_QUERY = "select * from deployment where data#>>'{dataId}' like ?1 order by data #>> regexp_split_to_array(?2, E'\\\\.') %s limit ?3 offset ?4";
	private static final String KEYWORD_DATA_ID_QUERY_COUNT = "select count(*) from deployment where data#>>'{dataId}' like ?1";
	private static final String KEYWORD_CAPABILITIES_URL_QUERY = "select * from deployment where data#>>'{capabilitiesUrl}' like ?1 order by data #>> regexp_split_to_array(?2, E'\\\\.') %s limit ?3 offset ?4";
	private static final String KEYWORD_CAPABILITIES_URL_QUERY_COUNT = "select count(*) from deployment where data#>>'{capabilitiesUrl}' like ?1";
	private static final String DEPLOYMENT_QUERY = "select * from deployment order by data ->> ?1 %s limit ?2 offset ?3";
	private static final String DEPLOYMENT_QUERY_COUNT = "select count(*) from deployment";
	
	public Page<DeploymentEntity> getDeploymentListByDeploymentId(String keyword, Pagination pagination) {
		// Query
		String queryString = String.format(KEYWORD_DEPLOYMENT_ID_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DeploymentEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<DeploymentEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(KEYWORD_DEPLOYMENT_ID_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<>(results, null, count);
	}

	public Page<DeploymentEntity> getDeploymentListByDataId(String keyword, Pagination pagination) {
		// Query
		String queryString = String.format(KEYWORD_DATA_ID_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DeploymentEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<DeploymentEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(KEYWORD_DATA_ID_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<>(results, null, count);
	}

	public Page<DeploymentEntity> getDeploymentListByCapabilitiesUrl(String keyword, Pagination pagination) {
		// Query
		String queryString = String.format(KEYWORD_CAPABILITIES_URL_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DeploymentEntity.class);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		query.setParameter(2, pagination.getSortBy());
		query.setParameter(3, pagination.getPerPage());
		query.setParameter(4, pagination.getPage() * pagination.getPerPage());
		List<DeploymentEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(KEYWORD_CAPABILITIES_URL_QUERY_COUNT);
		query.setParameter(1, String.format(WILDCARD_STRING_QUERY, keyword));
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<>(results, null, count);
	}
	
	public Page<DeploymentEntity> getDeploymentList(Pagination pagination) {
		// Query
		String queryString = String.format(DEPLOYMENT_QUERY, Direction.fromString(pagination.getOrder()));
		Query query = entityManager.createNativeQuery(queryString, DeploymentEntity.class);
		query.setParameter(1, pagination.getSortBy());
		query.setParameter(2, pagination.getPerPage());
		query.setParameter(3, pagination.getPage() * pagination.getPerPage());
		List<DeploymentEntity> results = query.getResultList();
		// Count
		query = entityManager.createNativeQuery(DEPLOYMENT_QUERY_COUNT);
		long count = ((BigInteger) query.getSingleResult()).longValue();
		return new PageImpl<>(results, null, count);
	}
}
