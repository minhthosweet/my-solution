package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.Utilities.Utilities;

public class BillingModule {

    // Below objects are the header menu of Billing module

    public String autoPayBalances = "//p[text() = 'AutoPay Balances']";
    public String accountsReceivable = "//p[text() = 'Accounts Receivable']";
    public String accountsWithCredit = "//p[text() = 'Accounts With Credits']";
    public String Collections = "//p[text() = 'Collections']";
    public String PaymentHistory = "//p[text() = 'Payment History']";
    public String PaymentBatches = "//p[text() = 'Payment Batches']";

    public void navigate(String needPath){
        Utilities.clickElement(needPath, Utilities.ElementType.XPath);
    }

}
