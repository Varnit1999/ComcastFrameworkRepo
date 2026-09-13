# Comcast CRM GUI Automation Framework

This repository contains a Selenium-based GUI automation testing framework for the Comcast CRM application.

## Project Structure

The main automation project is located inside:

ComcastCRMGUIFramework/

The project contains:

- `src/` - Automation source code and test classes
- `testdata/` - Test data used by the automation scripts
- `configAppData/` - Configuration and application data
- `AdvanceReport/` - Extent/advanced test reports
- `screenshots/` - Screenshots captured during test execution
- `test-output/` - Test execution output
- `pom.xml` - Maven configuration and project dependencies
- `suite_fullregressionTest.xml` - Full regression test suite
- `suite_parallel_crossbrowserTest.xml` - Parallel cross-browser test suite
- `suite_parallel_distributedTest.xml` - Parallel distributed execution suite
- `suite_regionalRegressionTest.xml` - Regional regression test suite

## Prerequisites

Before running the framework, make sure the following are installed:

1. Java JDK
2. Eclipse IDE
3. Maven
4. Git
5. Google Chrome browser
6. ChromeDriver (if required by the framework configuration)

## Clone the Repository

Clone the repository using Git:

```bash
git clone https://github.com/Varnit1999/ComcastFrameworkRepo.git
