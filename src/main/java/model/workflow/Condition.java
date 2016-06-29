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
package model.workflow;

import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sbortman on 6/2/16.
 */
// expresses the idea of "this ES query returns an event"
// Query is specific to the event type
//
// type model.workflow.Condition struct {
// EventTypeIDs []Ident `json:"eventtype_ids" binding:"required"`
// Query map[string]interface{} `json:"query" binding:"required"`
// }

public class Condition {

	@ApiModelProperty(value = "The array of Event Type IDs.")
	public String[] eventtype_ids;

	@ApiModelProperty(value = "Elastic Search DSL Query 2.2, for details see https://www.elastic.co/guide/en/elasticsearch/reference/2.2/query-dsl.html")
	public Map<String, Object> query;
}