#Auther: Faraz Khan
@Smoke
@Scheduling
@RegressionFRK
@JobPool
Feature: Job Pool Filter

  Scenario Outline: Validate All Job Pool Filter
    Given I add a new generic flag if it is not already existing "Automation Flag" and "Description 1" and "Customer"
    Given I add a new generic flag if it is not already existing "Test4Life" and "Description 2" and "Customer"
    Given I add a new generic flag if it is not already existing "Fire" and "Description 3" and "Subscription"
    Given I add a new generic flag if it is not already existing "Water" and "Description 4" and "Subscription"
    And I create a new user if it is not already existing "Technician"
    And I add a new customer source if it is not already existing
    And I add a new route region if it is not already existing
    And I add a renewal service
    And I add a recurring renewal service type if it is not already existing "Alternate Monthly"
    When I create customer with first name, last name, address and generic flag "Automation Flag"
    And I create a dynamic subscription with Sales Rep assigned "Automation User - Tech" and "Fire" and "<Day>"
    And I navigate to the job pool tab
    And I validate if all fields are displaying and are enabled in Job Pool
    Then I add all the fields in the job pool page "<Scheduling1>" and "<Scheduling2>" and "<Scheduling3>" and "<Customer1>" and "<Customer2>" and "<Customer3>" and "<Customer4>" and "<ServiceFlag>" and "<PreferredDay>" and "<Measurement>" and "Automation Flag" and "Test4Life" and "Fire" and "Water"
    Then I validate the job pool results
    And I remove the customer
    And I validate if there are errors exist in the list

    Examples:
      | Scheduling1                             | Scheduling2      | Scheduling3              | Customer1                                                      | Customer2                | Customer3              | Customer4         | ServiceFlag   | PreferredDay| Measurement               | Day |
      | Include Customers With Special Requests | Show pre-planned | Include One Time Services| Include Potential Customers (active with no completed services)| Include Followup Services| Exclude Pending Cancels| All Property Types| Show All Flags| Any Day     | Only Non-Measured Services| Any Day |
      | Exclude Customers With Special Requests | Don't show pre-planned | Only One Time Services | ONLY Potential Customers (Have NOT received initial service yet)| Exclude Followup Services| Include Pending Cancels| Residential Properties| Hide All Flags|Monday|Include Measured and Non-Measured Services| Monday |
