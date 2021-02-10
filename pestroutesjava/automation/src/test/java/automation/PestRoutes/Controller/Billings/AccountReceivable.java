package automation.PestRoutes.Controller.Billings;
import automation.PestRoutes.PageObject.Billing.BillingModule.AccountReceivablePage;
import automation.PestRoutes.PageObject.Billing.BillingModule.BillingModule;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.PageObject.Header;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Login.SignIn;

public class AccountReceivable extends BaseClass {
    BillingModule billingModule;
    AccountReceivablePage accountReceivable = new AccountReceivablePage();
    Header header;
    SignIn signin;

    @Test
    public void test() throws Exception {
        signin = new SignIn();
        signin.login();
        navigateToAccountReceivablePage();
        validateAllFieldsEnabled();
    }

    @And("I navigate to account receivable under Billings")
    public void navigateToAccountReceivablePage(){
        billingModule = new BillingModule();
        header = new Header();
        header.navigateTo(header.billingTab);
        billingModule.navigate(billingModule.accountsReceivable);
    }
    @Then("I validate if all fields are displaying and are enabled")
    public void validateAllFieldsEnabled(){

        String[] fields = {accountReceivable.asOfDateInputField, accountReceivable.accountStatusDropdown, accountReceivable.autoPayDropdown,
        accountReceivable.statusDropdown, accountReceivable.propertyDropdown, accountReceivable.balanceInputField, accountReceivable.balanceAgeDropdown,
        accountReceivable.paymentDueDropdown, accountReceivable.daysOverDueDropdown, accountReceivable.prefPaperDropdown, accountReceivable.hasEmailDropdown,
        accountReceivable.maxMonthlyDropdown, accountReceivable.groupByDropdown, accountReceivable.inclCollectionDropdown,
        accountReceivable.inclFlagsDropdown, accountReceivable.exclFlagsDropdown};
        accountReceivable.click(accountReceivable.advanceFilterLink);
        AssertException.validateFieldEnabled(fields);
    }
}
