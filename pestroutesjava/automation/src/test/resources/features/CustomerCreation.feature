#Author: Aarbi
@Regression
Feature: Create customer scenarios

  Scenario: Create a customer without address
    Given I sign in to pestroutes domain
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name and last name
    Then I validate if customer name match in overview tab
    And I validate if there are errors exist in the list

	Scenario: Create a customer with address
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list