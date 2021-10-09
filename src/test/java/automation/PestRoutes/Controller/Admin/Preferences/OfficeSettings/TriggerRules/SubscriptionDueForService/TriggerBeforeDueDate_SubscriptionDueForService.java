package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Utilities.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TriggerBeforeDueDate_SubscriptionDueForService extends BaseClass {
    CreateTrigger_SubscriptionDueForService createTrigger_subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
    TriggerOnSave_CustomerStatus triggerOnSave_customerStatus = new TriggerOnSave_CustomerStatus();

    private String description_TriggerBeforeDueDate = "TriggerBeforeDueDate_SubscriptionDueForService";

    @Test
    public void triggerBeforeDueDate_SubscriptionStatus() throws Exception {
        createTriggerBeforeDueDate(description_TriggerBeforeDueDate);
        triggerBeforeDueDate_SubscriptionDueForService_CreateAllActions(description_TriggerBeforeDueDate);
    }

    public void createTriggerBeforeDueDate(String description) throws Exception {
        createTrigger_subscriptionDueForService.createTrigger_SubscriptionDueForService(description);
    }

    public void triggerBeforeDueDate_SubscriptionDueForService_CreateAllActions(String description) throws IOException, InterruptedException {
        triggerOnSave_customerStatus.customerStatus_addAllAction(description);
    }

}
