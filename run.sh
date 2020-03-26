#!/bin/sh
#JAVA_HOME=
MAIN_CLASS=crawling.entertainment.feed_the_sanke.Launcher
if [ -f "$JAVA_HOME/bin/javaw" ]; then
cd target
"$JAVA_HOME/bin/javaw" $MAIN_CLASS
cd ..
else
echo Please set JAVA_HOME to proper JDK/JRE root folder.
fi






