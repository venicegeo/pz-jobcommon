package env.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import model.resource.CoreResource;
import model.resource.RegisterService;

public class DiscoveryServiceClient {
	
	private String discoveryServiceUrl = null;
	
	public DiscoveryServiceClient(String discoveryServiceUrl) {
		this.discoveryServiceUrl = discoveryServiceUrl;		
	}
	
	public void registerServiceWithDiscovery(String serviceHost, String servicePort, String serviceName, boolean isUpdate) {
		 ResponseEntity<String> response = null;
		 RestTemplate template = new RestTemplate();
		 
		 try {	         
	        // Prepare header
	        HttpHeaders headers = new HttpHeaders(); 
			headers.setContentType(MediaType.APPLICATION_JSON);

	        // Create a map to send things to the Piazza core log service
		    Map<String, String> map = new HashMap<String, String>();
			map.put("type", "core-service");			
			map.put("host", serviceHost);
			if( servicePort != null ) {
				map.put("port", servicePort);
			}
		
			HttpEntity<RegisterService> entity = new HttpEntity<RegisterService>(new RegisterService(serviceName, map),headers);

	       	response = template.exchange("http://" + discoveryServiceUrl, HttpMethod.PUT, entity, String.class, serviceName);
        
	        System.out.println("StatusCode: " + response.getStatusCode().toString());
	        System.out.println("Response: " + response.getBody().toString());
		} 
		 catch (HttpClientErrorException ex) {
			ex.printStackTrace();		    			
		}
	}
	
	public Map<String,String> discoverResource(String resourceName) { 
		 RestTemplate template = new RestTemplate();
		 ResponseEntity<CoreResource> response = null;
		 Map<String,String> propsToReturn = new HashMap<String,String>();
		 
		 try {	         	         
	         // Send the request as GET
	         response = template.getForEntity("http://" + discoveryServiceUrl + "/" + resourceName, CoreResource.class);

	         if (response.getStatusCode() == HttpStatus.OK) {
	        	 CoreResource coreResource = response.getBody();
	        	 
	        	 // Split out Port and Host
	        	 StringBuffer sBuffer;
	        	 
	        	 // Hack until interface is standardized!
	        	 if( resourceName.contains("mongo")) {
	        		 sBuffer = new StringBuffer(coreResource.getDburi());
	        	 }
	        	 else if( resourceName.contains("servicecontroller")) {
	        		 sBuffer = new StringBuffer(coreResource.getAddress());
	        	 }	        	 
	        	 else {
	        		 sBuffer = new StringBuffer(coreResource.getHost());
	        	 }
	        	 
	        	 if (sBuffer != null ) {
	        		 int portIndex = sBuffer.indexOf(":");
	        		 if (portIndex != -1) {
	        			 propsToReturn.put("host", sBuffer.substring(0, portIndex));
	        			 propsToReturn.put("port", sBuffer.substring(portIndex + 1));
	        		 }
	        		 else {
	        			 propsToReturn.put("host", sBuffer.toString());
	        			 propsToReturn.put("port", coreResource.getPort());
	        		 }
	        	 }
	         }
	     } 
		 catch (HttpClientErrorException ex) {
			System.out.println("Received 404 for: " + resourceName);
//			ex.printStackTrace();
		}
		return propsToReturn;
	}
}