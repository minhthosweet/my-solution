package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.SubscriptionStatusTab;
import automation.PestRoutes.Utilities.BaseClass;
import org.testng.annotations.Test;

public class TriggerAfterTime_AppointmentStatus extends BaseClass {
    TriggerOnSave_AppointmentStatus triggerOnSave_appointmentStatus;
    TriggerOnSave_CustomerStatus customerStatus;
    TriggerRules triggerAdmin;
    SubscriptionStatusTab subscriptionStatus;

    private String days_After_Change = "1";
    private String status = "Any";
    public String description_TriggerAfterTime = "TriggerAfterTime_AppointmentStatus";


    @Test
    public void triggerAfterTime_AppointmentStatus() throws Exception {
        createAfterTime_AppointmentStatus(description_TriggerAfterTime);
        editTrigger_triggerAfterTime_AppointmentStatus(status, description_TriggerAfterTime);
    }

    public void createAfterTime_AppointmentStatus(String description) throws Exception {
        triggerOnSave_appointmentStatus = new TriggerOnSave_AppointmentStatus();
        triggerOnSave_appointmentStatus.createTriggerOnSave_AppointmentStatus(description);
    }

    public void editTrigger_triggerAfterTime_AppointmentStatus(String statusChange, String description) {
        customerStatus = new TriggerOnSave_CustomerStatus();
        triggerAdmin = new TriggerRules();
        subscriptionStatus = new SubscriptionStatusTab();
        triggerAdmin.clickEditTrigger(description);
        triggerAdmin.selectDropdown(subscriptionStatus.whenToTrigger, subscriptionStatus.whenToTrigger_triggerOnSave);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
        triggerAdmin.setDaysAfterChange(days_After_Change);
        triggerAdmin.selectDropdown(subscriptionStatus.statusChangedTo, statusChange);
        triggerAdmin.clickSaveButton();
    }
}
