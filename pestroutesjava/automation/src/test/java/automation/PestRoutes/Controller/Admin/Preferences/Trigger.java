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
	private String descriptionTrigger = "trigger_renewal_email_snailmail";
	private String setStartDate_negativeScenario = "01/01/2020";
	private String beforeAfter_Dropdown = "Before Expiration Date";
	private String accountStatus_Dropdown = "Any";
	private String multiUnit_Dropdown = "Include Multi Unit Properties";
	private String hasInitialService_Dropdown = "Any";
	private String prefersPaper_Dropdown = "All";
	private String daysBeforeAfter_Dropdown = Double.toString(Utilities.generateRandomInteger(1));
	private String subscriptionStatus_Dropdown = "Any";
	private String propertyType_Dropdown = "All Properties";
	private String hasEmail_Dropdown = "All";
	
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
		triggerAdmin.setStartDate(setStartDate_negativeScenario);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, typeOfTrigger);
		triggerAdmin.setEndDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		//Positive Scenario
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.selectDropdown(triggerAdmin.before_AfterDropdown, beforeAfter_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.accountStatusDropdown, accountStatus_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.multiUnitDropdown, multiUnit_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.hasInitialServiceDropdown, hasInitialService_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.prefersPaperDropdown, prefersPaper_Dropdown);
		triggerAdmin.setDaysBefore_After(daysBeforeAfter_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.subscriptionStatusDropdown, subscriptionStatus_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.propertyTypeDropdown, propertyType_Dropdown);
		triggerAdmin.selectDropdown(triggerAdmin.hasEmailDropdown, hasEmail_Dropdown);
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
		triggerAdmin.selectDropdown(triggerAdmin.actionTypeDropDown, triggerAdmin.sendEmail);
		triggerAdmin.selectDropdown(triggerAdmin.messageTypeDropDown, triggerAdmin.renewalNotice);
		triggerAdmin.selectDropdown(triggerAdmin.ignoreContactPrefsDropDown, triggerAdmin.ignoreContactPrefsTypes_No);
		triggerAdmin.enterSubjectText(Utilities.generateRandomString(5));
		triggerAdmin.selectDropdown(triggerAdmin.renewalLinkDropDown, triggerAdmin.renewalLinkDropdown_Include);
		triggerAdmin.clickAddActionButton();
		//Second Action with Snail Mail
		triggerAdmin.selectDropdown(triggerAdmin.additionalActionTypeDropDown, triggerAdmin.sendSnailMail);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(triggerAdmin.additionalMessageTypeDropDown, triggerAdmin.renewalNotice);
		triggerAdmin.clickSaveButton();
	}
}