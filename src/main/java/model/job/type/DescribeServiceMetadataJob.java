package model.job.type;

import model.job.PiazzaJobType;

public class DescribeServiceMetadataJob implements PiazzaJobType {

		public String jobId = null;
		public final String type = "read-service";
		public String serviceID;
		
		public DescribeServiceMetadataJob() {

		}

		public DescribeServiceMetadataJob(String jobId) {
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

