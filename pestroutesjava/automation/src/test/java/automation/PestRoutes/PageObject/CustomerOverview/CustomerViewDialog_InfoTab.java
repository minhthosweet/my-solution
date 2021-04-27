package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;

public class CustomerViewDialog_InfoTab {
    CustomerViewDialog_Header customerDialog_Header;
    public String infoTab_AddAdditionalContactButton = "";
    public String infoTab_AddFromBusinessContactsButton = "";
    public String taxRate = "//input[@name='taxRate']";
    public String firstName = "//input[@name='fname']";
    public String lastName = "//input[@name='lname']";
    public String address = "//input[@name='address']";
    public String city = "//input[@name='city']";
    public String zip = "//input[@name='zip']";
    public String primaryPhoneNumber = "//input[@name='phone1']";
    public String state = "//select[@name='state']//option[@selected]";
    public String country = "//select[@name='countryID']//option[@selected]";
    public String county = "//select[@name='county']//option[@selected]";
    public String email = "//input[@name='email']";

    public String getTaxRate() {
        String taxRatePercentage = Utilities.getAttributeValue(taxRate, "value");
        return String.valueOf(taxRatePercentage.replaceAll("[%]", ""));
    }

    public String getFirstName() {
        Utilities.waitUntileElementIsVisible(firstName);
        return Utilities.getAttributeValue(firstName, "value");
    }

    public String getLastName() {
        Utilities.waitUntileElementIsVisible(lastName);
        return Utilities.getAttributeValue(lastName, "value");
    }

    public String getStreetAddress(){
        return Utilities.getAttributeValue(address,"value");
    }

    public String getCity(){
        return Utilities.getAttributeValue(city,"value");
    }

    public String getZip(){
        return Utilities.getAttributeValue(zip,"value");
    }

    public String getState(){
        return Utilities.getElementTextValue(state, Utilities.ElementType.XPath);
    }

    public String getCountry(){
        return Utilities.getElementTextValue(country, Utilities.ElementType.XPath);
    }

    public String getCounty(){
        return Utilities.getElementTextValue(county, Utilities.ElementType.XPath);
    }

    public String getPrimaryPhoneNumber(){
        return Utilities.getAttributeValue(primaryPhoneNumber,"value");
    }

    public String getEmail(){
        return Utilities.getAttributeValue(email, "value");
    }
}
