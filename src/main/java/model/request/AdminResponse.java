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
package model.request;

//An AdminResponse represents the data returned from a call to a service's
///admin API.
public class AdminResponse {
	private String starttime;
	private AdminResponseUuidgen uuidgen;
	private AdminResponseLogger logger;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public AdminResponseUuidgen getUuidgen() {
		return uuidgen;
	}
	public void setUuidgen(AdminResponseUuidgen uuidgen) {
		this.uuidgen = uuidgen;
	}
	public AdminResponseLogger getLogger() {
		return logger;
	}
	public void setLogger(AdminResponseLogger logger) {
		this.logger = logger;
	}
	

}
