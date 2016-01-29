# Common Job Java API/Models

Common Java models and factories shared between the Gateway/Dispatcher/JobManager components. 

## Maven POM Dependency

This project is deployed to the Piazza S3 Maven repository. For projects that wish to use this library, the POM files for that project must reference the Maven S3 Wagon extension in order to pull artifacts from S3.

First, update the `<repositories>` and point to the desired s3 repository (snapshot/release/private):

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
