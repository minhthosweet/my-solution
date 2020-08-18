#Author Aditya
@TriggerRules
Feature: Renewal

  Scenario: Validate Trigger Renewal of type After Next Billing Date
    Given I sign in to pestroutes domain
    Given I add a renewal service
    Given I add a trigger "TriggerAfterNextBillingDate_Renewal"
    And I validate the new trigger "TriggerAfterNextBillingDate_Renewal"
    And I validate Renewal actions "TriggerAfterNextBillingDate_Renewal"
    And I edit the trigger "TriggerAfterNextBillingDate_Renewal" to type "After Next Billing Date"
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription after next billing date
    When I run the trigger renewal script
    Then I assert the renewal trigger rules log
    And I validate if there are errors exist in the list