package automation.PestRoutes.Controller.Billings;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.Billing.BillingModule.AccountReceivablePage;
import automation.PestRoutes.PageObject.Billing.BillingModule.BillingModule;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreateNewInvoicePopUp;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import automation.PestRoutes.Utilities.Report.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Login.SignIn;
import java.util.Locale;

import static automation.PestRoutes.Utilities.Report.AssertException.result;

public class AccountReceivable {
    RoutePageInvoicing invoice;
    CreateCustomerDialog infoTab;
    BillingModule billingModule;
    BillingPage customerCardBillingTab;
    Billing billing;
    CreateNewInvoicePopUp newInvoice;
    AccountReceivablePage accountReceivable = new AccountReceivablePage();
    Header header = new Header();
    SignIn signin;
    CustomerViewDialog_Header customerCardHeader;
    CustomerViewDialog_Admin admin;
    CreateNewCustomer customer;

    @Test
    public void test() throws Exception {
        signin = new SignIn();
        signin.login();
        navigateToAccountReceivablePage();
        validateAllFieldsEnabled();
    }

    //**Author Aarbi**
    @Then("I validate if all fields are displaying and are enabled in account receivable page")
    public void validateAllFieldsEnabled() {

        String[] fields = {accountReceivable.asOfDateInputField, accountReceivable.accountStatusDropdown, accountReceivable.autoPayDropdown,
                accountReceivable.statusDropdown, accountReceivable.propertyDropdown, accountReceivable.balanceInputField, accountReceivable.balanceAgeDropdown,
                accountReceivable.paymentDueDropdown, accountReceivable.daysOverDueDropdown, accountReceivable.prefPaperDropdown, accountReceivable.hasEmailDropdown,
                accountReceivable.maxMonthlyDropdown, accountReceivable.groupByDropdown, accountReceivable.inclCollectionDropdown,
                accountReceivable.inclFlagsDropdown, accountReceivable.exclFlagsDropdown};
        accountReceivable.click(accountReceivable.advanceFilterLink);
        AssertException.validateFieldEnabled(fields);
    }

