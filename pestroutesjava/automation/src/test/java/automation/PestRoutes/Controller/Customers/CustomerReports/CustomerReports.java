package automation.PestRoutes.Controller.Customers.CustomerReports;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Leads.CreateLeads;
import automation.PestRoutes.Controller.Reporting.Office.BillingByServiceType;
import automation.PestRoutes.PageObject.CustomerOverview.*;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditMemoTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.Customers.CustomerReportsTab.CustomerReportsPage;
import automation.PestRoutes.PageObject.Customers.CustomersMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

import static automation.PestRoutes.Utilities.AssertException.result;

public class CustomerReports extends AppData {
    Header header;
    CustomersMainPage customersMainPage;
    CustomerReportsPage customerReportsPage = new CustomerReportsPage();
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_OverviewTab customerViewDialog_overviewTab;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;
    CreateLeads createLeads;
    CustomerViewDialog_Header customerCardHeader;
    CustomerViewDialog_SubscriptionTab customerViewDialog_subscriptionTab;
    CustomerviewDialog_AppointmentsTab customerviewDialog_appointmentsTab;
    BillingByServiceType billingByServiceType;
    InvoiceImplementation invoiceImplementation;
    BillingPage billingPage;
    CreditMemoTab creditMemoTab;

    private String customerName_CR;
    private String customerID_CR;
    private String fName_CR;
    private String lName_CR;
    private String email_CR;
    private String taxRate_CR;
    private String address_CR;
    private String city_CR;
    private String country_CR;
    private String state_CR;
    private String zipCode_CR;
    private String county_CR;

    //Author: Aditya
    @Test
    @And("I navigate to {string} in Customers tab")
    public void navigateTo(String needReportType) {
        header = new Header();
        customersMainPage = new CustomersMainPage();
        header.navigateTo(header.customersTab);
        customersMainPage.navigateTo(needReportType);
    }

