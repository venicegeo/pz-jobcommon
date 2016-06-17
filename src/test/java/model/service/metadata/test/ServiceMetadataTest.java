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
package model.service.metadata.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import model.data.type.TextDataType;
import model.service.metadata.Format;
import model.service.metadata.MetadataType;
import model.service.metadata.ParamDataItem;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the models in the service metadata package
 * 
 * @author Patrick.Doody
 *
 */
public class ServiceMetadataTest {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tests Format
	 */
	@Test
	public void testFormat() throws IOException {
		Format format = new Format();
		format.setDataType("geojson");
		format.setEncoding("UTF8");
		format.setMaximumMegabytes(5);
		format.setMimeType("application/json");
		format.setSchema("Test");

		String serialized = mapper.writeValueAsString(format);
		Format output = mapper.readValue(serialized, Format.class);

		assertTrue(output.getDataType().equals(format.getDataType()));
		assertTrue(output.getEncoding().equals(format.getEncoding()));
		assertTrue(output.getMaximumMegabytes().equals(format.getMaximumMegabytes()));
		assertTrue(output.getMimeType().equals(format.getMimeType()));
	}

	/**
	 * Test metadata type
	 */
	@Test
	public void testMetadata() throws IOException {
		MetadataType input = new MetadataType();
		input.setAbout("Test");
		input.setHref("localhost:8080");
		input.setRole("User");
		input.setTitle("Test");

		String serialized = mapper.writeValueAsString(input);
		MetadataType output = mapper.readValue(serialized, MetadataType.class);

		assertTrue(output.getAbout().equals(input.getAbout()));
		assertTrue(output.getHref().equals(input.getHref()));
		assertTrue(output.getRole().equals(input.getRole()));
		assertTrue(output.getTitle().equals(input.getTitle()));
	}

	/**
	 * Test Param Data Item
	 */
	@Test
	public void testParamDataItem() throws IOException {
		ParamDataItem input = new ParamDataItem();
		input.setDataType(new TextDataType());
		input.setFormats(new ArrayList<Format>());
		input.setMaxOccurs(5);
		input.setMinOccurs(0);
		input.setName("Test");

		String serialized = mapper.writeValueAsString(input);
		ParamDataItem output = mapper.readValue(serialized, ParamDataItem.class);

		assertTrue(output.getDataType().getType().equals(input.getDataType().getType()));
		assertTrue(output.getMinOccurs().equals(input.getMinOccurs()));
		assertTrue(output.getMaxOccurs().equals(input.getMaxOccurs()));
		assertTrue(output.getName().equals(input.getName()));
		assertTrue(output.getFormats().size() == 0);
	}
}
