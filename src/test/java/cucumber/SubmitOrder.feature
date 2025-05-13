#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase an order from e-commerce website
  I want to use this template for my feature file

  Background:
  Given I landed on the ecommerce page  

  @Regression
  Scenario Outline: Positive test of submitting an order
    Given Loggedin with username <name> and <password>
    When I add <productname> to cart
    And checkout <productname> to submit the order
    Then "Thankyou for the order." message is displayed on the confirmation page

    Examples: 
      | name 						  | password       | productname  |
      | shama@yopmail.com |     Tester@123 | IPHONE 13 PRO|
      | maryam@gmail.com  |     Tester@123 | ZARA COAT 3  |
