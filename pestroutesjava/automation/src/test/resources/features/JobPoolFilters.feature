#Auther: Faraz Khan
@Scheduling
  Feature: Job Pool Filters

    Scenario: Validate All Job Pool Filters
      Given I sign in to pestroutes domain
      And I navigate to the job pool tab
      And I validate if all fields are displaying and are enabled in Job Pool
      Then I add all the fields in the job pool page
      Then I validate the job pool results
      And I validate if there are errors exist in the list


