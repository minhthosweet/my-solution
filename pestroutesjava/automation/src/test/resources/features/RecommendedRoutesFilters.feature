#Author: Faraz
@RegressionFRK
@RecommendedRoutes
Feature: Validate Recommended Routes

  Scenario: Validate Recommended Routes Filters
    When I create customer with first name, last name, address and generic flag "Test4Life"
    And I create a renewal subscription
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I validate recommended route filters and schedule an appointment
    And I search customer
    And I complete an appointment