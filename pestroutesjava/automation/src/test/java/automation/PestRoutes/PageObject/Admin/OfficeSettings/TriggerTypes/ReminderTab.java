package automation.PestRoutes.PageObject.Admin.OfficeSettings.TriggerTypes;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;

public class ReminderTab {

	public String daysBefore_Reminder = "//input[@data-ruleitemtype='reminderDayOffset']";

	// Has Initial Service Objects Reminder
	public String hasInitialServiceDropdown_Reminder = "//select[@data-ruleitemtype='isInitialService']";
	public String hasInitialService_Any_Reminder = "Any";
	public String hasInitialService_isInitialService_Reminder = "Is Initial Service";
	public String hasInitialService_isNotInitialService_Reminder = "Is Not Initial Service";

	public void setdaysBefore_Reminder(String numberOfDays) {
		Utilities.waitUntileElementIsVisible(daysBefore_Reminder);
		FindElement.elementByAttribute(daysBefore_Reminder, InputType.XPath).sendKeys(numberOfDays);
	}
}
