package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.AppointmentStatusPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Footer;
import automation.PestRoutes.Utilities.AppData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.CreateTrigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.Utilities.Utilities;
import org.testng.asserts.SoftAssert;

import static automation.PestRoutes.Utilities.Utilities.currentDate;

public class TriggerOnSave_AppointmentStatus extends AppData {

    SoftAssert softAssert = new SoftAssert();
    CreateTrigger_AppointmentStatus createAppointmentStatus = new CreateTrigger_AppointmentStatus();
    TriggerRules triggerAdmin = new TriggerRules();
    ValidateRenewal validateRenewal;
    CreateTrigger_SubscriptionDueForService subscriptionDueForService;
    TriggerOnSave_CustomerStatus customerStatus;
    AddSubscription addSubscription;
    CreateNewCustomer createNewCustomer = new CreateNewCustomer();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    DashboardPage userOnDashboard = new DashboardPage();
    PreferencesPage userOnPreferences = new PreferencesPage();
    TriggerRules userOnTriggerRulesPage = new TriggerRules();
    AppointmentStatusPage userSelectsAppointmentStatusTrigger = new AppointmentStatusPage();
    AR_Age testTrigger = new AR_Age();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    Footer footer = new Footer();
    CreateNewCustomer testCustomer = new CreateNewCustomer();

    private String description_TriggerOnSave = "TriggerOnSave_AppointmentStatus";
    public List list = new ArrayList<String>();
    private String status = "Any";
    public static String notes;
    public static String category;

    @Test
    public void triggerOnSave_AppointmentStatus() throws Exception {
        createTriggerOnSave_AppointmentStatus(description_TriggerOnSave);
        editTrigger_triggerOnSave_AppointmentStatus(status);
        appointmentStatus_addAllAction(description_TriggerOnSave);
        createCutomerWithSubscription();
        scheduleappointment();
        hitTriggerQueue();
    }

    // Create Trigger
    public void createTriggerOnSave_AppointmentStatus(String description) throws Exception {
        createAppointmentStatus.createTrigger_AppointmentStatus(description);
    }

    // Create all actions under
    public void appointmentStatus_addAllAction(String description) throws InterruptedException, IOException {
        customerStatus = new TriggerOnSave_CustomerStatus();
        customerStatus.customerStatus_addAllAction(description);
    }

    // Edit Trigger
    public void editTrigger_triggerOnSave_AppointmentStatus(String statusChange) throws Exception {
        customerStatus = new TriggerOnSave_CustomerStatus();
        customerStatus.editTrigger_triggerOnSave_CustomerStatus(statusChange, description_TriggerOnSave);
    }

    // Create customer with Renewal Subscription
    public void createCutomerWithSubscription() throws Exception {
        addSubscription = new AddSubscription();
        createNewCustomer.createCustomerWithAddress();
        addSubscription.startSubscriptionWithSalesRep(getData("salesmanName", generalData), getData("subscriptionFlagName", generalData));
    }

    // Schedule an appointment
    public void scheduleappointment() throws Exception {
        validateRenewal = new ValidateRenewal();
        validateRenewal.scheduleSubscription("11:00");
    }

    // Hit trigger Queue
    public void hitTriggerQueue() {
        triggerAdmin.hitTriggerQueue();
    }

    // Hit trigger AppointmentStatus
    public void hitTriggerAppointmentStatus() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerAppointmentStatus.php");
    }

    // assert SMS Log
    public void assertSMSlog() throws IOException, Exception {
        subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
        subscriptionDueForService.assertSMSLog();
    }

    @Given("I Set Up {string} Trigger Type That {string} When Status Changed To {string}")
    public void automateSettingUpTriggerTypeAppointmentStatus(String trigger, String whenToTrigger, String changeStatus) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
        userOnTriggerRulesPage.addActiveTrigger
                (trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
        userSelectsAppointmentStatusTrigger.selectStatusChangedTo(changeStatus);
        userSelectsAppointmentStatusTrigger.selectWhenToTrigger(whenToTrigger);
        userSelectsAppointmentStatusTrigger.selectAppointmentType("Include Stand-Alone and Reservice Appointments");
        userSelectsAppointmentStatusTrigger.typeIncludeCustomerFlag(testTrigger.genericFlag);
    }

    @And("I Complete {string} Action With {string} Details")
    public void automateSendingCompleteStatusAction(String action, String details) {
        userSelectsAppointmentStatusTrigger.clickAddActionButton();
        userSelectsAppointmentStatusTrigger.completeStatusAction(action, details);
        notes = userSelectsAppointmentStatusTrigger.getActionNotes(action);
        category = userSelectsAppointmentStatusTrigger.getActionCategory(action);
        userSelectsAppointmentStatusTrigger.clickSaveButton();
    }

    @Then("I Verify Tasks Are Added After Executing The Trigger")
    public void testAddedTasksAfterExecutingTrigger(){
        sameUser.goToTasks();
        String actualCustomer = footer.getCustomerFromList(testCustomer.customerName).toUpperCase();
        String expectedCustomer = testCustomer.customerName.toUpperCase();
        softAssert.assertEquals(actualCustomer, expectedCustomer,
                "\n Actual Customer:   " + actualCustomer +
                        "\n Expected Customer: " + expectedCustomer +
                        "\n Actual & Expected Customers Do Not Match via Task List \n");
        String actualTask = footer.getTaskInformation(testCustomer.customerName);
        String expectedTask = notes;
        softAssert.assertEquals(actualTask, expectedTask,
                "\n Actual Task:   " + actualTask +
                        "\n Expected Task: " + expectedTask +
                        "\n The Actual & Expected Tasks Do Not Match \n");
        String actualCategory = footer.getTaskCategory(testCustomer.customerName);
        String expectedCategory = category;
        softAssert.assertEquals(actualCategory, expectedCategory,
                "\n Actual Category:   " + actualCategory +
                        "\n Expected Category: " + expectedCategory +
                        "\n The Actual & Expected Categories Do Not Match \n");
        footer.clickCustomerFromList(testCustomer.customerName);
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }

    @Then("I Verify The Alert Has Been Added After Executing The Trigger")
    public void testAddsAlertAfterExecutingTrigger() {
        sameUser.goToAlerts();
        String actualCustomer = footer.getCustomerFromList(testCustomer.customerName).toUpperCase();
        String expectedCustomer = testCustomer.customerName.toUpperCase();
        softAssert.assertEquals(actualCustomer, expectedCustomer,
                "\n Actual Customer:   " + actualCustomer +
                        "\n Expected Customer: " + expectedCustomer +
                        "\n Actual & Expected Customers Do Not Match via Alert List \n");
        String actualNotification = footer.getAlertNotification(testCustomer.customerName);
        String expectedNotification = notes;
        softAssert.assertEquals(actualNotification, expectedNotification,
                "\n Actual Notification:   " + actualNotification +
                        "\n Expected Notification: " + expectedNotification +
                        "\n The Actual & Expected Notifications Do Not Match \n");
        footer.clickCustomerFromList(testCustomer.customerName);
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}