package model.job;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Job {
	public String jobId;
	public PiazzaJob jobType;
	public DateTime submitted;
	public String submitter;
	public String status;
	public JobProgress progress = new JobProgress();
	public List<JobProgress> history = new ArrayList<JobProgress>();
	public Object result = null; // TODO: How to represent this model?
}
