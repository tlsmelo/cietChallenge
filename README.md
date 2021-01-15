# CI&T Challenge

Framework:
- IntelliJ Idea Community 2020.1
- Browser Chrome 87.0.4280.141 (64 bits)
- JDK 13
- Maven projects info in the pom.xml

Before execute:

- In Intellij go to Run - Edit Configurations - Cucumber Java - VM Options
```
-Dwebdriver.chrome.driver=src/test/drivers/chromedriver.exe -Dwebdriver=chrome
```