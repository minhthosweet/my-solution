#Author: Frankie White
<<<<<<< HEAD
# Ticket-105549: "Customer Communications" Tab Field Validations

=======
# Ticket-105549: "Customer Communications" Tab Fill Validations
>>>>>>> bac950c (Made updates to files for Ticket-127104)
@Smoke
@RegressionFWhite
@CustomerCommunication
Feature: Customer Communication Tab Validation

  @ExecuteCustomerCommunicationTabValidation
  Scenario: Validate Fields On Customer Communication Tab
    When I navigate to the Customer Communication Tab
    And I validate all fields can be edited and updated
    Then I validate all updated fields are saved successfully
