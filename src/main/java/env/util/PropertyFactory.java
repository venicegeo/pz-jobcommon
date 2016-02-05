package env.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.ReflectionUtils;

import env.CoreServiceProperties;

/**
 * Accessed statically, this populates an instance of the CoreServiceProperties
 * object with the available environment variables, first loading them from the local properties
 * file, then from the services registered with Discover. This ordering is important as it essentially
 * defaults to the local properties if the Discover service is unavailable.
 * 
 * 
 * @author Russell.Orf
 * 
 */
public class PropertyFactory {

	private static CoreServiceProperties CSP = null;
	private static DiscoveryServiceClient DSC = null;
	
	private static final String DEFAULT_PROP_PATH = "src/main/resources/application.properties";
	
	private PropertyFactory(Properties p) {	}
		
	public static CoreServiceProperties getCoreServiceProperties() {
		if( CSP == null ) {
			initialize();
		}
				
		return CSP;
	}
	
	private static void initialize() {
		try {
			CSP = new CoreServiceProperties();		
			loadDefaultProps();
			
//			DSC = new DiscoveryServiceClient(CSP.getDiscoveryServiceUrl());
//			DSC.registerServiceWithDiscovery(CSP.getRegisterHost(), CSP.getRegisterPort(), CSP.getRegisterResourceName(), false);
//			
//			loadPropsFromDiscovery();
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		}
	}
	
	private static void loadDefaultProps() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(DEFAULT_PROP_PATH));
		
		CSP.setAlerterServiceUrl(p.getProperty("core.alerterservice.url"));
		CSP.setAlerterServiceResourceName(p.getProperty("core.alerterservice.resourcename"));
		CSP.setDiscoveryServiceUrl(p.getProperty("core.discoveryservice.url"));
		CSP.setDiscoveryServiceResourceName(p.getProperty("core.discoveryservice.resourcename"));
		CSP.setDispatcherHost(p.getProperty("core.dispatcher.host"));
		CSP.setDispatcherPort(p.getProperty("core.dispatcher.port"));
		CSP.setDispatcherResourceName(p.getProperty("core.dispatcher.resourcename"));
		CSP.setJobManagerHost(p.getProperty("core.jobmanager.host"));
		CSP.setJobManagerPort(p.getProperty("core.jobmanager.port"));
		CSP.setJobManagerResourceName(p.getProperty("core.jobmanager.resourcename"));
		CSP.setKafkaGroup(p.getProperty("kafka.group"));
		CSP.setKafkaHost(p.getProperty("kafka.host"));
		CSP.setKafkaPort(p.getProperty("kafka.port"));
		CSP.setKafkaResourceName(p.getProperty("kafka.resourcename"));
		CSP.setLoggerResourceName(p.getProperty("core.loggerservice.resourcename"));
		CSP.setLoggerServiceUrl(p.getProperty("core.loggerservice.url"));
		CSP.setMongoDBCollectionName(p.getProperty("mongodb.collectionname"));
		CSP.setMongoDBName(p.getProperty("mongodb.name"));
		CSP.setMongoDBResourceName(p.getProperty("mongodb.resourcename"));
		CSP.setMongoDBHost(p.getProperty("mongodb.host"));		
		CSP.setMongoDBPort(p.getProperty("mongodb.port"));
		CSP.setRegisterHost(p.getProperty("_register.host"));
		CSP.setRegisterPort(p.getProperty("_register.port"));
		CSP.setRegisterResourceName(p.getProperty("_register.resourcename"));
		CSP.setServiceControllerHost(p.getProperty("core.servicecontroller.host"));
		CSP.setServiceControllerPort(p.getProperty("core.servicecontroller.port"));
		CSP.setServiceControllerResourceName(p.getProperty("core.servicecontroller.resourcename"));
		CSP.setUuidResourceName(p.getProperty("core.uuidservice.resourcename"));
		CSP.setUuidServiceUrl(p.getProperty("core.uuidservice.url"));
	}
	
	private static void loadPropsFromDiscovery() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Map<String,String> alerterProps = DSC.discoverResource(CSP.getAlerterServiceResourceName());
		setProp(alerterProps.get("host"), CoreServiceProperties.class.getMethod("setAlerterServiceUrl", String.class));

		Map<String,String> dispatcherProps = DSC.discoverResource(CSP.getDispatcherResourceName());		
		setProp(dispatcherProps.get("host"), CoreServiceProperties.class.getMethod("setDispatcherHost", String.class));
		setProp(dispatcherProps.get("port"), CoreServiceProperties.class.getMethod("setDispatcherPort", String.class));
		
		Map<String,String> jobManagerProps = DSC.discoverResource(CSP.getJobManagerResourceName());		
		setProp(jobManagerProps.get("host"), CoreServiceProperties.class.getMethod("setJobManagerHost", String.class));
		setProp(jobManagerProps.get("port"), CoreServiceProperties.class.getMethod("setJobManagerPort", String.class));		
		
		Map<String,String> kafkaProps = DSC.discoverResource(CSP.getKafkaResourceName());		
		setProp(kafkaProps.get("host"), CoreServiceProperties.class.getMethod("setKafkaHost", String.class));
		setProp(kafkaProps.get("port"), CoreServiceProperties.class.getMethod("setKafkaPort", String.class));

		Map<String,String> loggerProps = DSC.discoverResource(CSP.getLoggerResourceName());
		setProp(loggerProps.get("host"), CoreServiceProperties.class.getMethod("setLoggerServiceUrl", String.class));
		
		Map<String,String> mongodbProps = DSC.discoverResource(CSP.getMongoDBResourceName());
		setProp(mongodbProps.get("host"), CoreServiceProperties.class.getMethod("setMongoDBHost", String.class));
		setProp(mongodbProps.get("port"), CoreServiceProperties.class.getMethod("setMongoDBPort", String.class));		
		
		Map<String,String> serviceControllerProps = DSC.discoverResource(CSP.getServiceControllerResourceName());		
		setProp(serviceControllerProps.get("host"), CoreServiceProperties.class.getMethod("setServiceControllerHost", String.class));
		setProp(serviceControllerProps.get("port"), CoreServiceProperties.class.getMethod("setServiceControllerPort", String.class));
		
		Map<String,String> uuidProps = DSC.discoverResource(CSP.getUuidResourceName());
		setProp(uuidProps.get("host"), CoreServiceProperties.class.getMethod("setUuidServiceUrl", String.class));		
	}	
	
	private static void setProp(Object obj, Method method) {
		if( obj != null ) {
			ReflectionUtils.invokeMethod(method, CSP, obj);
		}		
	}
}