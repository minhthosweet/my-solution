@RegressionTest
Feature: Validate Customer Status Trigger Rule with Trigger on Save
  I want to validate all actions and all status of customer when we save a customer

  @SmokeTest
  Scenario: Validate for SMS Action for ANY status
    Given I want to create a trigger
    And I edit the trigger
    And I set SMS action
    And I create a customer with phone number and no address
    And yet another action
    Then I validate the outcomes
    And check more outcomes


