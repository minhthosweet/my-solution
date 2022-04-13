# Author: Rex Jones II
# Customer Status Trigger Rule
# @RegressionREX
@TriggerRule
@TriggerRules
@SubscriptionStatus_TriggerRule

Feature: Subscription Status Trigger Rule

  As a user, I want the Subscription Status Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyTriggerOnSaveSendEmailForTriggerRuleSubscriptionStatus
  Scenario: Verify Subscription Status Trigger On Save Send Email
    Given I Set Up A Customer "Subscription Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up Trigger Type "Subscription Status" That "Trigger on Save" When Status Changed To "Frozen"
    And   I Complete "Send Email" Action With "Important" Details
    When  I Add "Subscription Status Automation Trigger Rule" Flag To The Customer Before Changing The Subscription Status
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyStatusChangedForTriggerRuleSubscriptionStatus
  Scenario: Verify Subscription Status Trigger Sends SMS When Status Changed
    Given I Set Up A Customer "Subscription Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up Trigger Type "Subscription Status" That "Trigger on Save" When Status Changed To "Frozen"
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Subscription Status Automation Trigger Rule" Flag To The Customer Before Changing The Subscription Status
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyAddTaskForTriggerRuleSubscriptionStatus
  Scenario: Verify Subscription Status Trigger Adds A Task
    Given I Set Up A Customer "Subscription Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up Trigger Type "Subscription Status" That "Trigger on Save" When Status Changed To "Frozen"
    And   I Complete "Add Task" Action With "Specific Employee" Details
    When  I Add "Subscription Status Automation Trigger Rule" Flag To The Customer Before Changing The Subscription Status
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify Tasks Are Added After Executing The Trigger

  @VerifyAddAlertForTriggerRuleSubscriptionStatus
  Scenario: Verify Subscription Status Trigger Adds An Alert
    Given I Set Up A Customer "Subscription Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up Trigger Type "Subscription Status" That "Trigger on Save" When Status Changed To "Frozen"
    And   I Complete "Add Alert" Action With "Important" Details
    When  I Add "Subscription Status Automation Trigger Rule" Flag To The Customer Before Changing The Subscription Status
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Alert Has Been Added After Executing The Trigger

  @VerifyTriggerSendVoiceNotificationForTriggerRuleSubscriptionStatus
  Scenario: Verify Subscription Status Trigger Sends Notification
    Given I Set Up A Customer "Subscription Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up Trigger Type "Subscription Status" That "Trigger on Save" When Status Changed To "Frozen"
    And   I Complete "Send Voice" Action With "New Message" Details
    When  I Add "Subscription Status Automation Trigger Rule" Flag To The Customer Before Changing The Subscription Status
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger