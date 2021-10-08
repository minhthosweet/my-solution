#Aditya
@TriggerRules
Feature: Customer Status

  Scenario: Validate Trigger On Save Customer Status of Status Created
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_CustomerStatus"
    And I validate the new trigger "TriggerOnSave_CustomerStatus"
    Then I edit the trigger status on trigger on save "Created" of type "TriggerOnSave_CustomerStatus"
    When I create customer with pref paper and residential property
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

  Scenario: Validate Trigger On Save Customer Status of Status Active
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_CustomerStatus"
    And I validate the new trigger "TriggerOnSave_CustomerStatus"
    Then I edit the trigger status on trigger on save "Active" of type "TriggerOnSave_CustomerStatus"
    When I create active customer with first name, last name, email and address
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

  Scenario: Validate Trigger On Save Customer Status of Status Pending Cancel
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_CustomerStatus"
    And I validate the new trigger "TriggerOnSave_CustomerStatus"
    Then I edit the trigger status on trigger on save "Pending Cancel" of type "TriggerOnSave_CustomerStatus"
    When I create pending cancel customer with first name, last name, email and address
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

  Scenario: Validate Trigger On Save Customer Status of Status Frozen Cancel
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_CustomerStatus"
    And I validate the new trigger "TriggerOnSave_CustomerStatus"
    Then I edit the trigger status on trigger on save "Frozen" of type "TriggerOnSave_CustomerStatus"
    And I add remove payment profile action "TriggerOnSave_CustomerStatus"
    When I create frozen customer with first name, last name, email and address
    When I execute the trigger queue script
    Then I assert all the logs
    And I assert Remove Payment log
    And I validate if there are errors exist in the list