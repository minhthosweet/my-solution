#Author Aditya
@CustomAppointmentDate
Feature: Customer Status

  @CreateAndValidateCustomRecurringAppointments
  Scenario: Create Initial Invoice of type On Initial Billing Date
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I search customer
    And I navigate to Subscription Tab
    And I add a custom frequency recurring service
    Then I validate upcoming appointments for custom recurring appointments
    And I close customer card
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver