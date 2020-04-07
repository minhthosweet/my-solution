#Author Aarbi
@Regression
Feature: Subscription tab

  Scenario: Validate subscription upcoming appointments
    Given I sign in to pestroutes domain
    When I start a regular subscription
    Then I validate upcoming appointments per each day
    And I validate if there are errors exist in the list

  Scenario: Validate initial invoice
  	Given I sign in to pestroutes domain
  	When I start a regular subscription
  	Then I validate initial invoice
  	And I validate if there are errors exist in the list
  	