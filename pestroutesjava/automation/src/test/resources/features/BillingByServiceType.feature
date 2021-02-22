#Author: Aditya
@Regression
@BillingByServiceTypeReport
Feature: Validate if all fields present and are enabled

  Scenario: Fields validation
    Given I sign in to pestroutes domain
    And I navigate to Billing by Service Type
    Then I validate if all fields are displaying and are enabled in Billing by service type

  Scenario: Validating all filters with single group by
    When I create customer with first name, last name, email and address
    And I add additional properties to customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    And I close customer card
    And I navigate to Billing by Service Type
    And I group by customer name
    And I add all the filters
    When I search for the billing customer
    Then I validate if the report is linked to the customer card
    And I validate billing by service type report

  Scenario: Close browser
    And I quit driver


