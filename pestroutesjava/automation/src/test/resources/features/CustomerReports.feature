#Author: Aditya
@CustomerReports
@RegressionADI
Feature: Customer reports end to end validation

  @FieldsValidation_CustomerReports
  Scenario: Fields validation CR
    Given I navigate to "Customer Reports" in Customers tab
    And I validate if saved report fields are visible on the page
    And I validate if select columns to display fields are visible on the page
    And I validate if customer account fields are visible on the page
    And I validate if leads fields are visible on the page
    And I validate if service subscription fields are visible on the page
    And I validate if customer location fields are visible on the page
    And I validate if billing account fields are visible on the page
    And I validate if billing address fields are visible on the page
    And I validate if service appointment fields are visible on the page
    And I validate if there are errors exist in the list

  @CustomerAccountReportValidation_CustomerReports
  Scenario: Customer Account Report Data Validation
    When I create customer with pref paper and residential property
    Then I get customer name and customer ID details for customer reports
    And I add properties customer source, property type, prefers paper, division, purple dragon and generic flag to the customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Customer Account in Customer Reports
    And I search for customer in customer reports
    Then I validate customer account report in Customer Reports
    And I validate if there are errors exist in the list
    And I remove the customer

  @LeadsValidation_CustomerReports
  Scenario: Leads validation in Customer Reports
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with pref paper and residential property
    And I create a new lead
    Then I validate lead creation invoices
    Then I get customer name and customer ID details for customer reports
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Leads in Customer Reports
    Then I validate leads report in Customer Reports
    And I validate if there are errors exist in the list
    And I remove the customer