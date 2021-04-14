#Author: Aarbi
@RegressionARB
@PaymentGatewayValidationsCC
Feature: Validate all types of payments

  Scenario Outline: Card On FIle Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set cc gateway "<Gateway>"
    When I create customer with first name, last name, email and address
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I create standalone service invoice "<Amount>"
    Then I make payment with credit card on file
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      | Gateway             | Amount |
      | Braintree           | 400    |
      | Element             | 400    |
      | Spreedly            | 400    |
      | NMI                 | 400    |
      | PestRoutes Payments | 400    |

  @SingleCardPaymentValidation
  Scenario Outline: Single Card Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set cc gateway "<Gateway>"
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "<Amount>"
    Then I make payment with single credit card "4111111111111111" and "5412750109056250"
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      | Gateway             | Amount |
      | Braintree           | 400    |
      | Element             | 400    |
      | Spreedly            | 400    |
      | NMI                 | 400    |
      | PestRoutes Payments | 400    |