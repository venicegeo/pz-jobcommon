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
package env.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import model.resource.UUID;

// Add License header
/**
 * CoreUUIDGen class that calls the Piazza Core UUIDGen service and returns a
 * new ID. If the service is down, an ID is generated TODO this class has to be
 * integrated with the new Discovery approach before it can be used.
 * 
 * @author mlynum, rorf
 * @version 1.0
 */

@Component
public class CoreUUIDGen {

	private String uuidServiceUrl;
	private RestTemplate template;

	private final static Logger LOG = LoggerFactory.getLogger(CoreUUIDGen.class);
	
	@PostConstruct
	public void init() {
		LOG.info("CoreUUIDGen initialized");
		template = new RestTemplate();
		uuidServiceUrl = PropertyFactory.getCoreServiceProperties().getUuidServiceUrl();
	}

	/**
	 * Only gets one UUID
	 * 
	 * @return UUID - Piazza unique identifier
	 */
	public String getUUID() {
		return getUUID(1).get(0);
	}

	/**
	 * Calls the UUIDgen service to get a unique identifier. if the service
	 * cannot be reached then a UUID is generated.
	 * 
	 * @return List of UUIDs
	 */
	public List<String> getUUID(Integer count) {

		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("count", count);
			
			ResponseEntity<UUID> uuid = template.postForEntity("http://" + uuidServiceUrl + "?count={count}", null, UUID.class, map);
			return uuid.getBody().getData();
		} 
		catch (Exception ex) {
			LOG.error(ex.getMessage());

			return getUUID_local(count);			
		}
	}

	private List<String> getUUID_local(Integer count) {
		List<String> uuidList = new ArrayList<String>();
		for( int i = 0; i < count; i++ ) {
			uuidList.add( generateId() );
		}
		
		return uuidList;
	}
	
	/**
	 * Generates an ID for persisting data using Random
	 * 
	 * @return id
	 */
	private String generateId() {
		return java.util.UUID.randomUUID().toString();
	}
}