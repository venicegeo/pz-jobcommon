package model.job.type;

import model.job.PiazzaJobType;

public class ExecuteServiceJob implements PiazzaJobType {

		public String jobId = null;
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