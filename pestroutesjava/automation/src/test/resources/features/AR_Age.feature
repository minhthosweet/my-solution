#Author Aditya
@TriggerRules
Feature: AR

  Scenario: Validate Trigger AR
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerAge_AR"
    When I create customer with Invoice
    When I execute the trigger event script
    Then I assert trigger event logs
    And I validate if there are errors exist in the list