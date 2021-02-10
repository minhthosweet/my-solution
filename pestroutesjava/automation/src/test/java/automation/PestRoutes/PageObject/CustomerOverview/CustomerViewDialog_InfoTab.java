package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;

public class CustomerViewDialog_InfoTab {
    public String infoTab_AddAdditionalContactButton = "";
    public String infoTab_AddFromBusinessContactsButton = "";
    public String taxRate = "//input[@name='taxRate']";
    public String firstName = "//input[@name='fname']";
    public String lastName = "//input[@name='lname']";

    public String getTaxRate() {
        String taxRatePercentage = Utilities.getAttributeValue(taxRate, "value");
        return String.valueOf(taxRatePercentage.replaceAll("[%]", ""));
    }

    public String getFirstName() {
        return Utilities.getAttributeValue(firstName, "value");
    }

    public String getLastName() {
        return Utilities.getAttributeValue(lastName, "value");
    }
}
