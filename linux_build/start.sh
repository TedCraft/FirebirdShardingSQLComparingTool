#!/bin/sh
cd ..

java -jar -Dfile.encoding=windows-1251 -jar target/SQLStandComparator-1.0-SNAPSHOT.jar

exec $SHELL