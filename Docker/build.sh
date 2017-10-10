#!/bin/bash
cd "$(dirname "$0")"

# remove
docker network disconnect rf_network hsm-arduino-backend
docker stop hsm-arduino-backend
docker rm -v hsm-arduino-backend
docker rmi hsm-arduino-backend

# build
docker build -t hsm-arduino-backend .

# start
docker run -d --volume=/home/rfaller/build_temp/maven:/maven --name hsm-arduino-backend hsm-arduino-backend
docker network connect rf_network hsm-arduino-backend
