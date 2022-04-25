# Author: Rex Jones II
# Customer Portal - Products Tab
@RegressionREX
@Regression
@CustomerCard
@CustomerPortalProductsTab

Feature: Customer Portal - Products Tab Displays Accurate Information

  As a user, I must see accurate information
  on the Customer Portal - Products Tab
  for all customers

  Rule: User views the Products Tab within the Customer Portal and see accurate information

@CustomerPortal
# Ticket 136364: Customer Portal White Font Color On White Background
# https://fieldroutes.freshdesk.com/a/tickets/136364
# Note: Make Sure The Service Type Has A Product And Complete The Appointment
  @VerifyProductsTableHeaderDoesNotDisplayWhiteBackground
  Scenario: Verify Products Table Header Does Not Display White Background
    Given I Create A Customer With A Subscription
    When  I Complete An Appointment
    And   I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify The Products Table Header Background Is Not White