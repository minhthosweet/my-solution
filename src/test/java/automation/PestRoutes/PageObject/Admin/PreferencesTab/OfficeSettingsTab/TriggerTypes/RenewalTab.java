package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.Utilities.Legacy;

public class RenewalTab {
	// Renewal objects
	public String before_AfterDropdown = "//label[text()='Before/After']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String daysBefore_AfterInputField = "//label[text()='Days Before/After']/ancestor::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";
	public String accountStatusDropdown = "//label[text()='Account Status']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String subscriptionStatusDropdown = "//label[text()='Subscription Status']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String multiUnitDropdown = "//label[text()='Multi Unit']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String propertyTypeDropdown = "//label[text()='Property Type']/parent::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String includeServiceTypeDropdown = "//label[text()='Include Service Types']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeServiceTypeDropdown = "//label[text()='Exclude Service Types']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeCustomerFlagsDropdown = "//label[text()='Include Customer Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeCustomerFlagsDropdown = "//label[text()='Exclude Customer Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeSubscriptionFlagsDropdown = "//label[text()='Include Subscription Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeSubscriptionFlagsDropdown = "//label[text()='Exclude Subscription Flags']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeDivisionDropdown = "//label[text()='Include Divisions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeDivisionDropdown = "//label[text()='Exclude Divisions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String includeRegionsDropdown = "//label[text()='Include Regions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String excludeRegionsDropdown = "//label[text()='Exclude Regions']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String hasEmailDropdown = "//label[text()='Has Email']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String prefersPaperDropdown = "//label[text()='Prefers Paper']/parent::div[@class='col-6']/following-sibling::div//select[@name='filterItemValue']";
	public String minRenewalAmountInputField = "//label[text()='Min Renewal Amount']/parent::div[@class='col-6']/following-sibling::div//input[@name='filterItemValue']";
	public String maxRenewalAmountInputField = "//label[text()='Max Renewal Amount']/parent::div[@class='col-6']/following-sibling::div//input[@name='filterItemValue']";
	public String cancelButton = "//div[@id='triggerRulesTable']//span[text()='cancel']";
	// Identifiers for already created actions
	public String WebhookAction_actual = "//div[text()='Webhook']";

	// Multi Unit DropDown Objects
	public String multiUnit_Dropdown_Include = "Include Multi Unit Properties";
	public String multiUnit_Dropdown_Exclude = "Exclude Multi Unit Properties";
	public String multiUnit_Dropdown_Only = "Only Multi Unit Properties";

	// Has Initial Service ObjectsRenewal
	public String hasInitialService_Renewal = "//select[@data-ruleitemtype='hasInitialService']";
	public String hasInitialService_Any_Renewal = "Any";
	public String hasInitialService_initialServiceNotCompleted_Renewal = "Initial Service not Completed";
	public String hasInitialService_CompletedInitialService_Renewal = "Completed Initial Service";

	// Before After Filters for Renewals
	public String beforeAfter_beforeExpirationDate = "Before Expiration Date";
	public String beforeAfter_afterExpirationDate = "After Expiration Date";
	public String beforeAfter_beforeDueDate = "Before Due Date";
	public String beforeAfter_afterDueDate = "After Due Date";
	public String beforeAfter_beforeNextBillingDate = "Before Next Billing Date";
	public String beforeAfter_afterNextBillingDate = "After Next Billing Date";
	public String beforeAfter_beforeRenewalDate = "Before Renewal Date";
	public String beforeAfter_afterRenewalDate = "After Renewal Date";

	public void setDaysBefore_After(String needDays) {
		Legacy.locate(daysBefore_AfterInputField).sendKeys(needDays);
	}

	public void setMinRenewalAmount(String needAmount) {
		Legacy.locate(minRenewalAmountInputField).sendKeys(needAmount);
	}

	public void setMaxRenewalAmount(String needAmount) {
		Legacy.waitVisible(maxRenewalAmountInputField);
		Legacy.locate(maxRenewalAmountInputField).sendKeys(needAmount);
	}

	// Getters: get actual text value for action created(used for assertions)
	public String getWebhookActionTextValue() {
		return Legacy.getElementTextValue(WebhookAction_actual);
	}

}
