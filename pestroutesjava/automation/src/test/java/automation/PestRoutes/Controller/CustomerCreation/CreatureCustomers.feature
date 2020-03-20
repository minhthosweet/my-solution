@Smoketest
Feature: Create a customer
  I want to create a customer using Cucumber

  @OpenTest
  Scenario: Create a customer
    Given I want to login to the application using URL : "https://abdula.pestroutes.com/"
    When username is "mind" and password is "matter"
    And Click on New Customer
    When I enter firstname and lastname
    Then unit details
    And click save button
