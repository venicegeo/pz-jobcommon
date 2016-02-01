# Common Job Java API/Models

Common Java models and factories shared between the Gateway/Dispatcher/JobManager components. 

## Maven POM Dependency

This project is deployed to the Piazza S3 Maven repository. For projects that wish to use this library, the POM files for that project must reference the Maven S3 Wagon extension in order to pull artifacts from S3. The reference for the M3 Wagon used for S3 deployment is located [here](https://github.com/jcaddel/maven-s3-wagon/wiki/Usage). 

Before updating the POM files, you will need to provide credentials to the AWS S3 Bucket. In order to do this, you must edit the `settings.xml` file located in your Maven home directory (such as `%M2_HOME%/conf/settings.xml`). Under the `<servers>` tag in this file, add the following `<server>` blocks.

```
<servers>
 <server>
  <id>s3.site</id>
  <username>[AWS Access Key ID]</username>
  <password>[AWS Secret Access Key]</password>
 </server>
 <server>
  <id>s3.release</id>
  <username>[AWS Access Key ID]</username>
  <password>[AWS Secret Access Key]</password>
 </server>
 <server>
  <id>s3.snapshot</id>
  <username>[AWS Access Key ID]</username>
  <password>[AWS Secret Access Key]</password>
 </server>
</servers>
```

Now you will be able to edit the pom.xml for the project you wish to add this project as a dependency. First, update the `<repositories>` and point to the desired s3 repository (snapshot/release/private):

```
	<repositories>
		<repository>
			<id>s3.snapshot</id>
			<url>s3://venice-maven-private/snapshot</url>
		</repository>
	</repositories>
```

Next, add the extension that allows for connecting to S3 repositories:

```
	<build>
		<extensions>
			<extension>
				<groupId>org.kuali.maven.wagons</groupId>
				<artifactId>maven-s3-wagon</artifactId>
				<version>1.2.1</version>
			</extension>
		</extensions>
	</build>
```

Now, you will be able to add the artifact for this project:

```
	<dependency>
		<groupId>org.venice.piazza</groupId>
		<artifactId>pz-jobcommon</artifactId>
		<version>1.0.0.BUILD-SNAPSHOT</version>
	</dependency>
```

## Deploying to S3

This project is deployed to S3 as part of the Jenkins build target. Deployment is handled by the `mvn deploy` target. Running this target will push the artifacts to the S3 repostory.

## Jackson Models

The models in this library are fully annotated using [Jackson ](https://github.com/FasterXML/jackson) in order to support serialization of the Models to and from JSON. This is used in support of the [MongoJack](https://github.com/mongojack/mongojack) library that uses Jackson to allow Models to interact directly with MongoDB.
