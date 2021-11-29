package automation.PestRoutes.Controller.Leads;

import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DashboardPage;
import automation.PestRoutes.PageObject.Leads.LeadsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static automation.PestRoutes.Utilities.Utilities.generateRandomString;

public class TestCustomProduction {

    DashboardPage userOnDashboard = new DashboardPage();
    CreateCustomerDialog userCreateNewCustomer = new CreateCustomerDialog();
    CustomerViewDialog_Header sameUser = new CustomerViewDialog_Header();
    LeadsPage userOnLeadsTab = new LeadsPage();
    String recurringServiceAmount;

    @Given("I Create A Customer With A Lead")
    public void automateCreatingCustomerWithLead() {
        userOnDashboard.goToNewCustomerComponent();
        userCreateNewCustomer.typeFirstName(generateRandomString(3));
        userCreateNewCustomer.typeLastName(generateRandomString(4));
        userCreateNewCustomer.typeZipCode("75093");
        sameUser.clickCustomerSaveButton();
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
    public void automateRecurringServiceTypeAmount(String amount) throws InterruptedException {
        userOnLeadsTab.typeRecurringServiceTypeAmount(amount);
        recurringServiceAmount = userOnLeadsTab.getRecurringServiceTypeAmount();
    }

    @Then("I See The Correct Custom Production Amount When Multiplying {string} Times The Recurring Service Type Amount")
    public void testCustomProductionAmountIsCorrect(String number) throws InterruptedException {
        double actualCustomProductionAmount = Double.parseDouble(userOnLeadsTab.getRecurringCustomProduction());
        double expectedCustomProductionAmount = Double.parseDouble(recurringServiceAmount) * Double.parseDouble(number);
        Assert.assertEquals(actualCustomProductionAmount, expectedCustomProductionAmount,
                "The Actual & Expected Custom Production Amounts Do Not Match");
    }
}
