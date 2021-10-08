package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerviewDialog_AppointmentsTab;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.JobPoolTab;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.*;
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

    //Author: FK
    @And("I navigate to the job pool tab")
    public void navigateToJobPool() {
        header = new Header();
        scheduleDay = new SchedulingTab();

        header.navigateTo(header.schedulingTab);
        scheduleDay.clickJobPool();
    }
    //Author: FK
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
    //Author: FK
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
        String balance = getData("balance", generalData);
        String balanceAge = getData("balanceAge", generalData);
        String mapPages = getData("mapCode", generalData);
        String inclServiceType = getData("inclServiceType", generalData);
        String exclServiceType = getData("exclServiceType", generalData);
        String preferredTech = getData("userFirstName", generalData) + " " + getData("userLastName", generalData);
        String filterRegion = getData("region", generalData);
        String serviceCategory = getData("serviceCategory", generalData);
        customerDialog_Header.clickCloseButton();

        String[] dropDownFields = {
                jobPoolTab.filterTypes("IncludeCustomersWithSpecialRequests"),
                jobPoolTab.filterTypes("showPrePlanned"),
                jobPoolTab.filterTypes("excludeOneTimeServices"),
                jobPoolTab.filterTypes("filterPotential"),
                jobPoolTab.filterTypes("filterFollowUp"),
                jobPoolTab.filterTypes("pendingCancels"),
                jobPoolTab.filterTypes("propertyType"),
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
        String[] dropdownValues = {needScheduling, needPrePlanned, needOneTimeServices, needPotential, needFollowUpServices, needPendingCancels,
                needPropertyType, inclServiceType, exclServiceType, needFlag, customerCity, preferredTech, needPreferredDay, filterRegion,
                needMeasurement, serviceCategory, needIncludeCustomerFlag, needExcludeCustomerFlage, needIncludeSubscriptionFlag, needExcludeSubscriptionFlag};

        String[] dateFields = {
                jobPoolTab.filterTypes("dueBetween"),
                jobPoolTab.filterTypes("dueEnd"),
                jobPoolTab.filterTypes("followUpDueBetween"),
                jobPoolTab.filterTypes("followUpDueEnd")};

        String[] inputFields = {
                jobPoolTab.filterTypes("filterBalances"),
                jobPoolTab.filterTypes("balanceAge"),
                jobPoolTab.filterTypes("mapPages"),
                jobPoolTab.filterTypes("zipCodes")};
        String[] inputValues = {balance, balanceAge, mapPages, customerZipCode};

        setDropDownFilter(dropDownFields, dropdownValues);
        setInputFilter(inputFields, inputValues);
        setDateFilter(dateFields);
        jobPoolTab.clickRefreshButton();
    }
    //Author: FK
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
    //Author: FK
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
    //Author: FK
    public void clickPlayButton(String expectedCustomerName) {
        createCustomerDialog_AppointmentsTab = new CustomerviewDialog_AppointmentsTab();
        jobPoolTab.clickPlayButton(expectedCustomerName);
        createCustomerDialog_AppointmentsTab.clickCloseSchedulingNoticeButton();
    }
    //Author: FK
    public void setDropDownFilter(String[] needArray, String[] needValue) {
        for (int i = 0; i < needArray.length; i++) {
            if (needValue.length > 0) {
                jobPoolTab.selectFilter(needArray[i], needValue[i]);
            }
        }
    }
    //Author: FK
    public void setInputFilter(String[] needArray, String[] needValue) {
        for (int i = 0; i < needArray.length; i++) {
            if (needValue.length > 0) {
                jobPoolTab.setInputFilter(needArray[i], needValue[i]);
            }
        }
    }
    //Author: FK
    public void setDateFilter(String[] needArray) {
        for (int i = 0; i < needArray.length; i++) {
            if (needArray.length > 0) {
                jobPoolTab.setDate(needArray[i]);
            }
        }
    }
}


