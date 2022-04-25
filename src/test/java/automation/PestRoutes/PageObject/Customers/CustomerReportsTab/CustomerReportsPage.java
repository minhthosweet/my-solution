package automation.PestRoutes.PageObject.Customers.CustomerReportsTab;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Element.WebSelect;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.*;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Legacy;
import static automation.PestRoutes.Utilities.Legacy.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerReportsPage extends BasePage {

    public String savedReports = "//div[text()='Saved Reports']";
    public String selectColumnsToDisplay = "//div[text()='Select Columns to Display']";
    private By lnkSelectColumnsToDisplay = By.xpath("//div[text()='Select Columns to Display']");
    public String customerAccount = "//div[text()='Customer Account']";
    private By linkCustomerAccount = By.xpath("//div[text()='Customer Account']");
    public String leads = "//div[text()='Leads']";
    public String serviceSubscription = "//div[text()='Service Subscription']";
    public String customerLocation = "//div[text()='Customer Location']";
    public String billingAccount = "//div[text()='Billing Account']";
    private By lnkBillingAccount = By.xpath("//div[text()='Billing Account']");
    public String billingAddress = "//div[text()='Billing Address']";
    public String serviceAppointment = "//div[text()='Service Appointment']";
    public String refreshButton = "//span[text()='Run Report']";
    public String searchBox = "//input[@type='search' and @placeholder='Search...']";
    public String customerReportFirstEntry = "//table[@id='customerReportTable']//td[1]";
    public String textBox_selectColumnsToDisplay = "//div[@id='s2id_reportColumns']//input";
    private By inputSelectColumnsToDisplay =By.xpath("//div[@id='s2id_reportColumns']//input");
    public String addAll_selectColumnsToDisplay = "//div[@id='s2id_reportColumns']//i[2]";

    //Actions objects
    public String addAndRemoveFlags_action = "//div[text()='Add / Remove Flags']";
    public String sendMessage_action = "//div[text()='Send Message']";
    public String sendPasswordRecovery_action = "//div[text()='Send Password Recover']";
    public String addFlags = "//div[@id='s2id_applyFlags']//ul//input";
    public String applyButton_addRemoveFlags_CR = "//div[@aria-describedby='addRemoveFlags']//span[text()='Apply']";
    public String textMessage_actions = "//div[@id='voiceMessageID']/following-sibling::div//p";
    public String actionsDropDown = "//div[@id='customerReportTableActions']";
    public String sendMessageButton = "//div[@id='customerMessageDialog']/following-sibling::div//span[text()='Send Messages']";
    public String updateSubscriptionPrice = "//div[text()='Update Subscription Price']";
    public String fixedAmountTextBox_actions = "//fieldset//input[@name='fixedAmountValue']";
    public String proceedToVerificationButton = "//span[text()='Proceed to Verification']";
    public String confirmChangeButton_subscriptionPriceChange = "//div[@onclick='applySubscriptionChanges()' and text()='Confirm Change']";
    public String bulkFreeze = "//div[text()='Bulk Freeze']";
    public String bulkFreezeRollBack = "//div[text()='Bulk Freeze RollBack']";

    private By tdTextNoData = By.xpath("//*[@id='customerReportTable']/tbody/tr/td[@class='dataTables_empty']");

    //BulkFreeze objects
    public String cancellationNotesTextBox = "//textArea[@id='customerActionCancelNotes']";
    public String bulkFreezeApplyButton = "//div[@id='bulkFreeze']//following-sibling::div//span[text()='Apply']";
    public String cancellationCategory_bulkFreeze = "//div[@class='customerActionsRow customerFreezeAction']//select[@name='customerActionCancelCategory']//option[text() = 'No Contact']";
    public String customerCancellationReason = "//div[@class='customerActionsRow customerFreezeAction']/select[@name='customerActionCancelCategory']";

    //bulk freeze rollback objects
    public String searchInBulkFreezeRollBack = "//div[@id='bulkFreezeProcessDetailTable_filter']//input";
    public String rollBackButton = "//button[text()='Rollback ']";
    public String yesButtonRollBack = "//div[@id='bulkFreezeRollback']/following-sibling::div//span[text()='Yes']";

    // Table Column Headers & Row Values
    private By tableHeaderSubscriptionLastCompleted = By.xpath("//div[@id='customerReportTable_wrapper']//th[text()='Subscription Last Completed']");
    private By tableHeaderCustomerID = By.xpath("//div[@id='customerReportTable_wrapper']//th[text()='Customer ID']");
    private By tableCustomerIDs = By.xpath("//table[@id='customerReportTable']/tbody//td[1]");
    private By showingNumberOfEntries = By.id("customerReportTable_info");

    // Service Appointment Fields
    private By serviceAppointmentFromScheduledForDateField = By.xpath("//div[@key='scheduledFor']/input[1]");
    private By serviceAppointmentToScheduledForDateField = By.xpath("//div[@key='scheduledFor']/input[2]");
    private By serviceAppointmentCategoryMultiField = By.xpath("//input[@id='s2id_autogen46']");
    private By serviceAppointmentShowTechNotesDropDown = By.xpath("//div[@key='showTechNotes']/select");

    // Billing Account Fields
    private By billingAccountPaymentDaysPastDueOperatorSign = By.xpath("//div[@key='invoicePastDueDays']/select");
    private By billingAccountPaymentDaysPastDueField = By.xpath("//div[@key='invoicePastDueDays']/input");

    public Map<String, String> filterTypes_CR = new HashMap<>();

    //Author: Aditya
    public String filterTypes_CR(String key) {

        // Saved Reports fields
        filterTypes_CR.put("newReport_CR", "//input[@placeholder='New Report']");
        filterTypes_CR.put("saveButton_CR", "//button[text()='Save']");
        filterTypes_CR.put("saveAsNewButton_CR", "//button[text()='Save as New']");
        filterTypes_CR.put("selectColumnsToShow", "//button[text()='Save as New']");

        // Select Columns to Display fields
        filterTypes_CR.put("listBy_CR", "//div[@key='subscriptionGroupBy']/select");

        // Customer Account Fields
        filterTypes_CR.put("accountStatus_CR", "//div[@key='status']/select");
        filterTypes_CR.put("hasServiceSubscription_CR", "//div[@key='hasSubscriptions']/select");
        filterTypes_CR.put("hasPendingAppointments_CR", "//div[@key='hasPendingAppointment']/select");
        filterTypes_CR.put("hasLinkedProperties_CR", "//div[@key='linkedProperties']/select");
        filterTypes_CR.put("accountType_CR", "//div[@key='accountType']/select");
        filterTypes_CR.put("unitType_CR", "//div[@key='isMultiUnit']/select");
        filterTypes_CR.put("hasEMail_CR", "//div[@key='hasEmail']/select");
        filterTypes_CR.put("pendingCancel_CR", "//div[@key='pendingCancel']/select");
        filterTypes_CR.put("prefersPaper_CR", "//div[@key='prefersPaper']/select");
        filterTypes_CR.put("purpleDragon_CR", "//div[@key='purpleDragonStatus']/select");
        filterTypes_CR.put("switchOver_CR", "//div[@key='switchOverStatus']/select");
        filterTypes_CR.put("signedElectronicAgreement_CR", "//div[@key='eSign']/select");
        filterTypes_CR.put("zipTaxOverride_CR", "//div[@key='zipTaxOverride']/select");
        filterTypes_CR.put("systemTaxOverride_CR", "//div[@key='systemTaxOverride']/select");
        filterTypes_CR.put("hasZipTaxAssigned_CR", "//div[@key='hasZipTaxAssigned']/select");
        filterTypes_CR.put("dateCanceledFrom_CR", "//div[@key='customerDateCancelled']/input[1]");
        filterTypes_CR.put("dateCanceledTo_CR", "//div[@key='customerDateCancelled']/input[2]");
        filterTypes_CR.put("customerLastCompletedFrom_CR", "//div[@key='customerDateLastServiced']/input[1]");
        filterTypes_CR.put("customerLastCompletedTo_CR", "//div[@key='customerDateLastServiced']/input[2]");
        filterTypes_CR.put("customerDateAddedFrom_CR", "//div[@key='customerAdded']/input[1]");
        filterTypes_CR.put("customerDateAddedTo_CR", "//div[@key='customerAdded']/input[2]");
        filterTypes_CR.put("salesTaxPercentFrom_CR", "//div[@key='salesTax']/input[1]");
        filterTypes_CR.put("salesTaxPercentTo_CR", "//div[@key='salesTax']/input[2]");
        filterTypes_CR.put("lastName_CR", "//div[@key='lname']/input");
        filterTypes_CR.put("firstName_CR", "//div[@key='fname']/input");
        filterTypes_CR.put("companyName_CR", "//div[@key='companyName']/input");
        filterTypes_CR.put("companyDivisions_CR", "//div[@id='s2id_customerAccountCompanyDivisions']");
        filterTypes_CR.put("companyDivisionsTextBox_CR", "//div[@id='s2id_customerAccountCompanyDivisions']//input");
        filterTypes_CR.put("includeFlagsCustomerAccount_CR", "//div[@id='s2id_customerAccountIncludeFlags']//input");
        filterTypes_CR.put("excludeFlagsCustomerAccount_CR", "//div[@id='s2id_excludeCustomerGenericFlagID']//input");
        filterTypes_CR.put("companySource_CR", "//div[@id='s2id_customerAccountCustomerSource']");
        filterTypes_CR.put("companySourceTextBox_CR", "//div[@id='s2id_customerAccountCustomerSource']//input");

        // Leads Fields
        filterTypes_CR.put("leadStatus_CR", "//div[@id='s2id_LeadsLeadStatus']//input");
        filterTypes_CR.put("leadStage_CR", "//div[@id='s2id_LeadsLeadStage']//input");
        filterTypes_CR.put("leadAssignedTo_CR", "//div[@id='s2id_LeadsLeadAssignedTo']//input");
        filterTypes_CR.put("leadLostReason_CR", "//div[@id='s2id_leadsLeadLostReason']//input");
        filterTypes_CR.put("leadSource_CR", "//div[@id='s2id_LeadsLeadSource']//input");
        filterTypes_CR.put("leadValue_CR", "//div[@key='leadValue']/input");
        filterTypes_CR.put("leadAssignment_CR", "//div[@key='leadValue']/select");
        filterTypes_CR.put("leadAddedFrom_CR", "//div[@key='leadAdded']/input[1]");
        filterTypes_CR.put("leadAddedTo_CR", "//div[@key='leadAdded']/input[2]");
        filterTypes_CR.put("leadClosedFrom_CR", "//div[@key='leadClosed']/input[1]");
        filterTypes_CR.put("leadClosedTo_CR", "//div[@key='leadClosed']/input[2]");

        // Service Subscription fields
        filterTypes_CR.put("activeSubscription_CR", "//div[@key='subscriptionActive']/select");
        filterTypes_CR.put("subscriptionRecurring_CR", "//div[@key='subscriptionRecurring']/select");
        filterTypes_CR.put("completedInitial_CR", "//div[@key='completedInitial']/select");
        filterTypes_CR.put("subscriptionDateCancelledFrom_CR", "//div[@key='subscriptionDateCancelled']/input[1]");
        filterTypes_CR.put("subscriptionDateCancelledTo_CR", "//div[@key='subscriptionDateCancelled']/input[2]");
        filterTypes_CR.put("soldDateFrom_CR", "//div[@key='soldDate']/input[1]");
        filterTypes_CR.put("soldDateTo_CR", "//div[@key='soldDate']/input[2]");
        filterTypes_CR.put("serviceDueFrom_CR", "//div[@key='serviceDue']/input[1]");
        filterTypes_CR.put("serviceDueTo_CR", "//div[@key='serviceDue']/input[2]");
        filterTypes_CR.put("customDateFrom_CR", "//div[@key='customDate']/input[1]");
        filterTypes_CR.put("customDateTo_CR", "//div[@key='customDate']/input[2]");
        filterTypes_CR.put("expirationDateFrom_CR", "//div[@key='expirationDate']/input[1]");
        filterTypes_CR.put("expirationDateTo_CR", "//div[@key='expirationDate']/input[2]");
        filterTypes_CR.put("renewalDateFrom_CR", "//div[@key='renewalDate']/input[1]");
        filterTypes_CR.put("renewalDateTo_CR", "//div[@key='renewalDate']/input[2]");
        filterTypes_CR.put("dateAgreementSentFrom_CR", "//div[@key='dateAgreementSent']/input[1]");
        filterTypes_CR.put("dateAgreementSentTo_CR", "//div[@key='dateAgreementSent']/input[2]");
        filterTypes_CR.put("dateSignedFrom_CR", "//div[@key='dateSigned']/input[1]");
        filterTypes_CR.put("dateSignedTo_CR", "//div[@key='dateSigned']/input[2]");
        filterTypes_CR.put("nextBillingDateFrom_CR", "//div[@key='nextBillingDate']/input[1]");
        filterTypes_CR.put("nextBillingDateTo_CR", "//div[@key='nextBillingDate']/input[2]");
        filterTypes_CR.put("subscriptionLastCompletedDateFrom_CR", "//div[@key='subscriptionLastCompletedDate']/input[1]");
        filterTypes_CR.put("subscriptionLastCompletedDateTo_CR", "//div[@key='subscriptionLastCompletedDate']/input[2]");
        filterTypes_CR.put("soldBy_CR", "//div[@id='s2id_serviceSubscriptionsSoldBy']//input");
        filterTypes_CR.put("soldBy2_CR", "//div[@id='s2id_serviceSubscriptionsSoldBy2']//input");
        filterTypes_CR.put("soldBy3_CR", "//div[@id='s2id_serviceSubscriptionsSoldBy3']//input");
        filterTypes_CR.put("preferredTech_CR", "//input[@id='s2id_autogen29']");
        filterTypes_CR.put("routeRegions_CR", "//input[@id='s2id_autogen30']");
        filterTypes_CR.put("subscriptionSource_CR", "//input[@id='s2id_autogen31']");
        filterTypes_CR.put("includeServiceTypes_CR", "//input[@id='s2id_autogen32']");
        filterTypes_CR.put("excludeServiceTypes_CR", "//input[@id='s2id_autogen33']");
        filterTypes_CR.put("notHavingAny_CR", "//input[@id='s2id_autogen34']");
        filterTypes_CR.put("category_CR", "//input[@id='s2id_autogen35']");
        filterTypes_CR.put("includeFlagsServiceSubscription_CR", "//input[@id='s2id_autogen36']");
        filterTypes_CR.put("excludeFlagsServiceSubscription_CR", "//input[@id='s2id_autogen37']");
        filterTypes_CR.put("PONumber_CR", "//div[@key='poNumber']/input");
        filterTypes_CR.put("initialPrice_CR", "//div[@key='initialPrice']/input");
        filterTypes_CR.put("recurringPrice_CR", "//div[@key='servicePrice']/input");
        filterTypes_CR.put("totalContractValue_CR", "//div[@key='totalContract']/input");
        filterTypes_CR.put("frequency_CR", "//div[@key='frequency']/select[2]");
        filterTypes_CR.put("agreementLength_CR", "//div[@key='agreementLength']/input");
        filterTypes_CR.put("initialPriceAssignment_CR", "//div[@key='initialPrice']/select");
        filterTypes_CR.put("recurringPriceAssignment_CR", "//div[@key='servicePrice']/select");
        filterTypes_CR.put("totalContractValueAssignment_CR", "//div[@key='totalContract']/select");
        filterTypes_CR.put("frequencyAssignment_CR", "//div[@key='frequency']/select[1]");
        filterTypes_CR.put("seasonal_CR", "//div[@key='seasonal']/select");
        filterTypes_CR.put("agreementLengthAssignment_CR", "//div[@key='agreementLength']/select");
        filterTypes_CR.put("signedAgreement_CR", "//div[@key='signedAgreement']/select");
        filterTypes_CR.put("hasSubAutoPay_CR", "//div[@key='hasSubAutoPay']/select");
        filterTypes_CR.put("followupService_CR", "//div[@key='followup']/select");
        filterTypes_CR.put("recurringBilling_CR", "//div[@key='monthlyBilling']/select");
        filterTypes_CR.put("lastCompletedPaid_CR", "//div[@key='lastCompletedPaid']/select");
        filterTypes_CR.put("sentriconConnected_CR", "//div[@key='sentriconConnected']/select");
        filterTypes_CR.put("unitOfMeasure_CR", "//div[@key='unitOfMeasure']/select");

        // Customer Location fields
        filterTypes_CR.put("mapCode_CR", "//div[@key='mapCode']/input");
        filterTypes_CR.put("address_CR", "//div[@key='address']/input");
        filterTypes_CR.put("city_CR", "//div[@key='city']/input");
        filterTypes_CR.put("zip_CR", "//div[@key='zip']/input");
        filterTypes_CR.put("country_CR", "//div[@key='country']/select");
        filterTypes_CR.put("state_CR", "//div[@id='s2id_customerLocationState']//input");
        filterTypes_CR.put("county_CR", "//div[@id='s2id_customerLocationCounty']//input");
        filterTypes_CR.put("latitude_CR", "//div[@key='distance']/input[1]");
        filterTypes_CR.put("longitude_CR", "//div[@key='distance']/input[2]");
        filterTypes_CR.put("distance_CR", "//div[@key='distance']/input[3]");

        // Billing Account
        filterTypes_CR.put("balanceAgeAssignment_CR", "//div[@key='pastDue']/select");
        filterTypes_CR.put("balanceAge_CR", "//div[@key='pastDue']/input");
        filterTypes_CR.put("amountDueAssignment_CR", "//div[@key='amountDue']/select");
        filterTypes_CR.put("amountDue_CR", "//div[@key='amountDue']/input");
        filterTypes_CR.put("responsibleBalanceAgeAssignment_CR", "//div[@key='responsibleAgingDate']/select");
        filterTypes_CR.put("responsibleBalanceAge_CR", "//div[@key='responsibleAgingDate']/input");
        filterTypes_CR.put("responsibleBalanceAssignment_CR", "//div[@key='responsibleBalance']/select");
        filterTypes_CR.put("responsibleBalance_CR", "//div[@key='responsibleBalance']/input");
        filterTypes_CR.put("maxMonthlyChargeAssignment_CR", "//div[@key='maxMonthlyCharge']/select");
        filterTypes_CR.put("maxMonthlyCharge_CR", "//div[@key='maxMonthlyCharge']/input");
        filterTypes_CR.put("customerAutoPay_CR", "//div[@key='aPay']/select");
        filterTypes_CR.put("paymentProfileStatus_CR", "//div[@key='paymentProfileStatus']/select");
        filterTypes_CR.put("invoiceNumbers_CR", "//div[@key='invoiceNumber']/input");
        filterTypes_CR.put("invoicePONumbers_CR", "//div[@key='ticketPONumber']/input");
        filterTypes_CR.put("earliestDueDateFrom_CR", "//div[@key='invoiceDueDate']/input[1]");
        filterTypes_CR.put("earliestDueDateTo_CR", "//div[@key='invoiceDueDate']/input[2]");
        filterTypes_CR.put("paymentPastDueDaysAssignment_CR", "//div[@key='invoicePastDueDays']/select");
        filterTypes_CR.put("paymentPastDueDays_CR", "//div[@key='invoicePastDueDays']/input");
        filterTypes_CR.put("yearInFull_CR", "//div[@key='yif']/select");
        filterTypes_CR.put("customerHasCC_CR", "//div[@key='cc']/select");
        filterTypes_CR.put("customerHasACH_CR", "//div[@key='ach']/select");
        filterTypes_CR.put("collectionsStage_CR", "//input[@id='s2id_autogen40']");

        // Billing Address
        filterTypes_CR.put("billingLName_CR", "//div[@key='billingLName']/input");
        filterTypes_CR.put("billingFName_CR", "//div[@key='billingFName']/input");
        filterTypes_CR.put("billingAddress_CR", "//div[@key='billingAddress']/input");
        filterTypes_CR.put("billingCity_CR", "//div[@key='billingCity']/input");
        filterTypes_CR.put("billingState_CR", "//div[@id='s2id_billingAddressBillingState']//input");
        filterTypes_CR.put("billingZip_CR", "//div[@key='billingZip']/input");
        filterTypes_CR.put("billingCountry_CR", "//div[@key='billingCountry']/select");

        // Service Appointment
        filterTypes_CR.put("scheduledForFrom_CR", "//div[@key='scheduledFor']/input[1]");
        filterTypes_CR.put("scheduledForTo_CR", "//div[@key='scheduledFor']/input[2]");
        filterTypes_CR.put("scheduledOnFrom_CR", "//div[@key='scheduledOn']/input[1]");
        filterTypes_CR.put("scheduledOnTo_CR", "//div[@key='scheduledOn']/input[2]");
        filterTypes_CR.put("dateServiceWasDueFrom_CR", "//div[@key='appointmentDueDate']/input[1]");
        filterTypes_CR.put("dateServiceWasDueTo_CR", "//div[@key='appointmentDueDate']/input[2]");
        filterTypes_CR.put("scheduledBy_CR", "//div[@id='s2id_serviceAppointmentsScheduledBy']//input");
        filterTypes_CR.put("completedBy_CR", "//div[@id='s2id_serviceAppointmentsCompletedBy']//input");
        filterTypes_CR.put("servicedBy_CR", "//div[@id='s2id_serviceAppointmentsServicedBy']//input");
        filterTypes_CR.put("autoScheduled_CR", "//div[@key='autoScheduledAppointments']/select");
        filterTypes_CR.put("paidBetweenFrom_CR", "//div[@key='paidDate']/input[1]");
        filterTypes_CR.put("paidBetweenTo_CR", "//div[@key='paidDate']/input[2]");
        filterTypes_CR.put("serviceBalanceAssignment_CR", "//div[@key='serviceBalance']/select");
        filterTypes_CR.put("serviceBalance_CR", "//div[@key='serviceBalance']/input");
        filterTypes_CR.put("servicedType_CR", "//div[@id='s2id_serviceAppointmentsServiceType']//input");
        filterTypes_CR.put("initialService_CR", "//div[@key='isInitial']/select");
        filterTypes_CR.put("categoryServiceAppointment_CR", "//input[@id='s2id_autogen46']");
        filterTypes_CR.put("statusServiceAppointment_CR", "//div[@id='s2id_serviceAppointmentsStatus']//input");
        filterTypes_CR.put("appointmentReminders_CR", "//div[@key='appointmentReminders']/select");
        filterTypes_CR.put("feedbackRatingAssignment_CR", "//div[@key='starRating']/select");
        filterTypes_CR.put("feedbackRating_CR", "//div[@key='starRating']/input");
        filterTypes_CR.put("targetPests_CR", "//input[@id='s2id_autogen48']");
        filterTypes_CR.put("standAloneS_CR", "//div[@key='standAlones']/select");
        filterTypes_CR.put("includeFlagsServiceAppointment_CR", "//input[@id='s2id_autogen49']");
        filterTypes_CR.put("excludeFlagsServiceAppointment_CR", "//input[@id='s2id_autogen50']");
        filterTypes_CR.put("showTechNotes_CR", "//div[@key='showTechNotes']/select");
        filterTypes_CR.put("subscriptionAppointmentNumber_CR", "//div[@key='appointmentNumber']/input");
        filterTypes_CR.put("servicedInterior_CR", "//div[@key='servicedInterior']/select");
        filterTypes_CR.put("imagesUploaded_CR", "//div[@key='imagesUploaded']/select");

        return filterTypes_CR.get(key);
    }

    //Author: Aditya
    public String allFieldsTypes_CR(String key, String value) {

        // Select Columns to Display fields
        filterTypes_CR.put("allColumnsNames", "//div[@id='customerReportTable_processing']//following-sibling::div//th[" + value + "]");
        return filterTypes_CR.get(key);
    }

    public String getTextValue(String needXpath) {
        Utilities.delay(3000);
        Legacy.waitVisible(needXpath);
        Legacy.scrollToElementJS(needXpath);
        return Legacy.getElementTextValue(needXpath);
    }

    public String getTextValue(By locator) {
        delay(3000);
        Utilities.waitVisible(locator);
        Legacy.scrollToElementJS(locator);
        return Utilities.getText(locator);
    }

    public void click(String needButton) throws InterruptedException {
        Legacy.waitVisible(needButton);
        Legacy.clickElement(needButton);
    }

    public void setValueFromDropdown(String needXpath, String needValue) {
        Legacy.waitVisible(needXpath);
        Legacy.scrollToElementJS(needXpath);
        Legacy.selectByText(needXpath, needValue);
    }

    public void setType(String needXpath, String type) throws InterruptedException {
        Utilities.jsScrollToBottom();
        Legacy.locate(needXpath).sendKeys(type);
    }

    public void set(String needXpath, String needType) {
        Legacy.waitVisible(needXpath);
        Legacy.scrollToElementJS(needXpath);
        Legacy.waitVisible(needXpath);
        Legacy.selectByText(needXpath, needType);
    }

    public void setProperty(String needXpath, String type) throws InterruptedException, IOException {
        Utilities.jsScrollToBottom();
        Legacy.clickElement(needXpath);
        Legacy.locate(needXpath).sendKeys(type);
        Legacy.clickElement("//span[text()='" + type + "']");
    }

    public void searchCustomer_CustomerReports(String needXpath, String customerName) throws InterruptedException {
        Utilities.jsScrollToBottom();
        Legacy.locate(needXpath).sendKeys(customerName);
    }

    public void clickCustomerReport() {
        Legacy.waitVisible(customerReportFirstEntry);
        Legacy.scrollToElementJS(searchBox);
        Legacy.clickElement(customerReportFirstEntry);
    }

    public void clickActionType_action(String actionType) throws IOException {
        Legacy.waitVisible(actionsDropDown);
        Legacy.scrollToElementJS(actionsDropDown);
        Legacy.hoverElement(actionsDropDown, actionType);
    }

    public void addFlag_action() throws IOException, InterruptedException {
        AppData appData = new AppData();
        Legacy.waitVisible("//span[text()='Add / Remove Flags']");
        click(addFlags);
        Legacy.locate(addFlags).sendKeys(AppData.getData("flag", appData.generalData));
        click("//div[@id='select2-drop']//span[text()='" + AppData.getData("flag", appData.generalData) + "']");
        click(applyButton_addRemoveFlags_CR);
    }

    public void sendMessage_action(String textMessage) throws InterruptedException {
        Legacy.waitVisible(textMessage_actions);
        Legacy.locate(textMessage_actions).clear();
        Legacy.locate(textMessage_actions).sendKeys(textMessage);
        click("//div[@id='showMessagePlaceholdersButton']");
        click(sendMessageButton);
        Utilities.acceptAlert();
    }

    public void setBulkFreezeNote(String textMessage) {
        Legacy.waitVisible(cancellationNotesTextBox);
        Legacy.locate(cancellationNotesTextBox).sendKeys(textMessage);
    }

    public void listBy(String listBy) {
        Legacy.selectByText("//label[text()='List By']/parent::div//select", listBy);
    }

    public void updateSubscriptionPrice_action(String priceChange) throws InterruptedException {
        Legacy.locate(fixedAmountTextBox_actions).clear();
        Legacy.locate(fixedAmountTextBox_actions).sendKeys(priceChange);
        click(proceedToVerificationButton);
        Legacy.waitVisible(confirmChangeButton_subscriptionPriceChange);
        Legacy.clickElement(confirmChangeButton_subscriptionPriceChange);
        Utilities.acceptAlert();
    }

    public void clickSelectColumnsToDisplayLink(){
        Utilities.click(lnkSelectColumnsToDisplay);
    }//clickSelectColumnsToDisplayLink()

    public void displayColumnOnReport(String colName){
        type(inputSelectColumnsToDisplay, colName);
        Utilities.locate(inputSelectColumnsToDisplay).sendKeys(Keys.ENTER);
    }//clickSelectColumnsToDisplayLink()

    public String getNoDataResults(){
        return getTextValue(tdTextNoData).toString();
    }//getNoDataResults()

    public void clickSavedReports() {
        Utilities.delay(1000);
        Legacy.scrollToElementJS(savedReports);
        Utilities.click(By.xpath(savedReports));
        delay(1000);
    }

    public void clickRunReport() {
        delay(2000);
        scrollToElementJS(refreshButton);
        Utilities.click(By.xpath(refreshButton));
    }

    public void clickHeaderSubscriptionLastCompleted() {
        Utilities.jsScrollTo(tableHeaderSubscriptionLastCompleted);
        Utilities.click(tableHeaderSubscriptionLastCompleted);
        delay(3000);
    }

    public boolean isCustomerIDAvailable(String ID) {
        List<WebElement> allCustomerIDs = locateAll(tableCustomerIDs);
        for (WebElement customerID : allCustomerIDs) {
            if (customerID.getText().equals(ID)) {
                System.out.println("The Customer Is Available In The Customer Report");
                return true;
            }
        }
        return false;
    }

    public void clickCustomerID(String ID) {
        List<WebElement> allCustomerIDs = locateAll(tableCustomerIDs);
        for (WebElement customerID : allCustomerIDs) {
            if (customerID.getText().equals(ID)) {
                customerID.click();
                break;
            }
        }
    }

    public void clickCustomerReportsSection(String section) {
        By filterSection = By.xpath("//div[text()='" + section + "']");
        scrollToElementJS(filterSection);
        Utilities.click(filterSection);
        delay(1000);
    }

    public void typeToScheduledForDate_ServiceAppointment(String date) {
        scrollToElementJS(serviceAppointmentToScheduledForDateField);
        type(serviceAppointmentToScheduledForDateField, date);
    }

    public void typeFromScheduledForDate_ServiceAppointment(String date) {
        jsScrollTo(serviceAppointmentFromScheduledForDateField);
        type(serviceAppointmentFromScheduledForDateField, date);
    }

    public void serviceAppointment_TypeCategory(String category) {
        jsScrollTo(serviceAppointmentCategoryMultiField);
        type(serviceAppointmentCategoryMultiField, category);
    }

    public void selectFromShowTechNotes(String showTechNotes) {
        jsScrollTo(serviceAppointmentCategoryMultiField);
        WebSelect.selectByText(serviceAppointmentShowTechNotesDropDown, showTechNotes);
    }

    public int getNumberOfEntriesFromTableResults() {
        List<WebElement> allCustomerIDs = locateAll(tableCustomerIDs);
        int numberOfTableEntries = allCustomerIDs.size();
        System.out.println("# of Table Entries: " + numberOfTableEntries);
        return numberOfTableEntries;
    }

    public String getShowingEntriesBelowTable() {
        System.out.println(getText(showingNumberOfEntries));
        return getText(showingNumberOfEntries);
    }

    public void clickHeaderCustomerID() {
        jsScrollTo(tableHeaderCustomerID);
        Utilities.click(tableHeaderCustomerID);
        delay(3000);
    }

    public void billingAccount_TypePaymentDaysPastDue(String operatorSign, String days) {
        type(billingAccountPaymentDaysPastDueOperatorSign, operatorSign);
        type(billingAccountPaymentDaysPastDueField, days);
    }

    public String getValueAfterAddingOneColumn(String customerID) {
        By addedColumnHeaderName = By.xpath("//div[@id='customerReportTable_wrapper']//th[4]");
        By columnValue = By.xpath("//table[@id='customerReportTable']/tbody//td[text()='"+customerID+"']/following::td[3]");
        String value = getText(columnValue);
        System.out.println(getText(addedColumnHeaderName) + ": " + value);
        return value;
    }
}