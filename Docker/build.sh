#!/bin/bash

cd "$(dirname "$0")"

# remove
docker network disconnect rf_network my-home-backend
docker stop my-home-backend
docker rm -v my-home-backend
docker rmi my-home-backend

# build
docker build -t my-home-backend .

# start
docker run -d --volume=/home/rfaller/build_temp/maven:/maven --name my-home-backend my-home-backend
docker network connect rf_network my-home-backend
