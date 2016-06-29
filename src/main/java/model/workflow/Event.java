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

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sbortman on 6/2/16.
 */

// posted by some source (service, user, etc) to indicate Something Happened
// Data is specific to the event type
// TODO: use the delayed-parsing, raw-message json thing?
//
// type model.workflow.Event struct {
// ID Ident `json:"id"`
// EventTypeID Ident `json:"eventtype_id" binding:"required"`
// Date time.Time `json:"date" binding:"required"`
// Data map[string]interface{} `json:"data"`
// }
/*
 * { "id": "53dac" "eventtype_id": "17de4", "date": "2007-04-05T14:30:00Z",
 * "data": { "ItemId": "eb872", # in this case, the id of a data item loaded
 * into Pz "Severity": 4, "Problem": "us-bbox", "userName": "my-api-key-38n987",
 * "jobId": "43688858-b6d4-4ef9-a98b-163e1980bba8" } }
 */
@JsonInclude(Include.NON_NULL)
public class Event {

	@ApiModelProperty(value = "The unique identifier for this Event.", required = true)
	public String id;

	@ApiModelProperty(value = "The unique identifier of the Event Type whose schema this Event conforms to.", required = true)
	public String eventtype_id;

	@ApiModelProperty(value = "The date and time that the Event has generated.", required = true)
	@JsonIgnore
	public DateTime date;

	@ApiModelProperty(value = "The populated values for the Key-value pairs defined by the Event Type's 'mapping' dictionary. Each value in this dictionary must be populated here under this 'data' property.")
	public Map<String, Object> data;

	@JsonProperty("date")
	public String getDateString() {
		// Defaults to ISO8601
		return date.toString();
	}

	@JsonProperty("date")
	public void setDateString(String date) {
		this.date = new DateTime(date);
	}
}