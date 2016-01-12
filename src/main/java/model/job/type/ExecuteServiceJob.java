package model.job.type;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.job.PiazzaJobType;

public class ExecuteServiceJob implements PiazzaJobType {

		public String jobId = null;
		@JsonProperty("values")
		public Map<String, String> values;		
		public String resourceId;
		
		public Map<String,String> getValues() {
			return values;
		}

		public void setValues(Map<String,String> values) {
			this.values = values;
		}

		
		public final String type = "execute-service";
		
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