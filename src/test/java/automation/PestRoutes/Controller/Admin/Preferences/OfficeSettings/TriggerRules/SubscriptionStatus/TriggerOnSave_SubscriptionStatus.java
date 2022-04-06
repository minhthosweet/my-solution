package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import java.io.IOException;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;

import static automation.PestRoutes.Utilities.Data.GetDate.currentDate;

public class TriggerOnSave_SubscriptionStatus {

    CreateTrigger_SubscriptionStatus createSubscriptionStatus = new CreateTrigger_SubscriptionStatus();
    TriggerOnSave_CustomerStatus triggerOnSave_CustomerStatus = new TriggerOnSave_CustomerStatus();
    TriggerOnSave_AppointmentStatus triggerOnSave_AppointmentStatus = new TriggerOnSave_AppointmentStatus();
    TriggerOnSave_CustomerStatus triggerOnSave = new TriggerOnSave_CustomerStatus();
    ValidateRenewal renewal;
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    CustomerViewDialog_InfoTab userOnInfoTab = new CustomerViewDialog_InfoTab();
    CustomerViewDialog_SubscriptionTab userOnSubscriptionTab = new CustomerViewDialog_SubscriptionTab();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    DashboardPage userOnDashboard = new DashboardPage();
    PreferencesPage userOnPreferences = new PreferencesPage();
    TriggerRules userOnTriggerRulesPage = new TriggerRules();
    AR_Age testTrigger = new AR_Age();
    SubscriptionStatusTab userSelectsSubscriptionStatusTrigger = new SubscriptionStatusTab();

    private String description_TriggerOnSave = "TriggerOnSave_SubscriptionStatus";

    @Test
    public void triggerOnSave_SubscriptionStatus() throws Exception {
        createTriggerOnSave_SubscriptionStatus(description_TriggerOnSave);
        triggerOnSave_SubscriptionStatus_createAllActions(description_TriggerOnSave);

        //Any Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Any", description_TriggerOnSave);
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerQueue();
        assertAllLogs();

        //Frozen Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Frozen", description_TriggerOnSave);
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        createFrozenSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerQueue();
        assertAllLogs();

        //Active Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Active", description_TriggerOnSave);
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        createActiveSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerQueue();
        assertAllLogs();

    }

    // Create trigger
    public void createTriggerOnSave_SubscriptionStatus(String description) throws Exception {
        createSubscriptionStatus.createTrigger_SubscriptionStatus(description_TriggerOnSave);
    }

    // Create Actions
    public void triggerOnSave_SubscriptionStatus_createAllActions(String description) throws InterruptedException, IOException {
        triggerOnSave.customerStatus_addAllAction(description);
    }

    // Assert Trigger Log
    public void assertAllLogs() throws IOException, Exception {
        triggerOnSave.assertlog();
    }

    // Create frozen subscription
    public void createFrozenSubscription() throws Exception {
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        renewal = new ValidateRenewal();
        renewal.freezeSubscription();
    }

    // Activate Subscription
    public void createActiveSubscription() throws Exception {
        renewal = new ValidateRenewal();
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        renewal.freezeSubscription();
        renewal.reActivateSubscription();
    }

    @Given("I Set Up Trigger Type {string} That {string} When Status Changed To {string}")
    public void automateSettingUpTriggerTypeSubscriptionStatus(String trigger, String whenToTrigger, String changeStatus) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
        userOnTriggerRulesPage.addActiveTrigger
                (trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
        userSelectsSubscriptionStatusTrigger.selectWhenToTrigger(whenToTrigger);
        userSelectsSubscriptionStatusTrigger.selectStatusChangedTo(changeStatus);
        userSelectsSubscriptionStatusTrigger.typeIncludeCustomerFlag(testTrigger.genericFlag);
    }

    @When("I Add {string} Flag To The Customer Before Changing The Subscription Status")
    public void automateSettingUpCustomerWithFlagAndChangeSubscriptionStatus(String flagCode) {
        testCustomer.createCustomerWithBasicInfo();
        userOnInfoTab = sameUser.goToInfoTab();
        userOnInfoTab.selectCustomerGenericFlag(flagCode);
        sameUser.clickSaveButton();

        sameUser.goToSubscriptionTab();
        userOnSubscriptionTab.clickNewSubscription();
        userOnSubscriptionTab.selectRecurringServiceType("Automation Renewal");
        sameUser.clickSaveButton();

        userOnSubscriptionTab.clickActivateDeactivateButton();
        userOnSubscriptionTab.clickFreezeSubscriptionButtonOnCancelSubscriptionDialog();
        sameUser.clickSaveButton();
        sameUser.clickClose();
    }
}
