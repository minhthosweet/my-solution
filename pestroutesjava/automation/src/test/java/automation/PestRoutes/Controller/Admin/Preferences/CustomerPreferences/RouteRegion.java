package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.FormObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static automation.PestRoutes.Utilities.AppData.getData;

public class RouteRegion extends AppData {

        Header header;
        AdminMainPage adminPage;
        PreferencesPage preferences;
        FormObjects formObjects;
        Service service;

        @Given("I add a new route region if it is not already existing")
        public void addRouteRegion() throws InterruptedException, IOException {
            String routeRegion = getData("region", generalData);
            header = new Header();
            adminPage = new AdminMainPage();
            preferences = new PreferencesPage();
            formObjects = new FormObjects();
            service = new Service();

            header.navigateTo(header.adminTab);
            adminPage.navigateTo(adminPage.preferences);
            preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.routeRegions);
            service.searchService(routeRegion);

            try {
                WebElement elm = FindElement.elementByAttribute("//div[contains(text(),'"+routeRegion+"')]", FindElement.InputType.XPath);
            } catch(Exception e ) {
                Utilities.clickElement(formObjects.addRegionButton, Utilities.ElementType.XPath);
                formObjects.setInputField(formObjects.nameField, routeRegion);
                Utilities.clickElement(formObjects.saveButton, Utilities.ElementType.XPath);
            }

        }
}
