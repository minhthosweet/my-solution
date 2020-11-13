package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules.Renewal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.Trigger_Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;

public class CreateTrigger_Renewal extends AppData {

	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	InventoryTab inventory;
	RenewalTab renewalTab;
	Trigger_Actions triggerActions;
	ARTab ar;
	Service service;
	CreateNewCustomer createCustomer;
	ValidateRenewal validateRenewal;
	CustomerViewDialog_Header overviewHeader;
	ReminderTab reminder;
	CustomerViewDialog_SubscriptionTab subscription;

	private String descriptionTrigger = "trigger_renewal_all_actions";
	private String accountStatus_Dropdown = "Any";
	private String prefersPaper_Dropdown = "All";
	private String daysBeforeAfter_Dropdown = "1";
	private String subscriptionStatus_Dropdown = "Any";
	private String propertyType_Dropdown = "All Properties";
	private String hasEmail_Dropdown = "All";
	private String editAlertNote_Text = "Sorry, this note is not editable.";
	private String SMSMAppointmentRenewalNote = "SMS Sent";
	public List list = new ArrayList<String>();

	@Test
	public void createRenewalRule() throws Exception {
		createTrigger_Renewal(descriptionTrigger);
		createAllActions_Renewals(descriptionTrigger);
		assertActions_Renewal(descriptionTrigger);

		validateIfFailureExist();

	}

