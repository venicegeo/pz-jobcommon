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
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import model.logger.AuditElement;
import model.logger.LoggerPayload;
import model.logger.MetricElement;
import model.logger.Severity;

/**
 * PiazzaLogger is used to format logs into syslog message 
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

	private final Logger LOGGER = LoggerFactory.getLogger(PiazzaLogger.class);

	/**
	 * Default constructor, required for bean instantiation.
	 */
	public PiazzaLogger() {
		// Empty constructor required by Jackson
	}

	/**
	 * Creates a new Logger component. This constructor is not recommended. It's more recommended to have your project
	 * populate the pz.logger.url and pz.logger.name properties, and allow Spring to Autowire this object. However, this
	 * constructor exists for cases where that is not an option..
	 * 
	 * @param serviceName
	 *            The name of the pz-component that utilizes this logger. The name of the component will be included in
	 *            the message content for every log message generated by this instance of the PiazzaLogger.
	 */
	public PiazzaLogger(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Writes syslog format log message
	 * 
	 * @param logMessage
	 *            the message you want to log
	 * @param severity
	 *            the severity of the log
	 */
	public void log(String logMessage, Severity severity) {
		LoggerPayload loggerPayload = getLoggerPayload();
		writeLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Saves an audit message
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
		writeLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Writes metric message 
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
		writeLog(loggerPayload, logMessage, severity);
	}

	/**
	 * Writes full log message with metric and audit elements 
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
		writeLog(loggerPayload, logMessage, severity);
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
	 * Method to build the log message
	 * 
	 * @param loggerPayload
	 *            Syslog RFC 5424 standard log payload
	 * @param logMessage
	 *            actual log message
	 * @param severity
	 *            Syslog RFC-5424 Protocol severity codes
	 */
	private void writeLog(LoggerPayload loggerPayload, String logMessage, Severity severity) {
		// Setting generic fields on logger payload
		loggerPayload.setSeverity(severity);
		loggerPayload.setMessage(logMessage);
		loggerPayload.setTimestamp(new DateTime());

		// Log to console if requested
		try {
			if (logToConsole.booleanValue() && LOGGER.isInfoEnabled()) {
				LOGGER.info(loggerPayload.toRfc5424());
			}
		} catch (Exception exception) { /* Do nothing. */
			LOGGER.error("Could not log message to console. Application property is not set", exception);
		}
	}
}