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
package model.data.deployment.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;

import model.data.deployment.Deployment;
import model.data.deployment.Lease;

import model.response.Pagination;
import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.reflections.ReflectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.test.util.ReflectionTestUtils;
import org.venice.piazza.common.hibernate.dao.dataresource.DataResourceDaoImpl;
import org.venice.piazza.common.hibernate.dao.deployment.DeploymentDao;
import org.venice.piazza.common.hibernate.dao.deployment.DeploymentDaoImpl;
import org.venice.piazza.common.hibernate.entity.DeploymentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Tests deployment and lease serializations
 * 
 * @author Patrick.Doody
 *
 */
public class DeploymentTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests deployment model
	 */
	@Test
	public void testDeployment() throws IOException {
		// Create Deployment
		Deployment mockDeployment = new Deployment("123456", "654321", "localhost", "8080", "Test",
				"localhost:8080/test");
		// Serialize, deserialize
		String serialized = mapper.writeValueAsString(mockDeployment);
		Deployment deployment = mapper.readValue(serialized, Deployment.class);
		// Verify
		assertTrue(deployment.getDeploymentId().equals(mockDeployment.getDeploymentId()));
		assertTrue(deployment.getDataId().equals(mockDeployment.getDataId()));
		assertTrue(deployment.getPort().equals(mockDeployment.getPort()));
		assertTrue(deployment.getHost().equals(mockDeployment.getHost()));
		assertTrue(deployment.getCapabilitiesUrl().equals(mockDeployment.getCapabilitiesUrl()));
		assertTrue(deployment.getLayer().equals(mockDeployment.getLayer()));
	}

	/**
	 * Lease model test
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLease() throws IOException {
		// Mock
		Lease mockLease = new Lease("123456", "654321", new DateTime());
		// Serialize
		String serialized = mapper.writeValueAsString(mockLease);
		Lease lease = mapper.readValue(serialized, Lease.class);
		// Verify
		assertTrue(lease.getDeploymentId().equals(mockLease.getDeploymentId()));
		assertTrue(lease.getExpiresOn().equals(mockLease.getExpiresOn()));
		assertTrue(lease.getLeaseId().equals(mockLease.getLeaseId()));
	}

	@Test
	public void testDao() {
		EntityManager entityManager = Mockito.mock(EntityManager.class);
		Query query = Mockito.mock(Query.class);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(query);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(BigInteger.ZERO);

		DeploymentDaoImpl dao = new DeploymentDaoImpl();
		ReflectionTestUtils.setField(dao, "entityManager", entityManager);
		Pagination pagination = new Pagination(100L, 2, 10, "id", "asc");

		Page<DeploymentEntity> page1 = dao.getDeploymentListByDeploymentId("my_keyword", pagination);
		Page<DeploymentEntity> page2 = dao.getDeploymentListByDataId("my_keyword", pagination);
		Page<DeploymentEntity> page3 = dao.getDeploymentListByCapabilitiesUrl("my_keyword", pagination);
		Page<DeploymentEntity> page4 = dao.getDeploymentList(pagination);
	}
}