    //Author: Aditya
    @And("I validate if saved report fields are visible on the page")
    public void validateSavedReportFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.savedReports);
        String[] fields = {
                customerReportsPage.filterTypes_CR("newReport_CR"),
                customerReportsPage.filterTypes_CR("saveButton_CR"),
                customerReportsPage.filterTypes_CR("saveAsNewButton_CR")
        };
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if select columns to display fields are visible on the page")
    public void validateSelectColumnsToDisplayFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.selectColumnsToDisplay);
        String[] fields = {
                customerReportsPage.filterTypes_CR("selectColumnsToShow"),
                customerReportsPage.filterTypes_CR("listBy_CR")
        };
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if customer account fields are visible on the page")
    public void validateCustomerAccountFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.customerAccount);
        String[] fields_one = {customerReportsPage.filterTypes_CR("accountStatus_CR"),
                customerReportsPage.filterTypes_CR("dateCanceledFrom_CR"),
                customerReportsPage.filterTypes_CR("dateCanceledTo_CR"),
                customerReportsPage.filterTypes_CR("customerLastCompletedFrom_CR"),
                customerReportsPage.filterTypes_CR("customerLastCompletedTo_CR"),
                customerReportsPage.filterTypes_CR("hasServiceSubscription_CR"),
                customerReportsPage.filterTypes_CR("hasPendingAppointments_CR"),
                customerReportsPage.filterTypes_CR("hasLinkedProperties_CR"),
                customerReportsPage.filterTypes_CR("lastName_CR"),
                customerReportsPage.filterTypes_CR("firstName_CR")
        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("purpleDragon_CR"));
        String[] fields_two = {
                customerReportsPage.filterTypes_CR("accountType_CR"),
                customerReportsPage.filterTypes_CR("unitType_CR"),
                customerReportsPage.filterTypes_CR("hasEMail_CR"),
                customerReportsPage.filterTypes_CR("companyName_CR"),
                customerReportsPage.filterTypes_CR("companyDivisions_CR"),
                customerReportsPage.filterTypes_CR("customerDateAddedFrom_CR"),
                customerReportsPage.filterTypes_CR("customerDateAddedTo_CR"),
                customerReportsPage.filterTypes_CR("companySource_CR"),
                customerReportsPage.filterTypes_CR("pendingCancel_CR"),
                customerReportsPage.filterTypes_CR("prefersPaper_CR"),
                customerReportsPage.filterTypes_CR("includeFlagsCustomerAccount_CR"),
                customerReportsPage.filterTypes_CR("excludeFlagsCustomerAccount_CR"),
                customerReportsPage.filterTypes_CR("purpleDragon_CR")
        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("hasZipTaxAssigned_CR"));
        String[] fields_three = {customerReportsPage.filterTypes_CR("switchOver_CR"),
                customerReportsPage.filterTypes_CR("signedElectronicAgreement_CR"),
                customerReportsPage.filterTypes_CR("salesTaxPercentFrom_CR"),
                customerReportsPage.filterTypes_CR("salesTaxPercentTo_CR"),
                customerReportsPage.filterTypes_CR("zipTaxOverride_CR"),
                customerReportsPage.filterTypes_CR("systemTaxOverride_CR"),
                customerReportsPage.filterTypes_CR("hasZipTaxAssigned_CR")};

        AssertException.validateFieldEnabled(fields_one);
        AssertException.validateFieldEnabled(fields_two);
        AssertException.validateFieldEnabled(fields_three);
    }

    //Author: Aditya
    @And("I validate if leads fields are visible on the page")
    public void validateLeadsFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.leads);
        String[] fields = {
                customerReportsPage.filterTypes_CR("leadStatus_CR"),
                customerReportsPage.filterTypes_CR("leadStage_CR"),
                customerReportsPage.filterTypes_CR("leadAssignedTo_CR"),
                customerReportsPage.filterTypes_CR("leadLostReason_CR"),
                customerReportsPage.filterTypes_CR("leadSource_CR"),
                customerReportsPage.filterTypes_CR("leadValue_CR"),
                customerReportsPage.filterTypes_CR("leadAssignment_CR"),
                customerReportsPage.filterTypes_CR("leadAddedFrom_CR"),
                customerReportsPage.filterTypes_CR("leadAddedTo_CR"),
                customerReportsPage.filterTypes_CR("leadClosedFrom_CR"),
                customerReportsPage.filterTypes_CR("leadClosedTo_CR"),

        };
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if service subscription fields are visible on the page")
    public void validateServiceSubscriptionFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.serviceSubscription);
        String[] fields_one = {
                customerReportsPage.filterTypes_CR("activeSubscription_CR"),
                customerReportsPage.filterTypes_CR("subscriptionDateCancelledFrom_CR"),
                customerReportsPage.filterTypes_CR("subscriptionDateCancelledTo_CR"),
                customerReportsPage.filterTypes_CR("subscriptionRecurring_CR"),
                customerReportsPage.filterTypes_CR("completedInitial_CR"),
                customerReportsPage.filterTypes_CR("soldBy_CR"),
                customerReportsPage.filterTypes_CR("soldBy2_CR"),
                customerReportsPage.filterTypes_CR("soldBy3_CR"),
                customerReportsPage.filterTypes_CR("preferredTech_CR"),
                customerReportsPage.filterTypes_CR("routeRegions_CR"),
                customerReportsPage.filterTypes_CR("soldDateFrom_CR"),
                customerReportsPage.filterTypes_CR("soldDateTo_CR"),
                customerReportsPage.filterTypes_CR("serviceDueFrom_CR"),
                customerReportsPage.filterTypes_CR("serviceDueTo_CR")
        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("dateAgreementSentTo_CR"));
        String[] fields_two = {
                customerReportsPage.filterTypes_CR("customDateFrom_CR"),
                customerReportsPage.filterTypes_CR("customDateTo_CR"),
                customerReportsPage.filterTypes_CR("expirationDateFrom_CR"),
                customerReportsPage.filterTypes_CR("expirationDateTo_CR"),
                customerReportsPage.filterTypes_CR("renewalDateFrom_CR"),
                customerReportsPage.filterTypes_CR("renewalDateTo_CR"),
                customerReportsPage.filterTypes_CR("PONumber_CR"),
                customerReportsPage.filterTypes_CR("initialPrice_CR"),
                customerReportsPage.filterTypes_CR("recurringPrice_CR"),
                customerReportsPage.filterTypes_CR("totalContractValue_CR"),
                customerReportsPage.filterTypes_CR("initialPriceAssignment_CR"),
                customerReportsPage.filterTypes_CR("recurringPriceAssignment_CR"),
                customerReportsPage.filterTypes_CR("totalContractValueAssignment_CR"),
                customerReportsPage.filterTypes_CR("frequency_CR"),
                customerReportsPage.filterTypes_CR("frequencyAssignment_CR"),
                customerReportsPage.filterTypes_CR("seasonal_CR"),
                customerReportsPage.filterTypes_CR("agreementLength_CR"),
                customerReportsPage.filterTypes_CR("agreementLengthAssignment_CR"),
                customerReportsPage.filterTypes_CR("signedAgreement_CR"),
                customerReportsPage.filterTypes_CR("dateAgreementSentFrom_CR"),
                customerReportsPage.filterTypes_CR("dateAgreementSentTo_CR"),
        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("includeFlagsServiceSubscription_CR"));
        String[] fields_three = {
                customerReportsPage.filterTypes_CR("dateSignedFrom_CR"),
                customerReportsPage.filterTypes_CR("dateSignedTo_CR"),
                customerReportsPage.filterTypes_CR("hasSubAutoPay_CR"),
                customerReportsPage.filterTypes_CR("followupService_CR"),
                customerReportsPage.filterTypes_CR("recurringBilling_CR"),
                customerReportsPage.filterTypes_CR("nextBillingDateFrom_CR"),
                customerReportsPage.filterTypes_CR("nextBillingDateTo_CR"),
                customerReportsPage.filterTypes_CR("subscriptionSource_CR"),
                customerReportsPage.filterTypes_CR("includeServiceTypes_CR"),
                customerReportsPage.filterTypes_CR("excludeServiceTypes_CR"),
                customerReportsPage.filterTypes_CR("notHavingAny_CR"),
                customerReportsPage.filterTypes_CR("category_CR"),
                customerReportsPage.filterTypes_CR("includeFlagsServiceSubscription_CR")

        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("unitOfMeasure_CR"));
        String[] fields_four = {
                customerReportsPage.filterTypes_CR("excludeFlagsServiceSubscription_CR"),
                customerReportsPage.filterTypes_CR("subscriptionLastCompletedDateFrom_CR"),
                customerReportsPage.filterTypes_CR("subscriptionLastCompletedDateTo_CR"),
                customerReportsPage.filterTypes_CR("lastCompletedPaid_CR"),
                customerReportsPage.filterTypes_CR("sentriconConnected_CR"),
                customerReportsPage.filterTypes_CR("unitOfMeasure_CR")
        };

        AssertException.validateFieldEnabled(fields_one);
        AssertException.validateFieldEnabled(fields_two);
        AssertException.validateFieldEnabled(fields_three);
        AssertException.validateFieldEnabled(fields_four);
    }

    //Author: Aditya
    @And("I validate if customer location fields are visible on the page")
    public void validateCustomerLocationFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.customerLocation);
        String[] fields = {
                customerReportsPage.filterTypes_CR("mapCode_CR"),
                customerReportsPage.filterTypes_CR("address_CR"),
                customerReportsPage.filterTypes_CR("city_CR"),
                customerReportsPage.filterTypes_CR("zip_CR"),
                customerReportsPage.filterTypes_CR("country_CR"),
                customerReportsPage.filterTypes_CR("state_CR"),
                customerReportsPage.filterTypes_CR("county_CR"),
                customerReportsPage.filterTypes_CR("latitude_CR"),
                customerReportsPage.filterTypes_CR("longitude_CR"),
                customerReportsPage.filterTypes_CR("distance_CR")
        };
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if billing account fields are visible on the page")
    public void validateBillingAccountFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.billingAccount);
        String[] fields_one = {
                customerReportsPage.filterTypes_CR("balanceAgeAssignment_CR"),
                customerReportsPage.filterTypes_CR("balanceAge_CR"),
                customerReportsPage.filterTypes_CR("amountDueAssignment_CR"),
                customerReportsPage.filterTypes_CR("amountDue_CR"),
                customerReportsPage.filterTypes_CR("responsibleBalanceAgeAssignment_CR"),
                customerReportsPage.filterTypes_CR("responsibleBalanceAge_CR"),
                customerReportsPage.filterTypes_CR("responsibleBalanceAssignment_CR"),
                customerReportsPage.filterTypes_CR("responsibleBalance_CR"),
                customerReportsPage.filterTypes_CR("maxMonthlyChargeAssignment_CR"),
                customerReportsPage.filterTypes_CR("maxMonthlyCharge_CR"),
                customerReportsPage.filterTypes_CR("customerAutoPay_CR"),
                customerReportsPage.filterTypes_CR("paymentProfileStatus_CR"),
                customerReportsPage.filterTypes_CR("invoiceNumbers_CR"),
                customerReportsPage.filterTypes_CR("invoicePONumbers_CR"),
                customerReportsPage.filterTypes_CR("earliestDueDateFrom_CR"),
                customerReportsPage.filterTypes_CR("earliestDueDateTo_CR"),
                customerReportsPage.filterTypes_CR("paymentPastDueDaysAssignment_CR"),
                customerReportsPage.filterTypes_CR("paymentPastDueDays_CR")
        };
        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("collectionsStage_CR"));
        String[] fields_two = {
                customerReportsPage.filterTypes_CR("yearInFull_CR"),
                customerReportsPage.filterTypes_CR("customerHasCC_CR"),
                customerReportsPage.filterTypes_CR("customerHasACH_CR"),
                customerReportsPage.filterTypes_CR("collectionsStage_CR")
        };
        AssertException.validateFieldEnabled(fields_one);
        AssertException.validateFieldEnabled(fields_two);
    }

    //Author: Aditya
    @And("I validate if billing address fields are visible on the page")
    public void validateBillingAddressFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.billingAddress);
        String[] fields = {
                customerReportsPage.filterTypes_CR("billingLName_CR"),
                customerReportsPage.filterTypes_CR("billingFName_CR"),
                customerReportsPage.filterTypes_CR("billingAddress_CR"),
                customerReportsPage.filterTypes_CR("billingCity_CR"),
                customerReportsPage.filterTypes_CR("billingState_CR"),
                customerReportsPage.filterTypes_CR("billingZip_CR"),
                customerReportsPage.filterTypes_CR("billingCountry_CR"),
        };
        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if service appointment fields are visible on the page")
    public void validateServiceAppointmentFieldsDisplayed() throws InterruptedException {
        customerReportsPage.click(customerReportsPage.serviceAppointment);
        String[] fields_one = {
                customerReportsPage.filterTypes_CR("scheduledForFrom_CR"),
                customerReportsPage.filterTypes_CR("scheduledForTo_CR"),
                customerReportsPage.filterTypes_CR("scheduledOnFrom_CR"),
                customerReportsPage.filterTypes_CR("scheduledOnTo_CR"),
                customerReportsPage.filterTypes_CR("dateServiceWasDueFrom_CR"),
                customerReportsPage.filterTypes_CR("dateServiceWasDueTo_CR"),
                customerReportsPage.filterTypes_CR("scheduledBy_CR"),
                customerReportsPage.filterTypes_CR("completedBy_CR"),
                customerReportsPage.filterTypes_CR("servicedBy_CR"),
                customerReportsPage.filterTypes_CR("autoScheduled_CR"),
                customerReportsPage.filterTypes_CR("paidBetweenFrom_CR"),
                customerReportsPage.filterTypes_CR("paidBetweenTo_CR"),
                customerReportsPage.filterTypes_CR("serviceBalanceAssignment_CR"),
                customerReportsPage.filterTypes_CR("serviceBalance_CR"),
                customerReportsPage.filterTypes_CR("servicedType_CR"),
                customerReportsPage.filterTypes_CR("initialService_CR"),
                customerReportsPage.filterTypes_CR("categoryServiceAppointment_CR")
        };

        Utilities.scrollToElementJS(customerReportsPage.filterTypes_CR("imagesUploaded_CR"));
        String[] fields_two = {
                customerReportsPage.filterTypes_CR("statusServiceAppointment_CR"),
                customerReportsPage.filterTypes_CR("appointmentReminders_CR"),
                customerReportsPage.filterTypes_CR("feedbackRatingAssignment_CR"),
                customerReportsPage.filterTypes_CR("feedbackRating_CR"),
                customerReportsPage.filterTypes_CR("targetPests_CR"),
                customerReportsPage.filterTypes_CR("standAloneS_CR"),
                customerReportsPage.filterTypes_CR("includeFlagsServiceAppointment_CR"),
                customerReportsPage.filterTypes_CR("excludeFlagsServiceAppointment_CR"),
                customerReportsPage.filterTypes_CR("showTechNotes_CR"),
                customerReportsPage.filterTypes_CR("subscriptionAppointmentNumber_CR"),
                customerReportsPage.filterTypes_CR("servicedInterior_CR"),
                customerReportsPage.filterTypes_CR("imagesUploaded_CR")
        };
        AssertException.validateFieldEnabled(fields_one);
        AssertException.validateFieldEnabled(fields_two);
    }

    //Author : Aditya
    @And("I get customer details for customer reports")
    public void getCustomerDetails_CR() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerCardHeader = new CustomerViewDialog_Header();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        customerName_CR = createNewCustomer.getCustomerFullName();
        fName_CR = customerViewDialog_infoTab.getFirstName();
        lName_CR = customerViewDialog_infoTab.getLastName();
        customerID_CR = customerViewDialog_overviewTab.getCustomerIDFromHeader();
        email_CR = customerViewDialog_infoTab.getEmail();
        taxRate_CR = String.format("%2f", (Double.parseDouble(customerViewDialog_infoTab.getTaxRate()) / 100));
        address_CR = customerViewDialog_infoTab.getStreetAddress();
        city_CR = customerViewDialog_infoTab.getCity();
        country_CR = customerViewDialog_infoTab.getCountry();
        state_CR = customerViewDialog_infoTab.getState();
        zipCode_CR = customerViewDialog_infoTab.getZip();
        county_CR = customerViewDialog_infoTab.getCounty();
    }

    //Author : Aditya
    @When("I add filters to Customer Account in Customer Reports")
    public void addFilters_customerReports() throws InterruptedException, IOException {
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("hasServiceSubscription_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("hasLinkedProperties_CR"), "No");
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("accountType_CR"), "Commercial");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("unitType_CR"), "Multi Unit");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("hasEMail_CR"), "Has an Email");
        customerReportsPage.click(customerReportsPage.filterTypes_CR("companySource_CR"));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("companySourceTextBox_CR"), getData("customerSource", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("companyDivisionsTextBox_CR"), getData("division", generalData));
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("customerDateAddedFrom_CR"), Utilities.currentDate("MM/dd/yyyy"));
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("customerDateAddedTo_CR"), Utilities.currentDate("MM/dd/yyyy"));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("includeFlagsCustomerAccount_CR"), getData("flag", generalData));
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("purpleDragon_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("signedElectronicAgreement_CR"), "No");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("zipTaxOverride_CR"), "No");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("systemTaxOverride_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("hasZipTaxAssigned_CR"), "Yes");
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I add filters to Leads in Customer Reports")
    public void addFilters_leads() throws IOException, InterruptedException {
        createLeads = new CreateLeads();
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.click(customerReportsPage.leads);
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("leadStatus_CR"), "Open");
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("leadStage_CR"), "New");
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("leadValue_CR"), createLeads.lead_value);
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("leadAssignedTo_CR"), getData("assignto", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("leadSource_CR"), getData("source", generalData));
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I add filters to Service Subscription in Customer Reports")
    public void addFilters_serviceSubscription() throws IOException, InterruptedException {
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.click(customerReportsPage.serviceSubscription);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("activeSubscription_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("subscriptionRecurring_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("completedInitial_CR"), "Has Completed Initial Service");
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("soldBy_CR"), getData("assignto", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("soldBy2_CR"), getData("assignto", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("soldBy3_CR"), getData("assignto", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("includeServiceTypes_CR"), getData("serviceDescription", generalData));
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("frequency_CR"), "Custom Schedule");
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("includeFlagsServiceSubscription_CR"), getData("subscriptionFlagName", generalData));
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("soldDateFrom_CR"), Utilities.currentDate("MM/dd/yyyy"));
        customerReportsPage.click(customerReportsPage.serviceSubscription);
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I add invoice filters to Service Subscription in Customer Reports")
    public void addInvoiceFilters_serviceSubscription() throws InterruptedException {
        customerReportsPage.clickCustomerReport();
        createNewCustomer = new CreateNewCustomer();
        createNewCustomer.navigateToSubscriptionTab();
        customerViewDialog_subscriptionTab = new CustomerViewDialog_SubscriptionTab();
        String initialPrice = String.valueOf(customerViewDialog_subscriptionTab.getInitialSubTotal());
        String recurringPrice = String.valueOf(customerViewDialog_subscriptionTab.getRecurringSubTotal());
        createNewCustomer.closeCustomerCard();
        customerReportsPage.click(customerReportsPage.serviceSubscription);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("initialPrice_CR"), initialPrice);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("recurringPrice_CR"), recurringPrice);
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I add filters to Customer Location in Customer Reports")
    public void addFilters_customerLocation() throws InterruptedException, IOException {
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.click(customerReportsPage.customerLocation);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("mapCode_CR"), getData("mapCode", generalData));
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("address_CR"), address_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("city_CR"), city_CR);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("country_CR"), country_CR);
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("state_CR"), state_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("zip_CR"), zipCode_CR);
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("county_CR"), county_CR);
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I add filters to Billing Account with max monthly as {string} in Customer Reports")
    public void
    addFilters_billingAccount(String maxMonthly) throws InterruptedException, IOException {
        billingByServiceType = new BillingByServiceType();
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.click(customerReportsPage.billingAccount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("balanceAge_CR"), "0");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("amountDueAssignment_CR"), ">");
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("amountDue_CR"), billingByServiceType.standAloneInvoiceAmount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("responsibleBalanceAge_CR"), "0");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("responsibleBalanceAssignment_CR"), ">");
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("responsibleBalance_CR"), billingByServiceType.standAloneInvoiceAmount);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("maxMonthlyCharge_CR"), maxMonthly);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("customerAutoPay_CR"), "Credit Card");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("paymentProfileStatus_CR"), "Valid");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("customerHasCC_CR"), "Yes");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("customerHasACH_CR"), "Yes");
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @When("I search for customer in customer reports")
    public void searchCustomer_customerReports() throws InterruptedException {
        customerReportsPage.searchCustomer_CustomerReports(customerReportsPage.searchBox, fName_CR);
    }

    //Author : Aditya
    @Then("I validate customer account report in Customer Reports")
    public void customerAccountReportValidations_customerReports() throws IOException {
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]")).toLowerCase(Locale.ROOT), "Customer first name validation", " Customer Reports Validation");
        result("Multi Unit", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]"), "Unit type validation", " Customer Reports Validation");
        result(email_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]"), "Email validation", " Customer Reports Validation");
        result(getData("division", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[7]"), "Customer Division validation", " Customer Reports Validation");
        result(Utilities.currentDate("MM/dd/yy"), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[8]")).substring(0, 8), "Customer Date validation", " Customer Reports Validation");
        result(getData("customerSource", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[9]"), "Customer Source validation", " Customer Reports Validation");
        result(getData("flag", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[10]"), "Customer Flag validation", " Customer Reports Validation");
        result(taxRate_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[11]"), "Customer Source validation", " Customer Tax Validation");
    }

    //Author : Aditya
    @Then("I validate leads report in Customer Reports")
    public void leadsReportValidations_customerReports() throws IOException {
        createLeads = new CreateLeads();
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]")).toLowerCase(Locale.ROOT), "Customer first name validation", " Customer Reports Validation");
        result("Open", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[4]"), "Lead Status validation", " Customer Reports Validation");
        result("New", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]"), "Lead Stage validation", " Customer Reports Validation");
        result(createLeads.lead_value + ".00", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]"), "Lead Value validation", " Customer Reports Validation");
        result(getData("assignto", generalData), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[8]")) + "-Office", "Lead Assigned validation", " Customer Reports Validation");
        result(getData("source", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[9]"), "Lead Source validation", " Customer Reports Validation");
    }

    //Author : Aditya
    @Then("I validate service subscription report in Customer Reports")
    public void serviceSubscriptionReportValidations_customerReports() throws IOException {
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]")).toLowerCase(Locale.ROOT), "Customer first name validation", " Customer Reports Validation");
        result("Active", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[4]"), "Subscription Status validation", " Customer Reports Validation");
        result("Custom Schedule", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]"), "Frequency Type validation", " Customer Reports Validation");
        result(Utilities.currentDate("MM/dd/yy"), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]"), "Initial Service Date validation", " Customer Reports Validation");
        result(getData("serviceDescription", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[7]"), "Subscription Type validation", " Customer Reports Validation");
        String soldBy = customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[9]");
        result(soldBy, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[12]"), "Sold By 2 validation", " Customer Reports Validation");
        result(soldBy, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[15]"), "Sold By 3 validation", " Customer Reports Validation");
        int indexOfComma = soldBy.indexOf(",");
        soldBy = soldBy.substring(indexOfComma + 1) + soldBy.substring(0, indexOfComma);
        result(getData("assignto", generalData), soldBy + "-Office", "Sold By 1 validation", " Customer Reports Validation");
        result("Office Staff", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[10]"), "Sold By Type 1 validation", " Customer Reports Validation");
        result("Office Staff", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[13]"), "Sold By Type 2 validation", " Customer Reports Validation");
        result("Office Staff", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[16]"), "Sold By Type 3 validation", " Customer Reports Validation");
        try {
            WebElement schedulingAppointment = FindElement.elementByAttribute(customerviewDialog_appointmentsTab.closeSchedulingNotice, FindElement.InputType.XPath);
            if (schedulingAppointment.isDisplayed()) {
                customerviewDialog_appointmentsTab = new CustomerviewDialog_AppointmentsTab();
                customerviewDialog_appointmentsTab.clickCloseSchedulingNoticeButton();
            }
        } catch (Exception e) {
        }
    }

    //Author : Aditya
    @Then("I validate customer location report in Customer Reports")
    public void customerLocationReportValidations_customerReports() throws IOException {
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]")).toLowerCase(Locale.ROOT), "Customer first name validation", " Customer Reports Validation");
        result(getData("mapCode", generalData), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[4]")), "Map Code validation", " Customer Reports Validation");
        result(address_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]")), "Address validation", " Customer Reports Validation");
        result(city_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]")), "City validation", " Customer Reports Validation");
        result(country_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[7]")), "Country validation", " Customer Reports Validation");
        result(state_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[8]")), "State validation", " Customer Reports Validation");
        result(zipCode_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[9]")), "Zip Code validation", " Customer Reports Validation");
        result(county_CR, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[10]")), "County validation", " Customer Reports Validation");
    }

    //Author : Aditya
    @Then("I validate billing account report with max monthly as {string} in Customer Reports")
    public void billingAccountReportValidations_customerReports(String maxMonthly) throws IOException, InterruptedException {
        invoiceImplementation = new InvoiceImplementation();
        customerReportsPage.clickCustomerReport();
        customerCardHeader = new CustomerViewDialog_Header();
        creditMemoTab = new CreditMemoTab();
        createNewCustomer = new CreateNewCustomer();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoiceImplementation.clickInvoice(getData("serviceDescription", generalData));
        String balance = invoiceImplementation.getBalanceInPayments();
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        billingPage = new BillingPage();
        String customerAutoPayValue = billingPage.getAutoPayValue();
        String CCTokenNumber = (billingPage.getTokenValue(billingPage.ccOptionOnLeft, billingPage.tokenValue)).toLowerCase(Locale.ROOT);
        String ACHTokenNumber = (billingPage.getTokenValue(billingPage.ACHOptionOnLeft, billingPage.tokenValue)).toLowerCase(Locale.ROOT);
        createNewCustomer.closeCustomerCard();
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]")).toLowerCase(Locale.ROOT), "Customer first name validation", " Customer Reports Validation");
        result("0", (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[4]")), "A/R Aging validation", " Customer Reports Validation");
        result(balance, "$" + (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]")), "Account Balance validation", " Customer Reports Validation");
        result("0", (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]")), "Responsible A/R Aging validation", " Customer Reports Validation");
        result(balance, "$" + (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[7]")), "Responsible Account Balance validation", " Customer Reports Validation");
        result(String.format("%.2f", Double.parseDouble(maxMonthly)), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[8]")), "Max Monthly validation", " Customer Reports Validation");
        result(customerAutoPayValue, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[9]")), "Customer Auto Pay validation", " Customer Reports Validation");
        result(CCTokenNumber, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[11]")).toLowerCase(Locale.ROOT), "Credit Card Token validation", " Customer Reports Validation");
        String lastFourOfCC = customerAutoPayValue.substring(customerAutoPayValue.length() - 4, customerAutoPayValue.length());
        result(lastFourOfCC, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[12]")), "Credit Card Last Four validation", " Customer Reports Validation");
        String creditCardType = customerAutoPayValue.substring(customerAutoPayValue.indexOf("-") + 1, customerAutoPayValue.length() - 6);
        result(creditCardType, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[13]"), "Credit Card Type validation", " Customer Reports Validation");
        result(ACHTokenNumber, (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[15]")).toLowerCase(Locale.ROOT), "ACH Token validation", " Customer Reports Validation");

    }
}