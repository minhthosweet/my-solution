package automation.PestRoutes.Controller.Reporting.Office;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.OfficePage.CustomerRestorePageObjects;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.Utilities.Legacy;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static automation.PestRoutes.Utilities.Report.AssertException.result;

public class CustomerRestoreTests {
    DashboardPage dashboardPage = new DashboardPage();
    Header header = new Header();
    CreateNewCustomer createCustomer;
    CustomerViewDialog_Header customerCardHeader;
    CustomerRestorePageObjects customerRestorePgObjs;
    ReportingMainPage reportingMainPage;

    //Strings and Constants
    //------------------------------------------------------
    static final String PAGE_HEADER = "Customer Restore";
    static String customer_fullName = null;

    @And("I restore the removed customer")
    public void restoreCustomer() {
        createCustomer = new CreateNewCustomer();
        customer_fullName = createCustomer.customerName;
        System.out.println("******** Customer to be restored : " + customer_fullName);

        reportingMainPage = dashboardPage.goToReportingComponent();
        customerRestorePgObjs = reportingMainPage.clickCustomerRestore();

        if(Legacy.isVisible(customerRestorePgObjs.btnRefreshStr))
        {
            //Utilities.waitUntileElementIsVisible(customerRestorePgObjs.colCustomerNameStr,10);
            Utilities.delay(5);
            customerRestorePgObjs.enterSearchValue(customer_fullName);
            customerRestorePgObjs.restoreCustomer(customer_fullName);
            customerRestorePgObjs.clickYesBtn();
        }
    }//restoreCustomer()

    @Then("I validate the Restored Customer loads successfully")
    public void validateRestoredCustomerLoads() {
        customerCardHeader = new CustomerViewDialog_Header();
        String loadedCustomerName = "";

        header.searchCustomerWithName(customer_fullName);

        if (Legacy.isVisible(customerCardHeader.overviewPageTitle)) {
            if (customerCardHeader.isCustomerLoaded(customer_fullName))
                loadedCustomerName = customer_fullName;
        }
        result(customer_fullName,loadedCustomerName,"Customer Restore Validation","Customer Restore Validation");
    }//validateRestoredCustomerLoads


}//CustomerRestoreTests
