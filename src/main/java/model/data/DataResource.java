package model.data;

import model.job.metadata.ResourceMetadata;
import model.job.metadata.SpatialMetadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents a piece of data referened by the Piazza system, internally or
 * externally.
 * 
 * The format and type of the data is described by the ResourceType object, and
 * has interfaces for describing various formats and types.
 * 
 * Also contains metadata containers for describing the metadata of the data
 * object.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class DataResource {
	public ResourceType resourceType;
	public SpatialMetadata spatialMetadata;
	public ResourceMetadata metadata;

	public DataResource() {

	}

	public ResourceMetadata getMetadata() {
		return metadata;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public SpatialMetadata getSpatialMetadata() {
		return spatialMetadata;
	}
}