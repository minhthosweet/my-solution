#Author: Aditya
@CustomerReports
@RegressionADI_CR
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
    Then I get customer details for customer reports
    And I add properties customer source, property type, prefers paper, division, purple dragon and generic flag to the customer
    And I create a subscription of type "After Agreement Signed"
    And I sign the agreement for subscription of type After Agreement Signed
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Customer Account in Customer Reports
    And I search for customer in customer reports
    Then I validate customer account report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @Smoke_Adi
  @ServiceSubscription_CustomerReports
  Scenario: Service Subscription validation in Customer Reports
    Given I delete a routing group
    Given I create a new user if it is not already existing "Office Staff"
    Given I add a renewal service
    Given I add a new generic flag if it is not already existing "Fire" and "Its lit" and "Subscription"
    Given I add a new generic flag if it is not already existing "Test4Life" and "Test4Life" and "Customer"
    When I create customer with pref paper and residential property
    And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
    And I navigate to Subscription Tab
    And I add a custom frequency recurring service
    And I validate recurring invoice template values
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    Then I get customer details for customer reports
    Then I navigate to "Customer Reports" in Customers tab
    And I add filters to Service Subscription in Customer Reports
    And I add invoice filters to Service Subscription in Customer Reports
    Then I validate service subscription report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @CustomerLocationValidation_CustomerReports
  Scenario: Customer Location validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Customer Location in Customer Reports
    Then I validate customer location report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @Smoke_Adi
  @BillingAccountValidation_CustomerReports
  Scenario: Billing Account validation in Customer Reports
    When I create customer with balance with prefers paper and residential property type
    Then I get customer details for customer reports
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with "CC" and max limit "400"
    And I add an ACH payment option
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    And I add filters to Billing Account with max monthly as "400" in Customer Reports
    Then I validate billing account report with max monthly as "400" in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @BillingAddressValidation_CustomerReports
  @SavedFilterValidation_CustomerReports
  Scenario: Billing Address and Saved Filter validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Billing Address in Customer Reports
    Then I validate billing address report in Customer Reports
    When I create saved filter in Customer Reports
    Then I navigate to "Customer Reports" in Customers tab
    Then I validate saved filter in Customer Reports
    Then I validate billing address report in Customer Reports
    And I delete saved filter in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @ServiceAppointment_CustomerReports
  Scenario: Service Appointment validation in Customer Reports
    Given I delete a routing group
    When I create customer with pref paper and residential property
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I get customer details for customer reports
    And I get appointment details for customer reports
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I close customer card
    And I search customer
    Then I make payment with credit card on file
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Service Appointment in Customer Reports
    Then I validate service appointment report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @SelectColumnsToDisplayValidation_CustomerReports
  Scenario: Select Columns To Display validation in Customer Reports
    Given I delete a routing group
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    Then I navigate to "Customer Reports" in Customers tab
    Then I add filters to Select Columns To Display in Customer Reports
    And I validate select columns to display fields in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @LeadsValidation_CustomerReports
  Scenario: Leads validation in Customer Reports
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with pref paper and residential property
    And I create a new lead
    Then I validate lead creation invoices
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Leads in Customer Reports
    Then I validate leads report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @ActionsValidations_Flags
  Scenario: Flags actions validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add customer name filters in Customer Reports
    And I add flag to customer in customer report
    Then I validate flags added from actions in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @ActionsValidations_SendMessage
  Scenario: Send Message actions validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add customer name filters in Customer Reports
    When I send message or password recovery to customer in customer report
    Then I validate message was sent from actions in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @ActionsValidations_SendPasswordRecovery
  Scenario: Send Password Recovery actions validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add customer name filters in Customer Reports
    When I send message or password recovery to customer in customer report
    Then I validate message was sent from actions in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list