    //**Author Aarbi**
    @Then("I validate if the customer displays once account status is Active in account receivable page")
    public void validateAccountStatus() throws Exception {

        customer = new CreateNewCustomer();
        admin = new CustomerViewDialog_Admin();
        customerCardHeader = new CustomerViewDialog_Header();
        accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
        accountReceivable.insert(accountReceivable.asOfDateInputField, GetDate.currentDate("MM/dd/yyyy"));
        accountReceivable.click(accountReceivable.refreshButton);
        String customerName = customer.getCustomerName("1");
        customerCardHeader.clickCloseButton();
        accountReceivable.insert(accountReceivable.searchInputField, customerName);
        searchAndValidateCustomer_AccountReceivable(customerName, " Active account status");

        header.searchCustomerInOrder("1");
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Frozen(admin.cancelServiceProps);
        customer.closeCustomerCard();

        String[] accountStatus = {"Frozen", "All"};
        for (int i = 0; i < accountStatus.length; i++) {

            accountReceivable.select(accountReceivable.accountStatusDropdown, accountStatus[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            searchAndValidateCustomer_AccountReceivable(customerName, " Frozen, and all account status");

        }


    }

    //**Author Aarbi**
    @Then("I validate CC auto pay customer display in account receivable page")
    public void validateAutoPayCustomerWithCC() throws Exception {
        customer = new CreateNewCustomer();
        customerCardBillingTab = new BillingPage();
        customerCardHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        String customerName = customer.getCustomerFullName();
        billing.addPaymentCC("4111111111111111", "5412750109056250");
        billing.addCustomerOnAutoPay();
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.select(accountReceivable.autoPayDropdown, "Card AutoPay");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with auto pay");
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate ACH auto pay customer display in account receivable page")
    public void validateAutoPayCustomerWithAch() throws Exception {
        customer = new CreateNewCustomer();
        customerCardBillingTab = new BillingPage();
        customerCardHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        String customerName = customer.getCustomerFullName();
        billing.addBankAccount();
        billing.addCustomerOnAutoPay();
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.select(accountReceivable.autoPayDropdown, "Card or ACH AutoPay");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with auto pay");
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate customer by prop type in account receivable page")
    public void validateCustomerByPropType() throws Exception {
        customerCardHeader = new CustomerViewDialog_Header();
        infoTab = new CreateCustomerDialog();
        customer = new CreateNewCustomer();
        admin = new CustomerViewDialog_Admin();
        String[] typeOfCustomer = {infoTab.commercialProperty, infoTab.residentialProperty};
        String[] propType = {"Commercial Only", "Residential Only"};
        String customerName = customer.getCustomerFullName();
        for (int i = 0; i < typeOfCustomer.length; i++) {
            customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
            infoTab.selectProperty(typeOfCustomer[i]);
            customerCardHeader.clickSaveButton();
            createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
            customer.closeCustomerCard();
            navigateToAccountReceivablePage();
            accountReceivable.select(accountReceivable.propertyDropdown, propType[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            searchAndValidateCustomer_AccountReceivable(customerName, " Property type customer with frozen account");
            header.searchCustomerWithName(customerName);
        }
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Active();
        customerCardHeader.clickCloseButton();
        String[] accountStatus = {"Active", "All"};
        for (int i = 0; i < accountStatus.length; i++) {
            accountReceivable.select(accountReceivable.accountStatusDropdown, accountStatus[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            searchAndValidateCustomer_AccountReceivable(customerName, " Property type customer");
        }
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate customer with balance in account receivable page")
    public void validateBalance() throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        newInvoice = new CreateNewInvoicePopUp();
        admin = new CustomerViewDialog_Admin();
        customer.createCustomerWithEmail();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Active();
//        customer.closeCustomerCard();
        String customerName = customer.getCustomerFullName();
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
        accountReceivable.insert(accountReceivable.balanceInputField, "300");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with balance");
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate customer with balance age, payment due, and days overdue in account receivable page")
    public void validateBalanceAge() throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        admin = new CustomerViewDialog_Admin();
        String[] balanceAge = {"7+ Days Old", "30+ Days Old (Past Due)", "90+ Days Old (Way, Way Past Due)"};
        String[] daysOverDue = {"7+ Days Overdue", "30+ Days Overdue", "90+ Days Overdue"};
        int[] invoiceDaysPastDue = {7, 30, 90};
        for (int i = 0; i < balanceAge.length; i++) {
            String fname = GetData.generateRandomString(7).toLowerCase(Locale.ROOT);
            String lname = GetData.generateRandomString(6).toLowerCase(Locale.ROOT);
            System.out.println(fname + " " + lname);
            customer.createACustomer(fname, lname);
            int currentMonth = GetDate.getMonth(GetDate.currentDate("MM/dd/yyyy"));
            int currentYear = GetDate.getYear(GetDate.currentDate("MM/dd/yyyy"));
            String dateOfInvoice = GetDate.minusGenericDayToDate(GetDate.currentDate("MM/dd/yyyy"), invoiceDaysPastDue[i]);
            int monthOfInv = GetDate.getMonth(dateOfInvoice);
            int yearOfInv = GetDate.getYear(dateOfInvoice);
            createStandAloneServiceInvoice("400", dateOfInvoice, "Automation Renewal");
            customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
            admin.changeAccountStatus_Active();
            customer.closeCustomerCard();
            navigateToAccountReceivablePage();
            accountReceivable.click(accountReceivable.advanceFilterLink);
            accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
            accountReceivable.insert(accountReceivable.balanceInputField, "300");
            accountReceivable.select(accountReceivable.balanceAgeDropdown, balanceAge[i]);
            selectPaymentDue(currentMonth, monthOfInv, currentYear, yearOfInv);
            accountReceivable.select(accountReceivable.daysOverDueDropdown, daysOverDue[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            searchAndValidateCustomer_AccountReceivable(fname + " " + lname, " Customer with Balance age, payment due and days overdue");
            customer.removeCustomer();
        }

    }

    //**Author Aarbi**
    @Then("I validate customer with pref paper in account receivable page")
    public void validatePrefPaperCustomer() throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        String customerName = customer.getCustomerFullName();
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        navigateToAccountReceivablePage();
        accountReceivable.click(accountReceivable.advanceFilterLink);
        accountReceivable.select(accountReceivable.prefPaperDropdown, "Yes");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with Pref paper");
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate customer has email in account receivable page")
    public void validateCustomerWithEmail() throws Exception {
        customer = new CreateNewCustomer();
        String customerName = customer.getCustomerFullName();
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.click(accountReceivable.advanceFilterLink);
        accountReceivable.select(accountReceivable.hasEmailDropdown, "Yes");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with email");
        Legacy.hoverElement(accountReceivable.actionsButton, accountReceivable.emailStatementsButton_UnderActions);
        Utilities.acceptAlert();
        Utilities.acceptAlert();
        customer.removeCustomer();
    }

    //**Author Aarbi**
    @Then("I validate autopay customer with max monthly in account receivable page")
    public void validateAutoPayCustomerWithMaxMonthly() throws Exception {
        customer = new CreateNewCustomer();
        customerCardBillingTab = new BillingPage();
        customerCardHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        String customerName = customer.getCustomerFullName();
        billing.addPaymentCC("4111111111111111", "5412750109056250");
        billing.addCustomerOnAutoPayCCWithMaxLimit("CC","400");
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.click(accountReceivable.advanceFilterLink);
        accountReceivable.select(accountReceivable.autoPayDropdown, "Card AutoPay");
        accountReceivable.select(accountReceivable.maxMonthlyDropdown, "Yes");
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with autopay and max monthly limit");
        Legacy.hoverElement(accountReceivable.actionsButton, accountReceivable.exportToExcelButton_UnderActions);
        Legacy.hoverElement(accountReceivable.actionsButton, accountReceivable.printStatementsButton_UnderActions);
        GetWebDriver.switchToOldWindowOpened();
        Legacy.hoverElement(accountReceivable.actionsButton, accountReceivable.snailMailButton_UnderActions);
        Utilities.acceptAlert();
        customer.removeCustomer();
    }

    //**FK**
    @Then("I validate customer with including {string} and excluding {string} flags and groupBy {string} and collection {string} in account receivable page with CC {string} or {string}")
    public void validateIncludeFlags(String needInclFlag, String needExclFlag, String needGroupBy, String needInclCollection, String needRegularCC, String needNMICC) throws Exception {
        customer = new CreateNewCustomer();
        customerCardBillingTab = new BillingPage();
        customerCardHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        String customerName = customer.getCustomerFullName();
        billing.addPaymentCC(needRegularCC, needNMICC);
        billing.addCustomerOnAutoPayCCWithMaxLimit("CC","400");
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.click(accountReceivable.advanceFilterLink);
        if (needInclFlag.length() > 0) {
            accountReceivable.select(accountReceivable.inclFlagsDropdown, needInclFlag);
        }
        if (needExclFlag.length() > 0) {
            accountReceivable.select(accountReceivable.exclFlagsDropdown, needExclFlag);
        }
        if (needGroupBy.length() > 0) {
            accountReceivable.select(accountReceivable.groupByDropdown, needGroupBy);
        }
        if (needInclCollection.length() > 0) {
            accountReceivable.select(accountReceivable.inclCollectionDropdown, needInclCollection);
        }
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with flags");
    }

    //**FK**
    @Then("I validate autopay customer {string} and with status {string} in account receivable page")
    public void validateAutoPayCustomerWithMaxMonthly(String needAutopay, String needStatus) throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        billing = new Billing();
        String customerName = customer.getCustomerFullName();
        billing.addPaymentCC("4111111111111111", "5412750109056250");
        billing.addCustomerOnAutoPayCCWithMaxLimit("CC","400");
        createStandAloneServiceInvoice("400", GetDate.currentDate("MM/dd/yyyy"), "Automation Renewal");
        customer.closeCustomerCard();
        navigateToAccountReceivablePage();
        accountReceivable.click(accountReceivable.advanceFilterLink);
        accountReceivable.select(accountReceivable.autoPayDropdown, needAutopay);
        accountReceivable.select(accountReceivable.statusDropdown, needStatus);
        accountReceivable.click(accountReceivable.refreshButton);
        searchAndValidateCustomer_AccountReceivable(customerName, " Customer with autopay and status successful");
    }

    //**Author Aarbi**
    @And("I navigate to account receivable under Billings")
    public void navigateToAccountReceivablePage() {
        billingModule = new BillingModule();
        header.navigateTo(header.billingTab);
        billingModule.navigate(billingModule.accountsReceivable);
    }

    //**Author Aarbi**
    @And("I create standalone service invoice")
    public void createStandAloneServiceInvoice(String needAmount, String needDate, String needServiceType) throws InterruptedException {
        invoice = new RoutePageInvoicing();
        newInvoice = new CreateNewInvoicePopUp();
        customerCardHeader = new CustomerViewDialog_Header();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoice.clickAddNewInvoice(invoice.addNewInvoice);
        newInvoice.set(newInvoice.dateField, needDate);
        newInvoice.set(newInvoice.amountInputField, needAmount);
        newInvoice.select(newInvoice.serviceTypeDropdown, needServiceType);
        newInvoice.click(newInvoice.createButton);
    }

    //Author Aarbi
    @And("I select payment due in account receivable")
    public void selectPaymentDue(int needCurrentMonth, int needMonthOFInv, int needCurrentYear, int needYearOfInvoice) {
        int monthPastDue = needCurrentMonth - needMonthOFInv;
        int yearsPastDue = needCurrentYear - needYearOfInvoice;
        if (monthPastDue > 1 && yearsPastDue == 0) {
            accountReceivable.click(accountReceivable.paymentDueDropdown);
            accountReceivable.click(accountReceivable.thisYear);
        } else if (yearsPastDue > 0) {
            accountReceivable.click(accountReceivable.paymentDueDropdown);
            accountReceivable.click(accountReceivable.lastYear);
        } else if (monthPastDue == 1 && yearsPastDue == 0) {
            accountReceivable.click(accountReceivable.paymentDueDropdown);
            accountReceivable.click(accountReceivable.lastMonth);
        } else if (monthPastDue == 0 && yearsPastDue == 0) {
            accountReceivable.click(accountReceivable.paymentDueDropdown);
            // Clicking lastWeek Failed In Months That Have 31 Days. For example, 03/31/2022 Needs To Select thisMonth
            accountReceivable.click(accountReceivable.lastWeek);
            //accountReceivable.click(accountReceivable.thisMonth);
        }
    }

    //Author Aarbi
    @Then("I search and validate customer in account receivable")
    public void searchAndValidateCustomer_AccountReceivable(String needCustomerNameToSearch, String needTestName) {
        accountReceivable.insert(accountReceivable.searchInputField, needCustomerNameToSearch);
        String expectedName = needCustomerNameToSearch.toLowerCase(Locale.ROOT);
        String actualCustomer = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
        result(expectedName, actualCustomer, needTestName, "Validate Account receivable");
    }

    //**FK
    @Then("I send {string} message under actions with subject {string} and text {string}")
    public void sendMessageUnderActions(String needMessageType, String needSubject, String needText) throws InterruptedException {
        customer = new CreateNewCustomer();
        Legacy.hoverElement(accountReceivable.actionsButton, accountReceivable.sendMessageButton_UnderActions);
        Legacy.waitVisible(accountReceivable.sendMessagesButton);
        accountReceivable.click(accountReceivable.ignoreContactPreferencesCheckBox);
        accountReceivable.click(accountReceivable.getIgnoreMaxPerMinuteCheckBox);
        if (needMessageType.equals("Email")) {
            accountReceivable.click(accountReceivable.emailRadioButton);
            accountReceivable.setInputSubjectText(accountReceivable.emailSubjectField, needSubject);
            accountReceivable.setInputEmailText(accountReceivable.emailTextField, needText);
        } else if (needMessageType.equals("Voice")) {
            accountReceivable.click(accountReceivable.voiceRadioButton);
            accountReceivable.setInputEmailText(accountReceivable.emailTextField, needText);
        } else if (needMessageType.equals("SMS")){
            accountReceivable.click(accountReceivable.SMSradioButton);
            accountReceivable.setInputEmailText(accountReceivable.emailTextField, needText);
        }
        accountReceivable.click(accountReceivable.sendMessagesButton);
    }
}




