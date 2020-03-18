package automation.PestRoutes.Controller.Admin.Preferences;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger extends BaseClass {

	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	TriggerRules triggerAdmin = new TriggerRules();
	InventoryTab inventory = new InventoryTab();

	private String typeOfTrigger = "Renewal";
	private String descriptionTrigger = Utilities.generateRandomString(8);

	@Test
	public void createRenewalRule() throws Exception {
		createTrigger();
		searchTrigger();
		createAction();
	}

	//Create Trigger
	public void createTrigger() throws Exception {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setDescription(descriptionTrigger);
		//Negative Scenario
		triggerAdmin.setStartDate("01/01/2020");
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, typeOfTrigger);
		triggerAdmin.setEndDate("03/17/2020");
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		//Positive Scenario
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.selectDropdown(triggerAdmin.before_AfterDropdown, "Before Expiration Date");
		triggerAdmin.selectDropdown(triggerAdmin.accountStatusDropdown, "Any");
		triggerAdmin.selectDropdown(triggerAdmin.multiUnitDropdown, "Include Multi Unit Properties");
		triggerAdmin.selectDropdown(triggerAdmin.hasInitialServiceDropdown, "Any");
		triggerAdmin.selectDropdown(triggerAdmin.prefersPaperDropdown, "All");
		triggerAdmin.setDaysBefore_After("5");
		triggerAdmin.selectDropdown(triggerAdmin.subscriptionStatusDropdown, "Any");
		triggerAdmin.selectDropdown(triggerAdmin.propertyTypeDropdown, "All Properties");
		triggerAdmin.selectDropdown(triggerAdmin.hasEmailDropdown, "All");
		triggerAdmin.clickAddActionButton();
		triggerAdmin.clickSaveButton();
	}

	//Search Trigger
	public void searchTrigger() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionTrigger);
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	//Create Action
	public void createAction() throws InterruptedException {
		//First Action with Send Email
		triggerAdmin.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerAdmin.actionTypeDropDown, "Send Email");
		triggerAdmin.selectDropdown(triggerAdmin.messageTypeDropDown, "Renewal Notice");
		triggerAdmin.selectDropdown(triggerAdmin.ignoreContactPrefsDropDown, "No");
		triggerAdmin.enterSubjectText(Utilities.generateRandomString(5));
		triggerAdmin.selectDropdown(triggerAdmin.renewalLinkDropDown, "Include");
		triggerAdmin.clickAddActionButton();
		//Second Action with Snail Mail
		triggerAdmin.selectDropdown(triggerAdmin.additionalActionTypeDropDown, "Send Snail Mail");
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(triggerAdmin.additionalMessageTypeDropDown, "Renewal Notice");
		triggerAdmin.clickSaveButton();
	}
}