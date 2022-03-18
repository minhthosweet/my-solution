# Author: Rex Jones II
# Appointment Status Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@AppointmentStatus_TriggerRule

Feature: Appointment Status Trigger Rule

  As a user, I want the Appointment Status Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyTriggerOnSaveSendEmailForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger On Save Send Email
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Any"
    And   I Complete "Send Email" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerQueue"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyStatusChangedForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Sends SMS When Status Changed
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Execute Trigger "triggerQueue"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyAddTaskForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Adds A Task
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Add Task" Action With "Specific Employee" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Execute Trigger "triggerQueue"
    Then  I Verify Tasks Are Added After Executing The Trigger

  @VerifyAddAlertForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Adds An Alert
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Scheduled"
    And   I Complete "Add Alert" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerQueue"
    Then  I Verify The Alert Has Been Added After Executing The Trigger

  @VerifyTriggerSendVoiceNotificationForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Sends Notification
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Send Voice" Action With "New Message" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Execute Trigger "triggerQueue"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger