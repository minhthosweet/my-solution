#Author Aditya
@RegressionTest
Feature: End to end testing on the application

  @CreateCustomer
  Scenario: Create a customer with address
    Given I sign in to pestroutes domain
    Given I have disabled ECA
