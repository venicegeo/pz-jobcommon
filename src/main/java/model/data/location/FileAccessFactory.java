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

import java.io.File;
import java.io.InputStream;

/**
 * Factory class that helps with the obtaining of Files represented by
 * FileLocation interfaces; such as S3 files or folder shares.
 * 
 * @author Patrick.Doody
 * 
 */
public class FileAccessFactory {
	private String s3AccessKey;
	private String s3PrivateKey;

	private final String PROTOCOL_PREFIX = "https://";
	
	public FileAccessFactory() {

	}

	public FileAccessFactory(String s3AccessKey, String s3PrivateKey) {
		this.s3AccessKey = s3AccessKey;
		this.s3PrivateKey = s3PrivateKey;
	}

	/**
	 * Sets the AWS Credentials, required for pulling S3 files.
	 * 
	 * @param accessKey
	 *            Access key
	 * @param privateKey
	 *            Private key
	 */
	public void setS3Credentials(String s3AccessKey, String s3PrivateKey) {
		this.s3AccessKey = s3AccessKey;
		this.s3PrivateKey = s3PrivateKey;
	}

	/**
	 * Returns the InputStream for a file represented by an object implementing
	 * the FileLocation interface
	 * 
	 * @param fileLocation
	 *            The file location
	 * @return The File stream
	 */
	public InputStream getFile(FileLocation fileLocation) throws Exception {
		if (fileLocation instanceof FolderShare) {
			return ((FolderShare) fileLocation).getFile();
		} else if (fileLocation instanceof S3FileStore) {
			return ((S3FileStore) fileLocation).getFile(s3AccessKey, s3PrivateKey);
		} else {
			throw new Exception("Unsupported Object type.");
		}
	}


	/**
	 * Returns the URI for a file represented by an object implementing
	 * the FileLocation interface
	 * 
	 * @param fileLocation
	 *            The file location
	 * @return The File URI
	 */
	public String getFileUri(FileLocation fileLocation) throws Exception {
		if (fileLocation instanceof FolderShare) {
			return ((FolderShare) fileLocation).getFilePath();
		} else if (fileLocation instanceof S3FileStore) {
			S3FileStore s3FileStore = ((S3FileStore) fileLocation);
			return String.format("%s%s/%s/%s", PROTOCOL_PREFIX, s3FileStore.getDomainName(), s3FileStore.getBucketName(), s3FileStore.getFileName());
		} else {
			throw new Exception("Unsupported Object type.");
		}
	}
	
}
