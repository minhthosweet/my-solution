# Author: Rex Jones II
# Customer Portal - Billing Tab
# URL: https://fieldroutes.atlassian.net/browse/QTP-1056
@RegressionREX
@Regression
@CustomerCard
@CustomerPortal
@CustomerPortalBillingTab

Feature: Customer Portal - Billing Tab Displays Accurate Information

  As a user, I must see accurate information
  on the Customer Portal - Billing Tab
  for all customers

  Rule: User views the Billing Tab within the Customer Portal and see accurate information
    @VerifyBillingTabFirstNameInWelcomeBanner
    Scenario: Verify Customer First Name In The Welcome Banner Message via Billing Tab
      Given I Create A Customer With A Subscription
      When  I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify First Name In The Welcome Message via Billing Tab

    @VerifyBillingTabBalanceForUnpaidInvoice
    Scenario: Verify Responsible Balance via Billing Tab For An Unpaid Invoice
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Responsible Balance via Billing Tab Matches The Invoice Balance

    @VerifyBillingHistorySection
    Scenario: Verify Show Entries Drop Down Values, Table Column Names, Search Field, And Footer via Billing History Section
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Billing History Section via Billing Tab

    @VerifyCurrentSectionAfterCompletingAppointment
    Scenario: Verify Current Section Contains Correct Service Type And Amount After Completing An Appointment
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Service Type-Amount Is Correct via Current Section

    @VerifyTotalAmountRequiresPayment
    Scenario: Verify The Pay Total Amount Due Option Requires A Payment Method
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify An Error Shows Up When Selecting Pay Total Amount Due Without Selecting a Payment Method

    @VerifyAnotherAmountRequiresPayment
    Scenario: Verify The Pay Another Amount Option Requires A Payment Method
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify An Error Shows Up When Selecting Pay Another Amount Without Selecting a Payment Method

    @VerifyBillingTabHasUpdatePhoneAndMakePaymentLinks
    Scenario: Verify Billing Tab Has Images-Links That Allows A User To Update Phone And Make Payment
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The User Can Update Phone - Make Payment

    @VerifyErrorForOneTimeCardTotalAmount
    Scenario Outline: Verify Error Message For Required Fields When Using A One Time Card To Pay Total Amount
      Given I Set Up The Application For "<Gateway>"
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify All Of The Required Fields To Pay Total Amount Using A One Time Card For Each "<Gateway>"

      Examples:
       | Gateway             |
       | Braintree           |
       | Element             |
       | NMI                 |
       | Spreedly            |
       | PestRoutes Payments |

    @VerifyTotalAmountUsingOneTimeCardForEachGateway
    Scenario Outline: Verify Paying Total Amount Using One Time Card For Each Gateway
      Given I Set Up The Application For "<Gateway>"
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify Paying The Total Amount With A One Time Card Using "<Gateway>", "<Credit Card #>", "12/30", "234"

      Examples:
        | Gateway             | Credit Card #       |
        | Braintree           | 4111 1111 1111 1111 |
        | Element             | 4111 1111 1111 1111 |
  #      | NMI                 | 5412 7501 0905 6250 |
        | Spreedly            | 4111 1111 1111 1111 |
        | PestRoutes Payments | 4111 1111 1111 1111 |

    @VerifyTotalAmountUsingCardOnFileForEachGateway
    Scenario Outline: Verify Paying Total Amount Using Card On File For Each Gateway
      Given I Set Up The Application For "<Gateway>"
      Given I Create A Customer With A Subscription
      When  I Add A Card On File Using "<Gateway>", "<Credit Card #>", "12/30", "234"
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify Paying The Total Amount With A Card On File

      Examples:
        | Gateway             | Credit Card #       |
        | Braintree           | 4111 1111 1111 1111 |
        | Element             | 4111 1111 1111 1111 |
        | NMI                 | 5412 7501 0905 6250 |
        | Spreedly            | 4111 1111 1111 1111 |
        | PestRoutes Payments | 4111 1111 1111 1111 |