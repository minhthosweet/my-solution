@Smoke
@DocumentTab
@RegressionFRK
@Agreement
Feature: Customer Status

  Scenario: Create Initial Invoice of type After Agreement Signed
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I search customer
    And I validate the if agreement is created
    And I search customer
    And I validate initial invoice created on invoice tab
    Then I generate Account Statement Report of report type "Account Transactions" for "Yesterday"
    And I validate the Beginning balance and Ending balance for a day before the invoice was created
    And Validating beginning balance for invoice created "Today" of report type "Account Transactions"
    And Validating ending balance for invoice created "Today" of report type "Account Transactions"
    And Validating invoice balance for invoice created "Today" of report type "Account Transactions"
    And I print report and validate totals and enter notes "Automation Testing"
    And I remove the customer
    And I validate if there are errors exist in the list
