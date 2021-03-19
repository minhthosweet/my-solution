#Auther: Faraz Khan

@JobPool
  Feature: Job Pool Filters

      Scenario: Validate All Job Pool Filters
      Given I add a new generic flag if it is not already existing "Automation Flag" and "Description 1" and "Customer"
      Given I add a new generic flag if it is not already existing "Test4Life" and "Description 2" and "Customer"
      Given I add a new generic flag if it is not already existing "Fire" and "Description 3" and "Subscription"
      Given I add a new generic flag if it is not already existing "Water" and "Description 4" and "Subscription"
      And I create a new user if it is not already existing "Office Staff"
      And I add a renewal service
      When I create customer with first name, last name, address and generic flag "Automation Flag" and "Door to Door"
      And I create a subscription with Sales Rep assigned "Automation User - Office" and "Fire"
      And I navigate to the job pool tab
      And I validate if all fields are displaying and are enabled in Job Pool
      Then I add all the fields in the job pool page "Include Customers With Special Requests" and "Show pre-planned" and "Include One Time Services" and "Exclude Potential Customers" and "Include Followup Services" and "Exclude Pending Cancels" and "All Property Types" and "Show All Flags" and "Only Non-Measured Services" and "Automation Flag" and "Test4Life" and "Fire" and "Water"
      Then I validate the job pool results

