#Author: Aditya
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