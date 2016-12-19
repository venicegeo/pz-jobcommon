# Common Job Java API/Models

Common Java models and factories shared between the Java components. 

## Nexus Maven Repository

The artifact for this project is deployed to the Nexus Maven repository. 

Before updating the POM files, you will need to provide credentials to the Nexus Repository. In order to do this, you must edit the `settings.xml` file located in your Maven home directory (such as `%M2_HOME%/conf/settings.xml`). Under the `<servers>` tag in this file, add the following `<server>` block.

```
<servers>
 <server>
  <id>nexus</id>
  <username>[LDAP Username]</username>
  <password>[LDAP Password]</password>
 </server>
</servers>
```

Now you will be able to edit the pom.xml for the project you wish to add this project as a dependency. First, update the `<repositories>` and point to the Nexus repository:

```
	<repository>
		<id>nexus</id>
		<url>https://nexus.devops.geointservices.io/content/repositories/Piazza/</url>
	</repository>
```

Now, you will be able to add the artifact for this project:

```
	<dependency>
		<groupId>io.piazzageo</groupId>
		<artifactId>pz-jobcommon</artifactId>
		<version>{version_number_here}</version>
	</dependency>
```

## Jackson Models

The models in this library are fully annotated using [Jackson ](https://github.com/FasterXML/jackson) in order to support serialization of the Models to and from JSON. This is used in support of the [MongoJack](https://github.com/mongojack/mongojack) library that uses Jackson to allow Models to interact directly with MongoDB.
