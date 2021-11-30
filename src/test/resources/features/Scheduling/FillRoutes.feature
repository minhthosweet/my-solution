#Author: Frankie White
#
# Fill Routes Optimization Process optimize routes for max utilization of time
# Ticket-127104: This Feature will verify the Fill Routes Optimization Process executes successfully
@Smoke
@Regression
@Regression_FWhite


Feature: Validate Fill Routes Optimization Process

  @ExecuteFillRoutesOptimizingProcess
  Scenario Outline: Execute Fill Routes Optimizing Process
    When I navigate to the Scheduling Tab and access the Fill Routes sub Tab
    And I add filter values for "Due Between" "<DueBetweenSTART>" and "<DueBetweenEND>" fields in "Jobs Available" section
    And I click the "Refresh" button in the "Jobs Available" section
    Then I validate unscheduled "Routing" count and "Customers" count greater than zero in "Unscheduled Stops" section
    And I add filter values for "Date Range" "<DateRangeSTART>" and "<DateRangeEND>" fields in "Routes To Fill" section
    And I click the "Refresh" button in the "Routes To Fill" section
    Then I validate routes-by-date groups are displayed
    Then I click the "Fill Routes" button to launch the "Fill Routes with Available Jobs" dialog
    And I fill available routes with available jobs by Max Time option "<MaxTime>"
    And I execute the optimizeQueue script
    Then I validate "Fill Routes Review" Page displays and save optimized routes

    Examples:
      | DueBetweenSTART	| DueBetweenEND 		| DateRangeSTART    | DateRangeEND		| MaxTime 		        |
      | 10/01/2021		| 10/15/2021        	| 10/26/2021   		| 10/28/2021        | Determined by Route	|
