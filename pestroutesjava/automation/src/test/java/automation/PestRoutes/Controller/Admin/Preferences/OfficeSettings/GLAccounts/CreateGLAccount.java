package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.GLAccounts;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.GLAccounts.GLAccountCreation;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateGLAccount extends BaseClass {

    Header header;
    AdminMainPage adminMainPage;
    GLAccountCreation glAccountCreation = new GLAccountCreation();

    public String glAccountNumber = glAccountCreation.randomGLAccountNumber;
    public String title = Utilities.generateRandomString(5);
    public String description = Utilities.generateRandomString(5);
    List list = new ArrayList<String>();

    @Test
    public void createGLAccounts() {
    }

    @Then("I create a GL Account")
    public void createGLAccount() {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.NavigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        glAccountCreation.navigateToGLAccount();
        glAccountCreation.clickCreateGLAccount();
        glAccountCreation.setGLAccountNumber(glAccountNumber);
        glAccountCreation.setGLAccountTitle(title);
        glAccountCreation.setGLAccountDescription(description);
        glAccountCreation.clickSaveButton();
    }

    @When("I search for the GL Account")
    public void searchGLAccount() {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.NavigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        glAccountCreation.navigateToGLAccount();
        glAccountCreation.searchGLAccount();
    }

    @Then("I validate the GL Account")
    public void validateGLAccountCreated() {
        result(glAccountCreation.getCorrectedGLAccountNumber(), glAccountCreation.getGLAccountNumber(), "validating GL Number", "Validating GL Account");
        result(title, glAccountCreation.getGLTitle(), "validating GL Title", "Validating GL Account");
        result(description, glAccountCreation.getGLDescription(), "validating GL Description", "Validating GL Account");
    }

    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }

}
