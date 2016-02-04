package model.request;

import model.job.PiazzaJobType;

/**
 * The core format for a Job request into the Piazza system. Every Job request
 * contains an API Key. This is used to authenticate the user and ensure they
 * are authorized to execute the specified Job.
 * 
 * The PiazzaJobType is the interface container that is used to define the
 * format for every Job that the Piazza Gateway is capable of handling.
 * 
 * @author Patrick.Doody
 * 
 */
public class PiazzaJobRequest {
	public String apiKey;
	public PiazzaJobType jobType;
}
