package model.data;

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
		@Type(value = PostGISResource.class, name = "postgis") })
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface DataType {
	public String getType();
}
