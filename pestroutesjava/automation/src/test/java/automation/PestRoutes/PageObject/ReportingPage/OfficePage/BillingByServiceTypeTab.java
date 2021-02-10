package automation.PestRoutes.PageObject.ReportingPage.OfficePage;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Reporting.Office.OfficeObjects;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import java.util.*;

public class BillingByServiceTypeTab {

    Header header;
    ReportingMainPage reportingMainPage;
    OfficeObjects officeObjs;
    CreateNewCustomer createNewCustomer;

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

    Map<String, String> filter_Types = new HashMap<String, String>();
    public void filterTypes(){
        filter_Types.put("invoice_bbst", "//input[@type='text' and @id = 's2id_autogen1']");
        filter_Types.put("serviceType_bbst", "//input[@type='text' and @id = 's2id_autogen2']");
        filter_Types.put("customerSource_bbst", "//input[@type='text' and @id = 's2id_autogen3']");
        filter_Types.put("inclCollections", "//input[@type='text' and @id = 's2id_autogen4']");
        filter_Types.put("subSource_bbst", "//input[@type='text' and @id = 's2id_autogen5']");
        filter_Types.put("regions_bbst", "//input[@type='text' and @id = 's2id_autogen6']");
        filter_Types.put("divisions_bbst", "//input[@type='text' and @id = 's2id_autogen7']");
        filter_Types.put("offices_bbst", "//input[@type='text' and @id = 's2id_autogen8']");
        filter_Types.put("includeFlags_bbst", "//input[@type='text' and @id = 's2id_autogen9']");
        filter_Types.put("excludeFlags_bbst", "//input[@type='text' and @id = 's2id_autogen10']");
        filter_Types.put("scheduledBy_bbst", "//input[@type='text' and @id = 's2id_autogen11']");
        filter_Types.put("soldByTeam_bbst", "//input[@type='text' and @id = 's2id_autogen12']");
        filter_Types.put("soldbySalesRep_bbst", "//input[@type='text' and @id = 's2id_autogen13']");
    }
/*
    public String invoice_bbst = "//input[@type='text' and @id = 's2id_autogen1']";
    public String serviceType_bbst = "//input[@type='text' and @id = 's2id_autogen2']";
    public String customerSource_bbst = "//input[@type='text' and @id = 's2id_autogen3']";
    public String inclCollections = "//input[@type='text' and @id = 's2id_autogen4']";
    public String subSource_bbst = "//input[@type='text' and @id = 's2id_autogen5']";
    public String regions_bbst = "//input[@type='text' and @id = 's2id_autogen6']";
    public String divisions_bbst = "//input[@type='text' and @id = 's2id_autogen7']";
    public String offices_bbst = "//input[@type='text' and @id = 's2id_autogen8']";
    public String includeFlags_bbst = "//input[@type='text' and @id = 's2id_autogen9']";
    public String excludeFlags_bbst = "//input[@type='text' and @id = 's2id_autogen10']";
    public String scheduledBy_bbst = "//input[@type='text' and @id = 's2id_autogen11']";
    public String soldByTeam_bbst = "//input[@type='text' and @id = 's2id_autogen12']";
    public String soldbySalesRep_bbst = "//input[@type='text' and @id = 's2id_autogen13']";
*/
    /*public static String filterType(String type) {
        String str = "//input[@type='text' and @id = 's2id_autogen']";


        String ch = null;
        StringBuilder sb = new StringBuilder();

        switch (type) {
            case "invoice":
                ch = "1";
                break;
            case "serviceType":
                ch = "2";
                break;
            case "customerSource":
                ch = "3";
                break;
            case "inclCollections":
                ch = "4";
                break;
            case "subSource":
                ch = "5";
                break;
            case "regions":
                ch = "6";
                break;
            case "divisions":
                ch = "7";
                break;
            case "offices":
                ch = "8";
                break;
            case "includeFlags":
                ch = "9";
                break;
            case "excludeFlags":
                ch = "10";
                break;
            case "scheduledBy":
                ch = "11";
                break;
            case "soldByTeam":
                ch = "12";
                break;
            case "soldbySalesRep":
                ch = "13";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return str.substring(0, str.length() - 2) + sb.append(ch) + str.substring(str.length() - 2);
    }*/

    public void mainGroupBy(){
        officeObjs = new OfficeObjects();
        Utilities.waitUntileElementIsVisible(groupBy);
        Utilities.selectValueFromDropDownByValue(groupBy,"Customer Name");
        officeObjs.navigateTo(officeObjs.billingByServiceType);
    }

    public void navigateToBillingByServiceTypePage(){
        header = new Header();
        reportingMainPage = new ReportingMainPage();
        officeObjs = new OfficeObjects();
        header.navigateTo(header.reportingTab);
        reportingMainPage.navigateTo(reportingMainPage.office);
        officeObjs.navigateTo(officeObjs.billingByServiceType);
    }

    public void searchNewCustomer() throws Exception {
        createNewCustomer = new CreateNewCustomer();
        Utilities.waitUntileElementIsVisible(refresh_bbst);
        Utilities.clickElement(refresh_bbst, Utilities.ElementType.XPath);
        String customerName = createNewCustomer.getCustomerName("1");
        createNewCustomer.closeCustomerCard();
        Utilities.waitUntileElementIsVisible(search_bbst);
        Utilities.clickElement(search_bbst, Utilities.ElementType.XPath);
        FindElement.elementByAttribute(search_bbst, FindElement.InputType.XPath).sendKeys(customerName);
    }
       /*String invoice = null;
        String serviceType = null;
        String customerSource = null;
        String inclCollections = null;
        String subSource = null;
        String regions = null;
        String divisions = null;*/

     /* if (type == invoice) {
            ch = "1";
        }
        if (type == serviceType) {
            ch = "2";
        }
        if (type == customerSource) {
            ch = "3";
        }
        if (type == inclCollections) {
            ch = "4";
        }
        if (type == subSource) {
            ch = "5";
        }
        if (type == regions) {
            ch = "6";
        }
        if (type == divisions) {
            ch = "6";
        }*/
}
