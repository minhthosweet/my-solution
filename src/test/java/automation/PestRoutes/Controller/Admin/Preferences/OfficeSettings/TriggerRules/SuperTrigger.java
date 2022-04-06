package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_Age;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AR.AR_daysPastDue;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus.TriggerOnSave_AppointmentStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerAfterTime_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.TriggerDaysBefore_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Reminders.TriggerOnCheckIn_Reminder;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Renewal.CreateTrigger_Renewal;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.TriggerAfterDueDate_SubscriptionDueForService;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.TriggerBeforeDueDate_SubscriptionDueForService;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.TriggerAfterTime_SubscriptionStatus;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.TriggerOnSave_SubscriptionStatus;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules_Cucumber;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionStatus.CreateTrigger_SubscriptionStatus;

public class SuperTrigger {
    CreateTrigger_SubscriptionStatus SubscriptionStatus_trigger = new CreateTrigger_SubscriptionStatus();
    CreateTrigger_Renewal createTrigger_renewal;
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
    TriggerBeforeDueDate_SubscriptionDueForService triggerBeforeDueDate_subscriptionDueForService;
    TriggerAfterDueDate_SubscriptionDueForService triggerAfterDueDate_subscriptionDueForService;
    TriggerRules_Cucumber triggerRules_cucumber;

    @Test
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
        triggerDaysBefore_reminder = new TriggerDaysBefore_Reminder();
        triggerOnCheckIn_reminder = new TriggerOnCheckIn_Reminder();
        triggerBeforeDueDate_subscriptionDueForService = new TriggerBeforeDueDate_SubscriptionDueForService();
        triggerAfterDueDate_subscriptionDueForService = new TriggerAfterDueDate_SubscriptionDueForService();
        createTrigger_renewal = new CreateTrigger_Renewal();

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
            case "TriggerBeforeDueDate_SubscriptionDueForService":
                triggerBeforeDueDate_subscriptionDueForService.createTriggerBeforeDueDate(description);
                triggerBeforeDueDate_subscriptionDueForService.triggerBeforeDueDate_SubscriptionDueForService_CreateAllActions(description);
                break;
            case "TriggerAfterDueDate_SubscriptionDueForService":
                triggerAfterDueDate_subscriptionDueForService.createTriggerAfterDueDate(description);
                triggerBeforeDueDate_subscriptionDueForService.triggerBeforeDueDate_SubscriptionDueForService_CreateAllActions(description);
                break;
            case "TriggerBeforeExpirationDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerAfterExpirationDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerBeforeNextBillingDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerAfterNextBillingDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerBeforeDueDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerAfterDueDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerBeforeRenewalDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            case "TriggerAfterRenewalDate_Renewal":
                createTrigger_renewal.createTrigger_Renewal(description);
                createTrigger_renewal.createAllActions_Renewals(description);
                break;
            default:
                System.out.println("Trigger Not Found");
        }
    }
}