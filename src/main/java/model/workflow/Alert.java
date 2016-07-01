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

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by sbortman on 6/2/16.
 */
// a notification, automatically created when an model.workflow.Trigger happens
//
// type model.workflow.Alert struct {
// ID Ident `json:"id"`
// TriggerID Ident `json:"trigger_id"`
// EventID Ident `json:"event_id"`
// }

/*
 * { "id": "8e6fa", "trigger_id": "987d6", "event_id": "53dac" }
 */

public class Alert {
	@ApiModelProperty(value = "The unique identifier for this Alert.", required = true)
	public String alertId;

	@ApiModelProperty(value = "The unique identifier for the Trigger that was hit.")
	public String triggerId;

	@ApiModelProperty(value = "The unique identifier for the Event that triggered the Trigger.")
	public String eventId;

	@ApiModelProperty(value = "The unique identifier for the Job.")
	public String jobId;
}