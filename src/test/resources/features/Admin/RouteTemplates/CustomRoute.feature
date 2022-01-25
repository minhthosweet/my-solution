#Author Aditya
@Admin
@Regression
@RouteTemplates
@CustomRoute
@RegressionADI
Feature: Route Template

  Scenario: Create a route template
    Then I navigate to Route Templates
    When I create a route template
    Then I generate route from "4":"30" to "18":"30" with interval "60"
    And I block route template slot number "2" with block description "Testing"
    And I have a route template
    When I navigate to Route Templates
    Then I delete the route template
    And I validate if there are errors exist in the list