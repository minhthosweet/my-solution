package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Deprecated;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.FormObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;

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
		Deprecated.scrollToElement(form.htmlInputField);
		form.setInputField(form.htmlInputField, "<div> Employee </div>\n");
		form.setInputField(form.htmlInputField, getData("employeeSignHtml",generalData));
		Deprecated.scrollToElement(form.saveButton);
		form.clickButton(form.saveButton);
	}
	
	public void deleteForm(String needFormName) throws InterruptedException {
		form = new FormObjects();
		form.clickEditButton(needFormName);
		form.clickButton(form.deleteButton);
		Utilities.acceptAlert();
	}

}
