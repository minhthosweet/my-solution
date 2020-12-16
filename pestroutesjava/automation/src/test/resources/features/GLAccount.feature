#Author Aditya
@GLAccountOnServiceType
Feature: GLAccount creation, adding to service type and subscription and validation

  @GLAccountOnServiceType
  Scenario Outline: Create a GL Account
    Given I sign in to pestroutes domain
    Then I check if GLAccount is available "<description>"
    Then I validate the GL Account with description "<description>"
    And I validate if there are errors exist in the list

    Examples:
      | description   |
      | testGLAccount |
