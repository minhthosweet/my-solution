@CreateTriggerRule
Feature: Create Trigger
  I want to use this template for my feature file

  @CreateTrigger_Renewal
  Scenario Outline: Creating a trigger
    Given I want to login to the application using URL : "https://abdula.pestroutes.com/"
    When username is "mind" and password is "matter"
    And I navigate to trigger rules test
    And I create a trigger with negative scenrio with startdate as "01/01/2020" test
    And update with positive scenarios test
    And set before_AfterDropdowm to <beforeAfterDropdown> test
    And set account_StatusDropdown to <accountStatusDropdown> test
    And set multiUnit_Dropdown to <multiUnitDropdown> test
    And set hasInitialService_Dropdown to <initialServiceDropdown> test
    And set prefersPaper to <prefersPaper> test 
    And set subscriptionStatus_Dropdown to <subscriptionStatusDropdowm> test
    And set propoertyType_Dropdowm to <propertyTypeDropdown> test
    And set hasEmail_Dropdowm to <hasEmailDropdown> test
    And I click save button test

    Examples: 
      | beforeAfterDropdown    | accountStatusDropdown | multiUnitDropdown             | initialServiceDropdown | prefersPaper | daysBeforeAfter | subscriptionStatusDropdowm | propertyTypeDropdown | hasEmailDropdown |
      | Before Expiration Date | Any                   | Include Multi Unit Properties | Any                    | All          |               5 | Any                        | AllProperties        | All              |
