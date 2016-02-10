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

public class AdminResponseUuidgen {
	private int num_requests;
	private int num_uuids;
	public int getNum_requests() {
		return num_requests;
	}
	public void setNum_requests(int num_requests) {
		this.num_requests = num_requests;
	}
	public int getNum_uuids() {
		return num_uuids;
	}
	public void setNum_uuids(int num_uuids) {
		this.num_uuids = num_uuids;
	}
	

}
