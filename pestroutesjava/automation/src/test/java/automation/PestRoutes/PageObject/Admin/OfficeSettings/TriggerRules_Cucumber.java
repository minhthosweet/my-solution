package automation.PestRoutes.PageObject.Admin.OfficeSettings;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;
import automation.PestRoutes.Utilities.GetDate;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.And;

public class TriggerRules_Cucumber {
	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	TriggerRules triggerAdmin = new TriggerRules();
	InventoryTab inventory = new InventoryTab();

	private String typeOfTrigger = "Renewal";
	private String descriptionTrigger = "trigger_renewal_email_snailmail";

	@And("I navigate to trigger rules")
	public void i_navigate_to_trigger_rules() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		triggerAdmin.navigateToTriggerRules();
	}

	@And("I create a trigger with negative scenrio with startdate as {string}")
	public void i_create_a_trigger_with_negative_scenrio_with_startdate_as(String startDate)
			throws InterruptedException {
		triggerAdmin.clickAddTrigerButton();
		triggerAdmin.setDescription(descriptionTrigger);
		// Negative Scenario
		triggerAdmin.setStartDate(startDate);
		triggerAdmin.selectDropdown(triggerAdmin.triggerTypeDropdown, typeOfTrigger);
		triggerAdmin.setEndDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
	}

	@And("update with positive scenarios")
	public void update_with_positive_scenarios() throws InterruptedException, Exception {
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setStartDate(Utilities.currentDate("MM/dd/yyyy"));
		triggerAdmin.clickSaveButton();
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		triggerAdmin.setEndDate(GetDate.addOneYearToDate(Utilities.currentDate("MM/dd/yyyy")));
	}

	@And("set before_AfterDropdowm to Before Expiration Date")
	public void set_before_AfterDropdowm_to_Before_Expiration_Date() {
		triggerAdmin.selectDropdown(triggerAdmin.before_AfterDropdown, "Before Expiration Date");
	}

	@And("set account_StatusDropdown to Any")
	public void set_account_StatusDropdown_to_Any() {
		triggerAdmin.selectDropdown(triggerAdmin.accountStatusDropdown, "Any");

	}

	@And("set multiUnit_Dropdown to Include Multi Unit Properties")
	public void set_multiUnit_Dropdown_to_Include_Multi_Unit_Properties() {
		triggerAdmin.selectDropdown(triggerAdmin.multiUnitDropdown, "Include Multi Unit Properties");

	}

	@And("set hasInitialService_Dropdown to Any")
	public void set_hasInitialService_Dropdown_to_Any() {
		triggerAdmin.selectDropdown(triggerAdmin.hasInitialServiceDropdown, "Any");

	}

	@And("set prefersPaper to All")
	public void set_prefersPaper_to_All() {
		triggerAdmin.selectDropdown(triggerAdmin.prefersPaperDropdown, "All");

	}

	@And("set daysBefore_After to {int}")
	public void set_daysBefore_After_to(Integer int1) {
		triggerAdmin.setDaysBefore_After("5");

	}

	@And("set subscriptionStatus_Dropdown to Any")
	public void set_subscriptionStatus_Dropdown_to_Any() {
		triggerAdmin.selectDropdown(triggerAdmin.subscriptionStatusDropdown, "Any");

	}

	@And("set propoertyType_Dropdowm to AllProperties")
	public void set_propoertyType_Dropdowm_to_AllProperties() {
		triggerAdmin.selectDropdown(triggerAdmin.propertyTypeDropdown, "All Properties");

	}

	@And("set hasEmail_Dropdowm to All")
	public void set_hasEmail_Dropdowm_to_All() {
		triggerAdmin.selectDropdown(triggerAdmin.hasEmailDropdown, "All");

	}

	@And("I click save button")
	public void i_click_save_button() {
		triggerAdmin.clickSaveButton();
	}

	@And("I search for the trigger")
	public void i_search_for_the_trigger() {
		triggerAdmin.searchTrigger(descriptionTrigger);
	}

	@And("I click edit trigger")
	public void i_click_edit_trigger() {
		triggerAdmin.clickEditTrigger(descriptionTrigger);
	}

	@And("I add Actions")
	public void i_add_Actions() {
		triggerAdmin.clickAddActionButton();
	}

	@And("I select Send Email")
	public void i_select_Send_Email() {
		triggerAdmin.selectDropdown(triggerAdmin.actionTypeDropDown, "Send Email");
	}

	@And("I select Renewal Notice")
	public void i_select_Renewal_Notice() {
		triggerAdmin.selectDropdown(triggerAdmin.messageTypeDropDown, "Renewal Notice");
	}

	@And("I enter subject")
	public void i_enter_subject() {
		triggerAdmin.enterSubjectText(Utilities.generateRandomString(5));
	}

	@And("I select Snail Mail")
	public void i_select_Snail_Mail() {
		triggerAdmin.selectDropdown(triggerAdmin.additionalActionTypeDropDown, "Send Snail Mail");
		
	}
	
	@And("I select ignore Contact Prefs as {string}")
	public void i_select_ignore_Contact_Prefs_as(String string) {
		triggerAdmin.selectDropdown(triggerAdmin.ignoreContactPrefsDropDown, "No");
	}

	@And("I select Renewal Link Dropdown as {string}")
	public void i_select_Renewal_Link_Dropdown_as(String string) {
		triggerAdmin.selectDropdown(triggerAdmin.renewalLinkDropDown, "Include");
		
	}


}
