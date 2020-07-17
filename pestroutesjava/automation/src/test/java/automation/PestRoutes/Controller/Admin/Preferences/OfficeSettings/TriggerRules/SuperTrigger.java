package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_daysPastDue;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.CreateTrigger_AR;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerAfterTime_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.TriggerDaysBefore_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.TriggerOnCheckIn_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.TriggerAfterTime_SubscriptionStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.TriggerOnSave_SubscriptionStatus;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules_Cucumber;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.CreateTrigger_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.CreateTrigger_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.CreateTrigger_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.CreateTrigger_SubscriptionStatus;
import automation.PestRoutes.Utilities.BaseClass;

public class SuperTrigger extends BaseClass {
    CreateTrigger_Reminder Reminder_trigger = new CreateTrigger_Reminder();
    CreateTrigger_AR AR_trigger = new CreateTrigger_AR();
    CreateTrigger_AppointmentStatus AppointmentStatus_trigger = new CreateTrigger_AppointmentStatus();
    CreateTrigger_CustomerStatus CustomerStatus_trigger = new CreateTrigger_CustomerStatus();
    Trigger_Renewal Renewal_trigger = new Trigger_Renewal();
    Trigger_SubscriptionDueForService SubscriptionDueForService_trigger = new Trigger_SubscriptionDueForService();
    CreateTrigger_SubscriptionStatus SubscriptionStatus_trigger = new CreateTrigger_SubscriptionStatus();
    TriggerRules triggerAdmin;
    TriggerOnSave_AppointmentStatus triggerOnSave_appointmentStatus;
    AR_daysPastDue ar_daysPastDue;
    AR_Age ar_age;
    TriggerOnSave_CustomerStatus triggerOnSave_customerStatus;
    TriggerAfterTime_CustomerStatus triggerAfterTime_customerStatus;
    TriggerOnCheckIn_Reminder triggerOnCheckIn_reminder;
    TriggerDaysBefore_Reminder triggerDaysBefore_reminder;
    TriggerAfterTime_SubscriptionStatus triggerAfterTime_subscriptionStatus;
    TriggerOnSave_SubscriptionStatus triggerOnSave_subscriptionStatus;
    TriggerRules_Cucumber triggerRules_cucumber;

    @Test
    public void createTrigger() throws Exception {
        Reminder_trigger.createReminderRule();
        AR_trigger.createRenewalRule();
        AppointmentStatus_trigger.createAppointmentStatusRule();
        CustomerStatus_trigger.createCustomerStatusRule();
        Renewal_trigger.createRenewalRule();
        SubscriptionDueForService_trigger.createSubscriptionDueForService();
        SubscriptionStatus_trigger.createSubscriptionStatusRule();

    }

    @Given("I add a trigger {string}")
    public void workWithTrigger(String descriptionName) throws Exception {
        triggerRules_cucumber = new TriggerRules_Cucumber();
        SubscriptionStatus_trigger.searchTrigger_subscriptionStatus(descriptionName);
        try {
            triggerAdmin = new TriggerRules();
            WebElement elm = triggerAdmin.getDescription(descriptionName);
            System.out.println("Trigger Found");
        } catch (Exception e) {
            createTriggerRule(descriptionName);
        }
    }

    public void createTriggerRule(String description) throws Exception {
        triggerOnSave_appointmentStatus = new TriggerOnSave_AppointmentStatus();
        ar_daysPastDue = new AR_daysPastDue();
        ar_age = new AR_Age();
        triggerOnSave_customerStatus = new TriggerOnSave_CustomerStatus();
        triggerAfterTime_customerStatus = new TriggerAfterTime_CustomerStatus();
        triggerAfterTime_subscriptionStatus = new TriggerAfterTime_SubscriptionStatus();
        triggerOnSave_subscriptionStatus = new TriggerOnSave_SubscriptionStatus();

        switch (description) {
            case "TriggerOnSave_AppointmentStatus":
                triggerOnSave_appointmentStatus.createTriggerOnSave_AppointmentStatus(description);
                triggerOnSave_appointmentStatus.appointmentStatus_addAllAction(description);
                break;
            case "TriggerDaysPastDue_AR":
                ar_daysPastDue.createTriggerandActionsDaysPastDue_AR(description, ar_daysPastDue.pastDue);
                break;
            case "TriggerAge_AR":
                ar_age.createTriggerandActionsAge_AR(description, ar_age.age);
                break;
            case "TriggerOnSave_CustomerStatus":
                triggerOnSave_customerStatus.createTriggerOnSave_CustomerStatus(description);
                triggerOnSave_customerStatus.customerStatus_addAllAction(description);
                triggerOnSave_customerStatus.customerStatus_removePaymentProfileAction(description);
                break;
            case "TriggerAfterTime_CustomerStatus":
                triggerAfterTime_customerStatus.createTriggerAfterTime_CustomerStatus(description);
                triggerAfterTime_customerStatus.customerStatus_CreateAllActions(description);
                break;
            case "TriggerOnCheckIn_Reminder":
                triggerOnCheckIn_reminder.createTriggerOnCheckIn_Reminder(description);
                triggerOnCheckIn_reminder.reminder_createAllActions();
                break;
            case "TriggerBeforeDays_Reminder":
                triggerDaysBefore_reminder.createTriggerDaysBefore_Reminder(description);
                triggerDaysBefore_reminder.reminder_createAllActions();
                break;
            case "TriggerOnSave_SubscriptionStatus":
                triggerOnSave_subscriptionStatus.createTriggerOnSave_SubscriptionStatus(description);
                triggerOnSave_subscriptionStatus.triggerOnSave_SubscriptionStatus_createAllActions(description);
                break;
            case "TriggerAfterTime_SubscriptionStatus":
                triggerAfterTime_subscriptionStatus.createTriggerAfterTime_SubscriptionStatus(description);
                triggerAfterTime_subscriptionStatus.triggerAfterTime_SubscriptionStatus_CreateAllActions(description);
                break;
            default:
                System.out.println("Trigger Not Found");
        }
    }
}