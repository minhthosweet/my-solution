#Author: Adi
@RegressionADI_ACH
@PaymentGatewayValidationsACH
Feature: Validate all types of ACH payments

  Scenario: PreValidations
    Given I add a renewal service

  @Smoke_Adi @ACHOnFile
  Scenario Outline: ACH On File Payment Validation
    When I navigate to merchant Info
    Then I set ACH gateway "<Gateway>"
    When I create customer with balance with prefers paper and residential property type
    And I add an ACH payment option
    Then I make payment with ACH Account on file
    And I remove the customer
    And I validate if there are errors exist in the list

    Examples:
      | Gateway             |
      | PestRoutes Payments |
      | Element             |
      | NMI                 |
    #  | Authorize.net       |

  @ACHNotOnFile
  Scenario Outline: ACH Existing Transaction Payment Validation
    When I navigate to merchant Info
    Then I set ACH gateway "<Gateway>"
    When I create customer with balance with prefers paper and residential property type
    Then I make payment with Existing Transaction ACH on file
    And I remove the customer
    And I validate if there are errors exist in the list

    Examples:
      | Gateway             |
      | PestRoutes Payments |
      | Element             |
      | NMI                 |
#      | Authorize.net       |