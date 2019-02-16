rmdir ..\build /q /s
mkdir ..\build\compiled
call node compile.js
pause