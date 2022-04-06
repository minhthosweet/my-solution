#Author: Frankie White
#
# Single or Day Routes Optimization Process optimize a single route for max utilization of time
# Ticket-123000: Verify an error message is displayed when customer is locate in a different time zone
# Ticket- 124337: Verify the Single Route Optimization executes successfully
@Scheduling
@Smoke
@Regression
@Regression_FWhite
Feature: Validate Single or Day Route Optimization Process

  @ExecuteSingleRouteOptimizingProcess_1
  Scenario: 124337 - Execute Single Route Optimizing Process
    Given I create a route in a route group "Optimized Routes" with template type "TestRoutes"
    And I create multiple customers for Route Optimization
    And I add a subscription and schedule an appointment for each customer
    And I execute the Single Route Optimization Process
    Then I validate the Single Route Optimization Process Completed Successfully
    Then I remove customers used in Single Route Optimization Process
    Then I delete route from route group "Optimized Routes"
    Then I delete route group "Optimized Routes"
    Then I validate if there are errors exist in the list

  @ExecuteSingleRouteOptimizingProcess_2
  Scenario: 123000 - Execute Single Route Optimizing Process when customer and tech are in  different time zones
#   Given I retrieve the zip code of the company's Physical Location address
    Given I create a route in a route group "Optimized Routes v2" with template type "TestRoutes"
    And I create multiple customers and one with an address in a different time zone EST with zip "10013"
    And I add a subscription and schedule an appointment for each customer
    And I execute the Single Route Optimization Process
    Then I validate the Single Route Optimization Process Failed with error message "One or more routes failed due to conflicting time zones."
    Then I remove customers used in Single Route Optimization Process
    Then I delete route from route group "Optimized Routes v2"
    Then I delete route group "Optimized Routes v2"
    Then I validate if there are errors exist in the list