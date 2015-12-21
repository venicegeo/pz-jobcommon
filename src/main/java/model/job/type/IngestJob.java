package model.job.type;

import model.job.PiazzaJobType;

public class IngestJob implements PiazzaJobType {
	public final String type = "ingest";

	public IngestJob() {

	}

	public String getType() {
		return type;
	}
}
