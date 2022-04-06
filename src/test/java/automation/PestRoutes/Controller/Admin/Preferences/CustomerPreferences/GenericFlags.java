package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.FormObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Deprecated;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

public class GenericFlags {
    Header header;
    AdminMainPage adminPage;
    PreferencesPage preferences;
    FormObjects formObjects;
    Service service;

    @Given("I add a new generic flag if it is not already existing {string} and {string} and {string}")
    public void addGenericFlag(String needGenericFlagCode, String needFlagDescription, String needFlagType) throws InterruptedException {
        header = new Header();
        adminPage = new AdminMainPage();
        preferences = new PreferencesPage();
        formObjects = new FormObjects();
        service = new Service();

        header.navigateTo(header.adminTab);
        adminPage.navigateTo(adminPage.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.genericFlags);
        service.searchService(needGenericFlagCode);

        try {
            WebElement elm = Deprecated.locate("//div[contains(text(),'"+needGenericFlagCode+"')]");
        } catch(Exception e ) {
            Deprecated.clickElement(formObjects.addFlagButton);
            formObjects.setInputField(formObjects.flagCodeField, needGenericFlagCode);
            formObjects.setInputField(formObjects.nameField, needFlagDescription);
            formObjects.selectValueFromDropdown(formObjects.flagType, needFlagType);
            Deprecated.clickElement(formObjects.saveButton);
        }

    }

}
