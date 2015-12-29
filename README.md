# Common Job Java API/Models

Common Java models and factories shared between the Gateway/Dispatcher/JobManager components. 

## JitPack installation

If you want to avoid having to build the library yourself, you can use JitPack to build it for you based on a GitHub snapshot:

```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
    <groupId>com.github.venicegeo</groupId>
    <artifactId>pz-jobcommon</artifactId>
    <version>undefined</version>
</dependency>
``` 

## Jackson Models

The models in this library are fully annotated using [Jackson ](https://github.com/FasterXML/jackson) in order to support serialization of the Models to and from JSON. This is used in support of the [MongoJack](https://github.com/mongojack/mongojack) library that uses Jackson to allow Models to interact directly with MongoDB.
