#Author Aditya
@GLAccountAssignedToServiceType
Feature: GLAccount creation, adding to service type and subscription and validation

  @GLAccountOnServiceType
  Scenario Outline: Create a GL Account
    Given I sign in to pestroutes domain
    When I navigate to GLAccounts
    Then I check if GLAccount is available "<description>" and validate the GL Account
    And I add a renewal service
    When I navigate to GLAccounts
    Then I search for the GL Account "<description>"
    When I add GLAccount to service type with description "<description>"
    When I navigate to GLAccounts
    Then I search for the GL Account "<description>"
    Then I validate if GLAccount is added to the service type
    And I validate if there are errors exist in the list

    Examples:
      | description   |
      | testGLAccount |
