package model.job.type;

import model.job.PiazzaJobType;

public class ReadServiceMetadataJob implements PiazzaJobType {

		public String jobId = null;
		public final String type = "read-service";
		public String serviceID;
		
		public ReadServiceMetadataJob() {

		}

		public ReadServiceMetadataJob(String jobId) {
			this.jobId = jobId;
		}


		public String getJobId() {
			return jobId;
		}

		public void setJobId(String jobId) {
			this.jobId = jobId;
		}

		public String getType() {
			
			return type;
		}

	}

