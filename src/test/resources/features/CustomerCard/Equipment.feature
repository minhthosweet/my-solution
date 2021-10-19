#Author: Faraz
@Smoke
@Equipment
@RegressionFRK
Feature: Add Equipment on customer card

  Scenario Outline: Add equipment type on customer card
    Given I add equipment type "<ID>" and "<Description2>" and "<BarcodeRequired>"
    And I add a new generic flag if it is not already existing "<Flags>" and "<FlagDescription>" and "Equipment"
    When I create customer with first name, last name and address
    And I add new equipment with barcode required "<Description1>" and "<Description2>" and "<Flags>" and "1234" and "Injection" and "<Barcode>" and "Bed" and "Bed Bugs" and "Special Product"
    And I search customer
    And I verify that the equipment was added "<Description2>"
    And I validate if there are errors exist in the list

    Examples:
      | Description1 | Description2     | Flags | Barcode | ID | BarcodeRequired | FlagDescription |
      | Automation   | Automation Test1 | DE1   | Test123 | ID1| Yes             | Detox Flag 1    |
      | Automation2  | Automation Test2 | DE2   |         | ID2| No              | Detox Flag 2    |