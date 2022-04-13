# Author: Rex Jones II
# Appointment Status Trigger Rule
# @RegressionREX
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
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyStatusChangedForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Sends SMS When Status Changed
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyAddTaskForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Adds A Task
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Add Task" Action With "Specific Employee" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify Tasks Are Added After Executing The Trigger

  @VerifyAddAlertForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Adds An Alert
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Scheduled"
    And   I Complete "Add Alert" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Alert Has Been Added After Executing The Trigger

  @VerifyTriggerSendVoiceNotificationForTriggerRuleAppointmentStatus
  Scenario: Verify Appointment Status Trigger Sends Notification
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger Type That "Trigger on Save" When Status Changed To "Canceled"
    And   I Complete "Send Voice" Action With "New Message" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger

# Ticket 120690: Appointment Status Trigger Rule Not Firing
# https://fieldroutes.freshdesk.com/a/tickets/120690
  @VerifyAppointmentStatusTriggerAppropriateServiceTypeDivision
  Scenario: Verify Appointment Status Trigger For Appropriate Service Type & Division
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger That "Trigger on Save" When Status Changed To "Scheduled" For "Automation Renewal" & "Automation Division"
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag, "Automation Division", "Automation Renewal" To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

# Ticket 121468: Trigger Not Creating Tasks
# https://fieldroutes.freshdesk.com/a/tickets/121468
  @VerifyAppointmentStatusTriggerCreatesTaskForSpecificEmployee
  Scenario: Verify Appointment Status Trigger Creates Task For Specific Employee
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger That "Trigger on Save" When Status Changed To "Canceled" With Excluded "Automation Subscription Flag"
    And   I Complete "Add Task" Action With "Specific Employee" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Canceling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify Tasks Are Added After Executing The Trigger

# Ticket 126592: Rescheduled Trigger Rule Is Not Working
# https://fieldroutes.freshdesk.com/a/tickets/126592
  @VerifyAppointmentStatusWorksForRescheduledTriggerWithActionSMS
  Scenario: Verify Appointment Status Works For Rescheduled Trigger With SMS Action
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger That Changes To A "Rescheduled" Status With "Minutes" Notification Buffer For "1" Increment
    And   I Complete "Send SMS" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag To The Customer Before Rescheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing 2 Triggers Called "smsQueue" & "triggerQueue"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

# Ticket 122078: Welcome Letter Trigger Rule Does Not Send Email
# https://fieldroutes.freshdesk.com/a/tickets/122078
  @VerifyAppointmentStatusTriggerForInitialServiceSendsEmails
  Scenario: Verify Appointment Status Trigger For Initial Service Sends An Email
    Given I Set Up A Customer "Appointment Status Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Appointment Status" Trigger That "Trigger on Save" When "Scheduled" Status Changed For "Is Initial Service" & "Automation Renewal"
    And   I Complete "Send Email" Action With "Important" Details
    When  I Add "Appointment Status Automation Trigger Rule" Flag & "Automation Renewal" To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerQueue"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger