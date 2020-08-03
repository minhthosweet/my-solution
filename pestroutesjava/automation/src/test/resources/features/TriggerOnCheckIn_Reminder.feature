#Author Aditya
@TriggerRules
Feature: Reminder

  Scenario: Validate Trigger on CheckIn Remninder
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnCheckIn_Reminder"
    And I validate the new trigger "TriggerOnCheckIn_Reminder"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule a subscription appointment
    #And I check in from the app
    When I execute the trigger queue script
    Then I assert all the logs
    And I validate if there are errors exist in the list
