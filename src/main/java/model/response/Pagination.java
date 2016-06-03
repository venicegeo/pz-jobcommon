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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Metadata describing pagination information when returning lists of data in
 * responses.
 * 
 * @author Patrick.Doody
 *
 */
public class Pagination {

	@ApiModelProperty(value = " The number of results that matched the query.")
	public Integer count;

	@ApiModelProperty(value = "The current page returned.")
	public Integer page;

	@ApiModelProperty(value = "The current number of results returned per page.")
	public Integer per_page;

	public Pagination() {

	}

	public Pagination(Integer count, Integer page, Integer per_page) {
		this.count = count;
		this.page = page;
		this.per_page = per_page;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPer_page() {
		return per_page;
	}
}