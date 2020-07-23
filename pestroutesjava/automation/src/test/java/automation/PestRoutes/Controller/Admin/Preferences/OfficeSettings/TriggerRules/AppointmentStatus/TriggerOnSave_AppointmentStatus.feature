#Author Aditya
@TriggerRules
Feature: Appointment Status

  Scenario: Validate Trigger On Save Appointment Status of Status Any
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status on trigger on save "Any" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on Same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Scheduled
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status on trigger on save "Scheduled" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on Same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Complete
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    And I validate the new trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status on trigger on save "Complete" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on Same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    And I search customer
    And I complete an appointment
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Rescheduled
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status on trigger on save "Rescheduled" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on Same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    And I search customer
    And I reschedule an appointment
    And I schedule an subscription appointment
    When I execute the trigger queue script
    Then I assert all the logs

  Scenario: Validate Trigger On Save Appointment Status of Status Cancelled
    Given I sign in to pestroutes domain
    Given I add a trigger "TriggerOnSave_AppointmentStatus"
    Then I edit the trigger status on trigger on save "Cancelled" of type "TriggerOnSave_AppointmentStatus"
    When I create customer with first name, last name and address
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling on Same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an subscription appointment
    And I search customer
    And I cancel the appointment
    When I execute the trigger queue script
    Then I assert all the logs