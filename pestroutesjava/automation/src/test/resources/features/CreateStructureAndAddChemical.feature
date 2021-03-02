#Author Aditya
@Structures
Feature: Structures

  @CreateStructureandChemical
  Scenario: Structure and Chemical Validation
    Given I delete a routing group
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

  Scenario: Close browser
    And I quit driver