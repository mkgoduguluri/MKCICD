
@tag
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Order a Product
    Given I logged in with <username> and password <password>
    When I add the product <productname1> and <productname2> from cart
    And check out <productname1> and <productname2> in cart
    Then I verify the "THANkYOU FOR THE ORDER." in step

    Examples: 
      | username  								|	 password 	|	 productname1			| productname2	|
      | manojkumar99143@gmail.com |	 Manoj@0709	|	 ZARA COAT 3			|	ADIDAS ORIGINAL |
