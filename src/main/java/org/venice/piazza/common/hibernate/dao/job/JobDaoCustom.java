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
package org.venice.piazza.common.hibernate.dao.job;

import org.springframework.data.domain.Page;
import org.venice.piazza.common.hibernate.entity.JobEntity;

import model.response.Pagination;

/**
 * Interface for custom Job Queries
 * 
 * @author Patrick.Doody
 *
 */
public interface JobDaoCustom {
	/**
	 * Retrieves Jobs list for a particular user and a particular status
	 * 
	 * @param status
	 *            Status of the Job.
	 * @param userName
	 *            Username of the Job creator.
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	Page<JobEntity> getJobListForUserAndStatus(String status, String userName, Pagination pagination);

	/**
	 * Retrieves Jobs list
	 * 
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	Page<JobEntity> getJobList(Pagination pagination);

	/**
	 * Retrieves Jobs list for a particular user
	 * 
	 * @param userName
	 *            Username of the Job creator.
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	Page<JobEntity> getJobListByUser(String userName, Pagination pagination);

	/**
	 * Retrieves Jobs list for a particular status
	 * 
	 * @param status
	 *            Status of the Job.
	 * @param pagination
	 *            Pagination information.
	 * @return Paginated list of Results
	 */
	Page<JobEntity> getJobListByStatus(String status, Pagination pagination);
}
