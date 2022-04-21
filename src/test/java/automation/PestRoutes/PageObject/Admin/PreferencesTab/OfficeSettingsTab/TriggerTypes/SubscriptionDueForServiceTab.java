package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.Utilities.Legacy;

public class SubscriptionDueForServiceTab {

	// Subscription Filter Objects
	public String before_afterDueDate = "//label[text()='Before/After Due Date']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String days_before_afterDueDate = "//label[text()='Days Before/After Due Date']/ancestor::div[@class='col-6']/following-sibling::div/input[@name='filterItemValue']";

	// Before after due date objects
	public String beforeDueDate_dueDateType = "Before Due Date";
	public String afterDueDate_dueDateType = "After Due Date";

	// Set days before after due date
	public void setdays_before_afterDueDate_InputField(String setDays) {
		Legacy.waitVisible(days_before_afterDueDate);
		Legacy.locate(days_before_afterDueDate).clear();
		Legacy.waitVisible(days_before_afterDueDate);
		Legacy.locate(days_before_afterDueDate).sendKeys(setDays);
	}
}
