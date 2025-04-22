@echo off
REM Compilation script for JavaFX 24.0.1 on Windows

REM Set the source directory containing Java files
set "SRC_DIR=%~dp0"

REM Set the path to the JavaFX 24.0.1 SDK lib directory (update this path accordingly)
set "JAVA_FX_LIB_PATH=E:\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib"

echo Source directory: %SRC_DIR%
echo JavaFX SDK lib path: %JAVA_FX_LIB_PATH%

REM Recursively compile all Java files in SRC_DIR with JavaFX modules
for /r "%SRC_DIR%" %%f in (*.java) do (
    echo Compiling %%f
    javac --module-path "%JAVA_FX_LIB_PATH%" --add-modules javafx.controls,javafx.fxml -Xlint:all -Xdiags:verbose "%%f"
)

REM Update configuration file similar to the bash script
echo localRootDirectory=file://%SRC_DIR% > "%SRC_DIR%application\configuration\config.properties"

echo Compilation complete.
pause
