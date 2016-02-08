package model.data.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Common interface for representing Resource locations. File resources
 * referenced by the Piazza system can reside in a variety of locations
 * accessible by the Piazza system; such as S3 Buckets, or internal folder
 * shares accessible to internal components, or perhaps extensible to something
 * like an FTP site.
 * 
 * This interface is used by implementations of a specific file location that
 * describe the appropriate parameters required to connect to that
 * implementation.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = S3FileStore.class, name = "list-services3"),
		@Type(value = FolderShare.class, name = "share") })
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface FileLocation {
	public String getType();
}
