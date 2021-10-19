#Auther: Faraz
  @Regression
  @RegressionFRK
  @PaymentDistribution
  @BillingTab
Feature: Validate payment distribution in customer card
  Scenario: Validate invoice balance is not moved to linked customer
    When I create customer with first name and last name "Testers" "4Life" if not already existing
    And I add a renewal service
    Given I create customer with first name, last name, email and address
    Then I add a CC payment option "4111111111111111" and "5412750109056250"
    And I add an customer in auto pay with credit card
    And I create a renewal subscription
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I search customer
    When I add auto pay with cc or ach in subscription billing options and merge customer accounts "Tester 4Life"
    And I validate invoice
    And I validate if there are errors exist in the list
    Then I remove the customer
