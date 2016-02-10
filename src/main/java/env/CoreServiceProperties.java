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
package env;

/**
 * Represents the environment variables available to the running Services.
 * 
 * This contains properties for accessing the following services:
 * 		Alerter
 * 		Discover
 * 		Dispatcher
 * 		JobManager
 * 		Kafka
 * 		MongoDB
 * 		ServiceController
 * 		UUID
 * 
 * @author Russell.Orf
 * 
 */
public class CoreServiceProperties {
	
	private String alerterServiceUrl;
	private String alerterServiceResourceName;
	
	private String discoveryServiceUrl;
	private String discoveryServiceResourceName;
	
	private String dispatcherHost;
	private String dispatcherPort;
	private String dispatcherResourceName;
	
	private String loggerServiceUrl;
	private String loggerResourceName;

	private String jobManagerHost;
	private String jobManagerPort;
	private String jobManagerResourceName;
	
	private String kafkaHost;
	private String kafkaPort;
	private String kafkaGroup;
	private String kafkaResourceName;
	
	private String mongoDBHost;
	private String mongoDBPort;
	private String mongoDBName;
	private String mongoDBCollectionName;
	private String mongoDBResourceName;
	
	private String registerHost;
	private String registerPort;
	private String registerResourceName;
	
	private String serviceControllerHost;
	private String serviceControllerPort;
	private String serviceControllerResourceName;
	
	private String uuidServiceUrl;
	private String uuidResourceName;
	
