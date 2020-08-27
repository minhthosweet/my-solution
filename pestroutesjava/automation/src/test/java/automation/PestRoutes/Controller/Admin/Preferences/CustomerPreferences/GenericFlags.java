package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

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

    @Given("I add a new generic flag if it is not already existing {string} and {string} and {string}")
    public void addGenericFlag(String needGenericFlagCode, String needFlagDescription, String needFlagType) throws InterruptedException {
        header = new Header();
        adminPage = new AdminMainPage();
        preferences = new PreferencesPage();
        formObjects = new FormObjects();

        header.NavigateTo(header.adminTab);
        adminPage.navigateTo(adminPage.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.genericFlags);

        try {
            WebElement elm = FindElement.elementByAttribute(formObjects.existingFlag, FindElement.InputType.XPath);
        } catch(Exception e ) {
            formObjects.clickButton(formObjects.addFlagButton);
            formObjects.setInputField(needGenericFlagCode,needFlagDescription);
            formObjects.selectValueFromDropdown(needFlagType);
            formObjects.clickButton(formObjects.saveButton);
        }

    }

}
