@OnInitialBillingDate
Feature: Customer Status

  @CreateInitialInvoiceOnInitialBillingDate
  Scenario: Create Initial Invoice of type On Initial Billing Date
    Given I sign in to pestroutes domain
    When I create customer with first name, last name and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "On Initial Billing Date"
    Then I validate initial Billing date
    And I close customer card
    When I run the billing queue script
    Given I clear cache and reload the browser
    Given I sign in to pestroutes domain
    When I search customer
    And I validate initial invoice created on invoice tab
    And I validate if there are errors exist in the list
