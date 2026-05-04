@echo off
cd /d c:\Users\aliso\Documents\PassGo
echo Starting Gradle build...
gradlew.bat clean assembleDebug --stacktrace
echo Build completed with exit code %ERRORLEVEL%