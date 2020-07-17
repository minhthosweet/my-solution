#Author Aditya
@TriggerRules
Feature: Appointment Status

  Scenario: Validate Trigger On Save Appointment Status of Status Any
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status "Any" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment