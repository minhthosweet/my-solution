package automation.PestRoutes.Controller.CustomerCreation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateNewCustomer extends AppData {
    static ExtentTest test;
    CreateCustomerDialog customer;
    CustomerViewDialog_Header dialog;
    CustomerViewDialog_OverviewTab overview;
    Header header;
    CustomerViewDialog_Header customerDialogHeader;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;
    List list = new ArrayList<String>();

    public String fName = Utilities.generateRandomString(7);
    public String lName = Utilities.generateRandomString(6);
    String expectedAlert = "Required: You must fill in the customer's last name or company name!";
    public String streetAddress = "4500 W Eldorado Pkwy STE 3200";
    String city = "McKinney";
    String zipcode = "75070";
    public String email = "test@gmail.com";

    @Test
    public void createCustomer() throws Exception {
        createCustomerWithOutRequiredField();
        validateRequiredFieldError();
        createCustomerWithAddress();
        validateCreatedCustomerNameAndAddress();
        validateIfFailureExist();

    }

    @When("I create customer without required last name field")
    public void createCustomerWithOutRequiredField() {
        String fName = Utilities.generateRandomString(7);
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.selectUnit("Multi Unit");
        dialog.clickSaveButton();
    }

    @Then("I validate alert")
    public void validateRequiredFieldError() {
        String alert = Utilities.getAlertText();
        Utilities.acceptAlert();
        result(expectedAlert, alert, "required field while creating customer ", "Customer creation");
    }

    @When("I create customer with first name, last name and address")
    public void createCustomerWithAddress() throws Exception {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        dialog.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

   @Then("^I create customer with address and ZipCode and I verify Main Tax, State Tax, City Tax, County Tax, Custom Tax, District1 Tax, District2 Tax" +
           ", District3 Tax, District4 Tax, District5 Tax Rates \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" " +
           "and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifyCustomerWithAddressTaxRate(String needStreetAddress, String needZipCode, String needMainTax, String needStateTax,
                String needCityTax, String needCountyTax, String needCustomTax, String needDistrict1Tax, String needDistrict2Tax,
                String needDistrict3Tax, String needDistrict4Tax, String needDistrict5Tax) throws Exception {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setAddress(needStreetAddress);
        customer.setZipCode(needZipCode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        dialog.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
        dialog.navigateTo(dialog.infoTabInDialog);
        customer.clickOverrideTaxCheckBox();
        result(customer.getTaxRate(customer.mainTaxPercentage), needMainTax, "Entered ZipCode" , "Tax Rate Validation");
        result(customer.getTaxRate(customer.stateTaxPercentage), needStateTax, "Entered ZipCode" , "StateTax Rate Validation");
        result(customer.getTaxRate(customer.cityTaxPercentage), needCityTax, "Entered ZipCode" , "CityTax Rate Validation");
        result(customer.getTaxRate(customer.countyTaxPercentage), needCountyTax, "Entered ZipCode" , "CountyTax Rate Validation");
        result(customer.getTaxRate(customer.customTaxPercentage), needCustomTax, "Entered ZipCode" , "CustomTax Rate Validation");
        result(customer.getTaxRate(customer.district1TaxPercentage), needDistrict1Tax, "Entered ZipCode" , "District1 Tax Rate Validation");
        result(customer.getTaxRate(customer.district2TaxPercentage), needDistrict2Tax, "Entered ZipCode" , "District2 Tax Rate Validation");
        result(customer.getTaxRate(customer.district3TaxPercentage), needDistrict3Tax, "Entered ZipCode" , "District3 Tax Rate Validation");
        result(customer.getTaxRate(customer.district4TaxPercentage), needDistrict4Tax, "Entered ZipCode" , "District4 Tax Rate Validation");
        result(customer.getTaxRate(customer.district5TaxPercentage), needDistrict5Tax, "Entered ZipCode" , "District5 Tax Rate Validation");
        customer.clickOverrideTaxCheckBox();
    }

    @When("I create customer with first name, last name, address and generic flag {string} and {string}")
    public void createCustomerWithGenericFlag(String needFlagName, String needSource) throws Exception {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.selectSource(needSource);
        customer.selectGenericFlag(needFlagName);
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        dialog.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @And("I search customer")
    public void searchCustomer() throws Exception{
        header = new Header();
        header.searchCustomer( lName+ ", " + fName);
    }

    @When("I create customer with first name, last name, email and address")
    public void createCustomerWithEmail() throws Exception {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setEmailAddress(email);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        dialog.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @And("I add additional properties to the customer")
    public void addAdditionalProperties() throws IOException {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        customer.selectSource(getData("customerSource", generalData));
        customer.selectProperty(customer.commercialProperty);
        customer.clickPrefersPaperCheckBox();
        customer.selectDivision(getData("division", generalData));
        customer.selectGenericFlag(getData("flag", generalData));
        dialog.clickSaveButton();
    }

    @When("I create customer with first name and last name")
    public void createCustomerWithoutAddress() throws Exception {

        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        dialog.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @Given("I close customer card")
    public void closeCustomerCard() {
        dialog = new CustomerViewDialog_Header();
        dialog.clickCloseButton();
        dialog.discardChanges();
    }

    @When("I create customer with first name, last name, address, email and Structure")
    public void createCustomerWithStructure() throws Exception {
        dialog = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setEmailAddress(email);
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customer.selectUnit("Structures");
        dialog.clickSaveButton();
        alertCondition();
        dialog.saveAnyways();
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        result(fName, customerNameInHeader, "Created customer ", "Structure Validation");
        String id = overview.getCustomerIDFromHeader();
        String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
        addData("strutureUID", newId, generalData);
        AssertException.assertFailure(list);
    }

    @Then("I validate if customer name and address match in overview tab")
    public void validateCreatedCustomerNameAndAddress() {
        String expectedAddress = streetAddress + " " + city + ", TX " + zipcode;
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        String actualAddress = overview.getAddress();
        result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
        result(expectedAddress, actualAddress, "Created customer address", "Customer creation");
    }

    @Then("I validate if customer name match in overview tab")
    public void validateCreatedCustomerName() {
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
    }

    @And("I validate if there are errors exist in the list")
    public void validateIfFailureExist() {
        AssertException.assertFailure(list);
    }

    @And("I navigate to Subscription Tab")
    public void navigateToSubscriptionTab() throws InterruptedException {
        customerDialogHeader = new CustomerViewDialog_Header();
        Utilities.waitUntileElementIsVisible("//li[@name = '" + customerDialogHeader.subscriptionTabInDialog + "']");
        customerDialogHeader.navigateTo(customerDialogHeader.subscriptionTabInDialog);
    }

    @SuppressWarnings("unchecked")
    private void result(String expected, String actual, String stepName, String testName) {
        if (AssertException.result(expected, actual, stepName).size() > 0) {
            list.add(AssertException.result(expected, actual, stepName));
        }
        Reporter.status(stepName, expected, actual, testName);
    }

    private void alertCondition() throws Exception {
        int i = 0;
        while (i++ < 5) {
            try {
                Alert alert = Utilities.alertPopUp();
                String actionAlert = Utilities.getAlertText();
                String expected = "Action Required!";
                if (actionAlert.contains(expected)) {
                    alert.accept();
                    Utilities.clickElement("//div[text()='Save Anyways']", ElementType.XPath);
                }
                break;
            } catch (NoAlertPresentException e) {
                Thread.sleep(500);
                continue;
            }
        }
    }

    private void captureUserIdAndFullName() throws Exception {
        String id = overview.getCustomerIDFromHeader();
        String newId = id.replaceAll("[^a-zA-Z0-9]+", "");
        addData("userID", newId, generalData);
        addData("customerName", fName + " " + lName, generalData);
    }

    public String getCustomerName(String previousCustomerNumber) throws Exception {
        header = new Header();
        header.searchCustomerInOrder(previousCustomerNumber);
        dialog = new CustomerViewDialog_Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        dialog.navigateTo(dialog.infoTabInDialog);
        return customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
    }

    public String getCustomerName(){
        overview = new CustomerViewDialog_OverviewTab();
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        return customerNameInHeader;

    }

}
