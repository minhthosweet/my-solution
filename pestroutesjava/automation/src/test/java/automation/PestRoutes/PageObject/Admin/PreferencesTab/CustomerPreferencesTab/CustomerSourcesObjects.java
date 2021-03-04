package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;

public class CustomerSourcesObjects {

    private String clickNewCustomerSource = "//div[text() = '+ Customer Source']";
    private String customerSourcesName = "//input[@value ='' and @name='source']";

    public void createCustomerSource(String needCustomerSourceName) {
        clickNewCustomerSource();
        setCustomerSourceName(needCustomerSourceName);
        clickSaveButton();
    }

    public void clickNewCustomerSource() {
        Utilities.waitUntileElementIsVisible(clickNewCustomerSource);
        Utilities.clickElement(clickNewCustomerSource, Utilities.ElementType.XPath);
    }

    public void setCustomerSourceName(String needCustomerSourceName) {
        Utilities.waitUntileElementIsVisible(customerSourcesName);
        FindElement.elementByAttribute(customerSourcesName, FindElement.InputType.XPath).sendKeys(needCustomerSourceName);
    }

    public static void clickSaveButton() {
        Utilities.waitUntileElementIsVisible(FormObjects.saveButton);
        Utilities.clickElement(FormObjects.saveButton, Utilities.ElementType.XPath);
    }
}
