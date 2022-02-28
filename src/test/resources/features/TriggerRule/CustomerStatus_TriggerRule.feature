# Author: Rex Jones II
# Customer Status Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@CustomerStatus_TriggerRule

Feature: Customer Status Trigger Rule

  As a user, I want the Customer Status Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyTriggerOnSaveSendEmailForTriggerRuleCustomerStatus
  Scenario: Verify Customer Status Trigger On Save Send Email
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up A Trigger Type For "Customer Status" That "Trigger on Save" When Status Changed To "Active"
    And   I Complete "Send Email" Action With "Important" Details
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Updating The Customer Status
    And   I Execute Trigger "triggerQueue" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyStatusChangedForTriggerRuleCustomerStatus
  Scenario: Verify Customer Status Trigger Sends SMS When Status Changed
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up A Trigger Type For "Customer Status" That "Trigger on Save" When Status Changed To "Active"
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Updating The Customer Status
    And   I Execute Trigger "triggerQueue" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyAddTaskForTriggerRuleCustomerStatus
  Scenario: Verify Customer Status Trigger Adds A Task
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up A Trigger Type For "Customer Status" That "Trigger on Save" When Status Changed To "Any"
    And   I Complete "Add Task" Action With "Specific Employee" Details
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Updating The Customer Status
    And   I Execute Trigger "triggerQueue" On Subdomain "anguyen" For Office "4"
    Then  I Verify Tasks Are Added After Executing The Trigger

  @VerifyAddAlertForTriggerRuleCustomerStatus
  Scenario: Verify Customer Status Trigger Adds An Alert
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up A Trigger Type For "Customer Status" That "Trigger on Save" When Status Changed To "Active"
    And   I Complete "Add Alert" Action With "Important" Details
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Updating The Customer Status
    And   I Execute Trigger "triggerQueue" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Alert Has Been Added After Executing The Trigger

  @VerifyTriggerSendVoiceNotificationForTriggerRuleCustomerStatus
  Scenario: Verify Customer Status Trigger Sends Notification
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up A Trigger Type For "Customer Status" That "Trigger on Save" When Status Changed To "Any"
    And   I Complete "Send Voice" Action With "New Message" Details
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Updating The Customer Status
    And   I Execute Trigger "triggerQueue" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger