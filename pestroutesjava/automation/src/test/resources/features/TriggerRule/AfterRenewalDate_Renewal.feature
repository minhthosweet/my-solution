#Author Aditya
@TriggerRules
Feature: Renewal

  Scenario: Validate Trigger Renewal of type After Renewal Date
    Given I sign in to pestroutes domain
    Given I add a renewal service
    Given I add a trigger "TriggerAfterRenewalDate_Renewal"
    And I validate the new trigger "TriggerAfterRenewalDate_Renewal"
    And I validate Renewal actions "TriggerAfterRenewalDate_Renewal"
    And I edit the trigger "TriggerAfterRenewalDate_Renewal" to type "After Renewal Date"
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription after renewal date
    When I run the trigger renewal script
    Then I assert the renewal trigger rules log
    And I validate if there are errors exist in the list