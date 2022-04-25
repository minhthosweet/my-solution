package automation.PestRoutes.Controller.CustomerPortal;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalProductsTabPage;
import automation.PestRoutes.PageObject.CustomerPortal.CustomerPortalSummaryTabPage;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;
import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class TestCustomerPortalProductsTab {

    SoftAssert softAssert = new SoftAssert();
    CustomerPortalSummaryTabPage userOnCustomerPortalSummaryTab = new CustomerPortalSummaryTabPage();
    CustomerPortalProductsTabPage customerPortalProductsTabPage = new CustomerPortalProductsTabPage();
    CreateNewCustomer testCustomer = new CreateNewCustomer();

    @Then("I Verify The Products Table Header Background Is Not White")
    public void testProductsTableHeaderBackgroundIsNotWhite() {
        customerPortalProductsTabPage = userOnCustomerPortalSummaryTab.goToProductsTab();
        String actualTableHeaderBackgroundColor = customerPortalProductsTabPage.getTableHeaderBackgroundColor();
        String notExpectedWhiteBackgroundColor = "#FFFFFF";
        softAssert.assertNotEquals(actualTableHeaderBackgroundColor, notExpectedWhiteBackgroundColor,
                "\n The Table Header Background Color Is White: #FFFFFF \n");
        closeTab();
        switchToOldWindowOpened();
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}