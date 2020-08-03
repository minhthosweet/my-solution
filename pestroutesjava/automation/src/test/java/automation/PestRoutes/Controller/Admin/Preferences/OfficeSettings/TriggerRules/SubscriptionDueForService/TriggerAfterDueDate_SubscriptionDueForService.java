package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.CreateTrigger_SubscriptionStatus;
import automation.PestRoutes.Utilities.BaseClass;
import org.testng.annotations.Test;

public class TriggerAfterDueDate_SubscriptionDueForService extends BaseClass {

    CreateTrigger_SubscriptionDueForService createTrigger_subscriptionDueForService;
    CreateTrigger_SubscriptionStatus createTrigger_subscriptionStatus = new CreateTrigger_SubscriptionStatus();
    private String description_TriggerAfterDueDate = "TriggerAfterDueDate_SubscriptionDueForService";

    @Test
    public void triggerAfterDueDate_SubscriptionStatus() throws Exception {
        createTriggerAfterDueDate(description_TriggerAfterDueDate);
    }

    public void createTriggerAfterDueDate(String description) throws Exception {
        createTrigger_subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
        createTrigger_subscriptionDueForService.createTrigger_SubscriptionDueForService(description);
        createTrigger_subscriptionStatus.searchTrigger_subscriptionStatus(description);
        createTrigger_subscriptionDueForService.editTrigger_afterDueDate_subscriptionDueForService(description);
    }
}
