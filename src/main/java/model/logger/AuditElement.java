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
package model.logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Defines AuditElement data structure used for audit logging.
 * 
 * @author Sonny.Saniev
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditElement {

	public String actor;
	public String action;
	public String actee;

	public AuditElement() {

	}

	public AuditElement(String actor, String action, String actee) {
		this.actor = actor;
		this.action = action;
		this.actee = actee;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActee() {
		return actee;
	}

	public void setActee(String actee) {
		this.actee = actee;
	}

	@Override
	public String toString() {
		StringBuilder obj = new StringBuilder();
		obj.append("actor: " + getActor() + "\n");
		obj.append("action: " + getAction() + "\n");
		obj.append("actee : " + getActee() + "\n");
		return obj.toString();
	}

	public String toSDE() {
		StringBuilder builder = new StringBuilder();

		// Form SDE
		builder.append("[");
		builder.append("pzaudit@48851");

		// Add attributes if present
		if (getActee() != null) {
			builder.append(" actee=\"" + getActee() + "\"");
		}
		if (getActee() != null) {
			builder.append(" action=\"" + getAction() + "\"");
		}
		if (getActee() != null) {
			builder.append(" actor=\"" + getActor() + "\"");
		}

		builder.append("]");

		return builder.toString();
	}
}