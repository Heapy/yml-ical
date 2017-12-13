#!/bin/bash

# Uncomment and fill.
#export BINTRAY_USER=""
#export BINTRAY_API_KEY=""

./gradlew clean javadocJar sourcesJar assemble bintrayUpload
