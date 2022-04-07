package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerCommunicationPageObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;

import automation.PestRoutes.Utilities.Data.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CustomerCommunicationTests extends BasePage {
    public CustomerCommunicationTests() throws IOException {}
    Header header = new Header();
    PreferencesPage preferences = new PreferencesPage();
    CustomerCommunicationPageObjects customerCommPageObjs = new CustomerCommunicationPageObjects();
    DashboardPage userOnDashboard = new DashboardPage();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    PreferencesPage userOnPreferences = new PreferencesPage();

    //Retrieve Test Data
    HashMap<String, String> testData = ExcelData.readExcelFile(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata",
            "TestData_Ticket105549.xlsx", "CustomerCommData", 1);

    @When("I navigate to the Customer Communication Tab")
    public void navigateToCustomerCommunicationTab() throws Exception {
        header.navigateTo(header.adminTab);
        customerCommPageObjs.loadCustomCommunciationTab();
    }

    @And("I validate all fields can be edited and updated")
    public void fieldUpdateValidation() throws Exception {
       customerCommPageObjs.updateAllFields(testData);
    }

    @Then("I validate all updated fields are saved successfully")
    public void fieldSavedValidation() throws Exception {
       customerCommPageObjs.verifySaveProcess(testData);
    }

    @When("I Set The SMS Delivery Settings Window From {string} To {string}")
    public void automateChangingDeliverySettingsWindowForSMS(String startTime, String endTime) {
        userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnPreferences.clickCustomerPreferences();
        userOnPreferences.clickCustomerCommunication();
        customerCommPageObjs.clickDeliverySettingsEditButton();
        customerCommPageObjs.typeSMS_StartWindowTime(startTime);
        customerCommPageObjs.typeSMS_EndWindowTime(endTime);
        customerCommPageObjs.clickDeliverySettingsSaveButton();
    }
}//CustomerCommunicationTest
