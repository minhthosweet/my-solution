#Author: Faraz
@CustomerCard
@Smoke
@LeadsTab
@Leads
@RegressionFRK
Feature: Test Lead Creation
  Scenario: Create new lead
    Given I create a new user if it is not already existing "Office Staff"
    When I create customer with first name, last name and address
    And I create a new lead
    Then I validate lead creation invoices
    And I convert a successful lead to subscription and I verify it in the subscriptions tab
    And I validate if there are errors exist in the list
