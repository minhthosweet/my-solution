package automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.Preferences.PreferencesPage;
import automation.PestRoutes.Utilities.BaseClass;

public class FormTemplates extends BaseClass{
	AdminMainPage adminPage;
	Header header;
	PreferencesPage preferences;
	@Test
	public void navigateToFormTemplate() {
		adminPage = new AdminMainPage();
		header = new Header();
		preferences = new PreferencesPage();
		header.NavigateTo(header.adminTab);
		adminPage.navigateTo(adminPage.preferences);
		preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.formTemplate);
		
	}

}
