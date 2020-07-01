#Author: Aarbi
@Smoke
Feature: Create customer with address

	Scenario: Create a customer with address
		Given I sign in to pestroutes domain
    When I create customer without required last name field
    Then I validate alert
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I validate if there are errors exist in the list
    
  Scenario: Validate subscription upcoming appointments
    When I start a regular subscription
    Then I validate upcoming appointments per each day
    And I validate if there are errors exist in the list
    
  Scenario: Validate subscription invoices
  	When I start a regular subscription
  	Then I validate initial invoice
  	Then I validate recurring invoice
  	Then I validate billing frequency by month
  	Then I validate billing frequency by annually
  	And I validate if there are errors exist in the list