# Author: Rex Jones II
# Customer Portal - History Tab
# URL: https://fieldroutes.atlassian.net/browse/QTP-1056
@RegressionREX
@Regression
@CustomerCard
@CustomerPortal
@CustomerPortalHistoryTab

Feature: Customer Portal - History Tab Displays Accurate Information

  As a user, I must see accurate information
  on the Customer Portal - History Tab
  for all customers

  Rule: User views the History Tab within the Customer Portal and see accurate information

  Scenario: Verify Service History Section Contains Completed Appointments
    Given I Create A Customer With A Subscription
    When  I Complete An Appointment
    And   I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify The Service History Section Contains Accurate Information For Completed Appointment

  Scenario: Verify Customer First Name In The Welcome Banner Message via History Tab
    Given I Create A Customer With A Subscription
    When  I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify First Name In The Welcome Message via History Tab

  Scenario: Verify Responsible Balance via History Tab For An Unpaid Invoice
    Given I Create A Customer With A Subscription
    When  I Generate A Stand Alone Invoice
    And   I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify The Responsible Balance via History Tab Matches The Invoice Balance

  Scenario: Verify Clicking The View Details Button Transitions To The Billing Tab
    Given I Create A Customer With A Subscription
    When  I Generate A Stand Alone Invoice
    And   I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify View Details Button Directs A User To The Billing Tab

  Scenario: Verify Service Plan Section Contains The Correct Service Type via History Tab
    Given I Create A Customer With A Subscription
    When  I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify Service Type Is Correct In History Tab via Service Plan Section