
@tag
Feature: Error Validation
  I want to use this template for my feature file
	

  @Regression
  Scenario Outline: Error Validation
    Given I landed on Ecommerce page
    When  I logged in with <username> and password <password>
    Then "Incorrect email or password." message is displayed

      Examples: 
      | username  								|	 password 	|
      | manojkumar99143@gmail.com |	 Manoj@0709	|