package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Reporting.Office.OfficeObjects;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
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
        Deprecated.waitVisible(group);
        Deprecated.selectByText(group, groupByType);
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
        header.searchCustomerWithName(CreateNewCustomer.customerName);
//        header.clickAccessHistory();
//        header.searchCustomerInOrder("1");
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
        return Deprecated.getElementTextValue(needIdentifier);
    }

    public String getBilledServices_MultiGroupReport(String needCustomerID) {
        return Deprecated.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[4]");
    }

    public String getTaxRate_MultiGroupReport(String needCustomerID) {
        return Deprecated.getElementTextValue("//td[text()='" + needCustomerID + "']/parent::tr[@detailvalues]//td[6]");
    }

    public String getBilledTaxValue_Customer() {
        return Deprecated.getElementTextValue(tax_Customer).replace("\n", "");
    }

    public String getBilledServiceValue_Customer() {
        return Deprecated.getElementTextValue(billedServices_Customer).replace("\n", "");
    }

    public String getAttributeValue(String needIdentifier, String needAttribute) {
        return Deprecated.getAttribute(needIdentifier, needAttribute);
    }

    public void searchNewCustomer(String searchBox, String searchCustomerParameter) {
        Deprecated.waitVisible(searchBox);
        Deprecated.locate(searchBox).sendKeys(searchCustomerParameter);
    }

    public void click(String needButton) {
        Utilities.waitClickable(By.xpath(needButton), 10);
        Deprecated.scrollToElementJS(needButton);
        Deprecated.jsClickElement(needButton);
    }

    public void clickAdvancedFilters() {
        waitClickable(By.xpath("//div[@id = 'advancedFilterToggleButton']"), 10);
        Deprecated.clickElement("//div[@id = 'advancedFilterToggleButton']");
    }

    //Author: Aditya
    public void setType(String key, String type) {
        Deprecated.locate(filterTypes_BST(key)).sendKeys(type);
        Deprecated.clickElement("//span[text()='" + type + "']");
    }

    public void setIncludeCollections(String includeCollections) {
        Deprecated.locate(filterTypes_BST("inclCollections")).sendKeys(includeCollections);
    }

    public void setSubscriptionSource(String subscriptionSource) {
        Deprecated.locate(filterTypes_BST("subSource_bbst")).sendKeys(subscriptionSource);
    }

    public void setRegions(String regions) {
        Deprecated.locate(filterTypes_BST("regions_bbst")).sendKeys(regions);
    }

    public void setOffice_bbst(String office) {
        Deprecated.locate(filterTypes_BST("offices_bbst")).sendKeys(office);
    }

    public void setExcludeFlags(String excludeFlags) {
        Deprecated.locate(filterTypes_BST("excludeFlags_bbst")).sendKeys(excludeFlags);
    }

    public void setScheduledBy(String scheduledBy) {
        Deprecated.locate(filterTypes_BST("scheduledBy_bbst")).sendKeys(scheduledBy);
    }

    public void setSoldBy(String soldBy) {
        Deprecated.locate(filterTypes_BST("soldByTeam_bbst")).sendKeys(soldBy);
    }

    public void setSoldbySalesRep(String soldbySalesRep) {
        Deprecated.locate(filterTypes_BST("soldbySalesRep_bbst")).sendKeys(soldbySalesRep);
    }

    public void setSoldDateRange(String dateRange) {
        Deprecated.waitVisible(soldDateRange);
        Deprecated.locate(soldDateRange).sendKeys(dateRange);
    }

    public void clickDescription_reportDetails(String customerName) {
        delay(3000);
        Utilities.waitClickable(By.xpath("//tr[@detailvalues]//td[text()='" + customerName + "']"), 10);
        Deprecated.scrollToElementJS("//tr[@detailvalues]//td[text()='" + customerName + "']");
        Deprecated.jsClickElement("//tr[@detailvalues]//td[text()='" + customerName + "']");
    }

    //Author: Aditya
    public void customerDetails(String customerName) throws Exception {
        String customerName_detailed = "//tr[@onmouseup]//td[text()='" + customerName + "']";
        try {
            WebElement elm = Deprecated.locate(customerName_detailed);
            if (elm.isDisplayed()) {
                Deprecated.scrollToElementJS(customerName_detailed);
                Deprecated.clickElement(customerName_detailed);
            }
        } catch (Exception e) {
            System.out.println("Customer Card doesn't open");
        }
    }

    public void set(String needTag, String needType) {
        Deprecated.waitVisible(needTag);
        Deprecated.selectByText(needTag, needType);
    }

    public void setBalance_bbst(String balance) {
        Deprecated.waitVisible(balance_bbst);
        Deprecated.locate(balance_bbst).sendKeys(balance);
    }

    //Author: Aditya
    public void setDateRange(String type, String dateRange) {
        Deprecated.waitVisible(type);
        Deprecated.clickElement(type);
        Deprecated.waitVisible("//div[contains(@style,'block')]//li[text()='" + dateRange + "']");
        Deprecated.clickElement("//div[contains(@style,'block')]//li[text()='" + dateRange + "']");
    }

    //Author: Aditya
    public void setSalesRep(String salesRepTab, String salesRepType) {
        Deprecated.waitVisible(filterTypes_BST(salesRepTab));
        Deprecated.selectByText(filterTypes_BST(salesRepTab), salesRepType);
    }

}
