#Author: Aditya
@CouponsOrCredit
#@RegressionADI
Feature: Credit/Coupon Validation

  Scenario: Make payment with Coupon or Credit and validate in Invoicing
    When I create customer with balance with prefers paper and residential property type
    When I search the number "1" customer in History tab
    And I validate all the fields in the Coupons or Credit window
    And I validate if amounts are displayed correctly in coupons or credits
    Then I make a coupon or credit payment
    And I validate the success payment of credit or coupon payment
    And I validate if there are errors exist in the list