#Author: Aditya
@Regression
@FieldsValidationInBillingByServiceType
Feature: Validate if all fields present and are enabled

  Scenario: Customer validation
    Given I sign in to pestroutes domain
    When I create customer with first name, last name, email and address
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I close customer card
    And I navigate to Billing by Service Type
    And I group by customer name
    And I search for the billing customer
