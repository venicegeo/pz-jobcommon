#!/bin/bash -ex

mvn clean package

# Path to output JAR
jarfile=./target/pz-jobcommon-1.0.0.BUILD-SNAPSHOT.jar
