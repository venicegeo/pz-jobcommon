package model.job.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Spatial metadata fields associated with a Resource.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class SpatialMetadata {
	String boundingBox;
	String spatialReference;
	
}
