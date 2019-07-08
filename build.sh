#!/bin/bash

. ./setup_dev_env.sh

mvn clean install

# remove
docker network disconnect rf_network my-home-backend
docker stop my-home-backend
docker rm -v my-home-backend
docker rmi my-home-backend

# build
docker build -t my-home-backend Docker/.

# start
docker run -d --volume=$PWD/target/my-home-backend-webapp.jar:/maven --name my-home-backend my-home-backend
docker network connect rf_network my-home-backend
