package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;

public class CustomerViewDialog_InfoTab {
    public String infoTab_AddAdditionalContactButton = "";
    public String infoTab_AddFromBusinessContactsButton = "";
    public String taxRate = "//input[@name='taxRate']";

public String getTaxRate() {
    String taxRatePercentage = Utilities.getAttributeValue(taxRate,"value");
    return String.valueOf(taxRatePercentage.replaceAll("[%]", ""));
}
}
