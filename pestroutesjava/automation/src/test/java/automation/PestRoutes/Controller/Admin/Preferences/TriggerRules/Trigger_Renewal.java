package automation.PestRoutes.Controller.Admin.Preferences.TriggerRules;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger_Renewal extends BaseClass {

	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	TriggerRules triggerAdmin = new TriggerRules();
	InventoryTab inventory = new InventoryTab();
	RenewalTab renewalTab = new RenewalTab();
	Actions actions = new Actions();

	private String descriptionTrigger = "trigger_renewal_email_snailmail";
	private String setStartDate_negativeScenario = "01/01/2020";
	private String before_AfterDropdownValue = "Before Expiration Date";
	private String accountStatus_Dropdown = "Any";

	private String hasInitialService_Dropdown = "Any";
	private String prefersPaper_Dropdown = "All";
	private String daysBeforeAfter_Dropdown = Double.toString(Utilities.generateRandomInteger(1));
	private String subscriptionStatus_Dropdown = "Any";
	private String propertyType_Dropdown = "All Properties";
	private String hasEmail_Dropdown = "All";

	@Test
	public void createRenewalRule() throws Exception {
		createTrigger_Renewal();
		searchTrigger_Renewal();
		createAction_Renewal();
	}

	// Create Renewal Trigger
	public void createTrigger_Renewal() throws Exception {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.setDescription(descriptionTrigger);
		// Negative Scenario
		triggerAdmin.setStartDate(setStartDate_negativeScenario);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_Renewal);
		triggerAdmin.setEndDate(Utilities.currentDate("MM/dd/yyyy"));
		actions.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		actions.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		// Positive Scenario
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, before_AfterDropdownValue);
		triggerAdmin.selectDropdown(renewalTab.accountStatusDropdown, accountStatus_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(renewalTab.hasInitialServiceDropdown, hasInitialService_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.prefersPaperDropdown, prefersPaper_Dropdown);
		renewalTab.setDaysBefore_After(daysBeforeAfter_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.subscriptionStatusDropdown, subscriptionStatus_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, propertyType_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.hasEmailDropdown, hasEmail_Dropdown);
		actions.clickSaveButton();
	}

	// Search Renewal Trigger
	public void searchTrigger_Renewal() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionTrigger);
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	// Create Renewal Trigger Action
	public void createAction_Renewal() throws InterruptedException {
		// First Action with Send Email
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendEmail);
		triggerAdmin.selectDropdown(actions.messageTypeDropDown, actions.renewalNotice);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.enterSubjectText(Utilities.generateRandomString(5));
		triggerAdmin.selectDropdown(actions.renewalLinkDropDown, actions.renewalLinkDropdown_Include);
		actions.clickAddActionButton();
		// Second Action with Snail Mail
		triggerAdmin.selectDropdown(actions.additionalActionTypeDropDown, actions.sendSnailMail);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(actions.snailMail_messageType, actions.renewalNotice);
		actions.clickSaveButton();
	}
}