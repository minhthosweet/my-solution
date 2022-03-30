# Author: Rex Jones II
# Auto Payment Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@AutoPayment_TriggerRule

Feature: Auto Payment Trigger Rule

  As a user, I want the Auto Payment Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyAutoPaymentTriggerRuleAutomaticallyChargesCustomerCC
  Scenario: Verify Auto Payment Trigger Rule Automatically Charges A Customer Credit Card
    Given I Set Up The Application For "PestRoutes Payments"
    Given I Set Up A Customer "Auto Payment Automation Trigger Rule" Flag If The Flag Does Not Exist
    When  I Set Up "Auto Payment" Trigger Type
    When  I Complete The "Process Auto Payment" Action
    And   I Add "Auto Payment Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Select Credit Card "CC - Visa - 1111" For Auto Pay Using "PestRoutes Payments", "4111 1111 1111 1111", "12/25", "234"
    And   I Execute Trigger "triggerAutoPay"
    Then  I Verify The Customer Has A Fully Paid Balance After Being Charged via Auto Pay

  @VerifyAutoPaymentTriggerRuleAutomaticallyChargesCustomerACH
  Scenario: Verify Auto Payment Trigger Rule Automatically Charges A Customer ACH
    Given I Set Up The Application For "Element"
    Given I Set Up A Customer "Auto Payment Automation Trigger Rule" Flag If The Flag Does Not Exist
    When  I Set Up "Auto Payment" Trigger Type
    When  I Complete The "Process Auto Payment" Action
    And   I Add "Auto Payment Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Select Bank Account For Auto Pay
    And   I Execute Trigger "triggerAutoPay"
    Then  I Verify The Customer Has A Fully Paid Balance After Being Charged via Auto Pay