package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.FormsPage.FormObjects;
import automation.PestRoutes.PageObject.Admin.Preferences.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.FindElement;
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

        header.NavigateTo(header.adminTab);
        adminPage.navigateTo(adminPage.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.genericFlags);
        service.searchService(needGenericFlagCode);

        try {
            WebElement elm = FindElement.elementByAttribute("//div[contains(text(),'"+needGenericFlagCode+"')]", FindElement.InputType.XPath);
        } catch(Exception e ) {
            formObjects.clickButton(formObjects.addFlagButton);
            formObjects.setInputField(formObjects.flagCodeField, needGenericFlagCode);
            formObjects.setInputField(formObjects.nameField, needFlagDescription);
            formObjects.selectValueFromDropdown(formObjects.flagType, needFlagType);
            formObjects.clickButton(formObjects.saveButton);
        }

    }

}
