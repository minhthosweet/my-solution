package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab;

import automation.PestRoutes.Utilities.Legacy;

public class CustomerSourcesObjects {

    private String clickNewCustomerSource = "//div[text() = '+ Customer Source']";
    private String customerSourcesName = "//input[@value ='' and @name='source']";

    public void createCustomerSource(String needCustomerSourceName) {
        clickNewCustomerSource();
        setCustomerSourceName(needCustomerSourceName);
        clickSaveButton();
    }

    public void clickNewCustomerSource() {
        Legacy.waitVisible(clickNewCustomerSource);
        Legacy.clickElement(clickNewCustomerSource);
    }

    public void setCustomerSourceName(String needCustomerSourceName) {
        Legacy.waitVisible(customerSourcesName);
        Legacy.locate(customerSourcesName).sendKeys(needCustomerSourceName);
    }

    public static void clickSaveButton() {
        Legacy.waitVisible(FormObjects.saveButton);
        Legacy.clickElement(FormObjects.saveButton);
    }
}
