package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Utilities.AppData;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.FormObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.Utilities;

public class FormTemplates extends AppData {
	AdminMainPage adminPage;
	Header header;
	PreferencesPage preferences;
	FormObjects form;
	@Test
	public void navigateToFormTemplate() throws Exception {
		adminPage = new AdminMainPage();
		header = new Header();
		preferences = new PreferencesPage();
		header.navigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.preferences);
		preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.formTemplate);

		
	}
	public void createEmployeeSignForm(String needFormName) throws Exception {
		form = new FormObjects();
		form.clickButton(form.addFormButton);
		form.setInputField(form.nameField, needFormName);
		form.selectValueFromDropdown(form.requiredApprovalDropdown, "No");
		form.clickButton(form.htmlButton);
		Utilities.scrollToElement(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<div> Employee </div>");
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, getData("employeeSignHtml",generalData));
		Utilities.scrollToElement(form.saveButton);
		form.clickButton(form.saveButton);
	}
	
	public void createCustomerSignForm(String needFormName) throws Exception {
		form = new FormObjects();
		form.clickButton(form.addFormButton);
		form.setInputField(form.nameField, needFormName);
		form.selectValueFromDropdown(form.requiredApprovalDropdown, "No");
		form.clickButton(form.htmlButton);
		Utilities.scrollToElement(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<div> Customer </div>");
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, getData("customerSignHtml",generalData));
		Utilities.scrollToElement(form.saveButton);
		form.clickButton(form.saveButton);
	}
	
	public void createCustomerAndEmployeeSignForm(String needFormName) throws Exception {
		form = new FormObjects();
		form.clickButton(form.addFormButton);
		form.setInputField(form.nameField, needFormName);
		form.selectValueFromDropdown(form.requiredApprovalDropdown, "No");
		form.clickButton(form.htmlButton);
		Utilities.scrollToElement(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<div> Customer </div>");
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, getData("customerSignHtml",generalData));
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<br>");
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<div> Employee </div>");
		Utilities.hitEnter(form.htmlInputField);
		form.setInputField(form.htmlInputField, getData("employeeSignHtml",generalData));
		Utilities.scrollToElement(form.saveButton);
		form.clickButton(form.saveButton);
	}
	
	public void deleteForm(String needFormName) {
		form = new FormObjects();
		form.clickEditButton(needFormName);
		form.clickButton(form.deleteButton);
		Utilities.acceptAlert();
	}

}
