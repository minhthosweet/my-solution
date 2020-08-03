#Author Aditya
@TriggerRules
Feature: Structures

  @CreateStructureandChemical
  Scenario: Structure and Chemical Validation
    Given I sign in to pestroutes domain
    When I create customer with first name, last name, address, email and Structure
    And I add structure and sub structures
    When I create a subscription with Sales Rep assigned "Jared Green - Office" and "Fire"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule a subscription appointment
    And I search customer
    And I add chemicals to main structure
    And I add chemicals to substructures
    And I search customer
    Then I verify chemical in structure
    And I search customer
    Then I verify chemical in substructure
    And I validate if there are errors exist in the list