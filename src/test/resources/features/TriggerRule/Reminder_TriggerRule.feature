# Author: Rex Jones II
# Reminder Trigger Rule
#@RegressionREX
@TriggerRule
@TriggerRules
@Reminder_TriggerRule

Feature: Reminder Trigger Rule

  @VerifyTriggerDaysBeforeSendEmailForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Days Before
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Email Reminder" With "Standard Reminder Email" Type
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendSMSForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Include Customer Flag
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Standard Reminder Text Message" Type
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendVoiceForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Send Notification
    Given I Set Up A Customer "Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Voice Reminder" With "Standard Reminder Voice Message" Type
    When  I Add "Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Execute Trigger "triggerReminders" On Subdomain "anguyen" For Office "4"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger