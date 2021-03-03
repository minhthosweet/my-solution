#Author: Aarbi
@Regression
@FieldsValidationInAccountReceivable
Feature: Validate if all fields present and are enabled

#  Scenario: Fields validations
#    Given I sign in to pestroutes domain
#    Given I delete a routing group
#    When I create customer with first name, last name and address
#    And I validate if renewal fields display in Subscription tab if I choose renewal as service type
#    And I create a renewal subscription
#    And I navigate to scheduling tab
#    And I add a route
#    And I search customer
#    And I schedule an service appointment
#    And I search customer
#    And I complete an appointment
#    And I navigate to account receivable under Billings
#    Then I validate if all fields are displaying and are enabled
#    Then I validate if the customer displays once account status is Active
#
  Scenario: Fields validations
    Given I delete a routing group
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I validate the if agreement is created
    And I close customer card
    And I navigate to account receivable under Billings
    Then I validate if all fields are displaying and are enabled
    Then I validate if the customer displays once account status is Active

  Scenario: Fields validations
    Then I validate customer type in account receivable

  Scenario: Close browser
    And I quit driver