package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.AppointmentStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Subscriptions.AddSubscription;
import automation.PestRoutes.Utilities.AppData;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.SubscriptionDueForService.CreateTrigger_SubscriptionDueForService;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.CustomerStatus.TriggerOnSave_CustomerStatus;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.Utilities.Utilities;

public class TriggerOnSave_AppointmentStatus extends AppData {
    CreateTrigger_AppointmentStatus createAppointmentStatus = new CreateTrigger_AppointmentStatus();
    TriggerRules triggerAdmin = new TriggerRules();
    ValidateRenewal validateRenewal;
    CreateTrigger_SubscriptionDueForService subscriptionDueForService;
    TriggerOnSave_CustomerStatus customerStatus;
    AddSubscription addSubscription;
    CreateNewCustomer createNewCustomer = new CreateNewCustomer();

    private String description_TriggerOnSave = "TriggerOnSave_AppointmentStatus";
    public List list = new ArrayList<String>();
    private String status = "Any";

    @Test
    public void triggerOnSave_AppointmentStatus() throws Exception {
        createTriggerOnSave_AppointmentStatus(description_TriggerOnSave);
        editTrigger_triggerOnSave_AppointmentStatus(status);
        appointmentStatus_addAllAction(description_TriggerOnSave);
        createCutomerWithSubscription();
        scheduleappointment();
        hitTriggerQueue();

    }

    // Create Trigger
    public void createTriggerOnSave_AppointmentStatus(String description) throws Exception {
        createAppointmentStatus.createTrigger_AppointmentStatus(description);
    }

    // Create all actions under
    public void appointmentStatus_addAllAction(String description) throws InterruptedException, IOException {
        customerStatus = new TriggerOnSave_CustomerStatus();
        customerStatus.customerStatus_addAllAction(description);
    }

    // Edit Trigger
    public void editTrigger_triggerOnSave_AppointmentStatus(String statusChange) throws Exception {
        customerStatus = new TriggerOnSave_CustomerStatus();
        customerStatus.editTrigger_triggerOnSave_CustomerStatus(statusChange, description_TriggerOnSave);
    }

    // Create customer with Renewal Subscription
    public void createCutomerWithSubscription() throws Exception {
        addSubscription = new AddSubscription();
        createNewCustomer.createCustomerWithAddress();
        addSubscription.startSubscriptionWithSalesRep(getData("salesmanName", generalData), getData("subscriptionFlagName", generalData));

    }

    // Schedule an appointment
    public void scheduleappointment() throws Exception {
        validateRenewal = new ValidateRenewal();
        validateRenewal.scheduleSubscription("11:00");
    }

    // Hit trigger Queue
    public void hitTriggerQueue() {
        triggerAdmin.hitTriggerQueue();
    }

    // Hit trigger AppointmentStatus
    public void hitTriggerAppointmentStatus() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerAppointmentStatus.php");
    }

    // assert SMS Log
    public void assertSMSlog() throws IOException, Exception {
        subscriptionDueForService = new CreateTrigger_SubscriptionDueForService();
        subscriptionDueForService.assertSMSLog();
    }

}
