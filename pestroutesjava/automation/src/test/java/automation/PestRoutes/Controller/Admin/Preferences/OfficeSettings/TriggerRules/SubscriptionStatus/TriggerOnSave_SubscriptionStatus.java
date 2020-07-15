package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import java.io.IOException;

import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.Utilities.BaseClass;

public class TriggerOnSave_SubscriptionStatus extends BaseClass {

    CreateTrigger_SubscriptionStatus createSubscriptionStatus = new CreateTrigger_SubscriptionStatus();
    TriggerOnSave_CustomerStatus triggerOnSave_CustomerStatus = new TriggerOnSave_CustomerStatus();
    TriggerOnSave_AppointmentStatus triggerOnSave_AppointmentStatus = new TriggerOnSave_AppointmentStatus();
    TriggerOnSave_CustomerStatus triggerOnSave = new TriggerOnSave_CustomerStatus();
    ValidateRenewal renewal;

    private String description_TriggerOnSave = "TriggerOnSave_SubscriptionStatus";

    @Test
    public void triggerOnSave_SubscriptionStatus() throws Exception {
        createTriggerOnSave_SubscriptionStatus(description_TriggerOnSave);
        triggerOnSave_SubscriptionStatus_createAllActions(description_TriggerOnSave);

        //Any Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Any");
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerQueue();
        assertAllLogs();

        //Frozen Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Frozen");
        createFrozenSubscription();
        triggerOnSave_AppointmentStatus.hitTriggerQueue();
        assertAllLogs();

        //Active Subscription Status Trigger Validation
        triggerOnSave_CustomerStatus.editTrigger_triggerOnSave_CustomerStatus("Active");
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
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        renewal = new ValidateRenewal();
        renewal.freezeSubscription();
        //renewal.activateSubscription();
    }
}
