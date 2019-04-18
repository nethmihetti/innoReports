#!/usr/bin/env bash

git clone -b base-project git@github.com:nethmihetti/innoReports --depth=1

cd innoReports

docker build --no-cache -f infrastructure/Dockerfile -t sometag .

sudo docker run -p 8080:8080 sometag:latest
