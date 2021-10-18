
@echo off 
    setlocal enableextensions disabledelayedexpansion

@REM replace subdomain
    set "search=stagingdemo"
    set "replace=%1"

    set "textFile=src\test\resources\properties\env.properties"

    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )

@REM replace username
    set "search=mind"
    set "replace=%2"

    set "textFile=src\test\resources\properties\env.properties"

    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )

@REM replace password
    set "search=matter"
    set "replace=%3"

    set "textFile=src\test\resources\properties\env.properties"

    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )
@REM replace feature
    set "search=TestFeature"
    set "replace=%4"

    set "textFile=src\test\java\automation\PestRoutes\TestRunner\Feature.java"

    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )