package model.job.type;

import model.data.DataResource;
import model.job.PiazzaJobType;

/**
 * Represents the JSON Model for ingesting data into Piazza via the Ingest/Load
 * component. The contents of this payload should include all information that
 * Piazza needs in order to fetch the data, inspect for metadata, and to copy
 * into Piazza data holdings if necessary.
 * 
 * @author Patrick.Doody
 * 
 */
public class IngestJob implements PiazzaJobType {
	public final String type = "ingest";
	public DataResource data;
	public Boolean host;

	public IngestJob() {

	}

	public String getType() {
		return type;
	}

	public DataResource getData() {
		return data;
	}

	/**
	 * Determines if the Data should be copied into Piazza data holdings and
	 * stored internally. True if the data should be hosted in Piazza, false if
	 * the data should remain where it currently is.
	 * 
	 * If data is not to be hosted within Piazza, then direct file access may be
	 * required to be able to inspect metadata, and to access the file upon user
	 * request from within other Piazza Jobs.
	 * 
	 * @return True if the Data should be hosted in Piazza, false if not.
	 */
	public Boolean getHost() {
		return host;
	}
}
