package model.job.metadata;

public class Format {
	String mimeType;
	String encoding;
	String schema;
	Integer maximumMegabytes;
	// Represents the type returned from getType in model.data.type
	String dataType;
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public Integer getMaximumMegabytes() {
		return maximumMegabytes;
	}
	public void setMaximumMegabytes(Integer maximumMegabytes) {
		this.maximumMegabytes = maximumMegabytes;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
