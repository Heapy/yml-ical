#!/bin/bash

export BINTRAY_USER="irus"
export BINTRAY_API_KEY=""

./gradlew clean javadocJar sourcesJar assemble bintrayUpload
