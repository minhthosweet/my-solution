#Author Aditya
@CustomAppointmentDate
@RegressionADI
Feature: Custom Recurring Appointments

  Scenario: Create Initial Invoice of type On Initial Billing Date
    When I create customer with first name, last name, email and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I search customer
    And I navigate to Subscription Tab
    And I add a custom frequency recurring service
    Then I validate upcoming appointments for custom recurring appointments
    And I close customer card
    And I remove the customer
    And I validate if there are errors exist in the list
