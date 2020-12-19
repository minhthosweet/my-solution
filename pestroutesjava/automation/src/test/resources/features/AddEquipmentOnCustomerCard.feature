#Author: Faraz
  @Equipment
  Feature: Add Equipment on customer card
    Scenario: Add equipment type on customer card
      Given I sign in to pestroutes domain
      Given I delete a routing group
      Given I add equipment type "ID1" and "Automation Test1" and "Yes"
      When I create customer with first name, last name and address
      And I add new equipment with barcode required "Automation" and "Automation Test1" and "DE1" and "1234" and "Injection" and "Test123" and "Bed" and "Bed Bugs" and "Special Product"
      And I search customer
      And I verify that the equipment was added "Automation Test1"
      Given I add equipment type "ID2" and "Automation Test2" and "No"
      When I create customer with first name, last name and address
      And I add new equipment with barcode required "Automation2" and "Automation Test2" and "DE2" and "1234" and "Injection" and "" and "Bed" and "Bed Bugs" and "Special Product"
      And I search customer
      And I verify that the equipment was added "Automation Test2"
      And I validate if there are errors exist in the list
      
    Scenario: Close browser
      And I quit driver