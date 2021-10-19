#Author: Aarbi
@Smoke
@RegressionARB
@PaymentCC
Feature: Validate all types of payments
  @CardOnFilePaymentValidation
  Scenario Outline: Card On FIle Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set cc gateway "<Gateway>"
    When I create customer with first name, last name, email and address
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I create standalone service invoice "<Amount>"
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      | Gateway             | Amount |
      | Braintree           | 400    |
      | Element             | 400    |
      | Spreedly            | 400    |
      | NMI                 | 400    |
      | PestRoutes Payments | 400    |

#  Scenario Outline: Card On FIle Declined Payment Validation
#    Given I add a renewal service
#    When I navigate to merchant Info
#    Then I set cc gateway "<Gateway>"
#    When I create customer with first name, last name, email and address
#    And I add a CC payment option "<Cc1>" and "<Cc2>"
#    And I create standalone service invoice "<Amount>"
#    Then I make payment with credit card on file "Successfully Charged Credit Card!"
#    And I validate if there are errors exist in the list
#    Then I remove the customer
#
#    Examples:
#      | Gateway             | Amount | Cc1 | Cc2 |
#      | Braintree           | 2105   | 4111111111111111 | 5412750109056250 |
#      | Element             | 0.20   | 4111111111111111 | 5412750109056250 |
#      | Spreedly            | 400    | 4012888888881880 | 5412750109056250 |
#      | NMI                 | 400    | 4111111111111111 | 4111110865087717 |
#      | PestRoutes Payments | 500.01 | 4111111111111111 | 5412750109056250 |

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
      | NMI                 | 388    |
      | PestRoutes Payments | 400    |