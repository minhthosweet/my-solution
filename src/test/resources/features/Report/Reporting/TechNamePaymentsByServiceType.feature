# Author: Rex Jones II
# Ticket 105265: Invoice pops up under Tech's Name AND No Tech
# https://fieldroutes.freshdesk.com/a/tickets/105265

@Report
@RegressionREX
@Regression
@ReportingTab

Feature: Tech Name On Payment By Service Type Report

  As a user, I must see the correct technician name
  on a Payment By Service Type Report
  after the customer pays off their invoice

  Rule: Customer views their Payment By Service Type Report and sees correct technician name

  @RegressionREX_CustomerCard
  @VerifyTechNameStandAloneInvoice
  Scenario: Verify No Tech On The Payment By Service Type Report After Paying A Stand Alone Invoice
    Given I Create A Customer With A Subscription
    When I Generate A Stand Alone Invoice
    And I Pay Off The Stand Alone Invoice
    Then I See No Tech Is Displayed On The Payment By Service Type Report

  @RegressionREX_CustomerCard
  @VerifyTechNameCreatedAppointment
  Scenario: Verify Technician Name On The Payment By Service Type Report After Creating An Appointment
    Given I Create A Customer With A Subscription
    When I Complete An Appointment
    And I Pay Off A Non Stand Alone Invoice
    Then I See The Correct Technician Is Displayed On The Payment By Service Type Report