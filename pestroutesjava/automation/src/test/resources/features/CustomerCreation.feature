#Author: Aarbi
@Regression
@NewCustomerValidation
Feature: Create ans search customer with validation

  @CreateCustomerValidation
  Scenario: Create a customer
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate search customer with first name
    And I validate search customer with last name
    And I validate search customer with phone number
    And I validate search customer with last four of phone number
    And I validate search customer with zip code
    And I validate search customer with CustomerID
    And I validate search customer with street address
    And I validate if there are errors exist in the list

  Scenario: Close browser
    And I quit driver