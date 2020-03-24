package automation.PestRoutes.Controller.Admin.Preferences.TriggerRules;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.Actions;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerRules;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.ARTab;
import automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes.RenewalTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;

public class Trigger_AR extends BaseClass {

	Header header;
	AdminMainPage adminMainPage;
	TriggerRules triggerAdmin = new TriggerRules();
	RenewalTab renewalTab;
	Actions actions = new Actions();
	ARTab ar;

	private String descriptionTrigger = "trigger_ar_createinvoices";
	private String age_PastDueDropDownValue = "Age";
	private String age_pastDueDays_InputField_Value = Double.toString(Utilities.generateRandomInteger(1));
	private String minimum_Balance = "0";
	private String maximum_Balance = "10000";
	private String valueType_DropDownValue = "Percentage";
	private String value_createInvoice_Action = Double.toString(Utilities.generateRandomInteger(2));
	private String serviceType_createInvoice_Action = "Misc Service";

	@Test
	public void createRenewalRule() throws Exception {
		createTrigger_AR();
		searchTrigger_AR();
		createAction_AR();
		createSecondAction_AR();
	}

	// Create AR Trigger
	public void createTrigger_AR() throws InterruptedException, Exception {
		header = new Header();
		adminMainPage = new AdminMainPage();
		renewalTab = new RenewalTab();
		ar = new ARTab();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
		triggerAdmin.setDescription(descriptionTrigger);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, triggerAdmin.triigerType_AR);
		triggerAdmin.selectDropdown(triggerAdmin.globalType, triggerAdmin.global_SpecificToThisOffice);
		triggerAdmin.selectDropdown(triggerAdmin.activeType, triggerAdmin.activeType_Active);
		triggerAdmin.selectDropdown(ar.age_PastDueDropDown, age_PastDueDropDownValue);
		ar.setAge_PastDueDays_InputField(age_pastDueDays_InputField_Value);
		ar.setMinimum_Balance(minimum_Balance);
		ar.setMaximum_Balance(maximum_Balance);
		triggerAdmin.selectDropdown(renewalTab.multiUnitDropdown, renewalTab.multiUnit_Dropdown_Include);
		triggerAdmin.selectDropdown(renewalTab.propertyTypeDropdown, ar.propertyType_AllProperties);
		actions.clickSaveButton();
	}

	// Search AR Trigger
	public void searchTrigger_AR() {
		header = new Header();
		adminMainPage = new AdminMainPage();
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
		triggerAdmin.searchTrigger("trigger_ar_createinvoices");
		triggerAdmin.clickEditTrigger("trigger_ar_createinvoices");
	}

	// Create an action
	public void createAction_AR() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.actionTypeDropDown, actions.sendSMS);
		triggerAdmin.selectDropdown(actions.ignoreContactPrefsDropDown, actions.ignoreContactPrefsTypes_No);
		actions.setMessageinAction(actions.getPlaceHolders());
	}

	// Create second action
	public void createSecondAction_AR() {
		actions.clickAddActionButton();
		triggerAdmin.selectDropdown(actions.additionalActionTypeDropDown, actions.createInvoices);
		triggerAdmin.selectDropdown(actions.valueTypeDropDown, valueType_DropDownValue);
		actions.setValue_Action(value_createInvoice_Action);
		triggerAdmin.selectDropdown(actions.serviceType_Action, serviceType_createInvoice_Action);
		// actions.clickSaveButton();
	}
}
