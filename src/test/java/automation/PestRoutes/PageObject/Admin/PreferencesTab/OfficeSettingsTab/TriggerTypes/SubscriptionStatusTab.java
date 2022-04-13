package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubscriptionStatusTab extends PreferencesPage {
	// Subscription Status Filter Objects
	public String whenToTrigger = "//label[contains(text(),'When') and contains(text(),'Trigger')]/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	public String statusChangedTo = "//label[text()='Status Changed To']/ancestor::div[@class='col-6']/following-sibling::div/select[@name='filterItemValue']";
	private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");

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
		return Legacy.getElementTextValue(sendEmployeeEmail_actual);
	}

	public String getAddAlertActionTextValue() {
		return Legacy.getElementTextValue(addAlert_actual);
	}

	public String getAddTaskActionTextValue() {
		return Legacy.getElementTextValue(addTask_actual);
	}

	public String getSendEmploeeSMSActionTextValue() {
		return Legacy.getElementTextValue(sendEmployeeSMS_actual);
	}

	public String getSendEmployeeVoiceActionTextValue() {
		return Legacy.getElementTextValue(sendEmployeeVoice_actual);
	}

	public void selectWhenToTrigger(String whenToTriggerValue) {
		Legacy.scrollToElementJS(By.xpath(whenToTrigger));
		Utilities.selectByText(By.xpath(whenToTrigger), whenToTriggerValue);
	}

	public void selectStatusChangedTo(String changeStatus) {
		Legacy.scrollToElementJS(By.xpath(statusChangedTo));
		Utilities.selectByText(By.xpath(statusChangedTo), changeStatus);
		System.out.println("Subscription Status Changed To " + changeStatus);
	}

	    public boolean typeIncludeCustomerFlag(String flagCode) {
        List<WebElement> allFlags = Utilities.locateAll(By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement includeCustomerFlagsMultiField = Utilities.locate(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        Legacy.scrollToElementJS(includeCustomerFlagsMultiField);
        Legacy.type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

}
