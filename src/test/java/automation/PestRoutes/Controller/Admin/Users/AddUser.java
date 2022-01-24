package automation.PestRoutes.Controller.Admin.Users;

import java.io.IOException;

import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.UsersPage.AddUserDialog;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomers;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class AddUser extends AppData {

	Header header;
	AdminMainPage adminPage;
	AddUserDialog addUserDialog;
	CreateCustomers createCustomer;

	@Given("I create a new user if it is not already existing {string}")
	public void createUser(String needAccountType) throws IOException, InterruptedException {
		header = new Header();
		adminPage = new AdminMainPage();
		addUserDialog = new AddUserDialog();
		createCustomer = new CreateCustomers();
		String userFirstName = getData("userFirstName", generalData);
		String userLastName = getData("userLastName", generalData);

		// Navigate to the users page
		header.navigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.users);

		// Click on the User button
		try {
			WebElement elm = FindElement.elementByAttribute(adminPage.existingUser, InputType.XPath);
			deactivateUser();
			Utilities.clickElement(adminPage.userButton, Utilities.ElementType.XPath);
			addUserDialog.setInputValue(addUserDialog.firstNameInputField, userFirstName);
			addUserDialog.setInputValue(addUserDialog.lastNameInputField, userLastName);
			addUserDialog.selectValueFromDropDown(addUserDialog.accountTypeDropDown, needAccountType);
			addUserDialog.clickButton(addUserDialog.activateSaveButton);
			Thread.sleep(3000);
			
		} catch(Exception e ) {
			Utilities.clickElement(adminPage.userButton, Utilities.ElementType.XPath);
			addUserDialog.setInputValue(addUserDialog.firstNameInputField, userFirstName);
			addUserDialog.setInputValue(addUserDialog.lastNameInputField, userLastName);
			addUserDialog.selectValueFromDropDown(addUserDialog.accountTypeDropDown, needAccountType);
			addUserDialog.clickButton(addUserDialog.activateSaveButton);
			Thread.sleep(3000);
		}

	}
	@Then("I deactivate the existing user")
	public void deactivateUser() throws InterruptedException {
		addUserDialog = new AddUserDialog();
		adminPage = new AdminMainPage();

		header.navigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.users);
		try {
			Utilities.scrollToElementJS(adminPage.preExistingUser);
			Utilities.clickElement(adminPage.existingUser, Utilities.ElementType.XPath);
			addUserDialog.clickButton(addUserDialog.deactivateLink);
			addUserDialog.clickButton(addUserDialog.deactivateSaveButton);
			Thread.sleep(3000);
			Utilities.scrollToElementJS(addUserDialog.closeButton);
			addUserDialog.clickButton(addUserDialog.closeButton);
		} catch (Exception e) {
			System.out.println("Unable to find existing automation user or click on the cancel button");
		}
	}
}