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
package model.job.result;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a Result that a Job may have associated with it. This is to
 * provide standard serialization and deserialization of Results linked with
 * Jobs.
 * 
 * The result of a Job is stored as this Interface. The actual result of the Job
 * will depend on the type of result that needs to be stored.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = DataResult.class, name = "data"),
		@Type(value = DeploymentResult.class, name = "deployment"), @Type(value = TextResult.class, name = "text") })
public interface ResultType {
	public String getType();
}