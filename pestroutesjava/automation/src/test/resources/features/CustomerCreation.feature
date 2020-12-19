#Author: Aarbi
@Regression
@CreateCustomer
Feature: Create customer scenarios

  Scenario: Create a customer without address
    Given I sign in to pestroutes domain
    Given I delete a routing group
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver