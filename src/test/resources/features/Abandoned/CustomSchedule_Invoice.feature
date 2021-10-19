@CustomDateInitialAppointment
Feature: Customer Status

  @CreateInitialInvoiceCustomSchedule
  Scenario: Create Initial Invoice of type On Initial Billing Date
    When I create customer with first name, last name and address
    And I create a custom schedule subscription of type "Custom Schedule"
    And I set today as custom billing date
    And I calculate the Initial Invoice amount for the Custom Schedule
    When I run the billing queue script
    Then I clear cache and reload the browser
    And I sign in to pestroutes domain
    When I search customer
    And I validate initial invoice created on invoice tab from custom schedule
    And I remove the customer
    And I validate if there are errors exist in the list