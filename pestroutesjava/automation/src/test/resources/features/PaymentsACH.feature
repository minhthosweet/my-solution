#Author: Adi
@RegressionADI
@PaymentGatewayValidations
Feature: Validate all types of payments

  Scenario Outline: ACH On File Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set ACH gateway "<Gateway>"
    When I create customer with balance with prefers paper and residential property type
    And I add an ACH payment option
    Then I make payment with ACH Account on file
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      | Gateway             |
      | Element             |
      | NMI                 |
      | PestRoutes Payments |
    #  | Authorize.net       |

  Scenario Outline: ACH Existing Transaction Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set ACH gateway "<Gateway>"
    When I create customer with balance with prefers paper and residential property type
    Then I make payment with Existing Transaction ACH on file
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      | Gateway             |
      | Element             |
      | NMI                 |
      | PestRoutes Payments |
    #  | Authorize.net       |