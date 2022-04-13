# Author: Rex Jones II
# Customer Portal - Summary Tab
# URL: https://fieldroutes.atlassian.net/browse/QTP-1056
@RegressionREX
@Regression
@CustomerCard
@CustomerPortal
@CustomerPortalSummaryTab

Feature: Customer Portal - Summary Tab Displays Accurate Information

  As a user, I must see accurate information
  on the Customer Portal - Summary Tab
  for all customers

  Rule: User views the Summary Tab within the Customer Portal and see accurate information

    @VerifySummaryTabFirstNameInWelcomeBanner
    Scenario: Verify Customer First Name In The Welcome Banner Message via Summary Tab
      Given I Create A Customer With A Subscription
      When  I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify First Name In The Welcome Message via Summary Tab

    @VerifyCustomerInformationInPropertyDetailsSection
    Scenario: Verify Customer Account Number_Property Address_City_State_Zip_Email In Property Details Section
      Given I Create A Customer With A Subscription
      When  I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Property Details Section

    @VerifyLoveMessageAbovePropertyDetailsSection
    Scenario: Verify Share The Love Message Is Displayed Above Property Details Section
      Given I Create A Customer With A Subscription
      When  I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify Share The Love Message

    @VerifySummaryTabServicePlanHasCorrectServiceType
    Scenario: Verify Service Plan Section Contains The Correct Service Type via Summary Tab
      Given I Create A Customer With A Subscription
      When  I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify Service Type Is Correct In Summary Tab via Service Plan Section

    @VerifyServicePlanDoesNotShowFrozenSubscription
    Scenario: Verify Service Plans Section Does Not Show Frozen Subscription
      Given I Create A Customer With A Subscription
      When  I Deactivate-Freeze The Subscription
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify A Frozen Subscription Service Is Not Available via Service Plan Section

    @VerifyServicePlanDoesNotShowCancelledAppointments
    Scenario: Defect_Verify Service Plans Section Does Not Show Cancelled Scheduled Appointments
      Given I Create A Customer With A Subscription
      When  I Schedule An Appointment From The Routes Page
      And   I Cancel The Scheduled Appointment
      Then  I Verify The Cancelled Scheduled Appointment Is Not Displayed via Service Plan Section

    @VerifyBillingTabBalanceForUnpaidInvoice
    Scenario: Verify Responsible Balance via Summary Tab For An Unpaid Invoice
      Given I Create A Customer With A Subscription
      When  I Generate A Stand Alone Invoice
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Responsible Balance via Summary Tab Matches The Invoice Balance

    @VerifyFieldsInMostRecentServiceSectionAfterCompletingAppointment
    Scenario: Verify The Fields via Most Recent Service Section After Completing An Appointment
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Fields Contain Correct Information

    @VerifyLinksInMostRecentServiceSection
    Scenario: Verify Service Notification Link And Invoice Link via Most Recent Service Section
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Links Are Displayed via Most Recent Service Section

    @VerifyReviewAreaAndStarRatingInMostRecentServiceSection
    Scenario: Verify Technical Review Area And Star Rating via Most Recent Service Section
      Given I Create A Customer With A Subscription
      When  I Complete An Appointment
      And   I Navigate To Customer Portal From Customer Card - Admin Tab
      Then  I Verify The Technical Review Area And Star Rating Are Displayed via Most Recent Service Section

# (1) Ticket 139235: BETA - Cust portal Link taking customers to Old domain
# https://fieldroutes.freshdesk.com/a/tickets/139235
# (2) Ticket 140741: portal links directing to old portal URL
# https://fieldroutes.freshdesk.com/a/tickets/140741
  @VerifyCustomerPortalLinkIsDirectedToCorrectURL
  Scenario: Verify Customer Portal Link Is Directed To The Correct URL
    Given I Create A Customer With Basic Information
    And   I Navigate To Customer Portal From Customer Card - Admin Tab
    Then  I Verify The URL Contains The Correct SubDomain







