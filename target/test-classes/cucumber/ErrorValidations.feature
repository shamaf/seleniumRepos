
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on the ecommerce page
    And Loggedin with username <name> and <password>
    Then "Incorrect email or password." message is displayed
Examples: 
      | name 						  | password       | productname  |
      | shama@yopmail.com |     Tester@13  | IPHONE 13 PRO|
      | maryam@gmail.com  |     Tester@123 | ZARA COAT 3  |
