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
package hibernate.util;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

/**
 * Extends the PostgresQL Dialect to add a reference to the jsonb postgresql data type; which Hibernate otherwise would
 * not have any knowledge of.
 * 
 * @author Patrick.Doody
 *
 */
public class JsonPostgreSQL94Dialect extends PostgreSQL94Dialect {

	public JsonPostgreSQL94Dialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}