	public String getAlerterServiceUrl() {
		return alerterServiceUrl;
	}
	public String getAlerterServiceResourceName() {
		return alerterServiceResourceName;
	}
	public String getDiscoveryServiceUrl() {
		return discoveryServiceUrl;
	}	
	public String getDiscoveryServiceResourceName() {
		return discoveryServiceResourceName;
	}
	public String getDispatcherHost() {
		return dispatcherHost;
	}
	public String getDispatcherPort() {
		return dispatcherPort;
	}
	public String getDispatcherResourceName() {
		return dispatcherResourceName;
	}
	public String getJobManagerHost() {
		return jobManagerHost;
	}
	public String getJobManagerPort() {
		return jobManagerPort;
	}
	public String getJobManagerResourceName() {
		return jobManagerResourceName;
	}
	public String getKafkaHost() {
		return kafkaHost;
	}
	public String getKafkaPort() {
		return kafkaPort;
	}
	public String getKafkaGroup() {
		return kafkaGroup;
	}
	public String getKafkaResourceName() {
		return kafkaResourceName;
	}
	public String getLoggerServiceUrl() {
		return loggerServiceUrl;
	}
	public String getLoggerResourceName() {
		return loggerResourceName;
	}
	public String getMongoDBHost() {
		return mongoDBHost;
	}
	public String getMongoDBPort() {
		return mongoDBPort;
	}
	public String getMongoDBName() {
		return mongoDBName;
	}
	public String getMongoDBCollectionName() {
		return mongoDBCollectionName;
	}
	public String getMongoDBResourceName() {
		return mongoDBResourceName;
	}
	public String getRegisterHost() {
		return registerHost;
	}
	public String getRegisterPort() {
		return registerPort;
	}
	public String getRegisterResourceName() {
		return registerResourceName;
	}
	public String getUuidServiceUrl() {
		return uuidServiceUrl;
	}
	public String getUuidResourceName() {
		return uuidResourceName;
	}
	public String getServiceControllerHost() {
		return serviceControllerHost;
	}
	public String getServiceControllerPort() {
		return serviceControllerPort;
	}
	public String getServiceControllerResourceName() {
		return serviceControllerResourceName;
	}
	public void setAlerterServiceUrl(String alerterServiceUrl) {
		this.alerterServiceUrl = alerterServiceUrl;
	}
	public void setAlerterServiceResourceName(String alerterServiceResourceName) {
		this.alerterServiceResourceName = alerterServiceResourceName;
	}
	public void setDiscoveryServiceUrl(String discoveryServiceUrl) {
		this.discoveryServiceUrl = discoveryServiceUrl;
	}
	public void setDiscoveryServiceResourceName(String discoveryServiceResourceName) {
		this.discoveryServiceResourceName = discoveryServiceResourceName;
	}		
	public void setDispatcherHost(String dispatcherHost) {
		this.dispatcherHost = dispatcherHost;
	}
	public void setDispatcherPort(String dispatcherPort) {
		this.dispatcherPort = dispatcherPort;
	}
	public void setDispatcherResourceName(String dispatcherResourceName) {
		this.dispatcherResourceName = dispatcherResourceName;
	}
	public void setJobManagerHost(String jobManagerHost) {
		this.jobManagerHost = jobManagerHost;
	}
	public void setJobManagerPort(String jobManagerPort) {
		this.jobManagerPort = jobManagerPort;
	}
	public void setJobManagerResourceName(String jobManagerResourceName) {
		this.jobManagerResourceName = jobManagerResourceName;
	}
	public void setKafkaHost(String kafkaHost) {
		this.kafkaHost = kafkaHost;
	}
	public void setKafkaPort(String kafkaPort) {
		this.kafkaPort = kafkaPort;
	}
	public void setKafkaGroup(String kafkaGroup) {
		this.kafkaGroup = kafkaGroup;
	}
	public void setKafkaResourceName(String kafkaResourceName) {
		this.kafkaResourceName = kafkaResourceName;
	}
	public void setLoggerServiceUrl(String loggerServiceUrl) {
		this.loggerServiceUrl = loggerServiceUrl;
	}
	public void setLoggerResourceName(String loggerResourceName) {
		this.loggerResourceName = loggerResourceName;
	}
	public void setMongoDBHost(String mongoHost) {
		this.mongoDBHost = mongoHost;
	}
	public void setMongoDBPort(String mongoPort) {
		this.mongoDBPort = mongoPort;
	}
	public void setMongoDBName(String mongoDBName) {
		this.mongoDBName = mongoDBName;
	}
	public void setMongoDBCollectionName(String mongoCollectionName) {
		this.mongoDBCollectionName = mongoCollectionName;
	}
	public void setMongoDBResourceName(String mongoDBResourceName) {
		this.mongoDBResourceName = mongoDBResourceName;
	}
	public void setRegisterHost(String registerHost) {
		this.registerHost = registerHost;
	}
	public void setRegisterPort(String registerPort) {
		this.registerPort = registerPort;
	}
	public void setRegisterResourceName(String registerResourceName) {
		this.registerResourceName = registerResourceName;
	}
	public void setServiceControllerHost(String serviceControllerHost) {
		this.serviceControllerHost = serviceControllerHost;
	}
	public void setServiceControllerPort(String serviceControllerPort) {
		this.serviceControllerPort = serviceControllerPort;
	}
	public void setServiceControllerResourceName(String serviceControllerResourceName) {
		this.serviceControllerResourceName = serviceControllerResourceName;
	}	
	public void setUuidServiceUrl(String uuidServiceUrl) {
		this.uuidServiceUrl = uuidServiceUrl;
	}
	public void setUuidResourceName(String uuidResourceName) {
		this.uuidResourceName = uuidResourceName;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nAlerter Service URL: " + getAlerterServiceUrl());
		sb.append("\nAlerter Service Resource Name: " + getAlerterServiceResourceName());	
		sb.append("\nDiscovery Service URL: " + getDiscoveryServiceUrl());
		sb.append("\nDiscovery Service Resource Name: " + getDiscoveryServiceResourceName());
		sb.append("\nDispatcher Host: " + getDispatcherHost());
		sb.append("\nDispatcher Port: " + getDispatcherPort());
		sb.append("\nDispatcher Resource Name: " + getDispatcherResourceName());
		sb.append("\nJobManager Host: " + getJobManagerHost());
		sb.append("\nJobManager Port: " + getJobManagerPort());
		sb.append("\nJobManager Resource Name: " + getJobManagerResourceName());
		sb.append("\nKafka Host: " + getKafkaHost());
		sb.append("\nKafka Port: " + getKafkaPort());
		sb.append("\nKafka Group: " + getKafkaGroup());
		sb.append("\nKafka Resource Name: " + getKafkaResourceName());		
		sb.append("\nLogger Service URL: " + getLoggerServiceUrl());
		sb.append("\nLogger Service Resource Name: " + getLoggerResourceName());
		sb.append("\nMongoDB Host: " + getMongoDBHost());
		sb.append("\nMongoDB Port: " + getMongoDBPort());
		sb.append("\nMongoDB Name: " + getMongoDBName());
		sb.append("\nMongoDB Collection Name: " + getMongoDBCollectionName());
		sb.append("\nMongoDB Resource Name: " + getMongoDBResourceName());
		sb.append("\nRegister Host: " + getRegisterHost());
		sb.append("\nRegister Port: " + getRegisterPort());
		sb.append("\nRegister Resource Name: " + getRegisterResourceName());
		sb.append("\nService Controller Host: " + getServiceControllerHost());
		sb.append("\nService Controller Port: " + getServiceControllerPort());
		sb.append("\nService Controller Resource Name: " + getServiceControllerResourceName());
		sb.append("\nUUID Service URL: " + getUuidServiceUrl());
		sb.append("\nUUID Service Resource Name: " + getUuidResourceName());
		
		return sb.toString();
	}
}