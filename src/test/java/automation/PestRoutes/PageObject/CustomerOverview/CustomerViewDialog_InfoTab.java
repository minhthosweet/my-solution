package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
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
    private By divisionDropDown = By.xpath("//select[@name= 'divisionID']");

    public String getTaxRate() {
        String taxRatePercentage = Deprecated.getAttribute(taxRate, "value");
        return String.valueOf(taxRatePercentage.replaceAll("[%]", ""));
    }

    public String getFirstName() {
        Deprecated.waitVisible(firstName);
        return Deprecated.getAttribute(firstName, "value");
    }

    public String getLastName() {
        Deprecated.waitVisible(lastName);
        return Deprecated.getAttribute(lastName, "value");
    }

    public String getStreetAddress(){
        return Deprecated.getAttribute(address,"value");
    }

    public String getCity(){
        return Deprecated.getAttribute(city,"value");
    }

    public String getZip(){
        return Deprecated.getAttribute(zip,"value");
    }

    public String getState(){
        return Deprecated.getElementTextValue(state);
    }

    public String getCountry(){
        return Deprecated.getElementTextValue(country);
    }

    public String getCounty(){
        return Deprecated.getElementTextValue(county);
    }

    public String getPrimaryPhoneNumber(){
        return Deprecated.getAttribute(primaryPhoneNumber,"value");
    }

    public String getEmail(){
        return Deprecated.getAttribute(email, "value");
    }

    public String getFlagOnCustomerCard() {
        Deprecated.waitVisible(flagOnCustomerCard);
        return Deprecated.getElementTextValue(flagOnCustomerCard);
    }

    public boolean getGenericFlag(String flag) {
        By genericFlag = By.xpath("//div[text()='"+ flag +"']");
        if (Utilities.locate(genericFlag).isDisplayed()) {
            System.out.println("Info Tab (Generic Flag): " + Utilities.getText(genericFlag));
            return true;
        }
        return false;
    }

    public void selectCustomerGenericFlag(String flagCode) {
        Utilities.selectByText(genericFlagsDropDown, flagCode);
    }

    public void selectDivision(String division) {
        selectFromDropDown(division, divisionDropDown);
    }
}