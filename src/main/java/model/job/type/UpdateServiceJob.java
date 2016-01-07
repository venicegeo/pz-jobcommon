package model.job.type;

import model.job.PiazzaJobType;

public class UpdateServiceJob implements PiazzaJobType {

		public String jobId = null;
		public final String type = "update-service";
		
		public UpdateServiceJob() {

		}

		public UpdateServiceJob(String jobId) {
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

