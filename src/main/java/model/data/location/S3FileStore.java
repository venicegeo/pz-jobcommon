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

import io.swagger.annotations.ApiModelProperty;

/**
 * Model for the necessary information that is required to access a file on an
 * AWS S3 bucket share.
 * 
 * @author Patrick.Doody
 * 
 */
public class S3FileStore implements FileLocation {
	
	public static final String TYPE = "s3";	
	
	@ApiModelProperty(value = "The type of file location.", required = true, allowableValues = "s3")	
	public final String type = "s3";
	
	@ApiModelProperty(value = "")
	public String bucketName;

	@ApiModelProperty(value = "")
	public String fileName;
	
	@ApiModelProperty(value = "")
	public Long fileSize;
	
	@ApiModelProperty(value = "")
	public String domainName;

	public S3FileStore() {

	}

	public S3FileStore(String bucketName, String fileName, Long fileSize, String domainName) {
		this.bucketName = bucketName;
		this.fileName = fileName;
		this.domainName = domainName;
		this.fileSize = fileSize;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getType() {
		return type;
	}
}