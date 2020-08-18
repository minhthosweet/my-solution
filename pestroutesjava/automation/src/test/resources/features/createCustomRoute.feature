#Author Aditya
@CreateRouteTemplate
Feature: Route Tempalte

  Scenario: Create a route template
    Given I sign in to pestroutes domain
    When I create a route with name "Testing"
    And I validate if there are errors exist in the list
    Then I generate route from "8":"10" to "17":"10" with interval "60"
    And I block route template "Testing" slot number "2"
    And I validate if there are errors exist in the list