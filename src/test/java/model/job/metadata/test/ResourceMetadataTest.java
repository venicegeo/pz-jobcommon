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
package model.job.metadata.test;

import static org.junit.Assert.assertTrue;
import model.job.metadata.ResourceMetadata;

import org.junit.Test;

/**
 * Tests the ResourceMetadata model. Primarily tests the merging capabilities to
 * ensure correctness of merging.
 * 
 * @author Patrick.Doody
 *
 */
public class ResourceMetadataTest {

	/**
	 * Tests the merging capabilities.
	 */
	@Test
	public void testMerges() {
		// Create an original object with some standard properties
		ResourceMetadata original = new ResourceMetadata();
		original.setName("United States");
		original.setDescription("This is a map of the United States.");
		original.setFormat("Raster");

		// Check accessors
		assertTrue(original.getName().equals("United States"));
		assertTrue(original.getDescription().equals("This is a map of the United States."));

		// Create an object to merge.
		ResourceMetadata toMerge = new ResourceMetadata();
		toMerge.setName("North America");
		toMerge.setDescription("This is a map of North America.");

		// Check accessors
		assertTrue(toMerge.getName().equals("North America"));
		assertTrue(toMerge.getDescription().equals("This is a map of North America."));

		// Merge the two. Do not overwrite null values.
		original.merge(toMerge, false);

		// Verify the merge was successful
		assertTrue(original.getName().equals("North America"));
		assertTrue(original.getDescription().equals("This is a map of North America."));
		assertTrue(original.getFormat().equals("Raster"));

		// Merge again, but overwrite null values this time.
		original.merge(toMerge, true);

		// Verify
		assertTrue(original.getName().equals("North America"));
		assertTrue(original.getDescription().equals("This is a map of North America."));
		assertTrue(original.getFormat() == null);
	}
}
