#!/bin/bash

# Configuration
LINK_JAVA=https://d3pxv6yz143wms.cloudfront.net/11.0.3.7.1/amazon-corretto-11.0.3.7.1-linux-x64.tar.gz
LINK_MAVEN=https://www-eu.apache.org/dist/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz

# Installation

DEV_ENV_BASE_DIR=$PWD/.dev_env
mkdir -p $DEV_ENV_BASE_DIR

# install java
if [ ! -d $DEV_ENV_BASE_DIR/java ]; then
  wget -O $DEV_ENV_BASE_DIR/java.tar.gz $LINK_JAVA
  mkdir $DEV_ENV_BASE_DIR/java
  tar -xvf $DEV_ENV_BASE_DIR/java.tar.gz --strip-components=1 --directory=$DEV_ENV_BASE_DIR/java
fi

JAVA_HOME=$DEV_ENV_BASE_DIR/java
PATH=$JAVA_HOME/bin:$PATH

java -version

# install maven
if [ ! -d $DEV_ENV_BASE_DIR/maven ]; then
  wget -O $DEV_ENV_BASE_DIR/maven.tar.gz $LINK_MAVEN
  mkdir $DEV_ENV_BASE_DIR/maven
  tar -xvf $DEV_ENV_BASE_DIR/maven.tar.gz --strip-components=1 --directory=$DEV_ENV_BASE_DIR/maven
fi

MAVEN_HOME=$DEV_ENV_BASE_DIR/maven
PATH=$MAVEN_HOME/bin:$PATH

mvn --version
