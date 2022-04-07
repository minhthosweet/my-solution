package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.AutoPaymentPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static automation.PestRoutes.Utilities.Data.GetDate.currentDate;

public class AutoPaymentTest {

    DashboardPage userOnDashboard = new DashboardPage();
    AdminMainPage userOnAdminComponent = new AdminMainPage();
    PreferencesPage userOnPreferences = new PreferencesPage();
    TriggerRules userOnTriggerRulesPage = new TriggerRules();
    ARTab triggerAR = new ARTab();
    AR_Age testTrigger = new AR_Age();
    AutoPaymentPage userOnAutoPaymentPage = new AutoPaymentPage();

    @Given("I Set Up {string} Trigger Type")
    public void automateSettingUpTriggerTypeAutoPayment(String trigger) {
        userOnAdminComponent = userOnDashboard.goToAdminComponent();
        userOnPreferences = userOnAdminComponent.clickPreferencesSubComponent();
        userOnTriggerRulesPage = userOnPreferences.clickTriggerRules();
        userOnTriggerRulesPage.addActiveTrigger
                (trigger, trigger + " Automation Trigger", currentDate("MM/dd/yy"));
        userOnAutoPaymentPage.typeFlagToInclude(testTrigger.genericFlag);
    }

    @When("I Complete The {string} Action")
    public void automateSendingCompleteActionForAutoPayment(String action) {
        userOnAutoPaymentPage.clickAddActionButton();
        userOnAutoPaymentPage.completeProcessAutoPaymentAction(action);
        userOnAutoPaymentPage.clickSaveButton();
    }
}
