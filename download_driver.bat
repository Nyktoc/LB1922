@echo off
echo Скачивание ChromeDriver...
cd C:\temp\selenium
curl -L -o chromedriver.zip https://storage.googleapis.com/chrome-for-testing-public/145.0.7632.117/win64/chromedriver-win64.zip
echo Распаковка...
tar -xf chromedriver.zip
copy chromedriver-win64\chromedriver.exe chromedriver.exe
echo Готово!
pause