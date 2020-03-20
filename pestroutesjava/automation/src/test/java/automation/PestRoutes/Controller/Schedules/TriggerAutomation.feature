@CreateTriggerRule
Feature: Create Trigger
  I want to use this template for my feature file

  @CreateTrigger_Renewal
  Scenario Outline: Creating a trigger
    Given I want to login to the application using URL : "https://abdula.pestroutes.com/"
    When username is "mind" and password is "matter"
    And I navigate to trigger rules
    And I create a trigger with negative scenrio with startdate as "01/01/2020"
    And update with positive scenarios
    And set before_AfterDropdowm to <beforeAfterDropdown>
    And set account_StatusDropdown to <accountStatusDropdown>
    And set multiUnit_Dropdown to <multiUnitDropdown>
    And set hasInitialService_Dropdown to <initialServiceDropdown>
    And set prefersPaper to <prefersPaper>
    And set daysBefore_After to <daysBeforeAfter>
    And set subscriptionStatus_Dropdown to <subscriptionStatusDropdowm>
    And set propoertyType_Dropdowm to <propertyTypeDropdown>
    And set hasEmail_Dropdowm to <hasEmailDropdown>
    And I click save button

    Examples: 
      | beforeAfterDropdown    | accountStatusDropdown | multiUnitDropdown             | initialServiceDropdown | prefersPaper | daysBeforeAfter | subscriptionStatusDropdowm | propertyTypeDropdown | hasEmailDropdown |
      | Before Expiration Date | Any                   | Include Multi Unit Properties | Any                    | All          |               5 | Any                        | AllProperties        | All              |

  @SearchTrigger
  Scenario: Creating a trigger
    Given I navigate to trigger rules
    And I search for the trigger
    And I click edit trigger

  @SearchTrigger
  Scenario Outline: Creating a trigger
    Given I navigate to trigger rules
    And I search for the trigger
    And I click edit trigger
    And I add Actions
    And I select <ActionType>
    And I select <messageType>
    And I select ignore Contact Prefs as "No"
    And I enter subject
    And I select Renewal Link Dropdown as "Include"
    And I add Actions
    And I select <ActionType>
    And I select <messageType>
    And I click save button

    Examples: 
      | ActionType | messageType    |
      | Send Email | Renewal Notice |
      | Snail Mail | Renewal Notice |
