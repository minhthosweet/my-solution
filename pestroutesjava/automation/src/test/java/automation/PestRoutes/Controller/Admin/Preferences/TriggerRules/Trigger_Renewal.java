package automation.PestRoutes.Controller.Admin.Preferences.TriggerRules;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger_Renewal extends BaseClass {

	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	InventoryTab inventory;
	RenewalTab renewalTab;
	Actions actions;
	ARTab ar;

	private String descriptionTrigger = "trigger_renewal_all_actions";
	private String setStartDate_negativeScenario = "01/01/2020";
	private String before_AfterDropdownValue = "Before Expiration Date";
	private String accountStatus_Dropdown = "Any";
	private String prefersPaper_Dropdown = "All";
	private String daysBeforeAfter_Dropdown = Double.toString(Utilities.generateRandomInteger(1));
	private String subscriptionStatus_Dropdown = "Any";
	private String propertyType_Dropdown = "All Properties";
	private String hasEmail_Dropdown = "All";
	public List list = new ArrayList<String>();

	@Test
	public void createRenewalRule() throws Exception {
		createTrigger_Renewal();
		searchTrigger_Renewal();
		emailAction_Renewal();
		searchTrigger_Renewal();
		snailMailAction_Renewal();
		searchTrigger_Renewal();
		webhookAction_Renewal();
		searchTrigger_Renewal();
		assertActions_Renewal();
		validateIfFailureExist();
	}

	// Create Renewal Trigger
	public void createTrigger_Renewal() throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		inventory = new InventoryTab();
		renewalTab = new RenewalTab();
		actions = new Actions();
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
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		// Positive Scenario
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, before_AfterDropdownValue);
		triggerAdmin.selectDropdown(renewalTab.accountStatusDropdown, accountStatus_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(renewalTab.hasInitialService_Renewal, renewalTab.hasInitialService_Any_Renewal);
		triggerAdmin.selectDropdown(renewalTab.prefersPaperDropdown, prefersPaper_Dropdown);
		renewalTab.setDaysBefore_After(daysBeforeAfter_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.subscriptionStatusDropdown, subscriptionStatus_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, propertyType_Dropdown);
		triggerAdmin.selectDropdown(renewalTab.hasEmailDropdown, hasEmail_Dropdown);
		triggerAdmin.clickSaveButton();
	}

	// Search Renewal Trigger
	public void searchTrigger_Renewal() {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionTrigger);
		result(descriptionTrigger, triggerAdmin.getDescriptionText(descriptionTrigger), "Renewal Trigger Rule",
				"Renewal creation");
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	// Create Email Renewal Trigger Action
	public void emailAction_Renewal() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(actions.messageTypeDropDown, actions.renewalNotice);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.enterSubjectText(Utilities.generateRandomString(5));
		triggerAdmin.selectDropdown(actions.renewalLinkDropDown, actions.renewalLinkDropdown_Include);
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Snail Mail
	public void snailMailAction_Renewal() throws InterruptedException {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.snailMailMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(actions.snailMail_messageType, actions.renewalNotice);
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_Renewal() throws InterruptedException {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.webhookMessageType_Action);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_GET);
		triggerAdmin.selectDropdown(actions.webhook_MethodType, actions.webhookMethod_POST);
		actions.messageInWebhook(actions.URLMessage_Wehbook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestHeaderMessage_Webhook, actions.getPlaceHolders());
		actions.messageInWebhook(actions.requestBodyMessage_Webhook, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	public void assertActions_Renewal() {
		actions = new Actions();
		ar = new ARTab();
		renewalTab = new RenewalTab();
		result(actions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action", "Renewal Trigger Rule");
		result(actions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
				"Renewal Trigger Rule");
		result(actions.webhookMessageType_Action, renewalTab.getWebhookActionTextValue(), "Webhook Action",
				"Renewal Trigger Rule");
	}

	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public void validateIfFailureExist() {
		AssertException.asserFailure(list);
	}
}