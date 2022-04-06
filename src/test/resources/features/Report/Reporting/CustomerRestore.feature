# Author: Frankie White
# Ticket 121154: Customer Restore Not Functioning for One Customer Only

@Regression_FWhite
@Reporting
@CustomerRestoreReport
Feature: Validation of the Customer Restore Report
  This feature verifies Customer Restore Functionality...
  1. Verifies the restoration of a customer account after it has been removed

  @VerifyCustomerRestoreReport
  Scenario: Validate Customer Restore Report
  Given I create customer with first name, last name, email, address and "75010"
  And I remove the customer
  And I restore the removed customer
  Then I validate the Restored Customer loads successfully
  Then I remove the customer
  Then I validate if there are errors exist in the list