# Author: Frankie White
# Ticket 120352: Cannot edit notes on payments
# Ticket 120838: Invoice generating without number attached/Customer db issue
# Ticket 127695: Taxable Option Broken on Invoice"
# Ticket 124444: Expiration Date Does Not Advance
# Ticket 122268: Unable to type decimals in refund window/Firefox specific issue
# Ticket 123838: Payment applied to incorrect invoice/Customer db specific issue
# Ticket 123784: Customers Displaying a Balance when No Balance Should Exist. (Related to: 120838)
# Ticket 123825: No Invoice # for customer account #86355 for Special service. (Related to: 120838)
# Ticket 127013: Consolidated Invoices not including add-on
# Ticket 127056 : Incorrect Card Charged - Previous Linked Billing Accounts (Related to: 127031, 127727)
@Regression_FWhite
@CustomerCard

Feature: Validate Customer Invoice
    This feature verifies various invoice functionalities...
    1. Verifies the "customer notes", "transaction amount", and  "transaction date & time"  fields on  "Cash",
       "Check", and "Coupon Payment" payments are editable
    2. Verifies the taxable option functions as expected
    3. Verifies a newly created invoice has an invoice number
    4. Verifies that the subscription's Expiration Date advances after a payment is made with a Single Use Credit Card
    5. Verifies the payment is applied to the right invoice
    6. Verifies refund amounts that includes cents are acceptable
    7. Verifies Consolidated Invoices with add-on(s)

  @VerifyCustomerNotesAreEditableForCashAndCheckPayments
  Scenario Outline: Validate Notes Are Editable On Invoice
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "<Amount>"
    And I add a payment via pay option "<PaymentOption>" in this amount "<PartialAmount>"
    Then I validate notes "<PaymentNotes>", transAmt "<TransactionAmount>", and transDate "<TransactionDate>" fields can be edit on payment
    Then I remove the customer

    Examples:
        | Amount        | PaymentOption  | PartialAmount | PaymentNotes                 | TransactionAmount | TransactionDate |
        | 200           | Cash           | 150.00        | This is a Cash Payment Note  | 50.00             |  1/7/2022       |
        | 200           | Check          | 150.00        | This is a Check Payment Note | 50.00             |  1/7/2022       |
        | 200           | Coupon         | 150.00        | This is a Coupon Payment Note| 50.00             |  1/7/2022       |

  @VerifyInvoiceNumber
  Scenario: Invoice Number Validation
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "150.00"
    Then I validate the invoice generates with an invoice number
    Then I remove the customer

  @VerifyInvoiceTaxableOption
  Scenario: Taxable Option Validation
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "150.00"
    And I add a payment via pay option "Cash" in this amount "100.00"
    Then I validate the taxable option on invoice
    Then I remove the customer

  @VerifyFrozenSubscriptionExpirationDateAdvancesWhenSendToJobPoolOptionUnchecked
  Scenario: Validate a FROZEN subscription's "Expiration Date" advances when the "Send to Job Pool" option is UN-CHECKED and paying with a "Single Use Credit Card"
    Given I retrieve the merchant's configured gateway
    When I create customer with first name, last name, email and address
    And I add a subscription with Service Type "Annual Automation Inspection" and an Expiration Date
    And I freeze the subscription in category "Refused Service" with reason "Cancelled by Automation Process"
    And I schedule and complete an appointment for subscription with Service Type "Annual Automation Inspection"
    And I process an one-time Single Use Card "4111 1111 1111 1111" or "5412 7501 0905 6250" for payment limited to a subscription
    Then I validate the Expiration Date on the subscription has advanced
    Then I validate the subscription's status is "ACTIVE"
    And I validate if there are errors exist in the list
    Then I remove the customer

  @VerifyActiveSubscriptionExpirationDateAdvancesWhenSendToJobPoolOptionUnchecked
  Scenario: Validate an ACTIVE subscription's "Expiration Date" advances when the "Send to Job Pool" option is UN-CHECKED and paying with a "Single Use Credit Card"
    Given I retrieve the merchant's configured gateway
    When I create customer with first name, last name, email and address
    And I add a subscription with Service Type "Annual Automation Inspection" and an Expiration Date
    And I schedule and complete an appointment for subscription with Service Type "Annual Automation Inspection"
    And I process an one-time Single Use Card "4111 1111 1111 1111" or "5412 7501 0905 6250" for payment limited to a subscription
    Then I validate the Expiration Date on the subscription has advanced
    And I validate if there are errors exist in the list
    Then I remove the customer

  @VerifyAmountsWithDecimalsCanBeRefunded
  Scenario: Validate refund amount with cents process successfully
    Given I retrieve the merchant's configured gateway
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "11.11" for service "Annual Automation Inspection"
    And I make full payment via pay option "Single Use Card" "4111 1111 1111 1111" or "5412 7501 0905 6250" for balance due
    And I give a full refund
    Then I validate the refund processed successfully
    And I validate if there are errors exist in the list
    Then I remove the customer

  @VerifyPaymentOrderIsProcessedAccordingToApplyToFirsSelection
  Scenario: Validate payment order is processed according to "Apply To First" selection
    Given I retrieve the merchant's configured gateway
    When I create customer with first name, last name, email and address
    And I create multiple standalone service invoices
    And I make a full payment via pay option Single Use Card "4111 1111 1111 1111" or "5412 7501 0905 6250" with (Apply To First) selected
    Then I validate the payment was applied only to the selected invoice
    And I validate if there are errors exist in the list
    Then I remove the customer

  @VerifyConsolidatedInvoicesWithAddons
  Scenario: Validate consolidated invoices with add-ons
    Given I enable Preferences option Eligible for Consolidation in the system
    When I create customer with first name, last name, email and address
    And I add a subscription with Service Type "One-Time Automation" and marked Eligible for Consolidation
    And I schedule and complete multiple appointments for the subscription "One-Time Automation"
    And  I include add-on "Animal Removal" to a completed service generated invoice marked eligible for consolidation
    And I consolidate all invoices
    Then I validate the Consolidated Totals are correct
    And I validate if there are errors exist in the list
    Then I remove the customer

  @VerifyIncorrectCardCharged_1
  Scenario: 127056-Verify "Billing Info Is Used..." Message is displayed for each linked account when trying to edit a shared credit card
    Given I retrieve the merchant's configured gateway
    And I create two linked customers with first name, last name, email, address and zip
    And I add a shared credit card to linked properties
    And I validate a message is displayed when editing details of a shared credit card by customers
    Then I validate if there are errors exist in the list
    Then I remove linked customers

  @VerifyIncorrectCardCharged_2
  Scenario: 127056-Verify "Billing Info Is Used..." Message is not displayed for each linked account when editing a credit card that is not shared
  Given I retrieve the merchant's configured gateway
  And I create two linked customers with first name, last name, email, address and zip
  And I add a non-shared credit card to each linked customer
  And I edit the linked customers' non-shared credit card and validate a <Billing Info Is Used> message is not displayed
  Then I validate if there are errors exist in the list
  Then I remove linked customers

  @VerifyIncorrectCardCharged_3
  Scenario: 127056- Verify a newly added credit card to a linked account is charged after removing a shared credit card
    Given I retrieve the merchant's configured gateway
    And I create two linked customers with first name, last name, email, address and zip
    And I add a shared credit card to linked properties
    Then I remove the shared credit card and add a non-shared credit card to a linked customer
    And I add a standalone invoice to a linked customer
    And I make full payment via pay option "Use Card On File" "4111 1111 1111 1111" or "5412 7501 0905 6250" for balance due
    Then I validate if there are errors exist in the list
    Then I remove linked customers
