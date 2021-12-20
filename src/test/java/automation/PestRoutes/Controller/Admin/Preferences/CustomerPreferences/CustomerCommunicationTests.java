package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerCommunicationPageObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;

import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
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

    //Retrieve Test Data
    HashMap<String, String> testData = Utilities.readExcelFile(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata",
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
}//CustomerCommunicationTest
