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
package model.response.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.data.DataResource;
import model.data.deployment.Deployment;
import model.data.type.TextDataType;
import model.job.Job;
import model.job.metadata.ResourceMetadata;
import model.job.type.AbortJob;
import model.response.AlertListResponse;
import model.response.DataResourceListResponse;
import model.response.DataResourceResponse;
import model.response.DeploymentListResponse;
import model.response.DeploymentResponse;
import model.response.ErrorResponse;
import model.response.JobStatusResponse;
import model.response.Pagination;
import model.response.PiazzaResponse;
import model.response.ServiceListResponse;
import model.response.ServiceResponse;
import model.response.SuccessResponse;
import model.service.metadata.Service;
import model.status.StatusUpdate;
import model.workflow.Alert;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests Response Model serialization
 * 
 * @author Patrick.Doody
 *
 */
public class ResponseTest {
	private ObjectMapper mapper = new ObjectMapper();
	private Pagination pagination = new Pagination(1, 0, 10);

	/**
	 * Test Alert List
	 */
	@Test
	public void testAlert() throws IOException {
		List<Alert> alerts = new ArrayList<Alert>();
		Alert alert = new Alert();
		alert.eventId = "123456";
		alert.id = "654321";
		alert.triggerId = "123456T";
		alerts.add(alert);
		AlertListResponse input = new AlertListResponse(alerts, pagination);

		String serialized = mapper.writeValueAsString(input);
		AlertListResponse output = mapper.readValue(serialized, AlertListResponse.class);

		assertTrue(output.getAlerts().size() == 1);
		assertTrue(output.getPagination().getCount().equals(1));
		assertTrue(output.getAlerts().get(0).eventId.equals("123456"));
		assertTrue(output.getAlerts().get(0).id.equals("654321"));
		assertTrue(output.getAlerts().get(0).triggerId.equals("123456T"));
	}

	/**
	 * Test Data Responses
	 */
	@Test
	public void testData() throws IOException {
		DataResource data = new DataResource();
		data.setDataId("123456");
		data.dataType = new TextDataType();
		((TextDataType) data.dataType).content = "Testing";
		DataResourceResponse dataInput = new DataResourceResponse(data);
		String serialized = mapper.writeValueAsString(dataInput);
		DataResourceResponse dataOutput = mapper.readValue(serialized, DataResourceResponse.class);
		// Test Data Resource Response
		assertTrue(dataOutput.getType().equals(dataInput.getType()));
		assertTrue(dataOutput.data.getDataId().equals(dataInput.data.getDataId()));

		// Test List response
		List<DataResource> list = new ArrayList<DataResource>();
		list.add(data);
		DataResourceListResponse listInput = new DataResourceListResponse(list, pagination);
		serialized = mapper.writeValueAsString(listInput);
		DataResourceListResponse listOutput = mapper.readValue(serialized, DataResourceListResponse.class);
		assertTrue(listOutput.getPagination().getCount().equals(1));
		assertTrue(listOutput.getData().get(0).getDataId().equals(data.getDataId()));
	}

	/**
	 * Test Deployment responses
	 */
	@Test
	public void testDeployment() throws IOException {
		Deployment deployment = new Deployment();
		deployment.setCapabilitiesUrl("test/wfs");
		deployment.setDataId("123456");
		deployment.setHost("localhost");
		deployment.setPort("8080");
		deployment.setId("123456");
		deployment.setLayer("Test");
		DeploymentResponse deploymentInput = new DeploymentResponse(deployment);

		// Test Deployment Response
		String serialized = mapper.writeValueAsString(deploymentInput);
		DeploymentResponse deploymentOutput = mapper.readValue(serialized, DeploymentResponse.class);
		assertTrue(deploymentOutput.getType().equals(deploymentInput.getType()));
		assertTrue(deploymentOutput.deployment.getCapabilitiesUrl().equals(deployment.getCapabilitiesUrl()));
		assertTrue(deploymentOutput.deployment.getHost().equals(deployment.getHost()));
		assertTrue(deploymentOutput.deployment.getId().equals(deployment.getId()));

		// Test List Response
		List<Deployment> list = new ArrayList<Deployment>();
		list.add(deployment);
		DeploymentListResponse listInput = new DeploymentListResponse(list, pagination);
		serialized = mapper.writeValueAsString(listInput);
		DeploymentListResponse listOutput = mapper.readValue(serialized, DeploymentListResponse.class);
		assertTrue(listOutput.getPagination().getCount().equals(1));
		assertTrue(listOutput.getData().get(0).getLayer().equals(deployment.getLayer()));
	}

