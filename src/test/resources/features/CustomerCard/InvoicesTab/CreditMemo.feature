#Author: Aditya
@CustomerCard
@Smoke
@InvoicesTab
@RegressionADI
@CreditMemo
Feature: Credit Memo

  @CreateAndValidateCreditMemo
  Scenario: Add Credit Memo to StandAlone invoice and validate
    When I create customer with balance with prefers paper and residential property type
    And I create a credit memo for an existing invoice
    Then I validate the credit memo invoice
    And I remove the customer
    And I validate if there are errors exist in the list

# Author: Rex Jones II
# Ticket 103672: Credit Memo Error (Unable to change invoice amount that has credit memo)
# https://fieldroutes.freshdesk.com/a/tickets/103672
  @RegressionREX
  @VerifyInvoiceAmountWithCreditMemoCanChange
  Scenario: Change The Invoice Amount For An Invoice With A Credit Memo
    Given I Create A Customer With A Subscription
    When  I Complete An Appointment
    And   I Pay Off A Non Stand Alone Invoice
    Then  I Can Change The Invoice Amount Which Has A Credit Memo