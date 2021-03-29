#Author: Aditya
@PaymentsByServiceTypeReports
@RegressionADI
Feature: Payments by service Type report end to end validation

  @PSTFilterFields
  Scenario: Fields validation PST
    Given I navigate to Report of type "Payments by Service Type"
    Then I validate if all fields are displaying and are enabled in Payments by service type
    And I validate if there are errors exist in the list

    #WIP
  @PSTReportFields
  Scenario: Validating filters and fields generated with single group by and its line items PST
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Its lit" and "Customer"
    Given I add a new customer source if it is not already existing
    Given I add a new division if it is not already existing
    When I create customer with pref paper and residential property
    And I add additional properties to customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
