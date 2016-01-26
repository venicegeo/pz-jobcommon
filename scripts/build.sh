#!/bin/bash

pushd `dirname $0` > /dev/null

base=$(pwd -P)
popd > /dev/null
# Build Spring-boot JAR
mvn clean package
# Path to output JAR
jarfile=$base/../target/pz-jobcommon-1.0.0.BUILD-SNAPSHOT.jar

