package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerSourcesObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.FindElement;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CustomerSources extends AppData {
    Header header;
    AdminMainPage adminPage;
    PreferencesPage preferences;
    Service service;
    CustomerSourcesObjects customerSources;

    @Given("I add a new customer source if it is not already existing")
    public void addGenericFlag() throws InterruptedException, IOException {
        header = new Header();
        adminPage = new AdminMainPage();
        preferences = new PreferencesPage();
        service = new Service();
        customerSources = new CustomerSourcesObjects();

        String customerSourceName = getData("customerSource", generalData);

        header.navigateTo(header.adminTab);
        adminPage.navigateTo(adminPage.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.customerSources);
        service.searchService(customerSourceName);

        try {
            WebElement elm = FindElement.elementByAttribute("//div[contains(text(),'" + customerSourceName + "')]", FindElement.InputType.XPath);
        } catch (Exception e) {
            customerSources.createCustomerSource(customerSourceName);
        }
    }
}
