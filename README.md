This repository contains automated test scripts developed using Selenium WebDriver and TestNG for testing the https://www.entrata.com/ .
These tests aim to ensure the functionality and reliability of the application.

Prerequisites:
Before running the tests, ensure that the following prerequisites are met:
Java Development Kit (JDK) installed (JDK 17.0).
Maven installed.
IDE for Java development (e.g.Eclipse).

Setup Instructions:
Clone this repository to your local machine.
Open the project in your preferred IDE.
Ensure that Maven dependencies are resolved.

Configuration:
The test data and configuration settings can be modified in the config.properties file located in the src/test/resources directory.

Running the Tests From IDE:
Open the test: src/test/java/entrata/NewTest.java.
Right-click on the file and select "Run As TestNG Test" or "Run As Maven Verify".

Test Reports:
TestNG generates test reports in the test-output directory after test execution.
Open the index.html file in a web browser to view the test execution results.

Contributing:
If you find any issues or have suggestions for improvement, feel free to open an issue or create a pull request.

TestCases Automated:
TC01 : Validate 'Do it all with Entrata' tiles headers and description on home page.
TC02 : Validate user is able to navigate to base camp registration page.
TC03 : Validate error if username and passowrd is empty while login for Product Manager.
TC04 : Validate All Solutions list on home page.
