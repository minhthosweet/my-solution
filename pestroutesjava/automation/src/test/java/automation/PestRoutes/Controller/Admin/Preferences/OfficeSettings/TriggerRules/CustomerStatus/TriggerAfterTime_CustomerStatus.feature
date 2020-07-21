#Author Aditya
@TriggerRules
Feature: Customer Status

  Scenario: Validate Trigger After Time Customer Status of Status Created
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerAfterTime_CustomerStatus"
    Then I edit the trigger status on trigger after time "Created" of type "TriggerAfterTime_CustomerStatus"
    When I create customer with first name, last name, email and address
    When I execute the trigger customer status script
    Then I assert all the logs

  Scenario: Validate Trigger After Time Customer Status of Status Active
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerAfterTime_CustomerStatus"
    Then I edit the trigger status on trigger after time "Active" of type "TriggerAfterTime_CustomerStatus"
    When I create active customer with first name, last name, email and address
    When I execute the trigger customer status script
    Then I assert all the logs

  Scenario: Validate Trigger After Time Customer Status of Status Pending Cancel
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerAfterTime_CustomerStatus"
    Then I edit the trigger status on trigger after time "Pending Cancel" of type "TriggerAfterTime_CustomerStatus"
    When I create pending cancel customer with first name, last name, email and address
    When I execute the trigger customer status script
    Then I assert all the logs

  Scenario: Validate Trigger After Time Customer Status of Status Frozen
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerAfterTime_CustomerStatus"
    Then I edit the trigger status on trigger after time "Frozen" of type "TriggerAfterTime_CustomerStatus"
    And I add remove payment profile action "TriggerAfterTime_CustomerStatus"
    When I create frozen customer with first name, last name, email and address
    When I execute the trigger customer status script
    Then I assert all the logs
