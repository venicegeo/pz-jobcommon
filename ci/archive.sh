#!/bin/bash -ex

pushd `dirname $0`/.. > /dev/null
root=$(pwd -P)
popd > /dev/null

# gather some data about the repo
source $root/ci/vars.sh

# Path to output JAR
src=$root/target/pz-jobcommon-1.0.0.BUILD-SNAPSHOT.jar

# Build Spring-boot JAR
[ -f $src ] || mvn clean package -U

# stage the artifact for a mvn deploy
mv $src $root/$APP.$EXT
