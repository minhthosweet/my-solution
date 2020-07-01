package automation.PestRoutes.Controller.BackendTest.Admin.Users;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.UsersPage.AddUserDialog;
import automation.PestRoutes.PageObject.CreateCustomer.CreateCustomers;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class AddUser extends BaseClass {

	Header header;
	AdminMainPage adminPage;
	AddUserDialog addUserDialog;
	CreateCustomers createCustomer;

	public void createUser() throws IOException, InterruptedException {
		header = new Header();
		adminPage = new AdminMainPage();
		addUserDialog = new AddUserDialog();
		createCustomer = new CreateCustomers();
		String userFirstName = getData("userFirstName", generalData);
		String userLastName = getData("userLastName", generalData);
		String accountType = getData("accountTypeField", generalData);

		// Navigate to the users page
		header.NavigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.users);


		// Click on the User button
		try {
			WebElement elm = FindElement.elementByAttribute(adminPage.existingUser, InputType.XPath);
		} catch(Exception e ) {
			adminPage.clickButton(adminPage.userButton);
			addUserDialog.setInputValue(addUserDialog.firstNameInputField, userFirstName);
			addUserDialog.setInputValue(addUserDialog.lastNameInputField, userLastName);
			addUserDialog.selectValueFromDropDow(addUserDialog.accountTypeDropDown, accountType);
			addUserDialog.clickButton(addUserDialog.saveButton);
			Thread.sleep(3000);
		}
				
	}

	public void deactivateUser() {
		addUserDialog = new AddUserDialog();
		adminPage = new AdminMainPage();

		adminPage.clickButton(adminPage.existingUser);
		addUserDialog.clickButton(addUserDialog.deactivateLink);
		addUserDialog.clickButton(addUserDialog.saveButton);

	}
	
	
	


}
