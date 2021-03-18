#Author: Aditya
@RegressionADI
Feature: Credit Memo

  @CreateAndValidateCreditMemo
  Scenario: Add Credit Memo to StandAlone invoice and validate
    When I create customer with balance with prefers paper and residential property type
    When I search the number "1" customer in History tab
    And I create a credit memo for an existing invoice
    Then I validate the credit memo invoice
    And I validate if there are errors exist in the list