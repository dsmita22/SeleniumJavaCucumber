# Selenium Java BDD Automation Project   
### Getting Started
```sh
$ git clone https://github.com/dsmita22/SeleniumJavaCucumber.git
$ cd SeleniumJavaSecurePay
$ import project from intelij as a gradle project
$ gradle clean
$ gradle build
$ gradle task SecurePayTests
```   
### Run Test in Docker container Selenium Grid   
```sh
$ docker-compose up -d
# in build.gradle uncomment //dockerCompose.isRequiredBy(test) and environment "grid", "YES"
```
### Built With  

| **Dependency**                                                                    | **Use**                                                          |
| --------------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| [Selenium](https://seleniumhq.org)                                                | Selenium java library to automate and perform cross browser      |
| [Cucumber](https://cucumber.io/)                                                  | Cucumber for BDD test development                                |
| [Cucumber Reporting](https://github.com/damianszczepanik/cucumber-reporting)      | Cucumber test reporting                                          |
| [JavaFaker](https://github.com/DiUS/java-faker)                                   | Fake data generator library                                      |
| [TestNg](https://testng.org/doc/)                                                 | Test runner often used for parallel test runs                    |
| [Log4j](https://logging.apache.org/log4j/2.x/)                                    | Java logging library                                             |

### Test Results
  
* Report    
<img src="https://raw.githubusercontent.com/dsmita22/SeleniumJavaSecurePay/master/docs/Report.png" width="400">   
* Feature   
<img src="https://raw.githubusercontent.com/dsmita22/SeleniumJavaSecurePay/master/docs/Feature.png" width="400">   
* Steps   
<img src="https://raw.githubusercontent.com/dsmita22/SeleniumJavaSecurePay/master/docs/Steps.png" width="400">   