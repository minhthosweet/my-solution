package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;

import static automation.PestRoutes.Utilities.Utilities.*;

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
        String taxRatePercentage = Legacy.getAttribute(taxRate, "value");
        return String.valueOf(taxRatePercentage.replaceAll("[%]", ""));
    }

    public String getFirstName() {
        Legacy.waitVisible(firstName);
        return Legacy.getAttribute(firstName, "value");
    }

    public String getLastName() {
        Legacy.waitVisible(lastName);
        return Legacy.getAttribute(lastName, "value");
    }

    public String getStreetAddress(){
        return Legacy.getAttribute(address,"value");
    }

    public String getCity(){
        return Legacy.getAttribute(city,"value");
    }

    public String getZip(){
        return Legacy.getAttribute(zip,"value");
    }

    public String getState(){
        return Legacy.getElementTextValue(state);
    }

    public String getCountry(){
        return Legacy.getElementTextValue(country);
    }

    public String getCounty(){
        return Legacy.getElementTextValue(county);
    }

    public String getPrimaryPhoneNumber(){
        return Legacy.getAttribute(primaryPhoneNumber,"value");
    }

    public String getEmail(){
        return Legacy.getAttribute(email, "value");
    }

    public String getFlagOnCustomerCard() {
        Legacy.waitVisible(flagOnCustomerCard);
        return Legacy.getElementTextValue(flagOnCustomerCard);
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
        selectByText(genericFlagsDropDown, flagCode);
    }

    public void selectDivision(String division) {
        selectByText(divisionDropDown, division);
    }
}