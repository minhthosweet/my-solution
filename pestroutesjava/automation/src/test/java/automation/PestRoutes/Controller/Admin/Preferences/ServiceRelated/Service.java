package automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated;

import automation.PestRoutes.Utilities.AppData;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab.ServiceTypes;
import io.cucumber.java.en.Given;

public class Service extends AppData {
    Header header;
    AdminMainPage admin;
    PreferencesPage preferences;
    ServiceTypes service;

    @Given("I add a renewal service")
    public void workWithService() throws Exception {
        navigateToServiceType();
        searchService(getData("serviceDescription", generalData));
        try {
            service = new ServiceTypes();
            WebElement elm = service.getDescription(getData("serviceDescription", generalData));
            if (elm.isDisplayed()) {
                editService(getData("serviceDescription", generalData));
            }
        } catch (Exception e) {
            System.out.println("did not find renewal service");
            addRenewalServiceType();
        }
    }

    @And("I navigate to Service Types")
    public void navigateToServiceType() throws InterruptedException {
        admin = new AdminMainPage();
        header = new Header();
        preferences = new PreferencesPage();
        service = new ServiceTypes();
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        preferences.navigateTo(preferences.serviceRelatedNav, preferences.serviceTypesText);
    }

    @And("I search for the service type {string}")
    public void searchService(String needServiceDescription) {
        service = new ServiceTypes();
        service.setSearch(needServiceDescription);
    }

    public void addRenewalServiceType() throws Exception {
        service = new ServiceTypes();
        service.clickAddServiceButton();
        service.setDescription(getData("serviceDescription", generalData));
        service.setCategory(getData("serviceCategory", generalData));
        service.setAbbreviation(getData("serviceAbbreviation", generalData));
        service.selectFromDropdown(service.globalDropDown, "Specific to this office");
        service.selectFromDropdown(service.visibilityDropDown, "Visible");
        service.selectFromDropdown(service.appointmentFrequencyDropDown, "OneTime");
        service.selectFromDropdown(service.defaultFollowUpDelayDropDown, "No Followup");
        service.setAppointLegnth("60");
        service.selectFromDropdown(service.defaultContractLengthDropDown, "No Contract");
        service.selectFromDropdown(service.minimumContractLengthDropDown, "No Contract");
        service.selectFromDropdown(service.displayRenewalDateDropDown, "Yes");
        service.selectFromDropdown(service.renewalFrequencyDropDown, "Annually");
        service.selectFromDropdown(service.setRenewalDateDropDown, "On Initial Service Completion");
        service.clickSave();
    }

    public void editService(String needDescription) {
        service = new ServiceTypes();
        service.clickEditButton(needDescription);
        service.selectFromDropdown(service.setRenewalDateDropDown, "On Initial Service Completion");
        service.clickSave();
    }
}
