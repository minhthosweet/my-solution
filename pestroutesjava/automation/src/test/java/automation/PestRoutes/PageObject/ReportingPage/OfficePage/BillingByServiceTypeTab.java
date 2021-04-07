package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Reporting.Office.OfficeObjects;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BillingByServiceTypeTab {

    Header header;
    OfficeObjects officeObjs;
    CreateNewCustomer createNewCustomer;
    CustomerViewDialog_Header customerViewDialog_header;

    // Report Fields
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
    public String totalCollected_Report = "//tr[@detailvalues]//td[4]";
    public String tax_Report = "//tr[@detailvalues]//td[6]";
    public String exportDetailsToCSV_button = "//div[@id='serviceTypeDetail']//div[text()='Export details to CSV']";

    // Line Item Fields
    public String search_lineItem = "//div[@id='serviceTypeDetail']//input[@placeholder='Search...']";
    public String pageNumber_lineItem = "//span[text()='Page ']//input[@type='text']";
    public String invoiceID_lineItem = "//tr[@onmouseup]//td[3]";
    public String invoiceDate_lineItem = "//tr[@onmouseup]//td[4]";
    public String paymentDate_lineItem = "//tr[@onmouseup]//td[12]";
    public String serviceType_lineItem = "//tr[@onmouseup]//td[5]";
    public String totalCollected_Customer = "//tr[@onmouseup]//td[8]";
    public String tax_Customer = "//tr[@onmouseup]//td[10]";
    public String customerID_lineItem = "//tr[@onmouseup]//td[1]";
    public String customerName_lineItem = "//tr[@onmouseup]//td[2]";

    public Map<String, String> filterTypes_BST = new HashMap<String, String>();

    public BillingByServiceTypeTab() {
    }

    //Author: Aditya
    public String filterTypes_BST(String key) {

        // filter fields
        filterTypes_BST.put("invoice_bbst", "//label[text()='Invoice']//following-sibling::div//input");
        filterTypes_BST.put("serviceType_bbst", "//label[text()='Service Type']//following-sibling::div//input");
        filterTypes_BST.put("customerSource_bbst", "//label[text()='Cust Source']//following-sibling::div//input");
        filterTypes_BST.put("inclCollections", "//label[text()='Incl. Collections']//following-sibling::div//input");
        filterTypes_BST.put("subSource_bbst", "//label[text()='Sub Source']//following-sibling::div//input");
        filterTypes_BST.put("regions_bbst", "//label[text()='Regions']//following-sibling::div//input");
        filterTypes_BST.put("divisions_bbst", "//label[text()='Divisions']//following-sibling::div//input");
        filterTypes_BST.put("offices_bbst", "//label[text()='Offices']//following-sibling::div//input");
        filterTypes_BST.put("includeFlags_bbst", "//label[text()='Incl. Flags']//following-sibling::div//input");
        filterTypes_BST.put("excludeFlags_bbst", "//label[text()='Excl. Flags']//following-sibling::div//input");
        filterTypes_BST.put("scheduledBy_bbst", "//label[text()='Scheduler']//following-sibling::div//input");
        filterTypes_BST.put("soldByTeam_bbst", "//label[text()='Sale Teams']//following-sibling::div//input");
        filterTypes_BST.put("soldbySalesRep_bbst", "//label[text()='Sales Rep']//following-sibling::div//select");

        // bbst single group report fields
        filterTypes_BST.put("description_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Description']");
        filterTypes_BST.put("services_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Services']");
        filterTypes_BST.put("lineItemQuantity_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Line Item Quantity']");
        filterTypes_BST.put("totalCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Total Collected']");
        filterTypes_BST.put("taxCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Tax Collected']");
        filterTypes_BST.put("taxInvoiced_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Tax Invoiced']");
        filterTypes_BST.put("paymentsCollected_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Payments Collected']");
        filterTypes_BST.put("billedServices_bbstReport", "//table[@id='revenueByServiceType']//div[text()='Billed Services']");

        // bbst multi group report fields
        filterTypes_BST.put("description_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Description']");
        filterTypes_BST.put("services_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Services']");
        filterTypes_BST.put("lineItemQuantity_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Line Item Quantity']");
        filterTypes_BST.put("totalCollected_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Total Collected']");
        filterTypes_BST.put("taxCollected_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Tax Collected']");
        filterTypes_BST.put("taxInvoiced_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Tax Invoiced']");
        filterTypes_BST.put("paymentsCollected_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Payments Collected']");
        filterTypes_BST.put("billedServices_bbstReport_MultiGroup", "//table[@id='revenueByServiceType']//th[text()='Billed Services']");

        // individual line item fields
        filterTypes_BST.put("customerID_lineItem", "//th[@data-orderby='customerID']");
        filterTypes_BST.put("customerName_lineItem", "//th[@data-orderby='customerName']");
        filterTypes_BST.put("invoiceID_lineItem", "//th[@data-orderby='ticketID']");
        filterTypes_BST.put("date_lineItem", "//th[@data-orderby='ticketDate']");
        filterTypes_BST.put("serviceDescription_lineItem", "//th[@data-orderby='serviceDescription']");
        filterTypes_BST.put("billingFrequency_lineItem", "//th[@data-orderby='billingFrequency']");
        filterTypes_BST.put("itemsQuantity_lineItem", "//th[@data-orderby='itemsQuantity']");
        filterTypes_BST.put("totalCollected_lineItem", "//th[@data-orderby='totalCollected']");
        filterTypes_BST.put("taxCollected_lineItem", "//th[@data-orderby='taxCollected']");
        filterTypes_BST.put("tax_lineItem", "//th[@data-orderby='tax']");
        filterTypes_BST.put("appliedPaymentsBeforeTax_lineItem", "//th[@data-orderby='revenueCollected']");
        filterTypes_BST.put("dateCollected_lineItem", "//th[@data-orderby='dateCollected']");
        filterTypes_BST.put("billedServices_lineItem", "//th[@data-orderby='revenue']");

        // Line Item navigation buttons
        filterTypes_BST.put("first_page", "//div[@id='serviceTypeDetail']//span[text()='First']");
        filterTypes_BST.put("previous_page", "//div[@id='serviceTypeDetail']//span[text()='Previous']");
        filterTypes_BST.put("next_page", "//div[@id='serviceTypeDetail']//span[text()='Next']");
        filterTypes_BST.put("last_page", "//div[@id='serviceTypeDetail']//span[text()='Last']");

        return filterTypes_BST.get(key);
    }

    //Author: Aditya
    public void setGroupFilter(String group, String groupByType) {
        officeObjs = new OfficeObjects();
        Utilities.waitUntileElementIsVisible(group);
        Utilities.selectValueFromDropDownByValue(group, groupByType);
    }

    //Author: Aditya
    public void editCustomerPerFilters() throws InterruptedException, IOException {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_header = new CustomerViewDialog_Header();
        header = new Header();
        header.searchCustomerInOrder("1");
        customerViewDialog_header.navigateTo(customerViewDialog_header.infoTabInDialog);
        createNewCustomer.addAdditionalProperties();
    }

    //Author: Aditya
    public void editCustomer_NoPaper_Commercial() throws InterruptedException, IOException {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_header = new CustomerViewDialog_Header();
        header = new Header();
        header.clickAccessHistory();
        header.searchCustomerInOrder("1");
        createNewCustomer.editCustomer_NoPaper_CommercialProperty();
    }

    public String getCustomerName_CustomerCard_InfoTab() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        customerViewDialog_header = new CustomerViewDialog_Header();
        String customerName = createNewCustomer.getCustomerName("1");
        customerViewDialog_header.Click_X_Button();
        return customerName;
    }

    public String get(String needIdentifier) {
        return Utilities.getElementTextValue(needIdentifier, Utilities.ElementType.XPath);
    }

    public String getBilledServices_MultiGroupReport(String needCustomerID) {
        return Utilities.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[4]", Utilities.ElementType.XPath);
    }

    public String getTaxRate_MultiGroupReport(String needCustomerID) {
        return Utilities.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[6]", Utilities.ElementType.XPath);
    }

    public String getBilledTaxValue_Customer() {
        return Utilities.getElementTextValue(tax_Customer, Utilities.ElementType.XPath).replace("\n", "");
    }

    public String getBilledServiceValue_Customer() {
        return Utilities.getElementTextValue(billedServices_Customer, Utilities.ElementType.XPath).replace("\n", "");
    }

    public String getAttributeValue(String needIdentifier, String needAttribute) {
        return Utilities.getAttributeValue(needIdentifier, needAttribute);
    }

    public void searchNewCustomer(String searchBox, String searchCustomerParameter) {
        Utilities.waitUntileElementIsVisible(searchBox);
        FindElement.elementByAttribute(searchBox, FindElement.InputType.XPath).sendKeys(searchCustomerParameter);
    }

    public void click(String needButton) {
        Utilities.waitUntileElementIsVisible(needButton);
        Utilities.scrollToElementJS(needButton);
        Utilities.clickElement(needButton, Utilities.ElementType.XPath);
    }

    public void clickAdvancedFilters() {
        Utilities.clickAdvancedFilters();
    }

    //Author: Aditya
    public void setType(String key, String type) {
        FindElement.elementByAttribute(filterTypes_BST(key), FindElement.InputType.XPath).sendKeys(type);
        Utilities.clickElement("//span[text()='" + type + "']", Utilities.ElementType.XPath);
    }

    public void setIncludeCollections(String includeCollections) {
        FindElement.elementByAttribute(filterTypes_BST("inclCollections"), FindElement.InputType.XPath).sendKeys(includeCollections);
    }

    public void setSubscriptionSource(String subscriptionSource) {
        FindElement.elementByAttribute(filterTypes_BST("subSource_bbst"), FindElement.InputType.XPath).sendKeys(subscriptionSource);
    }

    public void setRegions(String regions) {
        FindElement.elementByAttribute(filterTypes_BST("regions_bbst"), FindElement.InputType.XPath).sendKeys(regions);
    }

    public void setOffice_bbst(String office) {
        FindElement.elementByAttribute(filterTypes_BST("offices_bbst"), FindElement.InputType.XPath).sendKeys(office);
    }

    public void setExcludeFlags(String excludeFlags) {
        FindElement.elementByAttribute(filterTypes_BST("excludeFlags_bbst"), FindElement.InputType.XPath).sendKeys(excludeFlags);
    }

    public void setScheduledBy(String scheduledBy) {
        FindElement.elementByAttribute(filterTypes_BST("scheduledBy_bbst"), FindElement.InputType.XPath).sendKeys(scheduledBy);
    }

    public void setSoldBy(String soldBy) {
        FindElement.elementByAttribute(filterTypes_BST("soldByTeam_bbst"), FindElement.InputType.XPath).sendKeys(soldBy);
    }

    public void setSoldbySalesRep(String soldbySalesRep) {
        FindElement.elementByAttribute(filterTypes_BST("soldbySalesRep_bbst"), FindElement.InputType.XPath).sendKeys(soldbySalesRep);
    }

    public void setSoldDateRange(String dateRange) {
        Utilities.waitUntileElementIsVisible(soldDateRange);
        FindElement.elementByAttribute(soldDateRange, FindElement.InputType.XPath).sendKeys(dateRange);
    }

    public void clickDescription_reportDetails() throws Exception {
        Utilities.scrollToElementJS("//tr[@detailvalues]//td[text()='" + getCustomerName_CustomerCard_InfoTab() + "']");
        Utilities.clickElement("//tr[@detailvalues]//td[text()='" + getCustomerName_CustomerCard_InfoTab() + "']", Utilities.ElementType.XPath);
    }

    //Author: Aditya
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

    public void set(String needTag, String needType) {
        Utilities.waitUntileElementIsVisible(needTag);
        Utilities.selectValueFromDropDownByValue(needTag, needType);
    }

    public void setBalance_bbst(String balance) {
        Utilities.waitUntileElementIsVisible(balance_bbst);
        FindElement.elementByAttribute(balance_bbst, FindElement.InputType.XPath).sendKeys(balance);
    }

    //Author: Aditya
    public void setDateRange(String type, String dateRange) {
        Utilities.waitUntileElementIsVisible(type);
        Utilities.clickElement(type, Utilities.ElementType.XPath);
        Utilities.waitUntileElementIsVisible("//div[contains(@style,'block')]//li[text()='" + dateRange + "']");
        Utilities.clickElement("//div[contains(@style,'block')]//li[text()='" + dateRange + "']", Utilities.ElementType.XPath);
    }

    //Author: Aditya
    public void setSalesRep(String salesRepTab, String salesRepType) {
        Utilities.waitUntileElementIsVisible(filterTypes_BST(salesRepTab));
        Utilities.selectValueFromDropDownByValue(filterTypes_BST(salesRepTab), salesRepType);
    }

}
