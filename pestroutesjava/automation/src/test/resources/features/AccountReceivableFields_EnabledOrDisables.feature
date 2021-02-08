#Author: Aarbi
@Regression
@FieldsValidationInAccountReceivable
Feature: Validate if all fields present and are enabled

  Scenario: Fields validations
    Given I sign in to pestroutes domain
    And I navigate to account receivable under Billings
    Then I validate if all fields are displaying and are enabled

  Scenario: Close browser
    And I quit driver