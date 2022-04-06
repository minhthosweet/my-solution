package automation.PestRoutes.Controller.Invoicing;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CouponOrCreditTab;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Report.AssertException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CouponOrCredit {

    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_Header customerCardHeader;
    Invoice_Header invoice_Header;
    Header header;
    InvoiceImplementation invoiceImplementation = new InvoiceImplementation();
    RoutePageInvoicing routePageInvoicing;
    CouponOrCreditTab couponOrCreditTab = new CouponOrCreditTab();

    private String randomNotes = GetData.generateRandomString(10);

    //Author: Aditya
    @And("I validate all the fields in the Coupons or Credit window")
    public void validateAllFieldsInCouponsOrCredits() throws InterruptedException {
        header = new Header();
        customerCardHeader = new CustomerViewDialog_Header();
        invoice_Header = new Invoice_Header();
        routePageInvoicing = new RoutePageInvoicing();
        header.searchCustomerInOrder("1");
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        invoiceImplementation.clickInitialInvoice();
        routePageInvoicing.clickAddPayment();
        Thread.sleep(1000);
        invoice_Header.navigate(invoice_Header.coupon);

        //Validating fields
        String[] fields = {
                couponOrCreditTab.filterTypes("paymentAmount"),
                couponOrCreditTab.filterTypes("confirmCashAmount"),
                couponOrCreditTab.filterTypes("couponNumber"),
                couponOrCreditTab.filterTypes("couponDescription"),
                couponOrCreditTab.filterTypes("customerPaymentNotes"),
                couponOrCreditTab.filterTypes("currentBalance"),
                couponOrCreditTab.filterTypes("CurrentAction"),
                couponOrCreditTab.filterTypes("cancelPayment"),
                couponOrCreditTab.filterTypes("confirmPayment")
        };

        couponOrCreditTab.doubleClickBoxes(couponOrCreditTab.officePayment);
        couponOrCreditTab.click(couponOrCreditTab.filterTypes("limitCustomer"));
        couponOrCreditTab.doubleClickBoxes(couponOrCreditTab.collectionPayment);
        couponOrCreditTab.click(couponOrCreditTab.filterTypes("limitSubscription"));
        couponOrCreditTab.doubleClickBoxes(couponOrCreditTab.collectionAgencyPayment);
        couponOrCreditTab.click(couponOrCreditTab.filterTypes("applyToFirst"));
        couponOrCreditTab.doubleClickBoxes(couponOrCreditTab.writeOff);

        AssertException.validateFieldEnabled(fields);
    }

    //Author: Aditya
    @And("I validate if amounts are displayed correctly in coupons or credits")
    public void validatePopulatedBalanceMakeCouponPayment() {
        String balanceOnOriginalInvoice = invoiceImplementation.getRemainingBalanceAmount();
        AssertException.result(balanceOnOriginalInvoice, couponOrCreditTab.getPaymentAmount(couponOrCreditTab.filterTypes("paymentAmount")), "Payment Amount Validation", "Coupond/Credit Validation");
        AssertException.result(balanceOnOriginalInvoice, (couponOrCreditTab.getTextValue(couponOrCreditTab.filterTypes("currentBalance"))).replaceAll("[^0-9.]", ""), "Sub Status Amount Validation", "Coupond/Credit Validation");
    }

    //Author: Aditya
    @And("I make a coupon or credit payment")
    public void MakeCouponPayment() {

        String balanceOnOriginalInvoice = invoiceImplementation.getRemainingBalanceAmount();
        couponOrCreditTab.click(couponOrCreditTab.filterTypes("confirmCashAmount"));
        couponOrCreditTab.set(couponOrCreditTab.filterTypes("confirmCashAmount"), balanceOnOriginalInvoice);
        couponOrCreditTab.set(couponOrCreditTab.filterTypes("couponNumber"), randomNotes);
        couponOrCreditTab.set(couponOrCreditTab.filterTypes("couponDescription"), randomNotes);
        couponOrCreditTab.set(couponOrCreditTab.filterTypes("customerPaymentNotes"), randomNotes);
        couponOrCreditTab.click(couponOrCreditTab.writeOff);
        couponOrCreditTab.click(couponOrCreditTab.filterTypes("confirmPayment"));
    }

    //Author : Aditya
    @Then("I validate the success payment of credit or coupon payment")
    public void validateCouponCreditPayment() throws InterruptedException {
        createNewCustomer = new CreateNewCustomer();
        header = new Header();
        String balanceOnOriginalInvoice = invoiceImplementation.getRemainingBalanceAmount();
        AssertException.result("Coupon Applied Successfully!", couponOrCreditTab.getTextValue(couponOrCreditTab.successMessage), "Success Message Validation", "Coupon/Credit Validation");
        AssertException.result("Applied Coupon in the amount of: $" + balanceOnOriginalInvoice + "Account: Coupon Code: " + randomNotes + "Transaction ID: " + randomNotes + "AuthorizationCode:AVSResponse:CVVResponse:", couponOrCreditTab.getTextValue(couponOrCreditTab.successAppliedAmount), "Success Transaction Validation", "Coupon/Credit Validation");
        AssertException.result("FULLY PAID", invoiceImplementation.checkPaymentStatus(), "After Payment Status", "Coupon/Credit Validation");
        header.clickAccessHistory();
        createNewCustomer.removeCustomer();
    }

}
