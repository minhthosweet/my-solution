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
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Scheduled
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status "Scheduled" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Completed
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    And I validate the new trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status "Completed" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    And I search customer
    And I complete an appointment

  Scenario: Validate Trigger On Save Appointment Status of Status Rescheduled
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status "Rescheduled" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    And I search customer
    And I reschedule an appointment
    And I schedule an subscription appointment
    When I execute the trigger queue script
    Then I assert all the logs