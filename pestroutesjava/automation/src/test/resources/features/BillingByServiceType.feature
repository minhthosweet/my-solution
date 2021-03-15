#Author: Aditya
@BillingByServiceTypeReports
@RegressionADI
Feature: Billing by service Type report validation

  Scenario: Fields validation
    Given I add a renewal service
    And I navigate to Billing by Service Type
    Then I validate if all fields are displaying and are enabled in Billing by service type
    And I validate if there are errors exist in the list

  Scenario: Validating filters and fields generated with single group by and its line items
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Its lit" and "Customer"
    Given I add a new customer source if it is not already existing
    Given I add a new division if it is not already existing
    When I create customer with first name, last name, email and address
    And I add additional properties to customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    And I close customer card
    And I navigate to Billing by Service Type
    And I group by customer name
    And I add all the filters
    When I search for the billing customer
    Then I validate the fields generated by billing by service type report
    Then I validate if the report is linked to the customer card
    And I validate billing by service type report
    And I validate the fields are displayed in individual line items
    And I validate if there are errors exist in the list

  Scenario: Prefer Paper and Property Type validation
    When I create customer with balance with prefers paper and residential property type
    And I search for customer with pref paper and residential property in BST
    Then I validate the fields generated by billing by service type report
    Then I validate if the report is linked to the customer card
    And I validate billing by service type report
    And I validate the fields are displayed in individual line items
    When I edit customer to commercial property and not require paper
    And I search for customer without pref paper and commercial property in BST
    Then I validate the fields generated by billing by service type report
    Then I validate if the report is linked to the customer card
    And I validate billing by service type report
    And I validate the fields are displayed in individual line items
    And I validate if there are errors exist in the list

  Scenario: Balance Age validation
    When I validate customer with balance age in BST
    And I validate if there are errors exist in the list

  Scenario: Sold filters validation BST123
    Given I delete a routing group
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with first name, last name, email and address
    And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I set filter for sold date, scheduler, sale teams and sales reps
    Then I validate the fields generated by billing by service type report
    Then I validate if the report is linked to the customer card
    And I validate billing by service type report
    And I validate the fields are displayed in individual line items
    And I validate if there are errors exist in the list

