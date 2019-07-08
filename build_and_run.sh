#!/bin/bash

. ./setup_dev_env.sh

mvn clean install -DskipTests=true

# remove
docker network disconnect rf_network my-home-backend
docker stop my-home-backend
docker rm -v my-home-backend
#docker rmi my-home-backend

# build
docker build -t my-home-backend --file Docker/Dockerfile .

# start
docker run -d --network="rf_network" --name my-home-backend my-home-backend
