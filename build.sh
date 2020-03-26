#!/bin/sh
#JAVA_HOME=
MAIN_CLASS=crawling/entertainment/feed_the_sanke/Launcher.java
SOURCE_PATH=src/main
RESOURCE_PATH=src/main/resources
if [ -f "$JAVA_HOME/bin/javac" ]
then
rm -rf target
mkdir target
"$JAVA_HOME/bin/javac" -d target -sourcepath $SOURCE_PATH $SOURCE_PATH/$MAIN_CLASS
cp -R $RESOURCE_PATH/* target
else
echo Please set JAVA_HOME to proper JDK root folder.
fi
