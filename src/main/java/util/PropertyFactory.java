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

import java.util.Iterator;
import java.util.Properties;

import com.amazonaws.util.json.JSONObject;

/**
 * Accessed statically, this populates a Properties object with the available
 * environment variables from the Cloud-Foundry populated VCAP_SERVICES
 * environment property.
 * 
 * @author Russell.Orf
 * 
 */
@Deprecated
public class PropertyFactory {

	private static Properties ENV_PROPS = null;

	private PropertyFactory(Properties p) {
	}

	public static Properties getEnvironmentProperties() {
		if (ENV_PROPS == null) {
			initializeEnvProps();
		}

		return ENV_PROPS;
	}

	private static void initializeEnvProps() {
		String envString = System.getenv("VCAP_SERVICES");
		if (envString != null) {
			try {
				JSONObject jsonEnv = new JSONObject(envString);
				Iterator<String> iter = jsonEnv.keys();
				String key;

				ENV_PROPS = new Properties();

				System.out.println("VCAP_SERVICES properties: ");
				while (iter.hasNext()) {
					key = iter.next();
					System.out.println("Key:" + key + " Value:" + jsonEnv.get(key));
					ENV_PROPS.put(key, jsonEnv.get(key));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}