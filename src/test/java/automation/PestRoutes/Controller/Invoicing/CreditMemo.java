package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Reporting.Office.BillingByServiceType;
import automation.PestRoutes.Controller.Reporting.TestTechNamePaymentsByServiceType;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditMemoTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.RoutePage.RoutePage;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

import static automation.PestRoutes.Utilities.AssertException.result;

public class CreditMemo extends AppData {

    CustomerViewDialog_Header customerCardHeader;
    InvoiceImplementation invImplementation;
    CreditMemoTab creditMemoTab;
    Header header;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;
    BillingByServiceType billingByServiceType;
    CreateNewCustomer createNewCustomer;
    RoutePage userOnRoutePage = new RoutePage();
    RoutePageInvoicing userOnInvoicesTab = new RoutePageInvoicing();
    Invoice_Header userSelectsPayment = new Invoice_Header();

    String customerName;
    String paymentBalance;

    //Author: Aditya
    @And("I create a credit memo for an existing invoice")
    public void createCreditMemo() throws InterruptedException, IOException {
        customerCardHeader = new CustomerViewDialog_Header();
        invImplementation = new InvoiceImplementation();
        creditMemoTab = new CreditMemoTab();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invImplementation.clickInitialInvoice();
        invImplementation.clickCreditMemoButton();
        creditMemoTab.setServiceType_creditMemoTab(getData("serviceDescription", generalData));
        creditMemoTab.clickCreateButton_creditMemoTab();
    }

    //Author: Aditya
    @Then("I validate the credit memo invoice")
    public void validateCreditMemo() throws Exception {
        header = new Header();
        creditMemoTab = new CreditMemoTab();
        invImplementation = new InvoiceImplementation();
        customerCardHeader = new CustomerViewDialog_Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        billingByServiceType = new BillingByServiceType();
        createNewCustomer = new CreateNewCustomer();
        header.searchCustomerInOrder("1");
        customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
        customerName = customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        creditMemoTab.clickInitialInvoice();
        String totalInvoiceValue = (invImplementation.getChargesBalance()).substring(1);
        String balance = invImplementation.getBalanceInPayments();
        String taxAmount = invImplementation.getTaxValue();
        String chargesBalance = invImplementation.getChargesBalance();
        String paymentBalance = invImplementation.getBalanceInPayments();
        invImplementation.click(creditMemoTab.getTicketID());
        creditMemoTab.validateServiceTypeName();
        result("-" + totalInvoiceValue, creditMemoTab.getChargesAmount_creditMemo(), "Charges validation in Credit Memo", "Invoice Validation");
        result(customerName + "(SELF)", creditMemoTab.getBilledAccount(), "Billed To validation in Credit Memo", "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), creditMemoTab.getCreditMemoDate().replaceAll("0", ""), "Credit Memo Date validation in Credit Memo", "Invoice Validation");
        result(creditMemoTab.getInvoiceApplicationDate(), creditMemoTab.getCreditMemoDate(), "Invoice Application Date validation in Credit Memo", "Invoice Validation");
        result(balance, creditMemoTab.getPaymentsBalance(), "Balance validation in Credit Memo", "Invoice Validation");
        creditMemoTab.clickAppliedCharge_invoiceApplications();
        result(billingByServiceType.standAloneInvoiceAmount, invImplementation.getServiceCostBeforeTax(), "Service Cost Validation",
                "Invoice Validation");
        result("$" + billingByServiceType.standAloneInvoiceAmount, invImplementation.getSubTotalValue(), "Sub Total Value Validation",
                "Invoice Validation");
        result(taxAmount, invImplementation.getTaxValue(), "Tax Value Validation",
                "Invoice Validation");
        result(chargesBalance, invImplementation.getChargesBalance(), "Total Invoice Value in Charges Validation",
                "Invoice Validation");
        result(paymentBalance, invImplementation.getBalanceInPayments(), "Total Invoice Value in Payments Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getInvoiceDate().replaceAll("0", ""), "Invoice Date Validation",
                "Invoice Validation");
        result(Utilities.currentDate("MM/dd/yy").replaceAll("0", ""), invImplementation.getDueDate().replaceAll("0", ""), "Due Date Validation",
                "Invoice Validation");
        result("N/A", "N/A", "Appointment Date Validation",
                "Invoice Validation");
        createNewCustomer.closeCustomerCard();
    }

    @Then("I Can Change The Invoice Amount Which Has A Credit Memo")
    public void testChangingInvoiceAmountWithCreditMemo() throws InterruptedException {
        TestTechNamePaymentsByServiceType test = new TestTechNamePaymentsByServiceType();
        CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
        InvoiceImplementation userMakesPayment = new InvoiceImplementation();
        String customer = test.customerName;
        String payment = test.paymentAmount;

        userOnRoutePage.goToCustomerSearchComponent(customer);
        sameUser.goToInvoicesTab();
        userOnInvoicesTab.clickFullyPaidPaymentStatus();
        userOnInvoicesTab.typeServiceChargeAmount("200.00"); // Hard-Coded For Now But Will Update Later
        userOnInvoicesTab.typeInitialDiscount("10.00"); // Hard-Coded For Now But Will Update Later
        paymentBalance = userOnInvoicesTab.getPaymentBalance();
        userOnInvoicesTab.addPayment();
        userSelectsPayment.clickCash();
        payment = userMakesPayment.getPaymentAmount();
        userMakesPayment.typeConfirmationAmount(payment);
        userMakesPayment.clickRecordPaymentButton();
        userMakesPayment.clickBackToAccountSummaryButton();
        Assert.assertTrue(userOnInvoicesTab.getRecentMemo().contains(payment),
                "The Memo Does Not Contain Payment Amount");
    }
}