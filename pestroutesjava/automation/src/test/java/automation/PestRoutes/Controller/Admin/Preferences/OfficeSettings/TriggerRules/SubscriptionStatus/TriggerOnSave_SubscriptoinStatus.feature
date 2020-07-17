#Author Aditya
@TriggerRules
Feature: Subscription Status

  Scenario: Validate Trigger On Save Subscription Status of Status Any
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status "Any" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Subscription Status of Status Frozen
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status "Frozen" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I freeze the subscription
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Subscription Status of Status Active
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_SubscriptionStatus"
    Then I edit the trigger status "Active" of type "TriggerOnSave_SubscriptionStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I freeze the subscription
    And I search customer
    And I reactive a frozen subscription
    When I execute the trigger queue script
    Then I assert all the logs

