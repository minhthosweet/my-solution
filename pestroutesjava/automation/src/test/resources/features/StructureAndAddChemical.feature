#Author Aditya
@StructuresValidation
@RegressionADI
Feature: Structures

  Scenario: Structure and Chemical Validation
    Given I delete a routing group
    When I create customer with first name, last name, address, email and Structure
    And I add structure and sub structures
    When I create a subscription of type "After Agreement Signed"
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I add chemicals to main structure
    And I add chemicals to substructures
    Then I verify chemical in structure
    Then I verify chemical in substructure
    And I close customer card
    And I remove the customer
    And I validate if there are errors exist in the list
