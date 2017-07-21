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
package org.venice.piazza.common.hibernate.usertype;

import org.venice.piazza.common.hibernate.util.GenericJsonUserType;

import model.service.async.AsyncServiceInstance;

/**
 * Hibernate AsyncService Instance Class that ties the API Key POJO to a JSONB table mapping in Postgres
 * 
 * @author Patrick.Doody
 *
 */
public class AsyncServiceInstanceType extends GenericJsonUserType {
	public Class<AsyncServiceInstance> returnedClass() {
		return AsyncServiceInstance.class;
	}
}
