#Author: Faraz
@Sales
Feature: New sales report

  Scenario: Validate sales report totals
     Given I sign in to pestroutes domain
     Given I create a new user if it is not already existing "Salesman"
     Given I add a renewal service
     When I create customer with first name, last name and address
     And I create a subscription with Sales Rep assigned "Automation User - Sales" and "Fire"
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
     And I validate if there are errors exist in the list