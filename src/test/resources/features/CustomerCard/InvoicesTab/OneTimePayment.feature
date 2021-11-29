# Author: Rex Jones II
# Ticket 110633: One Time Payments Don't Work w/Renewals
# https://fieldroutes.freshdesk.com/a/tickets/110633
@RegressionREX
@Regression
@CustomerCard
@InvoicesTab

Feature: One Time Payments Perform All Renewal Tasks

  As a user, I must see all of the renewal tasks
  after processing a one time payment
  using a Credit Card, Check, etc.

  Rule: User views the application and confirms all renewal tasks after processing a one time payment

    @VerifyApplicationPerformsRenewalTaskAfterSingleUseCC
    Scenario: Verify Application Performs All Renewal Tasks After Single Use Credit Card Payment
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Process A One Time Single Use Card Payment On A Renewal Subscription
      Then  I See All Of The Renewal Tasks