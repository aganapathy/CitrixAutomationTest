##################################################
Author: Aishwarya
Project details: UI Automation project using Selenium WebDriver
Framework : 
	- Page Object Modal based Framework
	- UI Objects scripted within the page objects as protected variables(assuming that the object definition will not change frequently)
	- Test Data managed via .properties file (The Scope of test data can be moved to Excel on need)
	- Configuration and Urls managed in .properties file
	- Maven used to manage dependencies
	- TestNG for reporting purposes
##################################################


*******************************************
Pre-requisites
*******************************************
1. Provide test username and password that can be used to login to gotowebinar project.
	Do the following
	a. Navigate to \src\test\resources\Data\prod\config.properties
	b. Edit the config.properties and provide value for keys
		#TestData
		email=
		password=

2. Change the testdata if needed in the same file for Scheduling Webinar
3. If your website is slow, we can change the wait time across the suite in \src\main\resources\Configurations\prod\config.properties 
	Configure the values under #Timeout Configurations for Elements/page

4. TestNg.xml parameter:
	<parameter name="test.env" value="prod" />
	<parameter name="test.browser" value="chrome" />
	<parameter name="test.os" value="mac" />
	
	test.browser values can be : chrome/firefox/ie/headless
	test.os values : mac/win
	
Hardware needs:
1. Maven installed and paths set



*******************************************
TO RUN TESTS
*******************************************
-- The pom.xml already has set the TestNG.xml as its default run properties.
1. Go to cmd
