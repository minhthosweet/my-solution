#Author Aditya
@RegressionTest
Feature: End to end testing on the application

  Scenario: Create a customer with address
    Given I sign in to pestroutes domain
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list

  Scenario: Create Initial Invoice of type After Initial Completion
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    And I validate initial invoice created on invoice tab
    And I validate if there are errors exist in the list

  Scenario: Create Initial Invoice of type After Agreement Signed
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I validate the if agreement is created
    And I search customer
    And I validate initial invoice created on invoice tab
    And I validate if there are errors exist in the list

  Scenario: Create a route template
    Then I navigate to Route Templates
    When I create a route template
    Then I generate route from "4":"30" to "18":"30" with interval "60"
    And I block route template slot number "2" with block description "Testing"
    And I have a route template
    When I navigate to Route Templates
    Then I delete the route template
    And I validate if there are errors exist in the list

  Scenario: Validate Renewal
    Given I add a renewal service
    When I create customer with first name, last name and address
    And I validate if renewal fields display in Subscription tab if I choose renewal as service type
    And I create a renewal subscription
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    Then I validate if the renewal date has posted
    And I freeze the subscription
    And I get the subscription total
    And I search customer
    And I pay the subscription
    And I search customer
    Then I validate if renewal date and account status changed
    And I validate if there are errors exist in the list
    Given I close customer card
    And I navigate to scheduling tab
    Then I delete a routing group

  Scenario: Structure and Chemical Validation
    When I create customer with first name, last name, address, email and Structure
    And I add structure and sub structures
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I close customer card
    And I navigate to scheduling tab
    And I add a route group if not already existing
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I add chemicals to main structure
    And I add chemicals to substructures
    Then I verify chemical in structure
    Then I verify chemical in substructure
    And I validate if there are errors exist in the list
    And I close customer card
    And I navigate to scheduling tab
    Then I delete a routing group

  Scenario: Add equipment type on customer card
    Given I add equipment type "ID1" and "Automation Test1" and "Yes"
    When I create customer with first name, last name and address
    And I add new equipment with barcode required "Automation" and "Automation Test1" and "DE1" and "1234" and "Injection" and "Test123" and "Bed" and "Bed Bugs" and "Special Product"
    And I search customer
    And I verify that the equipment was added "Automation Test1"
    Given I add equipment type "ID2" and "Automation Test2" and "No"
    When I create customer with first name, last name and address
    And I add new equipment with barcode required "Automation2" and "Automation Test2" and "DE2" and "1234" and "Injection" and "" and "Bed" and "Bed Bugs" and "Special Product"
    And I search customer
    And I verify that the equipment was added "Automation Test2"
    And I validate if there are errors exist in the list

  Scenario: Validate sales report totals
    Given I create a new user if it is not already existing "Office Staff"
    Given I add a renewal service
    Given I add a new generic flag if it is not already existing "Fire" and "Its lit" and "Subscription"
    Given I add a new generic flag if it is not already existing "Test4Life" and "Test4Life" and "Customer"
    When I create customer with first name, last name, address and generic flag "Test4Life" and "Door to Door"
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
    And I validate if there are errors exist in the list

  Scenario: Create Initial Invoice of type On Initial Billing Date
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I search customer
    And I navigate to Subscription Tab
    And I add a custom frequency recurring service
    Then I validate upcoming appointments for custom recurring appointments

   Scenario: Create new lead
    When I create customer with first name, last name and address
    And I create a new lead
    Then I validate lead creation invoices
    And I convert a successful lead to subscription and I verify it in the subscriptions tab

  Scenario: Create customer and verify all tax rates
    Then I create customer with address and ZipCode and I verify Main Tax, State Tax, City Tax, County Tax, Custom Tax, District1 Tax, District2 Tax, District3 Tax, District4 Tax, District5 Tax Rates "2310 Farrington Drive" and "75044" and "8.2500%" and "6.2500%" and "1.0000%" and "0.0000%" and "0.0000%" and "1.0000%" and "0.0000%" and "0.0000%" and "0.0000%" and "0.0000%"
    And I validate if there are errors exist in the list

  Scenario: Create initial and recurring invoice and validate them
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Initial Completion"
    And I close customer card
    And I search customer
    And I navigate to Subscription Tab
    Then I validate initial invoice template values
    Then I validate recurring invoice template values
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    And I validate initial invoice created on invoice tab
    And I navigate to Subscription Tab
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    And I validate recurring invoice created on invoice tab
    Then I close customer card
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver
