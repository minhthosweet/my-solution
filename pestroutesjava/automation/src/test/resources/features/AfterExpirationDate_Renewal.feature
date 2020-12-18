#Author Aditya
@TriggerRules
Feature: TriggerRenewal_AfterExpirationDate

  Scenario: Validate Trigger Renewal of type After Expiration Date
    Given I sign in to pestroutes domain
    Given I add a renewal service
    Given I add a trigger "TriggerAfterExpirationDate_Renewal"
    And I validate the new trigger "TriggerAfterExpirationDate_Renewal"
    And I validate Renewal actions "TriggerAfterExpirationDate_Renewal"
    And I edit the trigger "TriggerAfterExpirationDate_Renewal" to type "After Expiration Date"
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription after expiration date
    When I run the trigger renewal script
    Then I assert the renewal trigger rules log
    And I validate if there are errors exist in the list