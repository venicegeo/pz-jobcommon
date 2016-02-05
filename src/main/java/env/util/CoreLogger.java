package env.util;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import model.request.LogRequest;


// Add License header
/**
 * CoreLogger is a class that logs using the Piazza Core Logger service. TODO
 * this class has to be integrated with the new Discovery approach before it can
 * be used.
 * 
 * @author mlynum, rorf
 * @version 1.0
 */

@Component
public class CoreLogger {

	public static final String DEBUG = "Debug";
	public static final String ERROR = "Error";
	public static final String FATAL = "Fatal";
	public static final String INFO = "Info";
	public static final String WARNING = "Warning";

	private static final String[] SEVERITY_OPTIONS = { DEBUG, ERROR, FATAL, INFO, WARNING };
	
	private String loggerServiceUrl;
	private String serviceName;

	private RestTemplate template;

	private final static Logger LOG = LoggerFactory.getLogger(CoreLogger.class);

	@PostConstruct
	public void init() {
		LOG.info("CoreLogger initialized");
		template = new RestTemplate();
		loggerServiceUrl = PropertyFactory.getCoreServiceProperties().getLoggerServiceUrl();
		serviceName = PropertyFactory.getCoreServiceProperties().getRegisterResourceName();
	}

	/**
	 * method for logging messages to Pz-Logger
	 * 
	 * @param logMessage
	 *            - the message you want to log
	 * @param severity
	 *            - the severity of the log
	 */
	public void log(String logMessage, String severity) {		
		if ( isLogInputValid(logMessage, severity) ) {			
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				LogRequest logRequest = new LogRequest(serviceName, InetAddress.getLocalHost().toString(),
						new DateTime(DateTimeZone.UTC).toString(), logMessage, severity);
				
				template.postForEntity("http://" + loggerServiceUrl, new HttpEntity<LogRequest>(logRequest, headers), String.class);						
			} 
			catch (Exception ex) {
				LOG.error(ex.getMessage());
			}
		}
		else {
			LOG.error("Log input invalid!");
		}
	}
	
	public List<LogRequest> getLogs(Integer count) {	
		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("count", count);
			
			ResponseEntity<LogRequest[]> logs = template.getForEntity("http://" + loggerServiceUrl + "?count={count}", LogRequest[].class, map );	
			return (List<LogRequest>)Arrays.asList(logs.getBody());
		} 
		catch (Exception ex) {
			LOG.error(ex.getMessage());
			return null;
		}
	}	
	
	private boolean isLogInputValid(String logMessage, String severity) {
		if( logMessage == null || logMessage.length() == 0 ) {
			LOG.error("Message is null. Logger cannot send an empty message. This is a required field.");
			return false;
		}
		if( severity == null || severity.length() == 0 ) {
			LOG.error("Severity is null. Logger cannot send message without a severity. This is a required field.");
			return false;
		}
		if( !Arrays.asList(SEVERITY_OPTIONS).contains(severity) ) {
			LOG.error("Severity '" + severity + "' is not one of the available options: " + Arrays.toString(SEVERITY_OPTIONS));
			return false;
		}
		
		return true;
	}
}