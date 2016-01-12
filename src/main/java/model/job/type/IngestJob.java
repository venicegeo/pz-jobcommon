package model.job.type;

import model.job.PiazzaJobType;
import model.job.metadata.ResourceMetadata;

public class IngestJob implements PiazzaJobType {
	public final String type = "ingest";
	public ResourceMetadata data;

	public IngestJob() {

	}

	public String getType() {
		return type;
	}
}
