package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;

public class CustomerViewDialog_InfoTab extends BasePage {
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
    public String flagOnCustomerCard = "//div[@id='s2id_customerGenericFlags']//ul//div";
    private By genericFlagsDropDown = By.xpath("//select[@id= 'customerGenericFlags']");

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

    public String getFlagOnCustomerCard() {
        Utilities.waitUntileElementIsVisible(flagOnCustomerCard);
        return Utilities.getElementTextValue(flagOnCustomerCard, Utilities.ElementType.XPath);
    }

    public boolean getGenericFlag(String flag) {
        By genericFlag = By.xpath("//div[text()='"+ flag +"']");
        if (find(genericFlag).isDisplayed()) {
            return true;
        }
        return false;
    }

    public void selectCustomerGenericFlag(String flagCode) {
        selectFromDropDown(flagCode, genericFlagsDropDown);
    }
}