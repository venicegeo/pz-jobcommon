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
package hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import model.security.authz.UserThrottles;

/**
 * Entity class for User Throttles
 * 
 * @author Patrick.Doody
 *
 */
@Entity
@Table(name = "UserThrottles")
public class UserThrottlesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "data")
	@Type(type = "UserThrottlesType")
	private UserThrottles userThrottles;

	public UserThrottlesEntity() {

	}

	public UserThrottlesEntity(UserThrottles userThrottles) {
		this.userThrottles = userThrottles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserThrottles getUserThrottles() {
		return userThrottles;
	}

	public void setUserThrottles(UserThrottles userThrottles) {
		this.userThrottles = userThrottles;
	}

}
