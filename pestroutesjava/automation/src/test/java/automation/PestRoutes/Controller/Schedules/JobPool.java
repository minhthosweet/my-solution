package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.JobPoolTab;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.io.IOException;
import static automation.PestRoutes.Utilities.AssertException.result;

public class JobPool extends AppData {

    Header header;
    SchedulingTab scheduleDay;
    JobPoolTab jobPoolTab;
    CreateNewCustomer customer;
    CustomerViewDialog_Header customerDialog_Header;
    CreateCustomerDialog createCustomerDialog;
    CustomerviewDialog_AppointmentsTab createCustomerDialog_AppointmentsTab;

    private String customerName_JobPool = "";

    @And("I navigate to the job pool tab")
    public void navigateToJobPool() {
        header = new Header();
        scheduleDay = new SchedulingTab();

        header.navigateTo(header.schedulingTab);
        scheduleDay.clickJobPool();
    }

    @And("I validate if all fields are displaying and are enabled in Job Pool")
    public void verifyFilters() {
        jobPoolTab = new JobPoolTab();
        jobPoolTab.clickAdvanceFilterButton();
        String[] fields = {
                jobPoolTab.filterTypes("IncludeCustomersWithSpecialRequests"),
                jobPoolTab.filterTypes("showPrePlanned"),
                jobPoolTab.filterTypes("excludeOneTimeServices"),
                jobPoolTab.filterTypes("dueBetween"),
                jobPoolTab.filterTypes("dueEnd"),
                jobPoolTab.filterTypes("followUpDueBetween"),
                jobPoolTab.filterTypes("followUpDueEnd"),
                jobPoolTab.filterTypes("filterPotential"),
                jobPoolTab.filterTypes("filterFollowUp"),
                jobPoolTab.filterTypes("pendingCancels"),
                jobPoolTab.filterTypes("propertyType"),
                jobPoolTab.filterTypes("filterBalances"),
                jobPoolTab.filterTypes("balanceAge"),
                jobPoolTab.filterTypes("mapPages"),
                jobPoolTab.filterTypes("zipCodes"),
                jobPoolTab.filterTypes("includeServiceTypes"),
                jobPoolTab.filterTypes("excludeServiceTypes"),
                jobPoolTab.filterTypes("hideAllFlags"),
                jobPoolTab.filterTypes("cities"),
                jobPoolTab.filterTypes("preferredTech"),
                jobPoolTab.filterTypes("preferredDays"),
                jobPoolTab.filterTypes("filterRegion"),
                jobPoolTab.filterTypes("measurement"),
                jobPoolTab.filterTypes("serviceCategories"),
                jobPoolTab.filterTypes("includeCustomerFlags"),
                jobPoolTab.filterTypes("excludeCustomerFlags"),
                jobPoolTab.filterTypes("includeSubscriptionFlags"),
                jobPoolTab.filterTypes("excludeSubscriptionFlags")};

        AssertException.validateFieldEnabled(fields);
        }

    @Then("I add all the fields in the job pool page {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and " +
            "{string} and {string} and {string} and {string} and {string} and {string}")
    public void completeAllJobPoolFields(String needScheduling, String needPrePlanned, String needOneTimeServices, String needPotential,
                                         String needFollowUpServices, String needPendingCancels, String needPropertyType, String needFlag,
                                         String needPreferredDay, String needMeasurement, String needIncludeCustomerFlag, String needExcludeCustomerFlage,
                                         String needIncludeSubscriptionFlag, String needExcludeSubscriptionFlag)
            throws IOException, InterruptedException {
        customer = new CreateNewCustomer();
        customerDialog_Header = new CustomerViewDialog_Header();
        createCustomerDialog = new CreateCustomerDialog();
        header = new Header();

        header.searchCustomer_History(header.convertName(customer.customerName));
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String customerZipCode = createCustomerDialog.getZipCode();
        String customerCity = createCustomerDialog.getCity();
        customerDialog_Header.clickCloseButton();

        setFilters(needScheduling, needPrePlanned, needOneTimeServices, needPotential, needFollowUpServices,  needPendingCancels, needPropertyType, needFlag,
                needPreferredDay, needMeasurement, needIncludeCustomerFlag, needExcludeCustomerFlage, needIncludeSubscriptionFlag, needExcludeSubscriptionFlag,
                customerZipCode, customerCity);
    }

