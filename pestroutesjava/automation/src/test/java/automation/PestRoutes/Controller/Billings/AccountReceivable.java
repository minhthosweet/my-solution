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
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import java.util.Locale;

import static automation.PestRoutes.Utilities.AssertException.result;


public class AccountReceivable extends BaseClass {
    RoutePageInvoicing invoice;
    CreateCustomerDialog infoTab;
    BillingModule billingModule;
    BillingPage customerCardBillingTab;
    CreateNewInvoicePopUp newInvoice;
    AccountReceivablePage accountReceivable = new AccountReceivablePage();
    Header header = new Header();
    SignIn signin;
    CustomerViewDialog_Header customerCardHeader;
    CustomerViewDialog_Admin admin;
    CreateNewCustomer customer;
    ValidateRenewal subscription;

    @Test
    public void test() throws Exception {
        signin = new SignIn();
        signin.login();
        navigateToAccountReceivablePage();
        validateAllFieldsEnabled();
    }

    @And("I navigate to account receivable under Billings")
    public void navigateToAccountReceivablePage(){
        billingModule = new BillingModule();
        header.navigateTo(header.billingTab);
        billingModule.navigate(billingModule.accountsReceivable);
    }
    @Then("I validate if all fields are displaying and are enabled")
    public void validateAllFieldsEnabled(){

        String[] fields = {accountReceivable.asOfDateInputField, accountReceivable.accountStatusDropdown, accountReceivable.autoPayDropdown,
        accountReceivable.statusDropdown, accountReceivable.propertyDropdown, accountReceivable.balanceInputField, accountReceivable.balanceAgeDropdown,
        accountReceivable.paymentDueDropdown, accountReceivable.daysOverDueDropdown, accountReceivable.prefPaperDropdown, accountReceivable.hasEmailDropdown,
        accountReceivable.maxMonthlyDropdown, accountReceivable.groupByDropdown, accountReceivable.inclCollectionDropdown,
        accountReceivable.inclFlagsDropdown, accountReceivable.exclFlagsDropdown};
        accountReceivable.click(accountReceivable.advanceFilterLink);
        AssertException.validateFieldEnabled(fields);
    }

