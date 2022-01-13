# Author: Frankie White
# Ticket 120352: Cannot edit notes on payments
# Ticket 120838: Invoice generating without number attached/Customer db issue
# Ticket 127695: Taxable Option Broken on Invoice"
@Regression_FWhite
@CustomerCard
@VerifyCustomerNotesAreEditableForCashAndCheckPayments
@VerifyInvoiceTaxableOption
@VerifyInvoiceNumber

Feature: Validate Customer Invoice
    This feature verifies various invoice functionalities...
    1. Verifies the "customer notes", "transaction amount", and  "transaction date & time"  fields on  "Cash",
       "Check", and "Coupon Payment" payments are editable
    2. Verifies the taxable option functions as expected
    3. Verifies a newly created invoice has an invoice number

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

  Scenario: Invoice Number Validation
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "150.00"
    Then I validate the invoice generates with an invoice number
    Then I remove the customer

  Scenario: Taxable Option Validation
    When I create customer with first name, last name, email and address
    And I create standalone service invoice "150.00"
    And I add a payment via pay option "Cash" in this amount "100.00"
    Then I validate the taxable option on invoice
    Then I remove the customer