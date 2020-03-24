#Each feature contains one freature
#Feature files use Gherkin language - business language
Feature: Test the login functinality on sdet university

#A Feature may have given different specific scenarios
#Scenarios user Given-When-Then structure
Scenario: the user should be able to login with correct username and correct password

Given user is on the login page
When user enters correct username and correct password
Then user gets confirmation