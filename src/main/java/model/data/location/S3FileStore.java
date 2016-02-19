/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package model.data.location;

import java.io.InputStream;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model for the necessary information that is required to access a file on an
 * AWS S3 bucket share.
 * 
 * @author Patrick.Doody
 * 
 */
public class S3FileStore implements FileLocation {
	public static final String type = "s3";
	public String bucketName;
	public String fileName;
	public String domainName;

	public S3FileStore() {

	}

	public S3FileStore(String bucketName, String fileName, String domainName) {
		this.bucketName = bucketName;
		this.fileName = fileName;
		this.domainName = domainName;
	}

	public String getType() {
		return type;
	}

	/**
	 * Gets the input stream for this S3 file store. This will stream the bytes
	 * from S3 and return them for utilization. Null, or exception will be
	 * thrown if an error occurs during acquisition.
	 * 
	 * The S3 Credentials MUST be populated using the setCredentials() method
	 * before executing this call, or a Credentials exception is likely to be
	 * thrown by S3.
	 */
	@JsonIgnore
	public InputStream getFile(String accessKey, String privateKey) {
		// Get the file from S3
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, privateKey);
		AmazonS3 client = new AmazonS3Client(credentials);
		S3Object s3Object = client.getObject(bucketName, fileName);
		return s3Object.getObjectContent();
	}
}
