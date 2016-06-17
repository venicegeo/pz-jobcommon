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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import model.request.PiazzaJobRequest;

/**
 * Created by sbortman on 6/2/16.
 */
// when the and'ed set of Conditions all are true, do Something
// Events are the results of the Conditions queries
// model.workflow.Job is the JobMessage to submit back to Pz
// TODO: some sort of mapping from the event info into the model.workflow.Job
// string
//
// type model.workflow.Trigger struct {
// ID Ident `json:"id"`
// Title string `json:"title" binding:"required"`
// model.workflow.Condition model.workflow.Condition `json:"condition"
// binding:"required"`
// model.workflow.Job model.workflow.Job `json:"job" binding:"required"`
// PercolationID Ident `json:"percolation_id"`
// }
/*
 * { "id": "987d6", "title": "my found-a-bad-telephone-number trigger",
 * "condition": { "eventtype_ids": ["17de4"], # array of event type ids "query":
 * { "query" : { # the Elasticsearch query string "bool": { "must": [ { "match"
 * : { "Severity" : 4 } }, { "match" : { "Problem" : "us-bbox" } } ] } } },
 * "job": { "Task":
 * "{\"userName\": \"$userName\", \"jobType\": {\"type\": \"get\", \"jobId\": \"$jobId\"}}"
 * } } }
 */
@JsonInclude(Include.NON_NULL)
public class Trigger {

	@ApiModelProperty(value = "The unique identifier for this Event.", required = true)
	public String id;

	@ApiModelProperty(value = "The human-readable description of what this Trigger intends to accomplish.")
	public String title;

	@ApiModelProperty(value = "Defines the trigger conditions, the query replacements, and the Job to execute upon this object becoming Triggered.")
	public Condition condition;

	@ApiModelProperty(value = "The Template of the Job to be executed when the conditions are met.")
	public PiazzaJobRequest job;

	@ApiModelProperty(value = "")
	public String percolationId;
}