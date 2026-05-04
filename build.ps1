param(
    [string]$Task = "assembleDebug"
)

Write-Host "Starting Gradle build for task: $Task"
Set-Location "c:\Users\aliso\Documents\PassGo"

# Stop any running daemons
.\gradlew.bat --stop

# Clean and build
.\gradlew.bat clean $Task --no-daemon --console=plain

Write-Host "Build completed with exit code: $LASTEXITCODE"