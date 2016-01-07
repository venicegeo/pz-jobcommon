package model.job.type;

import model.job.PiazzaJobType;
import model.job.metadata.ResourceMetadata;

public class RegisterServiceJob implements PiazzaJobType {

		public String jobId = null;
		public final String type = "register-service";
		public ResourceMetadata metadata;

		
		public RegisterServiceJob() {

		}

		public RegisterServiceJob(String jobId) {
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

