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
package org.venice.piazza.common.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import model.service.async.AsyncServiceInstance;

/**
 * Entity class for Asynchronous Service Instances
 * 
 * @author Patrick.Doody
 *
 */
@Entity
@Table(name = "AsyncServiceInstance")
public class AsyncServiceInstanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "data")
	@Type(type = "AsyncServiceInstanceType")
	private AsyncServiceInstance asyncServiceInstance;

	public AsyncServiceInstanceEntity() {
		//public constructor sonar report finding
	}

	public AsyncServiceInstanceEntity(AsyncServiceInstance asyncServiceInstance) {
		this.asyncServiceInstance = asyncServiceInstance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AsyncServiceInstance getAsyncServiceInstance() {
		return asyncServiceInstance;
	}

	public void setAsyncServiceInstance(AsyncServiceInstance asyncServiceInstance) {
		this.asyncServiceInstance = asyncServiceInstance;
	}

}
