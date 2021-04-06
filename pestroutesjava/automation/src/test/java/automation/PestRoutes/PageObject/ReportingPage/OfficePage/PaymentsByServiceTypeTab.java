package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import java.util.HashMap;
import java.util.Map;

public class PaymentsByServiceTypeTab {

    public String Coupons = "//select[@name='excludeCoupons']";

    //Report Data Objects
    public String totalCollected_Report = "//tr[@detailvalues]//td[10]";
    public String totalCollected_VisaMasterEtc_Report = "//tr[@detailvalues]//td[7]";
    public String appliedPaymentBeforeTax_Report = "//tr[@detailvalues]//td[2]";
    public String tax_Report = "//tr[@detailvalues]//td[3]";

    //Line Item Data Objects
    public String totalCollected_Customer = "//tr[@onmouseup]//td[9]";
    public String tax_Customer = "//tr[@onmouseup]//td[8]";
    public String invoiceDate_lineItem = "//tr[@onmouseup]//td[3]";
    public String invoiceID_lineItem = "//tr[@onmouseup]//td[6]";
    public String appliedPaymentBeforeTax_Customer = "//tr[@onmouseup]//td[7]";
    public String paymentMethod = "//tr[@onmouseup]//td[4]";

    public Map<String, String> filterTypes_PST = new HashMap<String, String>();

    //Author: Aditya
    public String filterTypes_PST(String key) {
        // PST single group report fields
        filterTypes_PST.put("description_pstReport", "//table[@id='cashByServiceType']//div[text()='Description']");
        filterTypes_PST.put("appliedPaymentsBeforeTax_pstReport", "//table[@id='cashByServiceType']//div[text()='Applied Payments before Tax']");
        filterTypes_PST.put("tax_pstReport", "//table[@id='cashByServiceType']//div[text()='Tax']");
        filterTypes_PST.put("coupons_pstReport", "//table[@id='cashByServiceType']//div[text()='Coupons']");
        filterTypes_PST.put("cash_pstReport", "//table[@id='cashByServiceType']//div[text()='Cash']");
        filterTypes_PST.put("check_pstReport", "//table[@id='cashByServiceType']//div[text()='Check']");
        filterTypes_PST.put("cardType_pstReport", "//table[@id='cashByServiceType']//div[text()='Visa/Master/Etc']");
        filterTypes_PST.put("amexCard_pstReport", "//table[@id='cashByServiceType']//div[text()='Amex']");
        filterTypes_PST.put("ACH_pstReport", "//table[@id='cashByServiceType']//div[text()='ACH']");
        filterTypes_PST.put("totalCollected_pstReport", "//table[@id='cashByServiceType']//div[text()='Total Collected']");

        // individual line item fields
        filterTypes_PST.put("paymentDate_lineItem", "//th[@data-orderby='paymentDate']");
        filterTypes_PST.put("paymentMethod_lineItem", "//th[@data-orderby='paymentMethod']");
        filterTypes_PST.put("serviceType_lineItem", "//th[@data-orderby='description']");
        filterTypes_PST.put("invoiceID_lineItem", "//th[@data-orderby='invoiceID']");
        filterTypes_PST.put("totalPayment_lineItem", "//th[@data-orderby='total']");

        return filterTypes_PST.get(key);
    }

}
