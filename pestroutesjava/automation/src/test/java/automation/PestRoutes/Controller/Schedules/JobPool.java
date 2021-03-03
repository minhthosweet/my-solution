package automation.PestRoutes.Controller.Schedules;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.Customers.CustomersMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Scheduling.JobPoolTab;
import automation.PestRoutes.PageObject.Scheduling.SchedulingTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class JobPool extends AppData {

    Header header;
    SchedulingTab scheduleDay;
    JobPoolTab jobPoolTab;
    CreateNewCustomer customer;
    AssertException assertException;

    @And("I navigate to the job pool tab")
    public void navigateToJobPool() {
        header = new Header();
        scheduleDay = new SchedulingTab();
        jobPoolTab = new JobPoolTab();
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
                jobPoolTab.filterTypes("serviceCategory"),
                jobPoolTab.filterTypes("includeCustomerFlags"),
                jobPoolTab.filterTypes("excludeCustomerFlags"),
                jobPoolTab.filterTypes("includeSubscriptionFlags"),
                jobPoolTab.filterTypes("excludeSubscriptionFlags")};

        jobPoolTab.clickRefreshButton();
        AssertException.validateFieldEnabled(fields);
        }

    @Then("I add all the fields in the job pool page")
    public void completeAllJobPoolFields(String needScheduling, String needPrePlanned, String needOneTimeServices, String needPotential,
                                         String needFollowUpServices, String needPendingCancels, String needPropertyType, String needFlag,
                                         String needMeasurement)
                                         throws IOException {
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("IncludeCustomersWithSpecialRequests"), needScheduling);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("showPrePlanned"), needPrePlanned);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeOneTimeServices"), needOneTimeServices);
        jobPoolTab.setDate(jobPoolTab.filterTypes("dueBetween"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("dueEnd"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("followUpDueBetween"));
        jobPoolTab.setDate(jobPoolTab.filterTypes("followUpDueEnd"));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterPotential"), needPotential);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterFollowUp"), needFollowUpServices);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("pendingCancels"), needPendingCancels);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("propertyType"), needPropertyType);
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("filterBalances"),getData("balance", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("balanceAge"),getData("balanceAge", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("mapPages"),getData("mapCode", generalData));
        jobPoolTab.setInputFilter(jobPoolTab.filterTypes("zipCodes"),getData("zipCode", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeServiceTypes"),getData("inclServiceType", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeServiceTypes"),getData("exclServiceType", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("hideAllFlags"), needFlag);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("cities"),getData("city", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("preferredTech"),getData("techName", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("preferredDays"),getData("day", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("filterRegion"),getData("region", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("measurement"), needMeasurement);
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("serviceCategory"),getData("serviceTypeCategory", generalData));
        jobPoolTab.clickAdvanceFilterButton();
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeCustomerFlags"),getData("subscriptionFlagName", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeCustomerFlags"),getData("excludeSubscriptionFlag", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("includeSubscriptionFlags"),getData("includeCustomerFlag", generalData));
        jobPoolTab.selectFilter(jobPoolTab.filterTypes("excludeSubscriptionFlags"),getData("excludeCustomerFlag", generalData));
        jobPoolTab.clickRefreshButton();
    }

    @Then("I validate the job pool results")
    public void validateJobPoolResult() throws IOException {
        header = new Header();
        jobPoolTab = new JobPoolTab();
        customer = new CreateNewCustomer();
        assertException = new AssertException();
        header.searchCustomer_SearchField(customer.fName+ ", " + customer.lName);

        String expectedCustomerName = customer.fName+ ", " + customer.lName;
        String expectedPhoneNumber = getData("phoneNumber", generalData);
        String expectedStreetAddress = customer.streetAddress;
        String expectedCity = customer.city;
        String expectedZipCode = customer.zipcode;
        String expectedRegion = getData("region", generalData);
        String expectedServiceType = getData("inclServiceType", generalData);

        assertException.result(expectedCustomerName, jobPoolTab.getCustomerName(expectedCustomerName), "validate customer name");
        assertException.result(expectedPhoneNumber, jobPoolTab.getCustomerPhone(expectedCustomerName), "validate customer phone");
        assertException.result(expectedStreetAddress, jobPoolTab.getCustomerStreet(expectedCustomerName), "validate customer street");
        assertException.result(expectedCity, jobPoolTab.getCustomerCity(expectedCustomerName), "validate customer city");
        assertException.result(expectedZipCode, jobPoolTab.getCustomerZip(expectedCustomerName), "validate customer zip");
        assertException.result(expectedRegion, jobPoolTab.getCustomerRegion(expectedCustomerName), "validate customer region");
        assertException.result(expectedServiceType, jobPoolTab.getServiceType(expectedCustomerName), "validate customer serviceType");


    }


    }