    @Then("I validate the job pool results")
    public void validateJobPoolResult() throws IOException, InterruptedException {
        header = new Header();
        jobPoolTab = new JobPoolTab();
        customer = new CreateNewCustomer();
        customerDialog_Header = new CustomerViewDialog_Header();
        header = new Header();

        header.searchCustomer_History(header.convertName(customer.customerName));
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String expectedStreetAddress = createCustomerDialog.getAddress().toLowerCase();
        String expectedZipCode = createCustomerDialog.getZipCode();
        String expectedCity = createCustomerDialog.getCity().toLowerCase();
        String expectedCustomerName = header.convertName(customer.customerName);
        String expectedPhoneNumber = getData("phoneNumber", generalData);
        String expectedRegion = getData("region", generalData);
        String expectedServiceType = getData("inclServiceType", generalData);
        customerDialog_Header.clickCloseButton();

        checkResults(expectedStreetAddress, expectedZipCode, expectedCity, expectedCustomerName, expectedPhoneNumber,
                expectedRegion, expectedServiceType);
        clickPlayButton(expectedCustomerName);
    }

    public void setFilters(String needScheduling, String needPrePlanned, String needOneTimeServices, String needPotential,
                           String needFollowUpServices, String needPendingCancels, String needPropertyType, String needFlag,
                           String needPreferredDay, String needMeasurement, String needIncludeCustomerFlag, String needExcludeCustomerFlage,
                           String needIncludeSubscriptionFlag, String needExcludeSubscriptionFlag, String customerZipCode, String customerCity) throws IOException {
        //Set Scheduling Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("IncludeCustomersWithSpecialRequests"), needScheduling);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("showPrePlanned"), needPrePlanned);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeOneTimeServices"), needOneTimeServices);
        //Set Due Between Filters
        jobPoolTab.setDate(jobPoolTab.filterTypes("dueBetween"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("dueEnd"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("followUpDueBetween"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("followUpDueEnd"));
        //Set Customer Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterPotential"), needPotential);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterFollowUp"), needFollowUpServices);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("pendingCancels"), needPendingCancels);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("propertyType"), needPropertyType);
        //Set Balance Filters
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("filterBalances"),getData("balance", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("balanceAge"),getData("balanceAge", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("mapPages"),getData("mapCode", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("zipCodes"), customerZipCode);
        //Set Service Type Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeServiceTypes"),getData("inclServiceType", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeServiceTypes"),getData("exclServiceType", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("hideAllFlags"), needFlag);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("cities"),customerCity);
        //Set Preferred Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("preferredTech"),getData("userFirstName", generalData) + " " + getData("userLastName", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("preferredDays"),needPreferredDay);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterRegion"),getData("region", generalData));
        //Set Measurement and Category Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("measurement"), needMeasurement);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("serviceCategories"),getData("serviceCategory", generalData));
        //Set Advance Filters
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeCustomerFlags"),needIncludeCustomerFlag);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeCustomerFlags"),needExcludeCustomerFlage);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeSubscriptionFlags"),needIncludeSubscriptionFlag);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeSubscriptionFlags"),needExcludeSubscriptionFlag);
        jobPoolTab.clickRefreshButton();
    }

    public void checkResults(String expectedStreetAddress, String expectedZipCode, String expectedCity, String expectedCustomerName,
                             String expectedPhoneNumber, String expectedRegion, String expectedServiceType) {
        customer.searchCustomer_SearchField(jobPoolTab.SearchField);
        result(expectedCustomerName.toLowerCase(), jobPoolTab.getCustomerName(expectedCustomerName).toLowerCase(), "validate customer name", "validate customer");
        result(expectedPhoneNumber, jobPoolTab.getCustomerPhone(expectedCustomerName), "validate customer phone", "validate customer");
        result(expectedStreetAddress, jobPoolTab.getCustomerStreet(expectedCustomerName).toLowerCase(), "validate customer street", "validate customer");
        result(expectedCity, jobPoolTab.getCustomerCity(expectedCustomerName).toLowerCase(), "validate customer city", "validate customer");
        result(expectedZipCode, jobPoolTab.getCustomerZip(expectedCustomerName), "validate customer zip", "validate customer");
        result(expectedRegion, jobPoolTab.getCustomerRegion(expectedCustomerName), "validate customer region", "validate customer");
        result(expectedServiceType, jobPoolTab.getServiceType(expectedCustomerName), "validate customer serviceType", "validate customer");
    }

    public void clickPlayButton(String expectedCustomerName) {
        createCustomerDialog_AppointmentsTab = new CustomerviewDialog_AppointmentsTab();
        jobPoolTab.clickPlayButton(expectedCustomerName);
        createCustomerDialog_AppointmentsTab.clickCloseSchedulingNoticeButton();
    }
}


