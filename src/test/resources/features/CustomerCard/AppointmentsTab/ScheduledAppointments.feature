# Author: Rex Jones II
# Ticket 111187: Scheduled Appointment Rendering Glitch in Customer Card Appointments
# https://fieldroutes.freshdesk.com/a/tickets/111187
@RegressionREX
@Regression
@CustomerCard
@AppointmentsTab

Feature: Appointments Tab Renders Correct Information After Scheduling An Appointment

  As a user, I must see the correct appointment information
  on the Appointments Tab immediately
  after scheduling an appointment via Routes Page

  Rule: User views the Appointments tab and sees the correct information

  @RegressionREX_CustomerCard
  @VerifyAppointmentInfoOnAppointmentsTab
  Scenario: Verify Appointments Tab Displays Correct Information Immediately After Scheduling An Appointment
    Given I Open The Appointments Tab
    When  I Schedule An Appointment From The Routes Page
    And   I Cancel The Scheduled Appointment
    But   I Schedule Another Appointment From The Routes Page
    Then  I See The Correct Appointment Information On The Appointments Tab



