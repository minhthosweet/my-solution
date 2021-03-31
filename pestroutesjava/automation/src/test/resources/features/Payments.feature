#Author: Aarbi
@RegressionARB
  @PaymentGatewayValidations
Feature: Validate all types of payments
  Scenario Outline: Card On FIle Payment Validation
    Given I add a renewal service
    When I navigate to merchant Info
    Then I set cc gateway "<Gateway>"
    When I create customer with first name, last name, email and address
    And I add a CC payment option
    And I create standalone service invoice "<Amount>"
    Then I make payment with credit card on file "<ServiceType>"
    And I validate if there are errors exist in the list
    Then I remove the customer

    Examples:
      |Gateway|Amount|ServiceType|
      |Braintree|400|Automation Renewal|
      |Element  |400|Automation Renewal|
      |Spreedly |400|Automation Renewal|
      |NMI |400|Automation Renewal|
      |PestRoutes Payments |400|Automation Renewal|