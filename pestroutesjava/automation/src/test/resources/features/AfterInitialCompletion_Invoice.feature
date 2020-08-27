@TriggerRules
Feature: Customer Status

  @CreateInitialInvoiceAfterInitialCompletion
  Scenario: Structure and Chemical Validation
    Given I sign in to pestroutes domain
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "Jared Green - Office" and "Fire" of type "After Initial Completion"
    And I validate if there are errors exist in the list
    And I navigate to scheduling on same Day
    And I add a route
    And I search customer
    And I navigate to Subscription Tab
    And I schedule a subscription appointment
    And I search customer
    And I complete a subscription appointment
    And I search customer