package automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.TriggerRules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated.Service;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ReminderTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
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
	Service service;
	CreateNewCustomer createCustomer;
	ValidateRenewal validateRenewal;
	CustomerViewDialog_Header overviewHeader;
	ReminderTab reminder;
	CustomerViewDialog_SubscriptionTab subscription;

	private String descriptionTrigger = "trigger_renewal_all_actions";
	private String setStartDate_negativeScenario = "01/01/2020";
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
		createTrigger_Renewal();
		searchTrigger_Renewal();
		emailAction_Renewal();
		searchTrigger_Renewal();
		snailMailAction_Renewal();
		
		 // Webhooks are not available to all offices searchTrigger_Renewal();
		 // webhookAction_Renewal();
		 
		searchTrigger_Renewal();
		SMSAction_Renewal();
		searchTrigger_Renewal();
		voiceAction_Renewal();
		searchTrigger_Renewal();
		assertActions_Renewal();
		createRenewalServiceType();
		
		searchTrigger_Renewal();
		editTrigger_beforeExpirationDate();
		createCustomer();
		createSubscription_beforeExpirationDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_afterExpirationDate();
		createCustomer();
		createSubscription_afterExpirationDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_beforeNextBillingDate();
		createCustomer();
		createSubscription_beforeNextBillingDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_beforeNextBillingDate();
		createCustomer();
		createSubscription_beforeNextBillingDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_beforeDueDate();
		createCustomer();
		createSubscription_beforeDueDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_afterDueDate();
		createCustomer();
		createSubscription_afterDueDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_beforeRenewalDate();
		createCustomer();
		createSubscription_beforeRenewalDate();
		hitTriggerRenewalQuery();
		assertLog();
		
		searchTrigger_Renewal();
		editTrigger_afterRenewalDate();
		createCustomer();
		createSubscription_afterRenewalDate();
		hitTriggerRenewalQuery();
		assertLog();
		
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
		triggerAdmin.setStartDate(GetDate.minusOneWeekToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		// Positive Scenario
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
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

	// Create SMS action
	public void SMSAction_Renewal() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMSMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setMessageinAction_Type1(actions.sendSMSMessageType_Action, actions.getPlaceHolders());
		triggerAdmin.clickSaveButton();
	}

	// Create Voice action
	public void voiceAction_Renewal() {
		actions = new Actions();
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendVoiceMessageType_Action);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.preRecordedMessageVoice_Reminder);
		triggerAdmin.selectDropdown(actions.voiceType_Reminder, actions.newMessage_Voice);
		actions.setMessageinAction_Type1(actions.sendVoiceMessageType_Action, actions.getPlaceHolders());
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
		// result(actions.webhookMessageType_Action,
		// renewalTab.getWebhookActionTextValue(), "Webhook Action","Renewal Trigger Rule");
		result(actions.sendSMSMessageType_Action, ar.getSMSActionTextValue(), "SMS Renewal", "Renewal Trigger Rule");
		result(actions.sendVoiceMessageType_Action, ar.getVoiceActionTextValue(), "Voice Renewal",
				"Renewal Trigger Rule");
	}

	// Create service type with Renewal Service
	public void createRenewalServiceType() throws Exception {
		service = new Service();
		service.workWithService();
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
	public void createSubscription_beforeExpirationDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		validateRenewal = new ValidateRenewal();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.setExpDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.ClickSaveButton();
	}

	// Update Renewal Trigger beforeExpirationDate
	public void editTrigger_beforeExpirationDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_beforeExpirationDate);
		triggerAdmin.clickSaveButton();
	}

	// Hit the Script
	public void hitTriggerRenewalQuery() {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerRenewal.php");
	}

	// Navigate to customer and validate the log
	public void assertLog() throws IOException, Exception {
		Utilities.navigateToUrl("https://adityam.pestroutes.com/");
		header = new Header();
		reminder = new ReminderTab();
		header.Search_A_Customer(getData("userID", generalData));
		overviewHeader = new CustomerViewDialog_Header();
		overviewHeader.NavigateTo(overviewHeader.notesTabInDialog);
		overviewHeader.clickCustomerContactsInNotesTab();
		result(editAlertNote_Text, reminder.getAlertText_Notes(), "Edit Note Alert", "Renewal Trigger Rule");
		result(SMSMAppointmentRenewalNote + getData("phoneNumber",generalData), reminder.ConfirmationNote(getData("phoneNumber",generalData)), "SMS Notification Affirmative",
				"Renewal Trigger Rule");
	}

	// Update Renewal Trigger after ExpirationDate
	public void editTrigger_afterExpirationDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_afterExpirationDate);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Expiration Date set to tomorrow
	public void createSubscription_afterExpirationDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.setExpDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.ClickSaveButton();
	}

	// Update Renewal Trigger after before next Billing Date
	public void editTrigger_beforeNextBillingDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_beforeNextBillingDate);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Billing date set to tomorrow
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
		overviewHeader.ClickSaveButton();
	}

	// Update Renewal Trigger after next Billing Date
	public void editTrigger_afterNextBillingDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_afterNextBillingDate);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Billing date set to yesterday
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
		overviewHeader.ClickSaveButton();
	}
	
	// Update Renewal Trigger Before Due date
	public void editTrigger_beforeDueDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_beforeDueDate);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Due Date set to tomorrow
	public void createSubscription_beforeDueDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		validateRenewal.createRenewalSubscription();
		subscription.clickSubscription(subscription.getSubscriptionID(validateRenewal.serviceType));
		subscription.setCustomDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		overviewHeader.ClickSaveButton();
	}
	
	// Update Renewal Trigger after Due Date
	public void editTrigger_afterDueDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_afterDueDate);
		triggerAdmin.clickSaveButton();
	}

	// Create Subscription for Due Date set to yesterday
	public void createSubscription_afterDueDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.clickSubscription(subscription.getSubscriptionID(validateRenewal.serviceType));
		subscription.setCustomDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.ClickSaveButton();
	}
	
	// Update Renewal Trigger before Renewal Date
	public void editTrigger_beforeRenewalDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_beforeRenewalDate);
		triggerAdmin.clickSaveButton();
	}
	
	// Create Subscription for Renewal Due Date set to yesterday
	public void createSubscription_beforeRenewalDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.setRenewalDate(GetDate.minusOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.ClickSaveButton();
	}
	
	// Update Renewal Trigger after Renewal Date
	public void editTrigger_afterRenewalDate() {
		renewalTab = new RenewalTab();
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(renewalTab.before_AfterDropdown, renewalTab.beforeAfter_afterRenewalDate);
		triggerAdmin.clickSaveButton();
	}
	
	// Create Subscription for Renewal Due Date set to tomorrow
	public void createSubscription_afterRenewalDate() throws Exception {
		subscription = new CustomerViewDialog_SubscriptionTab();
		overviewHeader = new CustomerViewDialog_Header();
		validateRenewal = new ValidateRenewal();
		validateRenewal.renewalFieldsValidation();
		subscription.setRenewalDate(GetDate.addOneDayToDate(Utilities.currentDate("MM/dd/yyyy")));
		validateRenewal.createRenewalSubscription();
		overviewHeader.ClickSaveButton();
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