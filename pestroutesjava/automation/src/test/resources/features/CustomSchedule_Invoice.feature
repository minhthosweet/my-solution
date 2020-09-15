@CustomDate
Feature: Customer Status

  @CreateInitialInvoiceCustomSchedule
  Scenario: Create Initial Invoice of type On Initial Billing Date
    Given I sign in to pestroutes domain
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "Custom Schedule"
