package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.Deprecated;

public class CustomerSourcesObjects {

    private String clickNewCustomerSource = "//div[text() = '+ Customer Source']";
    private String customerSourcesName = "//input[@value ='' and @name='source']";

    public void createCustomerSource(String needCustomerSourceName) {
        clickNewCustomerSource();
        setCustomerSourceName(needCustomerSourceName);
        clickSaveButton();
    }

    public void clickNewCustomerSource() {
        Deprecated.waitVisible(clickNewCustomerSource);
        Deprecated.clickElement(clickNewCustomerSource);
    }

    public void setCustomerSourceName(String needCustomerSourceName) {
        Deprecated.waitVisible(customerSourcesName);
        Deprecated.locate(customerSourcesName).sendKeys(needCustomerSourceName);
    }

    public static void clickSaveButton() {
        Deprecated.waitVisible(FormObjects.saveButton);
        Deprecated.clickElement(FormObjects.saveButton);
    }
}
