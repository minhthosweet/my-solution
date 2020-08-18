#Author Aditya
@TriggerRules
Feature: Renewal

  Scenario: Validate Trigger Renewal of type Before Renewal Date
    Given I sign in to pestroutes domain
    Given I add a renewal service
    Given I add a trigger "TriggerBeforeRenewalDate_Renewal"
    And I validate the new trigger "TriggerBeforeRenewalDate_Renewal"
    And I validate Renewal actions "TriggerBeforeRenewalDate_Renewal"
    And I edit the trigger "TriggerBeforeRenewalDate_Renewal" to type "Before Renewal Date"
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription before renewal date
    When I run the trigger renewal script
    Then I assert the renewal trigger rules log
    And I validate if there are errors exist in the list