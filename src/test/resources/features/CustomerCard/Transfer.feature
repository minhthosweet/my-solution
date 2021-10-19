#Author: Aditya
@Smoke
@RegressionADI
@Transfer
Feature: Validate Transfer Account

  @ValidateTransferAccount
  Scenario: Validate transfer account visibility
    When I create customer with first name, last name and address
    And I validate if agent display in the list after clicking on transfer button "Jared Green"
    Then I remove the customer
    And I validate if there are errors exist in the list