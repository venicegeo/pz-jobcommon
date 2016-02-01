package model.job;

import model.job.type.AbortJob;
import model.job.type.AccessJob;
import model.job.type.DeleteServiceJob;
import model.job.type.ExecuteServiceJob;
import model.job.type.GetJob;
import model.job.type.GetResource;
import model.job.type.IngestJob;
import model.job.type.ListServicesJob;
import model.job.type.ReadServiceMetadataJob;
import model.job.type.RegisterServiceJob;
import model.job.type.UpdateServiceJob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = AbortJob.class, name = "abort"),
		@Type(value = DeleteServiceJob.class, name = "delete-service"),
		@Type(value = ExecuteServiceJob.class, name = "execute-service"), @Type(value = GetJob.class, name = "get"),
		@Type(value = AccessJob.class, name = "access"), @Type(value = GetResource.class, name = "get-resource"),
		@Type(value = IngestJob.class, name = "ingest"),
		@Type(value = ReadServiceMetadataJob.class, name = "read-service"),
		@Type(value = RegisterServiceJob.class, name = "register-service"),
		@Type(value = UpdateServiceJob.class, name = "update-service"),
		@Type(value = ListServicesJob.class, name = "list-service") })
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface PiazzaJobType {
	public String getType();
}
