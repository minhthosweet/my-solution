package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerAfterTime_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class TriggerAfterTime_SubscriptionStatus extends BaseClass {
    CreateTrigger_SubscriptionStatus createSubscriptionStatus = new CreateTrigger_SubscriptionStatus();
    TriggerRules triggerAdmin;
    SubscriptionStatusTab subscriptionStatus;
    TriggerOnSave_SubscriptionStatus triggerOnSave_subscriptionStatus = new TriggerOnSave_SubscriptionStatus();
    TriggerOnSave_CustomerStatus triggerOnSave_CustomerStatus = new TriggerOnSave_CustomerStatus();
    TriggerOnSave_AppointmentStatus triggerOnSave_AppointmentStatus = new TriggerOnSave_AppointmentStatus();
    TriggerAfterTime_CustomerStatus triggerAfterTime_CustomerStatus;
    private String description_TriggerOnSave = "TriggerAfterTime_SubscriptionStatus";

    @Test
    public void triggerAfterTime_SubscriptionStatus() throws Exception {
        createTriggerAfterTime_SubscriptionStatus(description_TriggerOnSave);
        triggerAfterTime_SubscriptionStatus_CreateAllActions(description_TriggerOnSave);

        //Any Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Any");
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerAppointmentStatus();
        assertAllLogs();

        //Frozen Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Frozen");
        triggerOnSave_subscriptionStatus.createFrozenSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerAppointmentStatus();
        assertAllLogs();

        //Active Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Active");
        triggerOnSave_subscriptionStatus.createActiveSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerAppointmentStatus();
        assertAllLogs();
    }

    // Create trigger
    public void createTriggerAfterTime_SubscriptionStatus(String description) throws Exception {
        triggerAdmin = new TriggerRules();
        subscriptionStatus = new SubscriptionStatusTab();
        createSubscriptionStatus.createTrigger_SubscriptionStatus(description_TriggerOnSave);
        createSubscriptionStatus.searchTrigger_subscriptionStatus(description_TriggerOnSave);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger,
                subscriptionStatus.whenToTrigger_triggerAfterTime);
        triggerAdmin.setDaysAfterChange(createSubscriptionStatus.days_After_Change);
        triggerAdmin.clickSaveButton();
    }

    //Create all Actions
    public void triggerAfterTime_SubscriptionStatus_CreateAllActions(String description) throws IOException, InterruptedException {
        triggerOnSave_subscriptionStatus.triggerOnSave_SubscriptionStatus_createAllActions(description);
    }

    //Assert all logs
    public void assertAllLogs() throws Exception {
        triggerAfterTime_CustomerStatus = new TriggerAfterTime_CustomerStatus();
        triggerAfterTime_CustomerStatus.assertFrozen_allActions();
    }

}
