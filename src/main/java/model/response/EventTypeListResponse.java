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
package model.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import model.workflow.EventType;

@ApiModel(value="EventTypeResults")
public class EventTypeListResponse extends PiazzaResponse {

	@ApiModelProperty(hidden = true)
	private String type = "eventtype-list";

	@ApiModelProperty(value = "The array of EventType results.")
	public List<EventType> eventTypes;

	@ApiModelProperty(value = "The pagination metadata for this query.")
	public Pagination pagination;

	public EventTypeListResponse() {

	}

	public EventTypeListResponse(List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}

	public EventTypeListResponse(List<EventType> eventTypes, Pagination pagination) {
		this(eventTypes);
		this.pagination = pagination;
	}

	/**
	 * Gets the type of this response.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the list of EventTypes held by this response object
	 * 
	 * @return The list
	 */
	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public Pagination getPagination() {
		return pagination;
	}
}