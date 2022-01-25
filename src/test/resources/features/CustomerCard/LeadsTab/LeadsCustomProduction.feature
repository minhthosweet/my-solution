# Author: Rex Jones II
# Ticket 110239: Custom Production Not working Properly
# https://fieldroutes.freshdesk.com/a/tickets/110239
@CustomerCard
@RegressionREX
@Regression
@LeadsTab

Feature: Leads Custom Production

  As a user, I must see the correct Custom Production amount
  on the Leads Tab within a Customer's Card
  for Recurring Invoice Template

  Rule: The user creates a lead using Recurring Invoice Template and Custom Production updates to the correct amount

  @VerifyCustomProductionInLeadsTab
  Scenario Outline: Verify The Custom Production Amount Is Correct For A Lead Using The Recurring Invoice Template
    Given I Create A Customer With A Lead
    When  I Select "<Frequency>" From Service Info Section
    And   I Enter A Recurring Service Type Amount "100.00"
    Then  I See The Correct Custom Production Amount When Multiplying "<Number>" Times The Recurring Service Type Amount

    Examples:
      | Frequency         | Number  |
      | Alternate Monthly | 2       |
      | Every 4 Months    | 4       |
      | Semi-Annually     | 6       |
