package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static automation.PestRoutes.Utilities.Utilities.*;

public class PaymentsByServiceTypeTab extends BasePage {

    private By dateField = By.xpath("//input[@name='dateRange-officeParams']");
    private By groupByField = By.xpath("//select[@name='groupBy']");
    private By refreshButton = By.xpath("//div[@id='refreshOfficeReport']");
    private By dateRange = By.xpath("//body[@id='reportsPage']/div[contains(@class,'daterangepicker')]//li");
    private By applyButton = By.xpath("//body[@id='reportsPage']//button[text()='Apply']");

    public String Coupons = "//select[@name='excludeCoupons']";

    // Table Values
    private By descriptionValues = By.xpath("//table[@id='cashByServiceType']/tbody//td[1]");
    private By customerNames = By.xpath("//div[@id='serviceTypeDetail']/table/tbody//td[2]");

    //Report Data Objects
    public String totalCollected_Report = "//tr[@detailvalues]//td[10]";
    public String totalCollected_VisaMasterEtc_Report = "//tr[@detailvalues]//td[7]";
    public String totalCollected_ACH_Report = "//tr[@detailvalues]//td[9]";
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

        // pst multi group report fields
        filterTypes_PST.put("description_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Description']");
        filterTypes_PST.put("appliedPaymentsBeforeTax_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Applied Payments before Tax']");
        filterTypes_PST.put("tax_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Tax']");
        filterTypes_PST.put("coupons_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Coupons']");
        filterTypes_PST.put("cash_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Cash']");
        filterTypes_PST.put("check_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Check']");
        filterTypes_PST.put("cardType_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Visa/Master/Etc']");
        filterTypes_PST.put("amexCard_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Amex']");
        filterTypes_PST.put("ACH_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='ACH']");
        filterTypes_PST.put("totalCollected_pstReport_MultiGroup", "//table[@id='cashByServiceType']//th[text()='Total Collected']");

        return filterTypes_PST.get(key);
    }

    public String getAppliedPaymentsBeforeTax_MultiGroupReport(String needCustomerID) {
        return Deprecated.getElementTextValue("//td[text()='" + needCustomerID + "']/following-sibling::td[1]");
    }

    public String getPaymentServices_MultiGroupReport(String needCustomerID) {
        return Deprecated.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[10]");
    }

    public String getTaxRate_MultiGroupReport(String needCustomerID) {
        return Deprecated.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[3]");
    }

    public void selectDateFor (String value) {
        delay(2000);
        click(dateField);
        locate(By.xpath("//body[@id='reportsPage']/div[18]//li[text()='" + value + "']")).click();
    }

    public void selectGroupBy (String value) {
        delay(2000);
        WebElement groupBy = locate(groupByField);
        Select selectGroupBy = new Select(groupBy);
        selectGroupBy.selectByVisibleText(value);
    }

    public void clickRefreshButton(){
        click(refreshButton);
    }

    public void clickDescription(String value) {
        List<WebElement> descriptionList = locateAll(descriptionValues);
        for(WebElement description : descriptionList) {
            if (description.getText().equals(value)) {
                description.click();
            }
        }
    }

    public boolean getCustomerName(String value) {
        List<WebElement> customerList = locateAll(customerNames);
        for(WebElement customer : customerList) {
            if (customer.getText().equals(value)) {
                return true;
            }
        }
        return false;
    }
}