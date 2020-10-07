#Author Aditya
@GLAccountValidation
Feature: GLAccount creation, adding to service type and subscription and validation

  @CreateGLAccount
  Scenario: Create a GL Account
    Given I sign in to pestroutes domain
    Then I create a GL Account
    When I search for the GL Account
    Then I validate the GL Account
    And I validate if there are errors exist in the list