    @Then("I validate if the customer displays once account status is Active")
    public void validateAccountStatus() throws Exception {

        customer = new CreateNewCustomer();
        admin = new CustomerViewDialog_Admin();
        customerCardHeader = new CustomerViewDialog_Header();
        accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
        accountReceivable.insert(accountReceivable.asOfDateInputField, Utilities.currentDate("MM/dd/yyyy"));
        accountReceivable.click(accountReceivable.refreshButton);
        String customerName = customer.getCustomerName("1");
        customerCardHeader.clickCloseButton();
        accountReceivable.insert(accountReceivable.searchInputField, customerName);
        String actualName = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
        String expectedName = customerName.toLowerCase(Locale.ROOT);
        result(expectedName, actualName, " Validate customer name", "Validate Account receivable");

        header.searchCustomerInOrder("1");
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Frozen(admin.cancelServiceProps);
        customer.closeCustomerCard();

        String[] accountStatus = {"Frozen", "All"};
        for (int i = 0; i < accountStatus.length; i++){

            accountReceivable.select(accountReceivable.accountStatusDropdown, accountStatus[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            accountReceivable.insert(accountReceivable.searchInputField, customerName);
            String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
            result(expectedName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");

        }

    }

    @Then("I validate auto pay customer display")
    public void validateAutoPayCustomer() throws Exception {
        customer = new CreateNewCustomer();
        customerCardBillingTab = new BillingPage();
        customerCardHeader = new CustomerViewDialog_Header();
        String customerName = customer.getCustomerName("1");
        customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
        customerCardBillingTab.clickElement(customerCardBillingTab.addPaymentMethodButton);
        customerCardBillingTab.clickElement(customerCardBillingTab.addCreditCardButton);
        String[] iFrame = {customerCardBillingTab.iFrameCc, customerCardBillingTab.iFrameExp, customerCardBillingTab.iFrameCvv};
        String[] fields = {customerCardBillingTab.ccCardNumberInputField, customerCardBillingTab.ccExpirationInputField, customerCardBillingTab.ccCvvInputField};
        String[] input = {"4111111111111111", "0222", "123"};
        for (int i = 0; i < iFrame.length; i++){
            Utilities.switchToIframeByXpath(iFrame[i]);
            customerCardBillingTab.setInputField(fields[i], input[i]);
            Utilities.switchBackToDom();
        }
        customerCardBillingTab.clickElement(customerCardBillingTab.savePaymentMethodButton);
        customerCardBillingTab.clickElement(customerCardBillingTab.billingInfoButton);
        Utilities.selectValueFromDropDownByIndex(customerCardBillingTab.autoPayDropdown, 1);
        customerCardBillingTab.clickElement(customerCardBillingTab.saveBillingInfoButton);
        customer.closeCustomerCard();
        accountReceivable.select(accountReceivable.autoPayDropdown, "CC Auto Pay");
        accountReceivable.click(accountReceivable.refreshButton);
        accountReceivable.insert(accountReceivable.searchInputField, customerName);
        String expectedName = customerName.toLowerCase(Locale.ROOT);
        String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
        result(expectedName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");

    }
    @Then("I validate customer type in account receivable")
    public void validateCustomerByPropType() throws Exception {
        customerCardHeader = new CustomerViewDialog_Header();
        infoTab = new CreateCustomerDialog();
        customer = new CreateNewCustomer();
        admin = new CustomerViewDialog_Admin();
        String[] typeOfCustomer = {infoTab.commercialProperty, infoTab.residentialProperty};
        String[] propType = {"Commercial Only", "Residential Only"};
        for (int i = 0; i < typeOfCustomer.length; i++){
            String customerName = customer.getCustomerName("1");
            customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
            infoTab.selectProperty(typeOfCustomer[i]);
            customerCardHeader.clickSaveButton();
            customerCardHeader.clickCloseButton();
            navigateToAccountReceivablePage();
            accountReceivable.select(accountReceivable.propertyDropdown, propType[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            accountReceivable.insert(accountReceivable.searchInputField, customerName);
            String expectedName = customerName.toLowerCase(Locale.ROOT);
            String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
            result(expectedName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");
        }
        String customerName = customer.getCustomerName("1");
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Active();
        customerCardHeader.clickCloseButton();
        String[] accountStatus = {"Active", "All"};
        for (int i = 0; i < accountStatus.length; i++){
            accountReceivable.select(accountReceivable.accountStatusDropdown, accountStatus[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            accountReceivable.insert(accountReceivable.searchInputField, customerName);
            String expectedName = customerName.toLowerCase(Locale.ROOT);
            String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
            result(expectedName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");

        }

    }
    @Then("I validate customer with balance")
    public void validateBalance() throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        newInvoice = new CreateNewInvoicePopUp();
        admin = new CustomerViewDialog_Admin();
        String amount = "400";
        header.navigateTo(header.newCustomerTab);
        customer.createCustomerWithAddress();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoice = new RoutePageInvoicing();
        invoice.clickAddNewInvoice(invoice.addNewInvoice);
        newInvoice.set(newInvoice.dateField, Utilities.currentDate("MM/dd/yyyy"));
        newInvoice.set(newInvoice.amountInputField, amount);
        newInvoice.select(newInvoice.serviceTypeDropdown, "AA Petst1");
        newInvoice.click(newInvoice.createButton);
        customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
        admin.changeAccountStatus_Active();
        String customerName = customer.getCustomerName("1").toLowerCase(Locale.ROOT);
        customerCardHeader.clickCloseButton();
        navigateToAccountReceivablePage();
        accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
        accountReceivable.insert(accountReceivable.balanceInputField, "300");
        accountReceivable.click(accountReceivable.refreshButton);
        accountReceivable.insert(accountReceivable.searchInputField, customerName);
        String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
        result(customerName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");

    }
    @Then("I validate customer with balance age, payment due, and days overdue")
    public void validateBalanceAge() throws Exception {
        customer = new CreateNewCustomer();
        customerCardHeader = new CustomerViewDialog_Header();
        newInvoice = new CreateNewInvoicePopUp();
        admin = new CustomerViewDialog_Admin();
        String amount = "400";
        String[] balanceAge = {"7+ Days Old", "30+ Days Old (Past Due)", "90+ Days Old (Way, Way Past Due)"};
        String[] daysOverDue = {"7+ Days Overdue", "30+ Days Overdue", "90+ Days Overdue"};
        int[] invoiceDaysPastDue = {7,30,90};
        for(int i = 0; i < balanceAge.length; i++){
            header.navigateTo(header.newCustomerTab);
            customer.createCustomerWithAddress();
            customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
            invoice = new RoutePageInvoicing();
            invoice.clickAddNewInvoice(invoice.addNewInvoice);
            int currentMonth = GetDate.getMonth(Utilities.currentDate("MM/dd/yyyy"));
            int currentYear = GetDate.getYear(Utilities.currentDate("MM/dd/yyyy"));
            String dateOfInvoice = GetDate.minusGenericDayToDate(Utilities.currentDate("MM/dd/yyyy"), invoiceDaysPastDue[i]);
            int monthOfInv = GetDate.getMonth(dateOfInvoice);
            int yearOfInv = GetDate.getYear(dateOfInvoice);
            newInvoice.set(newInvoice.dateField, dateOfInvoice);
            newInvoice.set(newInvoice.amountInputField, amount);
            newInvoice.select(newInvoice.serviceTypeDropdown, "AA Petst1");
            newInvoice.click(newInvoice.createButton);
            customerCardHeader.navigateTo(customerCardHeader.adminTabInDialog);
            admin.changeAccountStatus_Active();
            customer.closeCustomerCard();
            String customerName = customer.getCustomerName("1").toLowerCase(Locale.ROOT);
            customer.closeCustomerCard();
            navigateToAccountReceivablePage();
            accountReceivable.click(accountReceivable.advanceFilterLink);
            accountReceivable.select(accountReceivable.accountStatusDropdown, "Active");
            accountReceivable.insert(accountReceivable.balanceInputField, "300");
            accountReceivable.select(accountReceivable.balanceAgeDropdown, balanceAge[i]);
            int monthPastDue = currentMonth - monthOfInv;
            int yearsPastDue = currentYear - yearOfInv;
            if(monthPastDue > 1 && yearsPastDue == 0){
                accountReceivable.click(accountReceivable.paymentDueDropdown);
                accountReceivable.click(accountReceivable.thisYear);
            }else if (yearsPastDue > 0){
                accountReceivable.click(accountReceivable.paymentDueDropdown);
                accountReceivable.click(accountReceivable.lastYear);
            }else if (monthPastDue == 1 && yearsPastDue == 0){
                accountReceivable.click(accountReceivable.paymentDueDropdown);
                accountReceivable.click(accountReceivable.lastMonth);
            } else if (monthPastDue == 0 && yearsPastDue == 0){
                accountReceivable.click(accountReceivable.paymentDueDropdown);
                accountReceivable.click(accountReceivable.lastWeek);
            }
            accountReceivable.select(accountReceivable.daysOverDueDropdown, daysOverDue[i]);
            accountReceivable.click(accountReceivable.refreshButton);
            accountReceivable.insert(accountReceivable.searchInputField, customerName);
            String actualNameWithStatus = accountReceivable.getValueFromTable("2").toLowerCase(Locale.ROOT);
            result(customerName, actualNameWithStatus, " Validate customer name", "Validate Account receivable");
        }

    }


    private String customerName(){
        customer = new CreateNewCustomer();
        String name = customer.getCustomerName();
        return name;
    }
}
