package automation.PestRoutes.Controller.CustomerCreation;

import java.io.IOException;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.PageObject.Search.SearchBox;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomerDialog;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static automation.PestRoutes.Utilities.AssertException.result;

public class CreateNewCustomer extends AppData {
    CreateCustomerDialog customer;
    CustomerViewDialog_Header customerDialog_Header;
    CustomerViewDialog_OverviewTab overview;
    Header header;
    CustomerViewDialog_InfoTab customerViewDialog_infoTab;
    SearchBox searchBox;

    public String fName = Utilities.generateRandomString(7);
    public String lName = Utilities.generateRandomString(6);
    String expectedAlert = "Required: You must fill in the customer's last name or company name!";
    public String streetAddress = Integer.toString(Utilities.generateRandomNumber(4)) + " " + Utilities.generateRandomString(5) + " " + Utilities.generateRandomString(5);
    String zipcode = "7" + Utilities.generateRandomNumber(4);
    public String email = Utilities.generateRandomString(5)+"."+Utilities.generateRandomString(5)+""+"@gmail.com";
    public String primaryPhoneNumber = "6" + Integer.toString(Utilities.generateRandomNumber(9));

    @Test
    public void createCustomer() throws Exception {
        createCustomerWithOutRequiredField();
        validateRequiredFieldError();
        createCustomerWithAddress();
        validateCreatedCustomerNameAndAddress();

    }

    @When("I create customer without required last name field")
    public void createCustomerWithOutRequiredField() throws InterruptedException {
        String fName = Utilities.generateRandomString(7);
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.selectUnit("Multi Unit");
        customerDialog_Header.clickSaveButton();
    }

    @Then("I validate alert")
    public void validateRequiredFieldError() {
        String alert = Utilities.getAlertText();
        Utilities.acceptAlert();
        result(expectedAlert, alert, "required field while creating customer ", "Customer creation");
    }

