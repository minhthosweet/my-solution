#Author Faraz
@Smoke
@InfoTab
@TaxRate
@RegressionFRK
Feature: Tax Rate

  Scenario: Create customer and verify all tax rates
    Then I create customer with address and ZipCode and I verify Main Tax, State Tax, City Tax, County Tax, Custom Tax, District1 Tax, District2 Tax, District3 Tax, District4 Tax, District5 Tax Rates "2310 Farrington Drive" and "75044" and "8.2500%" and "6.2500%" and "1.0000%" and "0.0000%" and "0.0000%" and "1.0000%" and "0.0000%" and "0.0000%" and "0.0000%" and "0.0000%"
    And I validate if there are errors exist in the list

