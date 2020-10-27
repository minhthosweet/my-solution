#Author: Faraz

  @Leads
  Feature: Test Lead Creation
    Scenario: Create new lead
      Given I sign in to pestroutes domain
      When I create customer with first name, last name and address
      And I create a new lead
      Then I validate lead creation invoices
      And I convert a successful lead to subscription and I verify it in the subscriptions tab
      And I validate if there are errors exist in the list