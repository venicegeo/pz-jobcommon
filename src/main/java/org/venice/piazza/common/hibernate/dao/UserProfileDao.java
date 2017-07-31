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
package org.venice.piazza.common.hibernate.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.venice.piazza.common.hibernate.entity.UserProfileEntity;

/**
 * Data Access Object for User Profile Entities
 * 
 * @author Patrick.Doody
 *
 */
@Repository
public interface UserProfileDao extends CrudRepository<UserProfileEntity, Long> {
	@Query(value = "select * from user_profile where data ->> 'username' = ?1 limit 1", nativeQuery = true)
	UserProfileEntity getUserProfileByUserName(String userName);

	@Query(value = "select * from user_profile where data ->> 'username' = ?1 and data ->> 'distinguishedName' = ?2 limit 1")
	UserProfileEntity getUserProfileByUserNameAndDn(String userName, String dn);

	@Transactional
	@Modifying
	@Query(value = "delete from user_profile where data ->> 'username' = ?1", nativeQuery = true)
	void deleteUserProfileByUserName(String userName);
}