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

    @Defect
    @Defect_VerifyApplicationPerformsRenewalTaskAfterSingleUseCC
    Scenario Outline: Defect_Verify Application Performs Renewal Task After Single Use Credit Card Payment
      Given I Set Up The Application For "<Gateway>"
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Process A One Time Single Use Card Payment On A Renewal Subscription Using "<Gateway>", "<Credit Card #>", "12/25", "234"
      Then  I See The Subscription Renewal Date Move Forward After Making Single Use Card Payment For Each "<Gateway>"

      Examples:
        | Gateway             | Credit Card #       |
        | Braintree           | 4111 1111 1111 1111 |
        | Element             | 4111 1111 1111 1111 |
        | NMI                 | 5412 7501 0905 6250 |
        | Spreedly            | 4111 1111 1111 1111 |
        | PestRoutes Payments | 4111 1111 1111 1111 |