package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.DivisionsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Divisions extends AppData {

    Header header;
    AdminMainPage adminPage;
    PreferencesPage preferences;
    Service service;
    DivisionsObjects divisions;

    @Given("I add a new division if it is not already existing")
    public void addGenericFlag() throws InterruptedException, IOException {
        header = new Header();
        adminPage = new AdminMainPage();
        preferences = new PreferencesPage();
        service = new Service();
        divisions = new DivisionsObjects();

        String divisionName = getData("division", generalData);

        header.navigateTo(header.adminTab);
        adminPage.navigateTo(adminPage.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.divisions);
        service.searchService(divisionName);

        try {
            WebElement elm = Legacy.locate("//div[contains(text(),'" + divisionName + "')]");
        } catch (Exception e) {
            divisions.createNewDivisions(divisionName, divisionName);
        }
    }
}
