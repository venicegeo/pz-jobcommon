package model.job.type;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.job.PiazzaJobType;

public class ExecuteServiceJob implements PiazzaJobType {

		public String jobId = null;
		@JsonProperty("dataInputs")
		public Map<String, String> dataInputs;	
		public String dataInput;
		public String resourceId;
		public final String type = "execute-service";
		
		public Map<String, String> getDataInputs() {
			return dataInputs;
		}

		public void setDataInputs(Map<String, String> dataInputs) {
			this.dataInputs = dataInputs;
		}

		public String getDataInput() {
			return dataInput;
		}

		public void setDataInput(String dataInput) {
			this.dataInput = dataInput;
		}

		public String getResourceId() {
			return resourceId;
		}

		public void setResourceId(String resourceId) {
			this.resourceId = resourceId;
		}		
		
		public ExecuteServiceJob() {

		}

		public ExecuteServiceJob(String jobId) {
			this.jobId = jobId;
		}

		public String getType() {
			return type;
		}

		public String getJobId() {
			return jobId;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}

	}