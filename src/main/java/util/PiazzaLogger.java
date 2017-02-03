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
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.logger.AuditElement;
import model.logger.LoggerPayload;
import model.logger.MetricElement;
import model.logger.Severity;

/**
 * PiazzaLogger is a class to write syslog logs into Elasticsearch
 * 
 * @author Sonny.Saniev
 * @version 1.0
 */
@Component
public class PiazzaLogger {
	@Value("${logger.name:}")
	private String serviceName;
	@Value("${logger.console:}")
	private Boolean logToConsole;
	@Value("${LOGGER_INDEX}")
	private String loggerIndexName;
	@Value("${vcap.services.pz-elasticsearch.credentials.transportClientPort}")
	private Integer elasticSearchPort;
	@Value("${vcap.services.pz-elasticsearch.credentials.hostname}")
	private String elasticSearchHost;
	@Value("${elasticsearch.clustername}")
	private String clustername;

	@Autowired
	private Client elasticClient;

	private final static Logger LOGGER = LoggerFactory.getLogger(PiazzaLogger.class);
	private final String LOG_SCHEMA = "LogData";

	/**
	 * Default constructor, required for bean instantiation.
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
	}

	@PostConstruct
	public void init() {
		// Create elasticsearch index with mapping
		createIndexWithMapping(elasticClient, loggerIndexName, LOG_SCHEMA, null);
	}

	/**
	 * Spring bean for injecting elasticsearch client.
	 * 
	 * @return Elasticsearch client
	 * @throws UnknownHostException
	 */
	@Bean
	public Client getClient() throws UnknownHostException {
		Settings settings = Settings.settingsBuilder().put("cluster.name", clustername).build();
		TransportClient transportClient = TransportClient.builder().settings(settings).build();
		// Check if the ES Host property has multiple Hosts.
		if (elasticSearchHost.contains(";")) {
			// (In the form of "host;host2;host3")
			//
			// Multiple hosts. Split the string and add each host.
			List<String> hosts = Arrays.asList(elasticSearchHost.split(";"));
			for (String host : hosts) {
				transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(host, elasticSearchPort)));
			}
		} else {
			// (A single host)
			//
			// Single host. Add this one host.
			transportClient
					.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(elasticSearchHost, elasticSearchPort)));
		}
		return transportClient;
	}

	/**
	 * Writes syslog format log message to elasticsearch
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 */
	public void log(String logMessage, Severity severity) {
		LoggerPayload loggerPayload = getLoggerPayload();
		indexLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Creates elasticsearch index with default mapping.
	 * 
	 * @param client
	 *            elasticsearch client
	 * @param indexName
	 *            index to save to
	 * @param type
	 *            schema to save to
	 * @param mapping
	 *            mapping object
	 * @return boolean
	 */
	public boolean createIndexWithMapping(Client client, String indexName, String type, String mapping) {
		try {
			if (!indexExists(indexName)) {
				CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);
				if (mapping != null) {
					createIndexRequestBuilder.addMapping(type, mapping);
				} else {
					createIndexRequestBuilder.addMapping(type);
				}
				CreateIndexResponse response = createIndexRequestBuilder.execute().actionGet();
				return response.isAcknowledged();
			}
		} catch (Exception exception) {
			LOGGER.info(String.format("Unable to create Elasticsearch index %s, it should already exist, error", indexName), exception);
		}

		return false;
	}

	/**
	 * Checks to see if elastic index exists
	 * 
	 * @param indexName
	 * @return boolean
	 */
	public boolean indexExists(String indexName) {
		return elasticClient.admin().indices().prepareExists(indexName).execute().actionGet().isExists();
	}

	/**
	 * Saves an audit message to elasticsearch
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
		indexLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Saves a metric message to elasticsearch
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
		indexLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Saves full log payload with metric and audit elements present to elasticsearch
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
		indexLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Generates a LoggerPayload with default values populated
	 * 
	 * @return LoggerPayload payload object
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

	/**
	 * Method to index the log payload into elasticsearch.
	 * 
	 * @param loggerPayload
	 *            Syslog RFC 5424 standard log payload
	 * @param logMessage
	 *            actual log message
	 * @param severity
	 *            Syslog RFC-5424 Protocol severity codes
	 */
	private void indexLog(LoggerPayload loggerPayload, String logMessage, Severity severity) {
		// Setting generic fields on logger payload
		loggerPayload.setSeverity(severity);
		loggerPayload.setMessage(logMessage);
		loggerPayload.setTimestamp(new DateTime());

		// Log to console if requested
		try {
			if (logToConsole.booleanValue()) {
				LOGGER.info(loggerPayload.toString());
			}
		} catch (Exception exception) { /* Do nothing. */
			LOGGER.error("Could not log message to console. Application property is not set", exception);
		}

		// saving log to elastic search
		String loggerPayloadJson = "";
		try {
			loggerPayloadJson = new ObjectMapper().writeValueAsString(loggerPayload);
		} catch (JsonProcessingException e) {
			LOGGER.error("Failed to serialize the log payload", e);
		}

		LOGGER.debug(String.format("Writing the following log object to elastic search:%n%s", loggerPayloadJson));

		try {
			// Index to elasticsearch
			IndexRequest indexRequest = new IndexRequest(loggerIndexName, LOG_SCHEMA);
			indexRequest.source(loggerPayloadJson);
			IndexResponse esResponse = elasticClient.index(indexRequest).actionGet();
		} catch (Exception e) {
			LOGGER.info(String.format("Unable to index logs into Elasticsearch: %s", e.getMessage()), e);
		}
	}
}