	/**
	 * Test error response
	 */
	@Test
	public void testError() throws IOException {
		ErrorResponse input = new ErrorResponse("123456", "Bug", "Test");
		String serialized = mapper.writeValueAsString(input);
		ErrorResponse output = mapper.readValue(serialized, ErrorResponse.class);
		assertTrue(output.message.equals("Bug"));
		assertTrue(output.jobId.equals("123456"));
		assertTrue(output.origin.equals("Test"));
	}

	/**
	 * Test Job Status response
	 */
	@Test
	public void testStatus() throws IOException {
		Job job = new Job();
		job.setJobId("123456");
		job.setSubmittedString(new DateTime().toString());
		job.jobType = new AbortJob("123456", "Test");
		job.submitterUserName = "Tester";
		job.status = StatusUpdate.STATUS_SUCCESS;
		JobStatusResponse input = new JobStatusResponse(job);

		String serialized = mapper.writeValueAsString(input);
		JobStatusResponse output = mapper.readValue(serialized, JobStatusResponse.class);

		assertTrue(output.result == null);
		assertTrue(output.status.equals(StatusUpdate.STATUS_SUCCESS));
	}

	/**
	 * Test Piazza Response
	 */
	@Test
	public void testBaseResponse() throws IOException {
		PiazzaResponse input = new PiazzaResponse("123456");
		String serialized = mapper.writeValueAsString(input);
		PiazzaResponse output = mapper.readValue(serialized, PiazzaResponse.class);
		assertTrue(output.getType().equals(input.getType()));
		assertTrue(output.jobId.equals("123456"));
	}

	/**
	 * Test service responses
	 */
	@Test
	public void testServices() throws IOException {
		Service service = new Service();
		service.setContractUrl("localhost:8080");
		service.setMethod("GET");
		service.setResourceMetadata(new ResourceMetadata());
		service.getResourceMetadata().setName("Test Service");
		service.setServiceId("123456");
		service.setUrl("localhost:8080");
		ServiceResponse serviceInput = new ServiceResponse(service);

		// Test Single service response
		String serialized = mapper.writeValueAsString(serviceInput);
		ServiceResponse serviceOutput = mapper.readValue(serialized, ServiceResponse.class);
		assertTrue(serviceOutput.getType().equals(serviceInput.getType()));
		assertTrue(serviceOutput.service.getServiceId().equals("123456"));

		// Test service list response
		List<Service> list = new ArrayList<Service>();
		list.add(service);
		ServiceListResponse listInput = new ServiceListResponse(list, pagination);
		serialized = mapper.writeValueAsString(listInput);
		ServiceListResponse listOutput = mapper.readValue(serialized, ServiceListResponse.class);
		assertTrue(listOutput.getPagination().getCount().equals(1));
		assertTrue(listOutput.getData().get(0).getServiceId().equals("123456"));
	}

	/**
	 * Tests Success response
	 */
	@Test
	public void testSuccess() throws IOException {
		SuccessResponse input = new SuccessResponse("123456", "Success", "Testing");
		String serialized = mapper.writeValueAsString(input);
		SuccessResponse output = mapper.readValue(serialized, SuccessResponse.class);
		assertTrue(output.getMessage().equals("Success"));
		assertTrue(output.getOrigin().equals("Testing"));
		assertTrue(output.jobId.equals("123456"));
	}
}
