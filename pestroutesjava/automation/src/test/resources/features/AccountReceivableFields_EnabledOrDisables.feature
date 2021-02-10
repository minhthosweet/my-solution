#Author: Aarbi
@Regression
@FieldsValidationInAccountReceivable
Feature: Validate if all fields present and are enabled

  Scenario: Fields validations
    Given I sign in to pestroutes domain
    When I create customer with first name, last name and address
    And I validate if renewal fields display in Subscription tab if I choose renewal as service type
    And I create a renewal subscription
    And I navigate to scheduling tab
    And I add a route
    And I search customer
    And I schedule an service appointment
    And I search customer
    And I complete an appointment
    And I navigate to account receivable under Billings
    Then I validate if all fields are displaying and are enabled
    Then I validate if the customer displays once account status is Active

  Scenario: Close browser
    And I quit driver