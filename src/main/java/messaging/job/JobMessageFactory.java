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
package messaging.job;

/**
 * Factory class to abstract the production of Job-related Kafka messages using this projects defined POJO Models.
 * 
 * @author Patrick.Doody
 * 
 */
public enum JobMessageFactory {
	; // NOSONAR

	public static final String REQUEST_JOB_TOPIC_NAME = "RequestJob";
	public static final String CREATE_JOB_TOPIC_NAME = "CreateJob";
	public static final String ABORT_JOB_TOPIC_NAME = "AbortJob";
	public static final String UPDATE_JOB_TOPIC_NAME = "UpdateJob";
	public static final String TOPIC_TEMPLATE = "%s-%s";

}
