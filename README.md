# Common Job Java API/Models

The Job Common (or Java Common) project is a Java library that contains various useful utility classes and serializable models that are used commonly throughout the Java applications in the Piazza Core.

## Jackson Models

The models in this library are fully annotated using [Jackson ](https://github.com/FasterXML/jackson) in order to support serialization of the Models to and from JSON. 

***
## Requirements
Before building and running the pz-jobcommon project, please ensure that the following components are available and/or installed, as necessary:
- [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (JDK for building/developing, otherwise JRE is fine)
- [Maven (v3 or later)](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) (for checking out repository source)
- [Eclipse](https://www.eclipse.org/downloads/), or any maven-supported IDE
- Access to Nexus is required to build

Ensure that the nexus url environment variable `ARTIFACT_STORAGE_URL` is set:

	$ export ARTIFACT_STORAGE_URL={Artifact Storage URL}

For additional details on prerequisites, please refer to the Piazza Developer's Guide [Core Overview](https://github.com/venicegeo/pz-docs/blob/master/documents/devguide/02-pz-core.md) or [Piazza Job Common](https://github.com/venicegeo/pz-docs/blob/master/documents/devguide/16-job-common.md) sections. Also refer to the [prerequisites for using Piazza](hhttps://github.com/venicegeo/pz-docs/blob/master/documents/devguide/03-jobs.md) section for additional details.

***
## Setup, Configuring & Building

### Setup

Create the directory the repository must live in, and clone the git repository:

    $ mkdir -p {PROJECT_DIR}/src/github.com/venicegeo
	$ cd {PROJECT_DIR}/src/github.com/venicegeo
    $ git clone git@github.com:venicegeo/pz-jobcommon.git
    $ cd pz-jobcommon

>__Note:__ In the above commands, replace {PROJECT_DIR} with the local directory path for where the project source is to be installed.

### Configuring

For details on configuring the job common project, refer to the [Piazza job common](http://pz-docs.int.dev.east.paas.geointservices.io/devguide/16-job-common/) documentation.

### Building

To build and run the job common library locally, pz-jobcommon can be run using Eclipse any maven-supported IDE. Alternatively, pz-jobcommon can be run through command line interface (CLI), by navigating to the project directory and run:

    $ mvn clean install

### Running Unit Tests

To run the job common library unit tests from the main directory, run the following command:

	$ mvn test

