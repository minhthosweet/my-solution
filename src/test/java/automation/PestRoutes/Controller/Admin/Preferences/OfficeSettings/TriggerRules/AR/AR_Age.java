package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.GenericFlagsPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Notes;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static automation.PestRoutes.Utilities.Utilities.*;

public class AR_Age extends AppData {

    SoftAssert softAssert = new SoftAssert();
    CreateTrigger_AR createAR = new CreateTrigger_AR();
    InvoicingTab invoiceTab;
    AR_daysPastDue ar_daysPastDue;
    DashboardPage userOnDashboard = new DashboardPage();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    PreferencesPage userOnPreferences = new PreferencesPage();
    GenericFlagsPage userOnGenericFlagsPage = new GenericFlagsPage();
    CustomerViewDialog_InfoTab userOnInfoTab = new CustomerViewDialog_InfoTab();
    TriggerRules userOnTriggerRulesPage = new TriggerRules();
    CustomerViewDialog_Notes userOnNotesTab = new CustomerViewDialog_Notes();
    CustomerViewDialog_Admin userOnAdminTab = new CustomerViewDialog_Admin();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CreateNewCustomer testUser = new CreateNewCustomer();
    ARTab triggerAR = new ARTab();

    private String description_TriggerAge = "TriggerAge_AR";
    public String age = "1";
    public String genericFlag;

    @Test
    public void daysPastDue_AR() throws Exception {

        // Create trigger and all actions
        createTriggerandActionsAge_AR(description_TriggerAge, age);

        // Create customer with all communication
        createCustomerWithInvoice();

        // Run the script
        runTriggerEvent();

        // assert Notes
        assertLog();
    }

    public void createTriggerandActionsAge_AR(String description, String days) throws Exception {
        createAR.createTrigger_AR(description, "Days Past Due", days);
        createAR.createAllARActions(description);
    }

    @When("I create customer with Invoice")
    public void createCustomerWithInvoice() throws Exception {
        invoiceTab = new InvoicingTab();
        invoiceTab.addNewInvoice(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
    }

    @When("I execute the trigger event script")
    public void runTriggerEvent() {
        createAR.hitTriggerEvent();
    }

    public void assertLog() throws Exception {
        ar_daysPastDue = new AR_daysPastDue();
        ar_daysPastDue.assertLog();
    }

    @Given("I Set Up A Customer {string} Flag If The Flag Does Not Exist")
    public void automateCreatingCustomerGenericFlag(String flagCode) {
        userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnPreferences.clickCustomerPreferences();
        userOnGenericFlagsPage = userOnPreferences.clickGenericFlags();
        userOnGenericFlagsPage.createCustomerGenericFlag(flagCode, "Testing Trigger Rules");
        this.genericFlag = flagCode;
    }

    @Given("I Set Up {string} Trigger Type That Has {string} Filter With {string} Action")
    public void automateSelectingFilterWithAction(String trigger, String filter, String action) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
        userOnTriggerRulesPage.addActiveTrigger
                (trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
        triggerAR.selectAgePastDue(filter);
        triggerAR.typeAgePastDueDays("0");
        //triggerAR.typeFlagToInclude(genericFlag);
        triggerAR.clickAddActionButton();
        triggerAR.selectAction(action);
        triggerAR.selectEmailType("Email Statement");
        triggerAR.typeEmailTitle("Automation Trigger Rule Test");
        triggerAR.clickSaveButton();
    }

    @When("I Add {string} Flag To The Customer With A New Invoice")
    public void automateSettingUpCustomerWithFlagAndInvoice(String flagCode) {
        InvoicingTab testUserOnInvoicesTab = new InvoicingTab();
        testUser.createCustomerWithBasicInfo();
        userOnInfoTab = sameUser.goToInfoTab();
        userOnInfoTab.selectCustomerGenericFlag(flagCode);
        sameUser.clickSaveButton();
        testUserOnInvoicesTab.automateGeneratingStandAloneInvoice();
    }

    @And("I Execute Trigger {string} On Subdomain {string} For Office {string}")
    public void automateExecutingTriggerOnBrowserUsingAnEndPoint(String triggerName, String subdomain, String officeID) {
        // Only use variable testURL for testing only
        // After testing, I will use variable urlWithEndPoint
        String stagingdemoURL = "https://stagingdemo.pestroutes.com/resources/scripts/triggerEvents.php?debug=1&office=4&testing=1";
        String url = "https://" + subdomain + ".pestroutes.com/resources/scripts/" + triggerName +
                    ".php?debug=1&office=" + officeID +"&testing=1";
        WebDriver driver = loadIncognitoChromeBrowser(url);
        closeIncognitoBrowser(driver);
    }

    @Then("I Verify The Customer Received An Email After Executing The Trigger Rule")
    public void testCustomerReceivedAnEmail() {
        sameUser.goToNotesTab();
        boolean isEmailSent = userOnNotesTab.getNotesDetailLog().contains("Email");
        softAssert.assertTrue(isEmailSent);
        sameUser.goToAdminTab();
        userOnAdminTab.clickRemoveButton();
        userOnAdminTab.clickConfirmRemoveButton();
    }
}
