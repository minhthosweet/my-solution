package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.GLAccounts;

import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.GLAccounts.GLAccountCreation;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateGLAccount extends AppData {

    Header header;
    AdminMainPage adminMainPage;
    GLAccountCreation glAccountCreation = new GLAccountCreation();
    Service service;

    public String glAccountNumber = glAccountCreation.randomGLAccountNumber;
    public String title = Utilities.generateRandomString(5);
    List list = new ArrayList<String>();

    @Test
    public void createGLAccounts() {
    }

    @Then("I create a GL Account with description {string}")
    public void createGLAccount(String description) {
        glAccountCreation.clickCreateGLAccount();
        glAccountCreation.setGLAccountNumber(glAccountNumber);
        glAccountCreation.setGLAccountTitle(title);
        glAccountCreation.setGLAccountDescription(description);
        glAccountCreation.clickSaveButton();
    }

    @When("I navigate to GLAccounts")
    public void navigateToGLAccount() {
        header = new Header();
        adminMainPage = new AdminMainPage();
        header.navigateTo(header.adminTab);
        adminMainPage.navigateTo(adminMainPage.preferences);
        glAccountCreation.navigateToGLAccount();
    }

    @Then("I check if GLAccount is available {string} and validate the GL Account")
    public void searchGLAccount(String description) {
        glAccountCreation.searchGLAccountByDescription(description);
        try {
            WebElement elm = FindElement.elementByAttribute("//span[text()='" + description + "']", FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                System.out.println("GL Account is visible");
            }

        } catch (Exception e) {
            System.out.println("GL Account is not visible");
            createGLAccount(description);
            validateGLAccountCreated(description);
        }
    }

    @When("I search for the GL Account {string}")
    public void checkGLAccount(String description) {
        glAccountCreation.searchGLAccountByDescription(description);
    }

    @Then("I validate the GL Account with description {string}")
    public void validateGLAccountCreated(String description) {
        result(glAccountCreation.getCorrectedGLAccountNumber(), glAccountCreation.getGLAccountNumber(), "validating GL Number", "Validating GL Account");
        result(title, glAccountCreation.getGLTitle(), "validating GL Title", "Validating GL Account");
        result(description, glAccountCreation.getGLDescription(), "validating GL Description", "Validating GL Account");
    }

    @Then("I add GLAccount to service type with description {string}")
    public void addGLAccountToServiceType(String description) throws Exception {
        navigateToGLAccount();
        searchGLAccount(description);
        service = new Service();
        String glAccNo = glAccountCreation.getGLAccountNumber();
        service.navigateToServiceType();
        service.searchService(getData("serviceDescription", generalData));
        glAccountCreation.editService(getData("serviceDescription", generalData));
        glAccountCreation.clickGLAccountOnServiceType(getData("serviceDescription", generalData));
        glAccountCreation.selectGLAccountFromDropDown(getData("serviceDescription", generalData), glAccNo);
        glAccountCreation.saveService();

    }

    @Then("I validate if GLAccount is added to the service type")
    public void validateGLAccountInServiceType() throws Exception {
        service = new Service();
        String glAccNo = glAccountCreation.getGLAccountNumber();
        service.navigateToServiceType();
        service.searchService(getData("serviceDescription", generalData));
        result(glAccNo, glAccountCreation.getGLAccountNumberInServiceType(), "validating GL Number in service type", "Validating GL Account");
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
