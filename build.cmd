rem set JAVA_HOME=
set MAIN_CLASS=crawling\entertainment\feed_the_sanke\Launcher.java
set SOURCE_PATH=src/main
set RESOURCE_PATH=src\main\resources
if exist "%JAVA_HOME%/bin/javac.exe" goto OK
echo JAVA_HOME is not set to a valid JDK root folder, please set it.
GOTO END
:OK
rmdir target /s /q
md target
"%JAVA_HOME%/bin/javac" -d target -sourcepath %SOURCE_PATH% %SOURCE_PATH%/%MAIN_CLASS%
xcopy %RESOURCE_PATH%\*.* target /e
:END



