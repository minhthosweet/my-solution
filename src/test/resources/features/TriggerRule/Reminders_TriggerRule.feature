# Author: Rex Jones II
# Reminders Trigger Rule
# @RegressionREX
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
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendSMSForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Include Customer Flag
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Standard Reminder Text Message" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

  @VerifyTriggerDaysBeforeSendVoiceForTriggerRuleReminders
  Scenario: Verify Reminder Trigger Rules Send Notification
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Voice Reminder" With "Standard Reminder Voice Message" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "Voice" Note After Executing The Trigger

# Ticket 108825: Trigger Rule Not Working Properly
# https://fieldroutes.freshdesk.com/a/tickets/108825
  @VerifyReminderTriggerRuleWorkForCustomSMS
  Scenario: Verify Reminder Trigger Rules Work Properly For Custom SMS
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Custom Text Message" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "SMS" Note After Executing The Trigger

# Ticket 108825: Trigger Rule Not Working Properly
# https://fieldroutes.freshdesk.com/a/tickets/108825
  @VerifyReminderTriggerRuleWorkForCustomEmail
  Scenario: Verify Reminder Trigger Rules Work Properly For Custom Email
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Email Reminder" With "Custom Reminder Email" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "Email" Note After Executing The Trigger

# Ticket 123678: Email Header Showing Incorrect Date of Wednesday, DEC 31ST
# https://fieldroutes.freshdesk.com/a/tickets/123678
  @VerifyCorrectDateForRemindersTriggerRule
  Scenario: Verify Correct Date For Reminder Trigger Rules
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send Email Reminder" With "Custom Reminder Email" Type
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    Then  I Verify The Customer Received "Email" Note With Correct Dates After Executing The Trigger

# Ticket 123772: SMS Reminders Delayed Until Next Day
# https://fieldroutes.freshdesk.com/a/tickets/123772
  @VerifyRemindersTriggerIsProcessedToSendOnCorrectDayAndTimeForCustomSMS
  Scenario: Verify Reminders Trigger Is Processed To Send On Correct Day And Time For Custom SMS Text Message
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Custom Text Message" Type
    # Expecting, This Scenario To Be Executed After The Below Time Frame So Processing Is Carried Out Tomorrow Morning
    When  I Set The SMS Delivery Settings Window From "2:00 AM" To "2:30 AM"
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    But   I Set The SMS Delivery Settings Window From "4:00 AM" To "6:00 PM"
    Then  I Verify The Customer Received "SMS" Note That Begin Processing ETA Tomorrow Between "2:00" AM & "2:30" AM

  @VerifyRemindersTriggerIsProcessedToSendOnCorrectDayAndTimeForStandardSMS
  Scenario: Defect_Verify Reminders Trigger Is Processed To Send On Correct Day And Time For Standard SMS Text Message
    Given I Set Up A Customer "Reminders Automation Trigger Rule" Flag If The Flag Does Not Exist
    Given I Set Up "Reminders" Trigger Type That "Trigger Days Before" An Appointment
    And   I Complete An Action To "Send SMS Reminder" With "Standard Reminder Text Message" Type
    # Expecting, This Scenario To Be Executed After The Below Time Frame So Processing Is Carried Out Tomorrow Morning
    When  I Set The SMS Delivery Settings Window From "2:00 AM" To "2:30 AM"
    When  I Add "Reminders Automation Trigger Rule" Flag To The Customer Before Scheduling An Appointment
    And   I Reset The Most Recent Date Trigger Before Executing A Trigger Called "triggerReminders"
    But   I Set The SMS Delivery Settings Window From "4:00 AM" To "6:00 PM"
    Then  I Verify The Customer Received "SMS" Note That Begin Processing ETA Tomorrow Between "2:00" AM & "2:30" AM
