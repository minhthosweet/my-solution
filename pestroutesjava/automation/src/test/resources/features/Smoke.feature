#Author: Aarbi
@Smoke
Feature: Create customer with address

  @CreateCustomer
  Scenario: Create a customer with address
    Given I sign in to pestroutes domain
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list

  @CreateSubscription
  Scenario: Validate subscription upcoming appointments
    When I start a regular subscription
    Then I validate upcoming appointments per each day
    And I validate if there are errors exist in the list

  @CreateInvoice
  Scenario: Validate subscription invoices
    When I start a regular subscription
    Then I validate initial invoice
    Then I validate recurring invoice
    Then I validate billing frequency by month
    Then I validate billing frequency by annually
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


  Scenario: Close browser
    And I quit driver