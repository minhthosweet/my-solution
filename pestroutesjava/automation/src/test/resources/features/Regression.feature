#Author Aditya
@RegressionTest
Feature: End to end testing on the application

  @CreateCustomer
  Scenario: Create a customer with address
    Given I sign in to pestroutes domain
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list

  @CreateInitialInvoiceAfterInitialCompletion
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

  @CreateInitialInvoiceAfterAgreementSigned
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

  @CreateRouteTemplate
  Scenario: Create a route template
    Then I navigate to Route Templates
    When I create a route template
    Then I generate route from "4":"30" to "18":"30" with interval "60"
    And I block route template slot number "2" with block description "Testing"
    And I have a route template
    When I navigate to Route Templates
    Then I delete the route template
    And I validate if there are errors exist in the list

  @CreateServiceAppointment
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

  @CreateStructureAndChemical
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

  @AddEquipment
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

    @AddSalesRep
    Scenario: Validate sales report totals
    Given I create a new user if it is not already existing "Sales Rep"
    Given I add a renewal service
    Given I add a new generic flag if it is not already existing "Fire" and "Its lit" and "Subscription"
    When I create customer with first name, last name and address
    And I create a subscription with Sales Rep assigned "Automation User - Sales Rep" and "Fire"
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
