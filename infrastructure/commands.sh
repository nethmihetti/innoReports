#!/usr/bin/env bash

git clone -b master git@github.com:nethmihetti/innoReports --depth=1

cd innoReports

docker build --no-cache -f infrastructure/Dockerfile -t innoreports .

docker run -e -d CLASSIFICATION_SERVICE_URL=http://10.90.138.222:8081 \
-e PERSISTENCE_SERVICE_URL=http://10.90.138.222:5000 \
-p 8080:8080 innoreports:latest