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
import model.workflow.Event;

@ApiModel(value = "EventResults")
public class EventListResponse extends PiazzaResponse {

	@ApiModelProperty(value = "Legacy. This will eventually be removed.")
	private String type = "event-list";

	@ApiModelProperty(value = "The array of Event results.")
	public List<Event> events;

	@ApiModelProperty(value = "The pagination metadata for this query.")
	public Pagination pagination;

	public EventListResponse() {

	}

	public EventListResponse(List<Event> events) {
		this.events = events;
	}

	public EventListResponse(List<Event> events, Pagination pagination) {
		this(events);
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
	 * Returns the list of Events held by this response object
	 * 
	 * @return The list
	 */
	public List<Event> getEvents() {
		return events;
	}

	public Pagination getPagination() {
		return pagination;
	}
}