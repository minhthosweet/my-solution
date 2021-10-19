#Author: Faraz
@Regression
@RegressionFRK
@SalesReport
Feature: New sales report

  Scenario: Validate sales report totals
    Given I create a new user if it is not already existing "Office Staff"
    Given I add a renewal service
    Given I add a new generic flag if it is not already existing "Fire" and "Its lit" and "Subscription"
    Given I add a new generic flag if it is not already existing "Test4Life" and "Test4Life" and "Customer"
    When I create customer with first name, last name, address and generic flag "Test4Life"
    And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    And I run sold subscription report
    And I validate subscription flag column
    And I validate sales report totals
    Then I deactivate the existing user
    And I remove the customer
    And I validate if there are errors exist in the list
