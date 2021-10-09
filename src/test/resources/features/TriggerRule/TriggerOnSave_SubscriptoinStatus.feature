#Author Aditya
@TriggerRules
Feature: Subscription Status

  Scenario: Validate Trigger On Save Subscription Status of Status Any
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    And I validate the new trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status on trigger on save "Any" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

  Scenario: Validate Trigger On Save Subscription Status of Status Frozen
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    And I validate the new trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status on trigger on save "Frozen" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I freeze the subscription
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

  Scenario: Validate Trigger On Save Subscription Status of Status Active
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    And I validate the new trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status on trigger on save "Active" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I freeze the subscription
    And I search customer
    And I reactive a frozen subscription
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list

