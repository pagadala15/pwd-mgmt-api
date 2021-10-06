#!/usr/bin/env bash

docker build -t pwd-mgmt-api .

docker-compose up -d
