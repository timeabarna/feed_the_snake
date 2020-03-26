rem set JAVA_HOME=
set MAIN_CLASS=crawling.entertainment.feed_the_sanke.Launcher
if exist "%JAVA_HOME%/bin/javaw.exe" goto OK
echo JAVA_HOME is not set to a valid JDK/JRE root folder, please set it.
GOTO END
:OK
cd target
"%JAVA_HOME%/bin/javaw" %MAIN_CLASS%
cd ..
:END




