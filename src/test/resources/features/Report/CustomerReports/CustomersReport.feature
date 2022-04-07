#Author: Aditya
@Report
@Regression
@CustomerReports
@CustomersReport
@RegressionADI_CR
Feature: Customer reports end to end validation

  @Smoke
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
    Given I add a new customer source if it is not already existing
    Given I add a new division if it is not already existing
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
    And I close customer card
    And I search customer
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
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I get appointment details for customer reports
    And I complete an appointment
    And I close customer card
    And I search customer
    And I get customer details for customer reports
    Then I make payment with credit card on file "Successfully Charged Credit Card!"
    Then I navigate to "Customer Reports" in Customers tab
    When I add filters to Service Appointment in Customer Reports
    Then I validate service appointment report in Customer Reports
    And I remove the customer
    And I validate if there are errors exist in the list

  @ActionsValidations_Flags
  Scenario: Flags actions validation in Customer Reports
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Automation Flag" and "Customer"
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

  @ActionsValidations_UpdateSubscriptionPrice
  Scenario: Update Subscription Price actions validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I create a subscription of type "After Initial Completion"
    Then I get recurring invoice value for the customer
    Then I navigate to "Customer Reports" in Customers tab
    When I group by "Subscription" in Customer Reports under select columns to display
    And I add "Recurring Price" as column to display in Customer Reports
    When I add customer name filters in Customer Reports
    And I validate recurring price before price update
    When I update subscription price through actions in customer report
    And I validate recurring price after price update
    And I remove the customer
    And I validate if there are errors exist in the list

  @ActionsValidations_BulkFreeze
  Scenario: Bulk Freeze actions validation in Customer Reports
    When I create customer with pref paper and residential property
    Then I get customer details for customer reports
    And I change customer status
    Then I navigate to "Customer Reports" in Customers tab
    When I add customer name filters in Customer Reports
    And I bulk freeze customer through actions in customer report
    And I validate if the customer was frozen in Notes tab of customer card
    And I validate if the customer was frozen in Admin tab of customer card
    Then I close customer card
    And I bulk freeze rollback customer through actions in customer report
    Then I navigate to "Customer Reports" in Customers tab
    When I add customer name filters in Customer Reports
    And I validate if the customer was rolled back in Admin tab of customer card
    And I remove the customer
    And I validate if there are errors exist in the list

  @Smoke
  @Regression_FWhite
  @CustomerWithoutPaymentMethodAndAutoPayDisabled_CustomerReports
  Scenario: Verify customer WITHOUT Payment Method and Autopay DISABLED IS NOT included in Customer Report
    Given I create customer with "AutomatedTester1","Tester1","tester@test.com","214-111-0001","111 Loop Ln.,McKinney,TX", and "75070"
    And I set Autopay option to "No Auto Pay" for customer,"AutomatedTester1" "Tester1"
    Then I navigate to "Customer Reports" in Customers tab
    And I add Customer Account filters first name ("AutomatedTester1"), last name ("Tester1") and Account Status ("Any Status") in Customer Report
    And I add Billing Account filter Customer Auto Pay ("Yes") in Customer Report
    And I add column "Customer Auto Pay" to be displayed
    And I generate Customer Report
    Then I validate no listings are returned in the generated Customer Report
    And I validate if there are errors exist in the list
    Then I remove the customer

  @Smoke
  @Regression_FWhite
  @CustomerWithPaymentMethodAndAutoPayEnabled_CustomerReports
  Scenario: Verify customer WITH Payment Method configured and Autopay ENABLED IS included on Customer Report executed to retrieve customers on Autopay
    Given I create customer with "AutomatedTester1","Tester1","tester@test.com","469-111-1001","1001 Loop Ln.,McKinney,TX", and "75070"
    And I add a CC payment option "4111111111111111" and "5412750109056250"
    And I set Autopay option to "CC - Visa - 1111" for customer,"AutomatedTester1" "Tester1"
    Then I navigate to "Customer Reports" in Customers tab
    And I add Customer Account filters first name ("AutomatedTester1"), last name ("Tester1") and Account Status ("Any Status") in Customer Report
    And I add Billing Account filter Customer Auto Pay ("Credit Card") in Customer Report
    And I add column "Customer Auto Pay" to be displayed
    And I generate Customer Report
    Then I validate customer "AutomatedTester1" "Tester1" "CC-Visa-1111" is displayed in the generated Customer Report
    And I validate if there are errors exist in the list
    Then I remove the customer

    #below test case has a backlog defect: PD-5355. Need to enable it once the defect is fixed.
  #@LeadsValidation_CustomerReports
  #Scenario: Leads validation in Customer Reports
   # Given I create a new user if it is not already existing "Office Staff"
   # When I create customer with pref paper and residential property
   # And I create a new lead
   # Below step fails due to assertion issue in Leads test case
   # Then I validate lead creation invoices
   # Then I get customer details for customer reports
   # And I change customer status
   # Then I navigate to "Customer Reports" in Customers tab
    #When I add filters to Leads in Customer Reports
   # Then I validate leads report in Customer Reports
   # And I remove the customer
   # And I deactivate the existing user
   # And I validate if there are errors exist in the list

    #Below test case is not valid scenario since it considers subscription and customer filters
  #@SelectColumnsToDisplayValidation_CustomerReports
  #Scenario: Select Columns To Display validation in Customer Reports
  #  Given I delete a routing group
   # When I create customer with pref paper and residential property
    #Then I get customer details for customer reports
    #And I change customer status
    #And I create a subscription of type "After Initial Completion"
    #And I navigate to scheduling on same Day
    #And I add a route
    #And I search customer
    #And I navigate to Subscription Tab
    #And I schedule an service appointment
    #Then I navigate to "Customer Reports" in Customers tab
    #Then I add filters to Select Columns To Display in Customer Reports
    #And I validate select columns to display fields in Customer Reports
    #And I remove the customer
    #And I validate if there are errors exist in the list"""

