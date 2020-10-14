package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;
import org.testng.annotations.Test;
import java.io.IOException;

public class TriggerAfterTime_SubscriptionStatus extends BaseClass {
    CreateTrigger_SubscriptionStatus createSubscriptionStatus = new CreateTrigger_SubscriptionStatus();
    TriggerRules triggerAdmin;
    SubscriptionStatusTab subscriptionStatus;
    TriggerOnSave_SubscriptionStatus triggerOnSave_subscriptionStatus = new TriggerOnSave_SubscriptionStatus();
    TriggerOnSave_CustomerStatus triggerOnSave = new TriggerOnSave_CustomerStatus();
    TriggerOnSave_AppointmentStatus triggerOnSave_AppointmentStatus = new TriggerOnSave_AppointmentStatus();
    private String description_TriggerOnSave = "TriggerAfterTime_SubscriptionStatus";

    @Test
    public void triggerAfterTime_SubscriptionStatus() throws Exception {
        createTriggerAfterTime_SubscriptionStatus(description_TriggerOnSave);
        triggerAfterTime_SubscriptionStatus_CreateAllActions(description_TriggerOnSave);

        //Any Subscription Status Trigger Validation
        triggerOnSave.editTrigger_triggerOnSave_CustomerStatus("Any", description_TriggerOnSave);
        triggerOnSave_AppointmentStatus.createCutomerWithSubscription();
        hitTriggerSubscriptionStatus();
        assertAllLogs();

        //Frozen Subscription Status Trigger Validation
        triggerOnSave.editTrigger_triggerOnSave_CustomerStatus("Frozen", description_TriggerOnSave);
        triggerOnSave_subscriptionStatus.createFrozenSubscription();
        hitTriggerSubscriptionStatus();
        assertAllLogs();

        //Active Subscription Status Trigger Validation
        triggerOnSave.editTrigger_triggerOnSave_CustomerStatus("Active", description_TriggerOnSave);
        triggerOnSave_subscriptionStatus.createActiveSubscription();
        hitTriggerSubscriptionStatus();
        assertAllLogs();
    }

    // Create trigger
    public void createTriggerAfterTime_SubscriptionStatus(String description) throws Exception {
        triggerAdmin = new TriggerRules();
        subscriptionStatus = new SubscriptionStatusTab();
        createSubscriptionStatus.createTrigger_SubscriptionStatus(description);
        createSubscriptionStatus.searchTrigger_subscriptionStatus(description);
        triggerAdmin.clickEditTrigger(description);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger,
                subscriptionStatus.whenToTrigger_triggerAfterTime);
        triggerAdmin.setDaysAfterChange(createSubscriptionStatus.days_After_Change);
        triggerAdmin.clickSaveButton();
    }

    //Create all Actions
    public void triggerAfterTime_SubscriptionStatus_CreateAllActions(String description) throws IOException, InterruptedException {
        triggerOnSave.customerStatus_addAllAction(description);
    }

    //Assert all logs
    public void assertAllLogs() throws Exception {
        triggerOnSave.assertlog();
    }

    // Run script
    public void hitTriggerSubscriptionStatus() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerCustomerStatus.php");
    }

}
