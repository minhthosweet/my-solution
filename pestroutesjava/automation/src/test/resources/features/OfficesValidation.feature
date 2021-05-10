#Author: Aarbi
@RegressionARB
@OfficeValidation
Feature: Validated offices displayed
  @ValidateOfficesDisplayedAndAreEnabled
  Scenario Outline: Validate offices are enabled if every tabs in the header
    When I navigate to "<HeaderTab>" in header
    Then I validate if sorted offices displayed
    And I validate if there are errors exist in the list

    Examples:
      | HeaderTab|
      | Scheduling|
      | Customers|
      | Billing  |
      | Reporting|
      | Sales    |
      | Admin    |
  @ValidateOfficesInFooterTabsVisibleAndAreEnabled
  Scenario Outline: Validate offices are enabled if every tabs in the header
    When I navigate to "<FooterTab>" in footer
    Then I validate if sorted offices displayed
    And I validate if there are errors exist in the list

    Examples:
      | FooterTab|
      | Guides|
      | Clock|