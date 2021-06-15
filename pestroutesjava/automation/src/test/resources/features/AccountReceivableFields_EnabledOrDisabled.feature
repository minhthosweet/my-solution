#Author: Aarbi
@RegressionARB
@AccountReceivableScenarios
Feature: Validate if all fields present and are enabled
  @FieldValidations_AccountReceivable
  Scenario: Fields validations
    Given I add a renewal service
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
#    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I validate the if agreement is created
    And I close customer card
    And I navigate to account receivable under Billings
    Then I validate if all fields are displaying and are enabled in account receivable page
    Then I validate if the customer displays once account status is Active in account receivable page
    And I validate if there are errors exist in the list
  @AutoPayCC_Customer_AccountReceivable
  Scenario: Customer with CC Auto Pay validation
    When I create customer with first name, last name, email and address
    Then I validate CC auto pay customer display in account receivable page
    And I validate if there are errors exist in the list
  @AutoPayACH_Customer_AccountReceivable
  Scenario: Customer with ACH Auto Pay validation
    When I create customer with first name, last name, email and address
    Then I validate ACH auto pay customer display in account receivable page
    And I validate if there are errors exist in the list
  @Customer_PropType_AccountReceivable
  Scenario: Customer with prop type validation
    When I create customer with first name, last name, email and address
    Then I validate customer by prop type in account receivable page
    And I validate if there are errors exist in the list
  @CustomerWithBalance_BalanceAge_PaymentDue_DaysOverDue_AccountReceivable
  Scenario: Customer balance validations
    Then I validate customer with balance in account receivable page
    Then I validate customer with balance age, payment due, and days overdue in account receivable page
    And I validate if there are errors exist in the list
  @CustomerWithPrefPaper_AccountReceivable
  Scenario: Customer with pref paper validation
    When I create customer with pref paper
    Then I validate customer with pref paper in account receivable page
    And I validate if there are errors exist in the list
  @CustomerWithEmail_AccountReceivable
  Scenario: Customer has email validation
    When I create customer with first name, last name, email and address
    Then I validate customer has email in account receivable page
    And I validate if there are errors exist in the list
  @CustomerWithMaxMonthlyLimit_AccountReceivable
  Scenario: Auto pay customer with max monthly validation
    When I create customer with first name, last name, email and address
    Then I validate autopay customer with max monthly in account receivable page
    And I validate if there are errors exist in the list
  @CustomerWithFlags_AccountReceivable
  Scenario: Customer with flag validation
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Its lit" and "Customer"
    Given I add a new generic flag if it is not already existing "Test4Life" and "Test4Life" and "Customer"
    And I create customer with first name, last name, address and generic flag "Test4Life" and "Door to Door"
    Then I validate customer with including "Test4Life" and excluding "Automation Flag" flags and groupBy "Group By Bill To" and collection "Not Set" in account receivable page with CC "4111111111111111" or "5412750109056250"
    Then I send "SMS" message under actions with subject "TestSubject" and text "testText"
    Then I remove the customer
    And I validate if there are errors exist in the list
  @CustomerWithStatus_AccountReceivable
   Scenario: Customer with status validation
    When I create customer with first name, last name, email and address
    Then I validate autopay customer "CC Auto Pay" and with status "Successful" in account receivable page
    Then I send "Voice" message under actions with subject "TestSubject" and text "testText"
    Then I remove the customer
    And I validate if there are errors exist in the list
