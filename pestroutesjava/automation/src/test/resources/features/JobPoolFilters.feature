#Auther: Faraz Khan
@Scheduling
  Feature: Job Pool Filters

    Scenario: Validate All Job Pool Filters
      Given I sign in to pestroutes domain
      And I create a new user if it is not already existing "Office Staff"
      And I add a renewal service
      Given I add a new generic flag if it is not already existing "Fire" and "Its lit" and "Subscription"
      When I create customer with first name, last name, address and generic flag "Test4Life" and "Door to Door"
      And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
      And I navigate to the job pool tab
      And I validate if all fields are displaying and are enabled in Job Pool
      Then I add all the fields in the job pool page
      Then I validate the job pool results
      And I validate if there are errors exist in the list