    @When("I create customer with first name, last name and address")
    public void createCustomerWithAddress() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setAddress(streetAddress);
        customer.setZipCode(zipcode);
        customer.setEmailAddress(email);
        customer.setCellPhone(primaryPhoneNumber);
        customer.clickSmsCheckBox();
        customer.clickEmailCheckBox();
        customer.clickVoiceCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @And("I validate search customer with first name")
    public void validateFirstNameInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getFirstName());
        searchBox = new SearchBox();
        result(fName, searchBox.autoCompleteSearch(fName), "Validate first name in search", "Customer creation");
    }

    @And("I validate search customer with last name")
    public void validateLastNameInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getLastName());
        searchBox = new SearchBox();
        result(lName, searchBox.autoCompleteSearch(lName), "Validate last name in search", "Customer creation");
    }

    @And("I validate search customer with phone number")
    public void validatePhoneNumberInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getPrimaryPhoneNumber().replaceAll("[^0-9]",""));
        searchBox = new SearchBox();
        String phoneNumberInSearchBox = searchBox.autoCompleteSearch(customerViewDialog_infoTab.getPrimaryPhoneNumber()).substring(0,13);
        result(customerViewDialog_infoTab.getPrimaryPhoneNumber(), phoneNumberInSearchBox, "Validate phone number in search", "Customer creation");
    }

    @And("I validate search customer with last four of phone number")
    public void validateSearchLastFourPhoneNumber() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getPrimaryPhoneNumber().replaceAll("[^0-9]","").substring(6,10));
        searchBox = new SearchBox();
        String phoneNumberInSearchBox = searchBox.autoCompleteSearch(customerViewDialog_infoTab.getPrimaryPhoneNumber().substring(0,9)).substring(0,9);
        result(customerViewDialog_infoTab.getPrimaryPhoneNumber().substring(0,9), phoneNumberInSearchBox, "Validate with last four of phone number in search", "Customer creation");
    }

    @And("I validate search customer with zip code")
        public void validateZipCodeInSearch() throws InterruptedException {
            customerDialog_Header = new CustomerViewDialog_Header();
            customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
            header = new Header();
            customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
            header.searchCustomer_SearchField(customerViewDialog_infoTab.getZip());
            searchBox = new SearchBox();
            result(zipcode, searchBox.autoCompleteSearch(zipcode), "Validate zip code in search", "Customer creation");
        }

    @And("I validate search customer with CustomerID")
    public void validateCustomerIDInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        overview = new CustomerViewDialog_OverviewTab();
        header.searchCustomer_SearchField(overview.getCustomerIDFromHeader());
        searchBox = new SearchBox();
        result(overview.getCustomerIDFromHeader(), searchBox.autoCompleteSearch(overview.getCustomerIDFromHeader()), "Validate customer ID in search", "Customer creation");
    }

    @And("I validate search customer with street address")
    public void validateStreetAddressInSearch() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        header.searchCustomer_SearchField(customerViewDialog_infoTab.getStreetAddress());
        searchBox = new SearchBox();
        result(streetAddress, searchBox.autoCompleteSearch(streetAddress), "Validate street address in search", "Customer creation");
    }

   @Then("^I create customer with address and ZipCode and I verify Main Tax, State Tax, City Tax, County Tax, Custom Tax, District1 Tax, District2 Tax" +
           ", District3 Tax, District4 Tax, District5 Tax Rates \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" " +
           "and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifyCustomerWithAddressTaxRate(String needStreetAddress, String needZipCode, String needMainTax, String needStateTax,
                String needCityTax, String needCountyTax, String needCustomTax, String needDistrict1Tax, String needDistrict2Tax,
                String needDistrict3Tax, String needDistrict4Tax, String needDistrict5Tax) throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setAddress(needStreetAddress);
        customer.setZipCode(needZipCode);
        customer.setCellPhone(getData("phoneNumber", generalData));
        customerDialog_Header.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        customer.clickOverrideTaxCheckBox();
        result(needMainTax, customer.getTaxRate(customer.mainTaxPercentage),"Entered Main Tax" , "Tax Rate Validation");
        result(needStateTax, customer.getTaxRate(customer.stateTaxPercentage), "Entered State Tax" , "Tax Rate Validation");
        result(needCityTax, customer.getTaxRate(customer.cityTaxPercentage), "Entered City Tax" , "Tax Rate Validation");
        result(needCountyTax, customer.getTaxRate(customer.countyTaxPercentage), "Entered County Tax" , "Tax Rate Validation");
        result(needCustomTax, customer.getTaxRate(customer.customTaxPercentage), "Entered Custom Tax" , "Tax Rate Validation");
        result(needDistrict1Tax, customer.getTaxRate(customer.district1TaxPercentage), "Entered District1 Tax" , "Tax Rate Validation");
        result(needDistrict2Tax, customer.getTaxRate(customer.district2TaxPercentage), "Entered District2 Tax" , "Tax Rate Validation");
        result(needDistrict3Tax, customer.getTaxRate(customer.district3TaxPercentage), "Entered District3 Tax" , "Tax Rate Validation");
        result(needDistrict4Tax, customer.getTaxRate(customer.district4TaxPercentage), "Entered District4 Tax" , "Tax Rate Validation");
        result(needDistrict5Tax,customer.getTaxRate(customer.district5TaxPercentage), "Entered District5 Tax" , "Tax Rate Validation");
        customer.clickOverrideTaxCheckBox();
    }

    @When("I create customer with first name, last name, address and generic flag {string} and {string}")
    public void createCustomerWithGenericFlag(String needFlagName, String needSource) throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
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
        customerDialog_Header.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @And("I search customer")
    public void searchCustomer() throws Exception{
        header = new Header();
        header.searchCustomer_History( lName+ ", " + fName);
    }

    @When("I create customer with first name, last name, email and address")
    public void createCustomerWithEmail() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
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
        customerDialog_Header.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @And("I add additional properties to the customer")
    public void addAdditionalProperties() throws IOException, InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        customer.selectSource(getData("customerSource", generalData));
        customer.selectProperty(customer.commercialProperty);
        customer.clickPrefersPaperCheckBox();
        customer.selectDivision(getData("division", generalData));
        customer.selectGenericFlag(getData("flag", generalData));
        customerDialog_Header.clickSaveButton();
    }

    @When("I create customer with first name and last name")
    public void createCustomerWithoutAddress() throws Exception {

        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        header.navigateTo(header.newCustomerTab);
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.selectUnit("Multi Unit");
        customer.setCellPhone(getData("phoneNumber", generalData));
        customer.clickSmsCheckBox();
        customerDialog_Header.clickSaveButton();
        alertCondition();
        captureUserIdAndFullName();
    }

    @Given("I close customer card")
    public void closeCustomerCard() {
        customerDialog_Header = new CustomerViewDialog_Header();
        customerDialog_Header.clickCloseButton();
    }

    @When("I create customer with first name, last name, address, email and Structure")
    public void createCustomerWithStructure() throws Exception {
        customerDialog_Header = new CustomerViewDialog_Header();
        customer = new CreateCustomerDialog();
        overview = new CustomerViewDialog_OverviewTab();
        header = new Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
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
        customerDialog_Header.clickSaveButton();
        alertCondition();
        customerDialog_Header.saveAnyways();
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String customerNameInHeader = customerViewDialog_infoTab.getFirstName();
        result(fName, customerNameInHeader, "Created customer ", "Structure Validation");
        String newId = overview.getCustomerIDFromHeader();
        addData("strutureUID", newId, generalData);
        AssertException.assertFailure(Utilities.list);
    }

    @Then("I validate if customer name and address match in overview tab")
    public void validateCreatedCustomerNameAndAddress() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        overview = new CustomerViewDialog_OverviewTab();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String expectedAddress = streetAddress + " " + customerViewDialog_infoTab.getCity() + ", " +customerViewDialog_infoTab.getState() +" " + zipcode;
        customerDialog_Header.navigateTo(customerDialog_Header.overviewTabInDialog);
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String actualAddress = overview.getFullAddress();
        result(expectedAddress, actualAddress, "Created customer address", "Customer creation");
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        String customerNameInInfo = customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
        String actualName = fName + " " + lName;
        result(actualName, customerNameInInfo, "Created customer name ", "Customer creation");
    }

    @Then("I validate if customer name match in overview tab")
    public void validateCreatedCustomerName() {
        Utilities.waitUntileElementIsVisible(overview.overviewTab_Address);
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        result(fName, customerNameInHeader, "Created customer name ", "Customer creation");
    }

    @And("I navigate to Subscription Tab")
    public void navigateToSubscriptionTab() throws InterruptedException {
        customerDialog_Header = new CustomerViewDialog_Header();
        Utilities.waitUntileElementIsVisible("//li[@name = '" + customerDialog_Header.subscriptionTabInDialog + "']");
        customerDialog_Header.navigateTo(customerDialog_Header.subscriptionTabInDialog);
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
        String newId = overview.getCustomerIDFromHeader();
        addData("userID", newId, generalData);
        addData("customerName", fName + " " + lName, generalData);
    }

    public String getCustomerName(String previousCustomerNumber) throws Exception {
        header = new Header();
        header.searchCustomerInOrder(previousCustomerNumber);
        customerDialog_Header = new CustomerViewDialog_Header();
        customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
        customerDialog_Header.navigateTo(customerDialog_Header.infoTabInDialog);
        return customerViewDialog_infoTab.getFirstName() + " " + customerViewDialog_infoTab.getLastName();
    }

    public String getCustomerName(){
        overview = new CustomerViewDialog_OverviewTab();
        String customerNameInHeader = overview.getCustomerNameFromHeader();
        return customerNameInHeader;
    }

}
