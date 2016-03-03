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
package model.data;

import model.data.type.PointCloudResource;
import model.data.type.PostGISResource;
import model.data.type.RasterResource;
import model.data.type.ShapefileResource;
import model.data.type.TextResource;
import model.data.type.WfsResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface for Spatial Resources that can be ingested and stored in Piazza.
 * For different types of spatial resources that can be represented, such as
 * Shapefiles, WFS, etc; there must be an accompanying class that implements
 * this interface and references type information for deserialization in the @JsonTypeInfo
 * annotation.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = WfsResource.class, name = "wfs"), @Type(value = TextResource.class, name = "text"),
		@Type(value = RasterResource.class, name = "raster"),
		@Type(value = ShapefileResource.class, name = "shapefile"),
		@Type(value = PostGISResource.class, name = "postgis"),
		@Type(value = PointCloudResource.class, name = "pointcloud")})
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface DataType {
	public String getType();
}
