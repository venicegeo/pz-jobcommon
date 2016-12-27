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
package util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.logger.AuditElement;
import model.logger.LoggerPayload;
import model.logger.MetricElement;
import model.logger.Severity;

/**
 * PiazzaLogger is a class that logs using the Piazza Core Logger service.
 * 
 * @author mlynum, rorf, patrick.doody
 * @version 1.0
 */
@Component
public class PiazzaLogger {
	@Value("${logger.url}")
	private String LOGGER_URL;
	@Value("${logger.endpoint}")
	private String LOGGER_ENDPOINT;
	@Value("${logger.name:}")
	private String serviceName;
	@Value("${logger.console:}")
	private Boolean logToConsole;
	@Value("${http.max.total:5000}")
	private int httpMaxTotal;
	@Value("${http.max.route:2500}")
	private int httpMaxRoute;

	private RestTemplate restTemplate = new RestTemplate();
	private final static Logger LOGGER = LoggerFactory.getLogger(PiazzaLogger.class);

	/**
	 * Default constructor, required for beat instantiation.
	 */
	public PiazzaLogger() {
	}

	/**
	 * Creates a new Logger component. This constructor is not recommended. It's more recommended to have your project
	 * populate the pz.logger.url and pz.logger.name properties, and allow Spring to Autowire this object. However, this
	 * constructor exists for cases where that is not an option..
	 * 
	 * @param loggerServiceUrl
	 *            The URL of the PiazzaLogger service.
	 * @param serviceName
	 *            The name of the pz-component that utilizes this logger. The name of the component will be included in
	 *            the message content for every log message generated by this instance of the PiazzaLogger.
	 */
	public PiazzaLogger(String loggerServiceUrl, String serviceName) {
		this.serviceName = serviceName;
		this.LOGGER_URL = loggerServiceUrl;
	}

	@PostConstruct
	public void init() {
		LOGGER.info(String.format("PiazzaLogger initialized for service %s, url: %s", serviceName, LOGGER_URL));
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(httpMaxTotal).setMaxConnPerRoute(httpMaxRoute).build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	/**
	 * Sends a syslog payload message to pz-logger for logging into logstash.
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 */
	public void log(String logMessage, Severity severity) {
		LoggerPayload loggerPayload = getLoggerPayload();
		sendLogs(loggerPayload, logMessage, severity);
	}

	/**
	 * Sends an audit message to pz-logger
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 * @param auditElement
	 *            syslog audit parameter
	 */
	public void log(String logMessage, Severity severity, AuditElement auditElement) {
		LoggerPayload loggerPayload = getLoggerPayload();
		loggerPayload.setAuditData(auditElement);
		sendLogs(loggerPayload, logMessage, severity);
	}

	/**
	 * Sends a metric message to pz-logger
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 * @param metricElement
	 *            syslog metric parameter
	 */
	public void log(String logMessage, Severity severity, MetricElement metricElement) {
		LoggerPayload loggerPayload = getLoggerPayload();
		loggerPayload.setMetricData(metricElement);
		sendLogs(loggerPayload, logMessage, severity);
	}

	/**
	 * Sends a metric message to pz-logger
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 * @param auditElement
	 *            syslog audit parameter
	 * @param metricElement
	 *            syslog metric parameter
	 */
	public void log(String logMessage, Severity severity, AuditElement auditElement, MetricElement metricElement) {
		LoggerPayload loggerPayload = getLoggerPayload();
		loggerPayload.setAuditData(auditElement);
		loggerPayload.setMetricData(metricElement);
		sendLogs(loggerPayload, logMessage, severity);
	}

	/**
	 * Sends the logger payload to pz-logger
	 * 
	 * @param loggerPayload
	 *            payload
	 * 
	 */
	private void sendLogs(final LoggerPayload loggerPayload, final String logMessage, final Severity severity) {
		// Run the HTTP message on a separate thread
		Thread logThread = new Thread(new Runnable() {
			public void run() {
				// Setting generic fields on logger payload
				loggerPayload.setSeverity(severity);
				loggerPayload.setMessage(logMessage);
				loggerPayload.setTimestamp(new DateTime());

				String url = String.format("%s/%s", LOGGER_URL, LOGGER_ENDPOINT);

				try {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);

					// Log to console if requested
					try {
						if (logToConsole.booleanValue()) {
							LOGGER.info(loggerPayload.toString());
						}
					} catch (Exception exception) { /* Do nothing. */
						LOGGER.error("Could not log message to console. Application property is not set", exception);
					}

					restTemplate.postForEntity(url, new HttpEntity<LoggerPayload>(loggerPayload, headers), String.class);
				} catch (HttpClientErrorException httpException) {
					LOGGER.error("HTTP Client Error", httpException);
					handleHttpError(loggerPayload, url, httpException.getResponseBodyAsString());
				} catch (HttpServerErrorException httpException) {
					LOGGER.error("HTTP Server Error", httpException);
					handleHttpError(loggerPayload, url, httpException.getResponseBodyAsString());
				} catch (Exception exception) {
					LOGGER.error("Could not log due to an unhandled exception.", exception);
				}
			}
		});
		logThread.start();
	}

	/**
	 * Handles an HTTP Error from the pz-logger service.
	 */
	private void handleHttpError(LoggerPayload loggerPayload, String url, String responseString) {
		String loggerPayloadString = loggerPayload.toString();
		try {
			loggerPayloadString = new ObjectMapper().writeValueAsString(loggerPayload);
		} catch (Exception jsonException) {
			LOGGER.error("Failed to serialize JSON for Payload. Printing String instead.", jsonException);
		}
		LOGGER.error(String.format("Failed to send message to Pz-Logger component. Endpoint is %s. Payload is %s. Response was %s", url,
				loggerPayloadString, responseString));
	}

	/**
	 * Generates a LoggerPayload with default values populated
	 */
	private LoggerPayload getLoggerPayload() {
		LoggerPayload loggerPayload = new LoggerPayload();
		loggerPayload.setApplication(serviceName);
		loggerPayload.setProcess(ManagementFactory.getRuntimeMXBean().getName());
		try {
			loggerPayload.setHostName(InetAddress.getLocalHost().getHostName());
		} catch (Exception exception) {
			LOGGER.error("Could not get hostname for component.", exception);
		}
		return loggerPayload;
	}
}