package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerCommunicationTab.CustomerCommunicationTab;
import io.cucumber.java.en.Given;

public class CustomerCommunication {
    CustomerCommunicationTab customerCommunicationTab;

    @Given("I enable SMS checkbox if not already enabled")
    public void addGenericFlag() throws InterruptedException {
        customerCommunicationTab = new CustomerCommunicationTab();
        customerCommunicationTab.navigateToCustomerCommunication();
        customerCommunicationTab.clickEditCustomerPreferences();
        customerCommunicationTab.enableDefaultSMS();
    }
}
