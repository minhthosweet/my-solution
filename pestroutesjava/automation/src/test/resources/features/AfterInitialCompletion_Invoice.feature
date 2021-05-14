# Author : Adi
@AfterInitialCompletion
@RegressionADI
Feature: After Initial Appointment Completion

  Scenario: Create Initial Invoice of type After Initial Completion
    Given I delete a routing group
    Given I add a renewal service
    When I create customer with first name, last name, email and address
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I close customer card
    And I search customer
    And I validate initial invoice created on invoice tab
    And I remove the customer
    And I validate if there are errors exist in the list