# Ticket 123282: Results Disappear When Sorting Customers Report Column Subscription Last Completed
# https://fieldroutes.freshdesk.com/a/tickets/123282
  @RegressionREX
  @RegressionREX_CR
  @RegressionREX_Reports
  @VerifyResultsDoNotDisappearWhenSortingCRColumnSubscriptionLastCompleted
  Scenario: Verify Results Do Not Disappear When Sorting CR Column Subscription Last Completed
    Given I Create A Customer With A Subscription
    When  I Complete An Appointment
    And   I Run The Customer Report After Adding The "Subscription Last Completed" Column
    And   I Sort The Subscription Last Completed Column 2 Times
    Then  I Verify The Customer Report Results Are Available By Finding The Customer

# Ticket 121308: Incorrect Results for Saved Customer Report DispatchAfterReport - Pending
# https://fieldroutes.freshdesk.com/a/tickets/121308
  @RegressionREX
  @RegressionREX_CR
  @RegressionREX_Reports
  @VerifyResultsAreCorrectForShowTechNotes
  Scenario: Verify Correct Results For Customer Report With No Show Tech Notes
    Given I Navigate To The Service Appointment Section via Customer Reports
    When  I Set Scheduled For Date Range From One Year Ago To Today
    And   I Set Category To "Bi Monthly", "Dynamic", "CLEAN", & "General Pest"
    Then  I Verify The Customer Report # of Results After Setting Show Tech Notes To "No"

# Ticket 121308: Incorrect Results for Saved Customer Report DispatchAfterReport - Pending
# https://fieldroutes.freshdesk.com/a/tickets/121308
  @RegressionREX
  @RegressionREX_CR
  @RegressionREX_Reports
  @VerifyResultsAreCorrectForShowTechNotes
  Scenario: Verify Correct Results For Customer Report With Yes Show Tech Notes
    Given I Navigate To The Service Appointment Section via Customer Reports
    When  I Set Scheduled For Date Range From One Year Ago To Today
    And   I Set Category To "Bi Monthly", "Dynamic", "CLEAN", & "General Pest"
    Then  I Verify The Customer Report # of Results After Setting Show Tech Notes To "Yes"

# Ticket 127632: Negative and Incorrect Value for Days Past Due Column of Customers Report
# https://fieldroutes.freshdesk.com/a/tickets/127632
  @RegressionREX
  @RegressionREX_CR
  @RegressionREX_Reports
  @VerifyNegativeDaysPastDueOnCustomerReportsInverseToPositiveDaysToPayOnInvoicesTab
  Scenario: Verify Negative Days Past Due On Customer Reports Inverse To Positive Days To Pay On Invoices Tab
    Given I Create A Customer With A Subscription
    When  I Generate A Stand Alone Invoice
    And   I Add The "Days Past Due" Column To Customer Reports
    And   I Navigate To The "Billing Account" Section To Update Payment Days Past Due Filters "<" "0" Days
    Then  I Verify Days To Pay via Invoice Tab Inverses Days Past Due via Customer Reports

# Ticket 127632: Negative and Incorrect Value for Days Past Due Column of Customers Report
# https://fieldroutes.freshdesk.com/a/tickets/127632
  @RegressionREX
  @RegressionREX_CR
  @RegressionREX_Reports
  @VerifyPositiveDaysPastDueOnCustomerReportsInverseToNegativeDaysToPayOnInvoicesTab
  Scenario: Verify Positive Days Past Due On Customer Reports Inverse To Negative Days To Pay On Invoices Tab
    Given I Create A Customer With A Subscription
    When  I Generate A Stand Alone Invoice Using A Past Date
    And   I Add The "Days Past Due" Column To Customer Reports
    And   I Navigate To The "Billing Account" Section To Update Payment Days Past Due Filters ">" "0" Days
    Then  I Verify Days To Pay via Invoice Tab Inverses Days Past Due via Customer Reports
