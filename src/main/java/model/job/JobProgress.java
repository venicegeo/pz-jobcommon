package model.job;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the progress of a Job. Job Workers can fire update to the progress
 * of a Job throughout Job Processing to keep users and other components current
 * with the processing.
 * 
 * @author Patrick.Doody
 * 
 */
@JsonInclude(Include.NON_NULL)
public class JobProgress {
	public Integer percentComplete;
	public String timeRemaining;
	public String timeSpent;

	public JobProgress() {
	}

	public JobProgress(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}

	public Integer getPercentComplete() {
		return percentComplete;
	}
}
