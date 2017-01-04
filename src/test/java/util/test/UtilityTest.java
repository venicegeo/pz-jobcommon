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
package util.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import model.logger.AuditElement;
import model.logger.MetricElement;
import model.logger.Severity;
import model.request.LogRequest;
import model.resource.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import util.PiazzaLogger;
import util.UUIDFactory;

/**
 * Tests the Common utilities (Logger, UUIDgen, etc)
 * 
 * @author Patrick.Doody
 *
 */
public class UtilityTest {
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private PiazzaLogger logger;
	@InjectMocks
	private UUIDFactory uuidFactory;

	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(logger, "logToConsole", true);
		ReflectionTestUtils.setField(logger, "LOGGER_URL", "http://localhost");
		ReflectionTestUtils.setField(logger, "LOGGER_ENDPOINT", "logs");
		ReflectionTestUtils.setField(logger, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(uuidFactory, "restTemplate", restTemplate);
		
		logger.init();
	}

	/**
	 * Tests logging
	 */
	@Test
	public void testLog() {
		// Sample Data
		AuditElement auditElement = new AuditElement("me", "tests", "you");
		MetricElement metricElement = new MetricElement("test", "tests", "10");

		// Test
		logger.log("Test!", Severity.INFORMATIONAL);
		logger.log("Test!", Severity.WARNING);
		logger.log("Test!", Severity.CRITICAL);
		logger.log("Test!", Severity.DEBUG);
		logger.log("Test!", Severity.INFORMATIONAL, auditElement);
		logger.log("Test!", Severity.INFORMATIONAL, metricElement);
		logger.log("Test!", Severity.DEBUG, auditElement, metricElement);
	}

	/**
	 * Tests UUID Factory errors
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testUuid() {
		// Mock
		UUID mockUuid = new UUID();
		mockUuid.setData(new ArrayList<String>());
		mockUuid.getData().add("123456");
		when(restTemplate.postForEntity(anyString(), any(), eq(UUID.class), anyMap()))
				.thenReturn(new ResponseEntity<UUID>(mockUuid, HttpStatus.OK));

		// Test
		String uuid = uuidFactory.getUUID();
		assertTrue(uuid.equals("123456"));

		// Mock local generation
		Mockito.doThrow(new RestClientException("Test")).when(restTemplate).postForEntity(anyString(), any(), eq(UUID.class), anyMap());
		uuid = uuidFactory.getUUID();
		assertTrue(uuid.isEmpty() == false);
	}
}
