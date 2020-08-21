#Author Aditya
@CreateRouteTemplate
Feature: Route Tempalte

  Scenario: Create a route template
    Given I sign in to pestroutes domain
    When I create a route with name "Automation Route Template"
    And I validate if there are errors exist in the list
    Then I generate route from "4":"30" to "18":"30" with interval "60"
    And I block route template "Testing" slot number "2"
    And I validate if there are errors exist in the list