	// Create Renewal Trigger
	public void createTrigger_Renewal(String descriptionName) throws Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		inventory = new InventoryTab();
		renewalTab = new RenewalTab();
		triggerActions = new Trigger_Actions();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(GetDate.minusOneWeekToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.setDescription(descriptionName);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triggerType_Renewal);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_beforeExpirationDate);
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
	public void searchTrigger_Renewal(String descriptionName) {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger(descriptionName);
		result(descriptionName, triggerAdmin.getDescriptionText(descriptionName), "Renewal Trigger Rule",
				"Renewal creation");
		triggerAdmin.clickEditTrigger(descriptionName);
	}

	// Create all actions
	public void createAllActions_Renewals(String descriptionName) throws InterruptedException {
		searchTrigger_Renewal(descriptionName);
		emailAction_Renewal();
		searchTrigger_Renewal(descriptionName);
		snailMailAction_Renewal();

		// Webhooks are not available to all offices searchTrigger_Renewal();
		// webhookAction_Renewal();

		searchTrigger_Renewal(descriptionName);
		SMSAction_Renewal();
		searchTrigger_Renewal(descriptionName);
		voiceAction_Renewal();
	}

	// Create Email Renewal Trigger Action
	public void emailAction_Renewal() {
		triggerActions = new Trigger_Actions();
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.EmailMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.messageTypeDropDown, triggerActions.renewalNotice);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerActions.enterSubjectText(Utilities.generateRandomString(5));
		triggerAdmin.selectDropdown(triggerActions.renewalLinkDropDown, triggerActions.renewalLinkDropdown_Include);
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Snail Mail
	public void snailMailAction_Renewal() throws InterruptedException {
		triggerActions = new Trigger_Actions();
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.snailMailMessageType_Action);
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(triggerActions.snailMail_messageType, triggerActions.renewalNotice);
		triggerAdmin.clickSaveButton();
	}

	// Create Action with Webhook
	public void webhookAction_Renewal() throws InterruptedException {
		triggerActions = new Trigger_Actions();
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.webhookMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.webhook_MethodType, triggerActions.webhookMethod_GET);
		triggerAdmin.selectDropdown(triggerActions.webhook_MethodType, triggerActions.webhookMethod_POST);
		triggerActions.messageInWebhook(triggerActions.URLMessage_Wehbook, triggerActions.getPlaceHolders());
		triggerActions.messageInWebhook(triggerActions.requestHeaderMessage_Webhook, triggerActions.getPlaceHolders());
		triggerActions.messageInWebhook(triggerActions.requestBodyMessage_Webhook, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create SMS action
	public void SMSAction_Renewal() {
		triggerActions = new Trigger_Actions();
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerActions.setMessageinAction_Type1(triggerActions.sendSMSMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice action
	public void voiceAction_Renewal() {
		triggerActions = new Trigger_Actions();
		triggerActions.clickAddActionButton();
		triggerAdmin.selectDropdown(triggerActions.actionTypeDropDown, triggerActions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(triggerActions.ignoreContactPrefsDropDown, triggerActions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(triggerActions.voiceType_Reminder, triggerActions.newMessage_Voice);
		triggerActions.setMessageinAction_Type1(triggerActions.sendVoiceMessageType_Action, triggerActions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Assert all created actions
	@And("I validate Renewal actions {string}")
	public void assertActions_Renewal(String descriptionName) {
		searchTrigger_Renewal(descriptionName);
		triggerActions = new Trigger_Actions();
		ar = new ARTab();
		renewalTab = new RenewalTab();
		result(triggerActions.EmailMessageType_Action, ar.getEmailActionTextValue(), "Email Action", "Renewal Trigger Rule");
		result(triggerActions.snailMailMessageType_Action, ar.getSnailMailActionTextValue(), "Snail Mail Action",
				"Renewal Trigger Rule");
		// result(actions.webhookMessageType_Action,
		// renewalTab.getWebhookActionTextValue(), "Webhook Action","Renewal Trigger Rule");
		result(triggerActions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Renewal", "Renewal Trigger Rule");
		result(triggerActions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Renewal",
				"Renewal Trigger Rule");
	}

	// Create customer with Renewal Subscription
	public void createCustomer() throws Exception {
		header = new Header();
		header.NavigateTo(header.schedulingTab);
		createCustomer = new CreateNewCustomer();
		createCustomer.createCustomerWithAddress();
		createCustomer.validateCreatedCustomerNameAndAddress();

	}

	// Create Subscription for Expiration Date set to tomorrow
	@When("I create a subscription before expiration date")
	public void createSubscription_beforeExpirationDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		validateRenewal = new ValidateRenewal();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.setExpDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.clickSaveButton();
	}

	// Update Renewal Trigger beforeExpirationDate
	@And("I edit the trigger {string} to type {string}")
	public void editTrigger_Renewal(String descriptionName, String renewalType) {
		searchTrigger_Renewal(descriptionName);
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalType);
		triggerAdmin.clickSaveButton();
	}

	// Hit the Script
	@Then("I run the trigger renewal script")
	public void hitTriggerRenewalQuery() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerRenewal.php");
	}

	// Navigate to customer and validate the log
	@Then("I assert the renewal trigger rules log")
	public void assertLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		header.searchCustomer(getData("userID", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.navigateTo(overviewHeader.notesTabInDialog);
		overviewHeader.clickCustomerContactsInNotesTab();
		result(editAlertNote_Text, reminder.getAlertText_Notes(), "Edit Note Alert", "Renewal Trigger Rule");
		result(SMSMAppointmentRenewalNote + getData("phoneNumber",generalData), reminder.ConfirmationNote(getData("phoneNumber",generalData)), "SMS Notification Affirmative",
				"Renewal Trigger Rule");
	}

	// Create Subscription for Expiration Date set to tomorrow
	@When("I create a subscription after expiration date")
	public void createSubscription_afterExpirationDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.setExpDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Billing date set to tomorrow
	@When("I create a subscription before next billing date")
	public void createSubscription_beforeNextBillingDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(subscription.billingFrequencyDropdown, subscription.billingFrequency_Renewal);
		triggerAdmin.selectDropdown(subscription.billingInitialInvoiceDropdown, subscription.billing_initialBillingDate);
		subscription.setInitialBillingDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Billing date set to yesterday
	@When("I create a subscription after next billing date")
	public void createSubscription_afterNextBillingDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		Thread.sleep(3000);
		triggerAdmin.selectDropdown(subscription.billingFrequencyDropdown, subscription.billingFrequency_Renewal);
		triggerAdmin.selectDropdown(subscription.billingInitialInvoiceDropdown, subscription.billing_initialBillingDate);
		subscription.setInitialBillingDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Due Date set to tomorrow
	@When("I create a subscription before due date")
	public void createSubscription_beforeDueDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.clickSubscription(subscription.getSubscriptionID(validateRenewal.serviceType));
		subscription.setCustomDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Due Date set to yesterday
	@When("I create a subscription after due date")
	public void createSubscription_afterDueDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.clickSubscription(subscription.getSubscriptionID(validateRenewal.serviceType));
		subscription.setCustomDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Renewal Due Date set to yesterday
	@When("I create a subscription before renewal date")
	public void createSubscription_beforeRenewalDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.setRenewalDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.clickSaveButton();
	}

	// Create Subscription for Renewal Due Date set to tomorrow
	@When("I create a subscription after renewal date")
	public void createSubscription_afterRenewalDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.setRenewalDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.clickSaveButton();
	}

	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public void validateIfFailureExist() {
		AssertException.assertFailure(list);
	}
}