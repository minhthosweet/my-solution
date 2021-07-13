#Author: Aditya
@RegressionADI
@NewCustomerValidation
Feature: Create and search customer with validation

  @Smoke_Adi
  @CreatedCustomerSearchValidation
  Scenario: Create a customer and validate customer search field
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and dynamic zip
    Then I validate if customer name and address match in overview tab
    And I validate search customer with first name
    And I validate search customer with last name
    And I validate search customer with phone number
    And I validate search customer with zip code
    And I validate search customer with CustomerID
    And I validate search customer with street address
    And I validate search customer with email address
    Then I remove the customer
    And I validate if there are errors exist in the list

  @ValidateTaxRateAfterEditing
  Scenario: Validate tax rate after editing zip
    When I create customer with first name, last name and address
    And I edit zipcode in info tab "92780"
    Then I validate if tax rate is same
    Then I remove the customer
    And I validate if there are errors exist in the list

  @ValidateTransferAccount
  Scenario: Validate transfer account visibility
    When I create customer with first name, last name and address
    And I validate if agent display in the list after clicking on transfer button "Jared Green"
    Then I remove the customer
    And I validate if there are errors exist in the list