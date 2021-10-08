package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;

import java.util.HashMap;
import java.util.Map;

public class CouponOrCreditTab {

    public String officePayment = "//input[@name='officePayment']";
    public String collectionPayment = "//input[@name='collectionPayment']";
    public String collectionAgencyPayment = "//input[@name='paymentOrigin']";
    public String writeOff = "//input[@name='writeOff']";
    public String successMessage = "//form[@id='singlePaymentForm']//h2[1]";
    public String successAppliedAmount = "//form[@id='singlePaymentForm']//p";

    public void click(String needIdentifier) {
        Utilities.waitUntileElementIsVisible(needIdentifier);
        Utilities.clickElement(needIdentifier, Utilities.ElementType.XPath);
    }

    public Map<String, String> filter_Types = new HashMap<String, String>();

    //Author: Aditya
    public String filterTypes(String key) {

        //Payment Details
        filter_Types.put("paymentAmount", "//input[@name='amount']");
        filter_Types.put("confirmCashAmount", "//input[@name='confirmCashAmount']");
        filter_Types.put("couponNumber", "//input[@name='couponNumber']");
        filter_Types.put("couponDescription", "//input[@name='couponDescription']");
        filter_Types.put("customerPaymentNotes", "//textarea[@name='customerNotes2']");

        //Distribution Details
        filter_Types.put("limitCustomer", "//div[text()='Limited To Customer: ']/following-sibling::div[1]");
        filter_Types.put("limitSubscription", "//div[text()='Limited To Subscription: ']//following-sibling::div[1]");
        filter_Types.put("applyToFirst", "//div[text()='Apply To First: ']//following-sibling::div[1]");

        //Payment Balance
        filter_Types.put("currentBalance", "//form[@id='singlePaymentForm']//div[@id='SubStatus']");
        filter_Types.put("CurrentAction", "//form[@id='singlePaymentForm']//span[@id='SubStatusAction']");

        //Payment confirmation buttons
        filter_Types.put("cancelPayment", "//div[text()='Cancel Payment']");
        filter_Types.put("confirmPayment", "//div[text()='Record Coupon']");

        //Success message button
        filter_Types.put("backToSummary_CouponCredit","//div[text()='Back to Account Summary']");

        return filter_Types.get(key);
    }

    public void doubleClickBoxes(String needIdentifier) {
        Utilities.waitUntileElementIsVisible(needIdentifier);
        Utilities.doubleClick(needIdentifier);
    }

    //getters

    public String getPaymentAmount(String needXPath) {
        Utilities.waitUntileElementIsVisible(needXPath);
        return Utilities.getAttributeValue(needXPath, "value");
    }

    public String getTextValue(String needXPath) {
        Utilities.waitUntileElementIsVisible(needXPath);
        return Utilities.getElementTextValue(needXPath, Utilities.ElementType.XPath);
    }

    //setters
    public void set(String needIdentifier, String needValue) {
        Utilities.waitUntileElementIsVisible(needIdentifier);
        FindElement.elementByAttribute(needIdentifier, FindElement.InputType.XPath).sendKeys(needValue);
    }
}
