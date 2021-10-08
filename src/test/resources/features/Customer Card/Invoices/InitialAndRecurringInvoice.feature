#Author Aditya
@Smoke
@InitialAndRecurringInvoices
@RegressionADI
Feature: Initial And Recurring Invoices

  @Smoke_Adi
  Scenario: Create initial and recurring invoice and validate
    Given I delete a routing group
    When I create customer with first name, last name, email and address
    And I create a subscription of type "After Initial Completion"
    And I navigate to Subscription Tab
    And I close customer card
    And I search customer
    And I navigate to Subscription Tab
    Then I validate initial invoice template values
    Then I validate recurring invoice template values
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    When I close scheduling notice button
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I close customer card
    And I search customer
    And I validate initial invoice created on invoice tab
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I close customer card
    And I search customer
    And I validate recurring invoice created on invoice tab
    Then I close customer card
    And I remove the customer
    And I validate if there are errors exist in the list
