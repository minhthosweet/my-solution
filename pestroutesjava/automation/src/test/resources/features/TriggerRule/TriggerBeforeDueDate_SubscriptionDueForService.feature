#Author Aditya
@TriggerRules
Feature: Subscription Due For Service

  Scenario: Validate Trigger Subscription Due For Service with status trigger before due date
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerBeforeDueDate_SubscriptionDueForService"
    And I validate the new trigger "TriggerBeforeDueDate_SubscriptionDueForService"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to "1" days before on scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule a subscription appointment
    And I search customer
    And I complete a subscription appointment
    And I search customer
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule a subscription appointment
    Then I assert all the logs
    And I validate if there are errors exist in the list