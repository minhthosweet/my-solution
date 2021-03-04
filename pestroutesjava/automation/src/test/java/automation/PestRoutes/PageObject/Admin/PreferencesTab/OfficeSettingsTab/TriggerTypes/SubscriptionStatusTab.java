package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SubscriptionStatusTab {
	// Subscription Status Filter Objects
	public String whenToTrigger = "//label[contains(text(),'When') and contains(text(),'Trigger')]/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String statusChangedTo = "//label[text()='Status Changed To']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";

	// When to Trigger Objects
	public String whenToTrigger_triggerOnSave = "Trigger on Save";
	public String whenToTrigger_triggerAfterTime = "Trigger After Time";

	// Status Changed to Objects
	public String statusChangedTo_Any = "Any";
	public String statusChangedTo_Active = "Active";
	public String statusChangedTo_Frozen = "Frozen";
	public String statusChangedTo_Scheduled = "Scheduled";
	public String statusChangedTo_Pending = "Pending";
	public String statusChangedTo_Complete = "Complete";
	public String statusChangedTo_Cancelled = "Cancelled";
	public String statusChangedTo_Rescheduled = "Rescheduled";
	public String statusChangedTo_notServiced = "Not Serviced";

	// Identifiers for already created actions
	public String sendEmployeeEmail_actual = "//div[text()='Send Employee Email']";
	public String addAlert_actual = "//div[text()='Add Alert']";
	public String addTask_actual = "//div[text()='Add Task']";
	public String sendEmployeeSMS_actual = "//div[text()='Send Employee SMS']";
	public String sendEmployeeVoice_actual = "//div[text()='Send Employee Voice']";

	// Getters: get actual text value for action created(used for assertions)
	public String getSendEmployeeEmailActionTextValue() {
		return Utilities.getElementTextValue(sendEmployeeEmail_actual, ElementType.XPath);
	}

	public String getAddAlertActionTextValue() {
		return Utilities.getElementTextValue(addAlert_actual, ElementType.XPath);
	}

	public String getAddTaskActionTextValue() {
		return Utilities.getElementTextValue(addTask_actual, ElementType.XPath);
	}

	public String getSendEmploeeSMSActionTextValue() {
		return Utilities.getElementTextValue(sendEmployeeSMS_actual, ElementType.XPath);
	}

	public String getSendEmployeeVoiceActionTextValue() {
		return Utilities.getElementTextValue(sendEmployeeVoice_actual, ElementType.XPath);
	}
}
