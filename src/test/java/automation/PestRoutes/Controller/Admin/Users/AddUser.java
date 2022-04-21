package automation.PestRoutes.Controller.Admin.Users;

import java.io.IOException;

import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.UsersPage.AddUserDialog;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomers;

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
		if (Legacy.isPresent(adminPage.existingUser)) {
			deactivateUser();
		}
		Legacy.clickElement(adminPage.userButton);
		addUserDialog.setInputValue(addUserDialog.firstNameInputField, userFirstName);
		addUserDialog.setInputValue(addUserDialog.lastNameInputField, userLastName);
		addUserDialog.selectValueFromDropDown(addUserDialog.accountTypeDropDown, needAccountType);
		addUserDialog.clickButton(addUserDialog.activateSaveButton);
		Thread.sleep(3000);
	}

	@Then("I deactivate the existing user")
	public void deactivateUser() throws InterruptedException {
		addUserDialog = new AddUserDialog();
		adminPage = new AdminMainPage();

		header.navigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.users);
		try {
			Legacy.scrollToElementJS(adminPage.preExistingUser);
			Legacy.clickElement(adminPage.existingUser);
			addUserDialog.clickButton(addUserDialog.deactivateLink);
			addUserDialog.clickButton(addUserDialog.deactivateSaveButton);
			Thread.sleep(3000);
			Legacy.scrollToElementJS(addUserDialog.closeButton);
			addUserDialog.clickButton(addUserDialog.closeButton);
		} catch (Exception e) {
			System.out.println("Unable to find existing automation user or click on the cancel button");
		}
	}
}