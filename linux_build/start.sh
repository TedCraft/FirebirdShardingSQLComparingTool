#!/bin/sh
cd ..

java -jar target/SQLStandComparator-1.0-SNAPSHOT.jar

exec $SHELL