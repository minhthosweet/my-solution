package automation.PestRoutes.PageObject.CreateCustomer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_OverviewTab;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateCustomers extends AppData {

	WebDriver driver = GetWebDriver.getInstance();
	SignIn signInPage;
	CreateCustomerDialog customer;
	CustomerViewDialog_Header dialog;
	CustomerViewDialog_OverviewTab overview;
	Header header;
	String fName = Utilities.generateRandomString(7);
	String lName = Utilities.generateRandomString(6);

	@Given("I want to login to the application using URL : {string}")
	public void i_want_to_login_to_the_application_using_URL(String url) {
		driver.get(url);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("I want to login to the application using URL : {string} using mind and matter")
	public void i_want_to_login_to_the_application_using_URL_using_mind_and_matter(String url) {
		driver.get(url);

		driver.manage().window().maximize();
	}

	@And("Click on New Customer")
	public void click_on_New_Customer() {

		dialog = new CustomerViewDialog_Header();
		customer = new CreateCustomerDialog();
		overview = new CustomerViewDialog_OverviewTab();
		header = new Header();
		header.navigateTo(header.newCustomerTab);
	}

	@When("I enter firstname and lastname")
	public void i_enter_firstname_and_lastname() {
		customer.setFirstName(fName);
		customer.setLastName(lName);
	}

	@Then("unit details")
	public void unit_details() {
		customer.selectUnit("Multi Unit");
	}

	@Then("click save button")
	public void click_save_button() throws InterruptedException {
		dialog.clickSaveButton();
	}
}
