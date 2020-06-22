#!/bin/bash

# TODO: setup selenium grid
# TODO: Turn this into a docker-compose

driver=chrome
# driver=firefox
mkdir -p /tmp/prtestdocker.${driver}
cp ../pestroutesjava/automation/pom.xml /tmp/prtestdocker.${driver}
cp Dockerfile.${driver} /tmp/prtestdocker.${driver}/Dockerfile
rootdir=$(git rev-parse --show-toplevel)
docker build -t prmaven${driver} /tmp/prtestdocker.${driver} || exit 1
docker run -it --rm -v /dev/shm:/dev/shm -e START_XVFB=false -v ${rootdir}:/src -w /src/pestroutesjava/automation prmaven${driver} mvn test -Dsurefire.suiteXmlFiles=Tests/Smoke.xml

