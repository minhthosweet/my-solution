package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Reporting.Office.OfficeObjects;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BillingByServiceTypeTab {

    Header header;
    ReportingMainPage reportingMainPage;
    OfficeObjects officeObjs;
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_Header dialog;

    public String dateParams = "//input[@name='dateRange-officeParams']";
    public String groupBy = "//select[@name='groupBy']";
    public String subGroupOne = "//select[@name='groupBy2']";
    public String subGroupTwo = "//select[@name='groupBy3']";
    public String asOfDate = "//input[@name='paymentAsOfDate']";
    public String productionValue = "//select[@name='useProductionValue']";
    public String writeOffs = "//select[@name='writeOff']";
    public String aPayType_bbst = "//select[@name='aPay']";
    public String aPayStatus_bbst = "//select[@name='paymentAccountStatus']";
    public String propType_bbst = "//select[@name='propertyType']";
    public String prefersPaper = "//select[@name='prefersPaper']";
    public String balance_bbst = "//input[@name='balance']";
    public String balanceAge_bbst = "//select[@name='balanceAge']";
    public String soldDateRange = "//input[@placeholder='Sold Date Range']";
    public String search_bbst = "//input[@type='search' and @placeholder='Search...']";
    public String refresh_bbst = "//div[text()=' Refresh ']";
    public String toggleChart = "//div[text() = 'Toggle Chart']";
    public String billedServices_Report = "//tr[@detailvalues]//span[@class='revenue']";
    public String billedServices_Customer = "//tr[@onmouseup]//td[13]";
    public String tax_Report = "//tr[@detailvalues]//td[6]";
    public String tax_Customer = "//tr[@onmouseup]//td[10]";
    public String exportDetailsToCSV_button = "//div[@id='serviceTypeDetail']//div[text()='Export details to CSV']";
    public String search_lineItem = "//div[@id='serviceTypeDetail']//input[@placeholder='Search...']";
    public String pageNumber_lineItem = "//span[text()='Page ']//input[@type='text']";

    public Map<String, String> filter_Types = new HashMap<String, String>();

    public BillingByServiceTypeTab() throws Exception {
    }

    public String filterTypes(String key) {

        // filter fields
        filter_Types.put("invoice_bbst", "//label[text()='Invoice']//following-sibling::div//input");
        filter_Types.put("serviceType_bbst", "//label[text()='Service Type']//following-sibling::div//input");
        filter_Types.put("customerSource_bbst", "//label[text()='Cust Source']//following-sibling::div//input");
        filter_Types.put("inclCollections", "//label[text()='Incl. Collections']//following-sibling::div//input");
        filter_Types.put("subSource_bbst", "//label[text()='Sub Source']//following-sibling::div//input");
        filter_Types.put("regions_bbst", "//label[text()='Regions']//following-sibling::div//input");
        filter_Types.put("divisions_bbst", "//label[text()='Divisions']//following-sibling::div//input");
        filter_Types.put("offices_bbst", "//label[text()='Offices']//following-sibling::div//input");
        filter_Types.put("includeFlags_bbst", "//label[text()='Incl. Flags']//following-sibling::div//input");
        filter_Types.put("excludeFlags_bbst", "//label[text()='Excl. Flags']//following-sibling::div//input");
        filter_Types.put("scheduledBy_bbst", "//label[text()='Scheduler']//following-sibling::div//input");
        filter_Types.put("soldByTeam_bbst", "//label[text()='Sale Teams']//following-sibling::div//input");
        filter_Types.put("soldbySalesRep_bbst", "//label[text()='Sales Rep']//following-sibling::div//select");

        // bbst report fields
        filter_Types.put("description_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Description']");
        filter_Types.put("services_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Services']");
        filter_Types.put("lineItemQuantity_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Line Item Quantity']");
        filter_Types.put("totalCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Total Collected']");
        filter_Types.put("taxCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Tax Collected']");
        filter_Types.put("taxInvoiced_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Tax Invoiced']");
        filter_Types.put("paymentsCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Payments Collected']");
        filter_Types.put("billedServices_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Billed Services']");

        // individual line item fields
        filter_Types.put("customerID_lineItem", "//th[@data-orderby='customerID']");
        filter_Types.put("customerName_lineItem", "//th[@data-orderby='customerName']");
        filter_Types.put("invoiceID_lineItem", "//th[@data-orderby='ticketID']");
        filter_Types.put("date_lineItem", "//th[@data-orderby='ticketDate']");
        filter_Types.put("serviceDescription_lineItem", "//th[@data-orderby='serviceDescription']");
        filter_Types.put("billingFrequency_lineItem", "//th[@data-orderby='billingFrequency']");
        filter_Types.put("itemsQuantity_lineItem", "//th[@data-orderby='itemsQuantity']");
        filter_Types.put("totalCollected_lineItem", "//th[@data-orderby='totalCollected']");
        filter_Types.put("taxCollected_lineItem", "//th[@data-orderby='taxCollected']");
        filter_Types.put("tax_lineItem", "//th[@data-orderby='tax']");
        filter_Types.put("appliedPaymentsBeforeTax_lineItem", "//th[@data-orderby='revenueCollected']");
        filter_Types.put("dateCollected_lineItem", "//th[@data-orderby='dateCollected']");
        filter_Types.put("billedServices_lineItem", "//th[@data-orderby='revenue']");

        // Line Item navigation buttons
        filter_Types.put("first_page", "//div[@id='serviceTypeDetail']//span[text()='First']");
        filter_Types.put("previous_page", "//div[@id='serviceTypeDetail']//span[text()='Previous']");
        filter_Types.put("next_page", "//div[@id='serviceTypeDetail']//span[text()='Next']");
        filter_Types.put("last_page", "//div[@id='serviceTypeDetail']//span[text()='Last']");

        return filter_Types.get(key);
    }

    public void mainGroupBy(String groupByType) {
        officeObjs = new OfficeObjects();
        Utilities.waitUntileElementIsVisible(groupBy);
        Utilities.selectValueFromDropDownByValue(groupBy, groupByType);
        officeObjs.navigateTo(officeObjs.billingByServiceType);
    }

    public void navigateToBillingByServiceTypePage() {
        header = new Header();
        reportingMainPage = new ReportingMainPage();
        officeObjs = new OfficeObjects();
        header.navigateTo(header.reportingTab);
        reportingMainPage.navigateTo(reportingMainPage.office);
        officeObjs.navigateTo(officeObjs.billingByServiceType);
    }

    public void editCustomerPerFilters() throws InterruptedException, IOException {
        createNewCustomer = new CreateNewCustomer();
        dialog = new CustomerViewDialog_Header();
        header = new Header();
        header.searchCustomerInOrder("1");
        dialog.navigateTo(dialog.infoTabInDialog);
        createNewCustomer.addAdditionalProperties();
    }

    public void editCustomer_NoPaper_Commercial() throws InterruptedException, IOException {
        createNewCustomer = new CreateNewCustomer();
        dialog = new CustomerViewDialog_Header();
        header = new Header();
        header.clickAccessHistory();
        header.searchCustomerInOrder("1");
        createNewCustomer.editCustomer_NoPaper_CommercialProperty();
    }

    public String getCustomerName_CustomerCard_InfoTab() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        dialog = new CustomerViewDialog_Header();
        String customerName = createNewCustomer.getCustomerName("1");
        dialog.Click_X_Button();
        return customerName;
    }

    public String getBilledServiceValue_Report() {
        return Utilities.getElementTextValue(billedServices_Report, Utilities.ElementType.XPath);
    }

    public String getBilledTaxValue_Report() {
        return Utilities.getElementTextValue(tax_Report, Utilities.ElementType.XPath).replace("\n", "");
    }

    public String getBilledTaxValue_Customer() {
        return Utilities.getElementTextValue(tax_Customer, Utilities.ElementType.XPath).replace("\n", "");
    }

    public String getBilledServiceValue_Customer() {
        return Utilities.getElementTextValue(billedServices_Customer, Utilities.ElementType.XPath).replace("\n", "");
    }

    public void searchNewCustomer() throws Exception {
        Utilities.waitUntileElementIsVisible(search_bbst);
        FindElement.elementByAttribute(search_bbst, FindElement.InputType.XPath).sendKeys(getCustomerName_CustomerCard_InfoTab());
    }

    public void click(String needButton) {
        Utilities.clickElement(needButton, Utilities.ElementType.XPath);
    }

    public void clickAdvancedFilters() {
        Utilities.clickAdvancedFilters();
    }

    public void setType(String key, String type) {
        FindElement.elementByAttribute(filterTypes(key), FindElement.InputType.XPath).sendKeys(type);
        Utilities.clickElement("//span[text()='" + type + "']", Utilities.ElementType.XPath);
    }

    public void setIncludeCollections(String includeCollections) {
        FindElement.elementByAttribute(filterTypes("inclCollections"), FindElement.InputType.XPath).sendKeys(includeCollections);
    }

    public void setSubscriptionSource(String subscriptionSource) {
        FindElement.elementByAttribute(filterTypes("subSource_bbst"), FindElement.InputType.XPath).sendKeys(subscriptionSource);
    }

    public void setRegions(String regions) {
        FindElement.elementByAttribute(filterTypes("regions_bbst"), FindElement.InputType.XPath).sendKeys(regions);
    }

    public void setOffice_bbst(String office) {
        FindElement.elementByAttribute(filterTypes("offices_bbst"), FindElement.InputType.XPath).sendKeys(office);
    }

    public void setExcludeFlags(String excludeFlags) {
        FindElement.elementByAttribute(filterTypes("excludeFlags_bbst"), FindElement.InputType.XPath).sendKeys(excludeFlags);
    }

    public void setScheduledBy(String scheduledBy) {
        FindElement.elementByAttribute(filterTypes("scheduledBy_bbst"), FindElement.InputType.XPath).sendKeys(scheduledBy);
    }

    public void setSoldBy(String soldBy) {
        FindElement.elementByAttribute(filterTypes("soldByTeam_bbst"), FindElement.InputType.XPath).sendKeys(soldBy);
    }

    public void setSoldbySalesRep(String soldbySalesRep) {
        FindElement.elementByAttribute(filterTypes("soldbySalesRep_bbst"), FindElement.InputType.XPath).sendKeys(soldbySalesRep);
    }

    public void setSoldDateRange(String dateRange) {
        Utilities.waitUntileElementIsVisible(soldDateRange);
        FindElement.elementByAttribute(soldDateRange, FindElement.InputType.XPath).sendKeys(dateRange);
    }

    public void clickDescription_reportDetails() throws Exception {
        Utilities.scrollToElementJS("//tr[@detailvalues]//td[text()='" + getCustomerName_CustomerCard_InfoTab() + "']");
        Utilities.clickElement("//tr[@detailvalues]//td[text()='" + getCustomerName_CustomerCard_InfoTab() + "']", Utilities.ElementType.XPath);
    }

    public void customerDetails() throws Exception {
        String customerName_detailed = "//tr[@onmouseup]//td[text()='" + getCustomerName_CustomerCard_InfoTab() + "']";
        try {
            WebElement elm = FindElement.elementByAttribute(customerName_detailed, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                Utilities.scrollToElementJS(customerName_detailed);
                Utilities.clickElement(customerName_detailed, Utilities.ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("Customer Card doesn't open");
        }
    }

    public void set(String needTag, String needType){
        Utilities.waitUntileElementIsVisible(needTag);
        Utilities.selectValueFromDropDownByValue(needTag, needType);
    }

    public void setBalance_bbst(String balance) {
        Utilities.waitUntileElementIsVisible(balance_bbst);
        FindElement.elementByAttribute(balance_bbst, FindElement.InputType.XPath).sendKeys(balance);
    }

    public void setDateRange(String type, String dateRange){
        Utilities.waitUntileElementIsVisible(type);
        Utilities.clickElement(type, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[contains(@style,'block')]//li[text()='"+dateRange+"']");
        Utilities.clickElement("//div[contains(@style,'block')]//li[text()='"+dateRange+"']", Utilities.ElementType.XPath);
    }

    public void setSalesRep(String salesRepTab, String salesRepType){
        Utilities.waitUntileElementIsVisible(filterTypes(salesRepTab));
        Utilities.selectValueFromDropDownByValue(filterTypes(salesRepTab), salesRepType);
    }

}
