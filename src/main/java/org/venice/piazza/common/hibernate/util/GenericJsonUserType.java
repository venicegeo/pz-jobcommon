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
package org.venice.piazza.common.hibernate.util;

import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A generic UserType class that populates the common stubs when defining a Hibernate custom UserType.
 * 
 * This provides a reliable way to put all the common implementations of UserType in this single class, while allowing
 * the overriding child classes to handle entity-specific serialization logic (if any)
 * 
 * @author Patrick.Doody
 *
 */
public abstract class GenericJsonUserType implements UserType {
	public int[] sqlTypes() {
		return new int[] { Types.JAVA_OBJECT };
	}

	/**
	 *
	 * @param resultSet
	 * @param names
	 * @param session
	 * @param owner
	 * @return
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public Object nullSafeGet(final ResultSet resultSet, final String[] names, final SessionImplementor session, final Object owner)
			throws SQLException {
		String jsonFromDatabase = resultSet.getString(names[0]);
		if (jsonFromDatabase == null) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonFromDatabase.getBytes("UTF-8"), returnedClass());
		} catch (Exception exception) {
			//sonar asking for a dedicated exception to be thrown
			throw new SQLException(String.format("Failed to deserialize JSON Object to POJO from Database: %s", exception.getMessage()),
					exception);
		}
	}

	/**
	 *
	 * @param preparedStatement
	 * @param value
	 * @param index
	 * @param session
	 * @throws HibernateException
	 * @throws SQLException
	 */
	public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index,
			final SessionImplementor session) throws SQLException {
		if (value == null) {
			preparedStatement.setNull(index, Types.OTHER);
			return;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			StringWriter writer = new StringWriter();
			objectMapper.writeValue(writer, value);
			writer.flush();
			preparedStatement.setObject(index, writer.toString(), Types.OTHER);
		} catch (Exception exception) {
			//sonar asking for a dedicated exception to be thrown
			throw new SQLException(String.format("Failed to serialize Model Object from Database to JSON: %s", exception.getMessage()),
					exception);
		}
	}

	/**
	 *
	 * @param value
	 * @return
	 * @throws HibernateException
	 */
	public Object deepCopy(final Object value)  {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String clonedString = objectMapper.writeValueAsString(value);
			return objectMapper.readValue(clonedString, returnedClass());
		} catch (Exception exception) {
			throw new HibernateException(exception);
		}
	}

	public boolean isMutable() {
		return true;
	}

	/**
	 *
	 * @param value
	 * @return
	 * @throws HibernateException
	 */
	public Serializable disassemble(final Object value) {
		return (Serializable) this.deepCopy(value);
	}

	/**
	 *
	 * @param cached
	 * @param owner
	 * @return
	 * @throws HibernateException
	 */
	public Object assemble(final Serializable cached, final Object owner) {
		return this.deepCopy(cached);
	}

	/**
	 *
	 * @param original
	 * @param target
	 * @param owner
	 * @return
	 * @throws HibernateException
	 */
	public Object replace(final Object original, final Object target, final Object owner) {
		return this.deepCopy(original);
	}

	/**
	 *
	 * @param left
	 * @param right
	 * @return
	 * @throws HibernateException
	 */
	public boolean equals(final Object left, final Object right) {
		if (left == null) {
			return right == null;
		}
		return left.equals(right);
	}

	/**
	 *
	 * @param obj
	 * @return
	 * @throws HibernateException
	 */
	public int hashCode(final Object obj) {
		return obj.hashCode();
	}
}
