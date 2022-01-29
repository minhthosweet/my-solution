# Author: Rex Jones II
# AR Trigger Rule
@RegressionREX
@Regression
@TriggerRule
@TriggerRules
@AR_TriggerRule

Feature: AR - Age Trigger Rule

  Scenario: Verify AR Age Sends Email To A Customer
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "AR" Trigger Type That Has "Age" Filter With "Send Email" Action
    When  I Add "Automation Trigger Rule" Flag To The Customer With A New Invoice
    And   I Execute Trigger "triggerEvents" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received An Email After Executing The Trigger Rule

