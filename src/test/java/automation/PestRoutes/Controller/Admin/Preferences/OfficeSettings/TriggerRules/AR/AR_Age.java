package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Invoicing.InvoicingTab;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.GenericFlagsPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
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
    AddSubscription testSubscription = new AddSubscription();
    Header userOnHeader = new Header();
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    CustomerViewDialog_OverviewTab userOnOverviewTab = new CustomerViewDialog_OverviewTab();

    private String description_TriggerAge = "TriggerAge_AR";
    public String age = "1";
    public static String genericFlag;

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

    @Given("I Set Up {string} Trigger Type That Has {string} Filter With {string} Filter")
    public void automateSettingUpTriggerTypeAR(String trigger, String filter, String additionalFilter) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
        userOnTriggerRulesPage.addActiveTrigger
                (trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
        triggerAR.selectAgePastDue(filter);
        triggerAR.typeAgePastDueDays("0");
        triggerAR.typeFlagToInclude(genericFlag);
        triggerAR.selectAdditionalFilter(additionalFilter);
    }

    @And("I Complete An Action To {string} With {string} Details")
    public void automateSendingCompleteActionForAR(String action, String details) {
        triggerAR.clickAddActionButton();
        triggerAR.completeAction(action, details);
        triggerAR.clickSaveButton();
    }

    @When("I Add {string} Flag To The Customer With A New Invoice")
    public void automateSettingUpCustomerWithFlagSubscriptionAndInvoice(String flagCode) throws Exception {
        InvoicingTab testUserOnInvoicesTab = new InvoicingTab();
        testUser.createCustomerWithBasicInfo();
        userOnInfoTab = sameUser.goToInfoTab();
        userOnInfoTab.selectCustomerGenericFlag(flagCode);
        sameUser.clickSaveButton();
        testSubscription.createNewSubscriptionWithOnlyServiceType();
        testUserOnInvoicesTab.automateGeneratingStandAloneInvoice();
    }

    @And("I Execute Trigger {string} On Subdomain {string} For Office {string}")
    public void automateExecutingTriggerOnSubdomainWithEndPoint(String triggerName, String subdomain, String officeID) {
        String url = "https://" + subdomain + ".pestroutes.com/resources/scripts/" + triggerName +
                    ".php?debug=1&office=" + officeID + "&testing=1";
        WebDriver driver = loadIncognitoChromeBrowser(url);
        closeIncognitoBrowser(driver);
    }

    @Then("I Verify The Customer Received {string} Note After Executing The Trigger")
    public void testCustomerReceivedDetailNoteLog(String noteDetail) {
       userOnHeader.searchCustomerWithName(testCustomer.customerName);
       sameUser.goToNotesTab();
       boolean isNoteSent = userOnNotesTab.getNotesLogInfo().contains(noteDetail);
       softAssert.assertTrue(isNoteSent,
                "Customer Did Not Receive " + noteDetail + " After Executing Trigger");
       sameUser.goToAdminTab();
       userOnAdminTab.clickRemoveButton();
       userOnAdminTab.clickConfirmRemoveButton();
       softAssert.assertAll();
    }

    @Then("I Verify The Customer Received {string} After Executing The Trigger")
    public void testCustomerReceivedAddedFlag(String additionalFlag) {
        userOnHeader.searchCustomerWithName(testCustomer.customerName);
        boolean isGenericFlagDisplayedOnOverviewTab = userOnOverviewTab.getAlert(genericFlag);
        boolean isAdditionalFlagDisplayedOnOverviewTab = userOnOverviewTab.getAlert(additionalFlag);
        softAssert.assertTrue(isGenericFlagDisplayedOnOverviewTab,
                "\n" + genericFlag + " Is Not Displayed On The Overview Tab After Executing Trigger" );
        softAssert.assertTrue(isAdditionalFlagDisplayedOnOverviewTab,
                additionalFlag + " Is Not Displayed On The Overview Tab After Executing Trigger \n" );

        sameUser.goToInfoTab();
        boolean isGenericFlagDisplayedOnInfoTab = userOnInfoTab.getGenericFlag(genericFlag);
        boolean isAdditionalFlagDisplayedOnInfoTab = userOnInfoTab.getGenericFlag(additionalFlag);
        softAssert.assertTrue(isGenericFlagDisplayedOnInfoTab,
                "\n" + genericFlag + " Is Not Displayed On The Info Tab After Executing Trigger" );
        softAssert.assertTrue(isAdditionalFlagDisplayedOnInfoTab,
                additionalFlag + " Is Not Displayed On The Info Tab After Executing Trigger \n" );
        sameUser.goToAdminTab();
        userOnAdminTab.clickRemoveButton();
        userOnAdminTab.clickConfirmRemoveButton();
        softAssert.assertAll();
    }
}