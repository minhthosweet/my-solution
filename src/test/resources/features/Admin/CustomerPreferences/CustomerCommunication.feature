#Author: Frankie White
#
# Validate all fields on the "Customer Communication" can be updated and saved successfully
# Ticket-105549: "Customer Communications" Tab Field Validations

@Smoke
@Regression_FWhite
@CustomerCommunication
Feature: Customer Communication Tab Validation

  @ExecuteCustomerCommunicationTabValidation
  Scenario: Validate Fields On Customer Communication Tab
    When I navigate to the Customer Communication Tab
    And I validate all fields can be edited and updated
    Then I validate all updated fields are saved successfully
