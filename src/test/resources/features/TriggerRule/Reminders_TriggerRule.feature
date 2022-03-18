# Author: Rex Jones II
# Reminders Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@Reminders_TriggerRule

Feature: Reminders Trigger Rule

  As a user, I want the Reminders Trigger Rule to be a rule that automatically
  runs to perform an action. It has 2 parts Filter/Condition and Action.
  If a condition happens then the rule performs an action.

  @VerifyTriggerDaysBeforeSendEmailForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Days Before
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Email Reminder" With "Standard Reminder Email" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendSMSForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Include Customer Flag
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Standard Reminder Text Message" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendVoiceForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Send Notification
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Voice Reminder" With "Standard Reminder Voice Message" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger