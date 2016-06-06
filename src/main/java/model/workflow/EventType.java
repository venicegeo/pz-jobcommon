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

// type model.workflow.EventType struct {
// ID Ident `json:"id"`
// Name string `json:"name" binding:"required"`
// Mapping map[string]elasticsearch.MappingElementTypeName `json:"mapping"
// binding:"required"`
// }

/*
 * { "id": "17de4", "name": "USDataFound", # short, id-like string "mapping": {
 * "ItemId": "string", # the uuid of the bad data "Severity": "integer", # level
 * of offense, 1..5 "Problem": "string" # nature of the issue, e.g. US bbox, US
 * phone number, etc } }
 */

public class EventType {
	
	@ApiModelProperty(value = "The unique identifier of this Event Type.", required=true)
	public String id;

	@ApiModelProperty(value = "The human-readable name for this Event Type.")
	public String name;
	
	@ApiModelProperty(value = "The map of key-value pairs that define the properties of this Event Type. The key is the name of the property, and the value is the type of that property. Valid types are 'string' and 'integer'.")
	public Map<String, String> mapping;
}