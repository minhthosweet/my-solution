package automation.PestRoutes.Controller.Leads;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class TestCustomProduction {

    SoftAssert softAssert = new SoftAssert();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    LeadsPage userOnLeadsTab = new LeadsPage();
    CreateNewCustomer testCustomer = new CreateNewCustomer();
    String recurringServiceAmount;

    @Given("I Create A Customer With A Lead")
    public void automateCreatingCustomerWithLead() throws Exception {
        testCustomer.createCustomerWithBasicInfo();
        userOnLeadsTab = sameUser.goToLeadsTab();
        userOnLeadsTab.clickNewQuote();
    }

    @When("I Select {string} From Service Info Section")
    public void automateSelectFromServiceInfoSection(String frequency) {
        userOnLeadsTab.selectServiceType("Automation Renewal");
        userOnLeadsTab.selectFrequency(frequency);
        userOnLeadsTab.selectBilling("Monthly");
    }

    @And("I Enter A Recurring Service Type Amount {string}")
    public void automateRecurringServiceTypeAmount(String amount)  {
        userOnLeadsTab.typeRecurringServiceTypeAmount(amount);
        recurringServiceAmount = userOnLeadsTab.getRecurringServiceTypeAmount();
    }

    @Then("I See The Correct Custom Production Amount When Multiplying {string} Times The Recurring Service Type Amount")
    public void testCustomProductionAmountIsCorrect(String number) {
        double actualCustomProductionAmount = Double.parseDouble(userOnLeadsTab.getRecurringCustomProduction());
        double expectedCustomProductionAmount = Double.parseDouble(recurringServiceAmount) * Double.parseDouble(number);
        softAssert.assertEquals(actualCustomProductionAmount, expectedCustomProductionAmount,
                "\n Actual: " + actualCustomProductionAmount +
                        "\n Expected: " + expectedCustomProductionAmount +
                        "\n The Actual & Expected Custom Production Amounts Do Not Match");
        testCustomer.removeCustomer();
        softAssert.assertAll();
    }
}
