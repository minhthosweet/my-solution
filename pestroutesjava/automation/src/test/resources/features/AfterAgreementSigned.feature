@AfterAgreementSigned
Feature: Customer Status

  @CreateInitialInvoiceAfterAgreementSigned
  Scenario: Create Initial Invoice of type After Agreement Signed
    Given I sign in to pestroutes domain
    Given I have disabled ECA
    When I create customer with first name, last name, email and address
    Then I validate if customer name and address match in overview tab
    And I create a subscription of type "After Agreement Signed"
    And I search customer
    And I sign the agreement for subscription of type After Agreement Signed
    And I validate the if agreement is created
    And I search customer
    And I validate initial invoice created on invoice tab
    And I validate if there are errors exist in the list