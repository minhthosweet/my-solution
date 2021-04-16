package automation.PestRoutes.Controller.Customers.CustomerReports;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.PageObject.Customers.CustomerReportsTab.CustomerReportsPage;
import automation.PestRoutes.PageObject.Customers.CustomersMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    private String customerName_CR;
    private String customerID_CR;
    private String fName_CR;
    private String lName_CR;

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
    @And("I get customer name and customer ID details for customer reports")
    public void updateCustomerIDAndCustomerNameDetails_CR() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerViewDialog_overviewTab = new CustomerViewDialog_OverviewTab();
        customerName_CR = createNewCustomer.getCustomerFullName();
        fName_CR = customerViewDialog_infoTab.getFirstName();
        lName_CR = customerViewDialog_infoTab.getLastName();
        customerID_CR = customerViewDialog_overviewTab.getCustomerIDFromHeader();
    }

    //Author : Aditya
    @When("I add filters to Customer Account in Customer Reports")
    public void addFilters_customerReports() throws InterruptedException, IOException {
        customerReportsPage.click(customerReportsPage.customerAccount);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("hasServiceSubscription_CR"), "Yes");
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("lastName_CR"), lName_CR);
        customerReportsPage.setType(customerReportsPage.filterTypes_CR("firstName_CR"), fName_CR);
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("accountType_CR"), "Commercial");
        customerReportsPage.setValueFromDropdown(customerReportsPage.filterTypes_CR("unitType_CR"), "Multi Unit");
        customerReportsPage.click(customerReportsPage.filterTypes_CR("companySource_CR"));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("companySourceTextBox_CR"), getData("customerSource", generalData));
        customerReportsPage.setProperty(customerReportsPage.filterTypes_CR("companyDivisionsTextBox_CR"), getData("division", generalData));
        customerReportsPage.click(customerReportsPage.refreshButton);
    }

    //Author : Aditya
    @Then("I validate customer account report in Customer Reports")
    public void customerAccountReportValidations() throws IOException {
        result(customerID_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[1]"), "Customer ID validation", " Customer Reports Validation");
        result(lName_CR.toLowerCase(Locale.ROOT), (customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[2]")).toLowerCase(Locale.ROOT), "Customer last name validation", " Customer Reports Validation");
        result(fName_CR, customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[3]"), "Customer first name validation", " Customer Reports Validation");
        result("Multi Unit", customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[5]"), "Unit type validation", " Customer Reports Validation");
        result(getData("division", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[6]"), "Customer Division validation", " Customer Reports Validation");
        result(getData("customerSource", generalData), customerReportsPage.getTextValue("//table[@id='customerReportTable']//td[7]"), "Customer Source validation", " Customer Reports Validation");
    }
}