#Author: Aarbi
@Regression
@FieldsValidationInAccountReceivable
Feature: Validate if all fields present and are enabled

  Scenario: Fields validations
    Given I delete a routing group
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I validate the if agreement is created
    And I close customer card
    And I navigate to account receivable under Billings
    Then I validate if all fields are displaying and are enabled
    Then I validate if the customer displays once account status is Active
    And I validate if there are errors exist in the list

  Scenario: Customer type validation
    Then I validate customer type in account receivable
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver