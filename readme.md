# Web & API Test Automation
 Features of the Framework
-----
-  Use of 'Page Object Model' as Design pattern
-  Allure Reports with graphs and test execution details
-  Screenshots for failed tests
-  Logging
-  Reading the test data and configuration parameters from config file using `typesafe` library
-  Ability to choose browsers from Config file
-  Use of WebDriver Manager to manage the browser binaries, no need to supply the binaries anymore


Steps to Run
----
* Run the complete test suite from command line using command `mvn clean test`
* Run the test suite from `testng.xml` or individual test classes (APITest, WebTest) from the IDE of your choice e.g. Intellij or Eclipse


Test execution reports
-----
- The solution is developed to generate the Allure test results reports
- The command to generate and view the report is `allure serve allure-results` from the project directory
- It gives details like: Passed, Failed, Skipped, Total execution time, package, behaviour etc.


Technical Details 
----
  * `modules`: This package contains the class `BusinessReusable` which has all the reusable methods as per business logic.
  * `pageobject`: This package contains the `PageObject` class which contains the xpath of page web elements and the one liner methods to interact with the page elements or retrieve the text from the page elements.
  * `tools`: This package contains the helper classes i.e. `BaseTest` class, `Configuration` class, `TestListener` and `Wiremock` class
  * `APITest`: This is Test class which contains all the API tests.
  * `WebTest`: This is Test class which contains all the UI tests.
  * `default.conf`: Config file which contains the configuration parameters and test data in JSON format.
  * `pom.xml`: This XML contains Maven dependencies and plugins configuration for the project.

Dependencies
-----
* Selenium: Selenium WebDriver library for UI automation.
* TestNG: Test framework to run the automated tests.
* Typesafe: This library is used for configuration management.
* WebDriverManager: This library is used to automate the Selenium WebDriver binaries management.
* Allure-testng: This library is used to integrate Allure reports with TestNG.
* Rest-assured: This library is used to automate the REST API services.
* Wiremock: This library is used to mock the un-implemented REST API services.
* Logback: This library is used for logging.

-----