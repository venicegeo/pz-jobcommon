package model.job;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonIgnore
	public DateTime timeRemaining;
	@JsonIgnore
	public DateTime timeSpent;

	public JobProgress() {
	}

	public JobProgress(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}

	public JobProgress(DateTime timeSpent, DateTime timeRemaining) {
		this.timeSpent = timeSpent;
		this.timeRemaining = timeRemaining;
	}

	public JobProgress(Integer percentComplete, DateTime timeSpent, DateTime timeRemaining) {
		this.percentComplete = percentComplete;
		this.timeSpent = timeSpent;
		this.timeRemaining = timeRemaining;
	}

	public Integer getPercentComplete() {
		return percentComplete;
	}

	public DateTime getTimeRemaining() {
		return timeRemaining;
	}

	public DateTime getTimeSpent() {
		return timeSpent;
	}

	@JsonProperty("timeRemaining")
	public String getTimeRemainingString() {
		// Defaults to ISO8601
		return timeRemaining.toString();
	}

	@JsonProperty("timeRemaining")
	public void setTimeRemainingString(String timeRemaining) {
		this.timeRemaining = new DateTime(timeRemaining);
	}

	@JsonProperty("timeSpent")
	public String getTimeSpentString() {
		// Defaults to ISO8601
		return timeSpent.toString();
	}

	@JsonProperty("timeSpent")
	public void setTimeSpentString(String timeSpent) {
		this.timeSpent = new DateTime(timeSpent);
	}
}
