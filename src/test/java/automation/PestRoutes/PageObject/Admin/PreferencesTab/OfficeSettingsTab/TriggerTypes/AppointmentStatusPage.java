package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.PestRoutes.Utilities.Utilities.*;

public class AppointmentStatusPage extends PreferencesPage {

    private By statusChangedToDropDown = By.xpath("//label[text()='Status Changed To']//following::div//select[@name='filterItemValue']");
    private By whenToTriggerDropDown = By.xpath("//label[text()='When to Trigger']//following::div//select[@data-ruleitemtype='triggerWhen']");
    private By includeCustomerFlagsMultiDropDown = By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By appointmentTypeDropDown = By.xpath("//label[text()='Appointment Type']//following::select[@name='filterItemValue']");
    private By saveTriggerButton = By.xpath("//span[text()='save']");
    private By greenActionButton = By.xpath("//div[text()='+ Action']");
    private By actionDropDown = By.xpath("//div[@id='observer']//select[@name='eventObserverID']");
    private By emailTitleField = By.xpath("//input[@id='observerItem' and @name='observerItemValue']");
    private By textArea_EmailMessage = By.xpath("//textarea[@id='observerItem']/../div");
    private By voiceTypeDropDown = By.xpath("//select[@name='observerItemValue' and @value='new']");
    private By textAreaMessageNotes = By.xpath("//textarea[@name='observerItemValue' and @data-observeritemtype='text']");
    private By addTaskAssignToDropDown = By.xpath("//select[@id='observerItem' and @data-observeritemtype='assignTo']");
    private By addTaskEmployeeDropDown = By.xpath("//select[@id='observerItem' and @data-observeritemtype='employeeID']");
    private By addTaskDaysTillDueField = By.xpath("//input[@id='observerItem' and @data-observeritemtype='daysDue']");
    private By addTaskCategoryDropDown = By.xpath("//select[@id='observerItem' and @data-observeritemtype='category']");

    public void selectStatusChangedTo(String changeStatus) {
        scrollToElementJS(statusChangedToDropDown);
        selectFromDropDown(changeStatus, statusChangedToDropDown);
        System.out.println("Appointment Status Changed To " + changeStatus);
    }

    public void selectWhenToTrigger(String whenToTrigger) {
        scrollToElementJS(whenToTriggerDropDown);
        selectFromDropDown(whenToTrigger, whenToTriggerDropDown);
    }

    public boolean typeIncludeCustomerFlag(String flagCode) {
        List<WebElement> allFlags = findElements(By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement includeCustomerFlagsMultiField = find(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        scrollToElementJS(includeCustomerFlagsMultiField);
        type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

    public void selectAppointmentType(String appointmentType) {
        scrollToElementJS(appointmentTypeDropDown);
        selectFromDropDown(appointmentType, appointmentTypeDropDown);
    }

    public void clickAddActionButton() {
        elementIsVisible(greenActionButton);
        click(greenActionButton);
    }

    public void completeAddTaskAction(String assignTo) {
        String addTaskEmployee = "//select[@id='observerItem' and @data-observeritemtype='employeeID']";

        waitUntileElementIsVisible(addTaskAssignToDropDown);
        selectFromDropDown(assignTo, addTaskAssignToDropDown);
        if(assignTo.equalsIgnoreCase("Specific Employee")) {
            selectValueFromDropDownByIndex(addTaskEmployee, 0);
        }
        type("1", addTaskDaysTillDueField);
        scrollToElementJS(textAreaMessageNotes);
        type("Appointment Status - Task Test For Trigger Rules", textAreaMessageNotes);
        selectFromDropDown("Appt Status", addTaskCategoryDropDown);
    }

    public void completeSendVoiceAction(String voiceType) {
        String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
        if (voiceType.equalsIgnoreCase("New Message")) {
            selectFromDropDown(voiceType, voiceTypeDropDown);
            elementIsVisible(textAreaMessageNotes);
            scrollToElementJS(textAreaMessageNotes);
            type("Appointment Status - Voice Test For Trigger Rules", textAreaMessageNotes);
        } else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
            selectFromDropDown(voiceType, voiceTypeDropDown);
            selectValueFromDropDownByIndex(voiceMessage, 0);
        }
    }

    public void completeSendEmailAction() {
        type("Automation Trigger Rule Test", emailTitleField);
        scrollToElementJS(emailTitleField);
        type("Appointment Status - Email Test For Trigger Rules", textArea_EmailMessage);
    }

     public void completeAppointmentStatusAction(String action, String details) {
        waitUntileElementIsVisible(actionDropDown);
        selectFromDropDown(action, actionDropDown);
        switch(action) {
            case "Add Alert":
                delay(500);
                type("Appointment Status - Alert Test For Trigger Rules", textAreaMessageNotes);
                break;
            case "Add Task":
                completeAddTaskAction(details);
                break;
            case "Send Email":
                completeSendEmailAction();
                break;
            case "Send SMS":
                type("Appointment Status - SMS Test For Trigger Rules", textAreaMessageNotes);
                break;
            case "Send Voice":
                completeSendVoiceAction(details);
                break;
        }
    }

    public void completeSecondAction(String action2, String details) {
        By secondActionDropDown = By.xpath("//select[@id='observerItem' and @data-observeritemtype='ignoreContactPrefs']//preceding::select[2]");
        clickAddActionButton();
        waitUntileElementIsVisible(secondActionDropDown);
        selectFromDropDown(action2, secondActionDropDown);
        if (action2.equalsIgnoreCase("Add Alert")) {
            type("Appointment Status - Alert Test For Trigger Rules", textAreaMessageNotes);
        } else if (action2.equalsIgnoreCase("Add Task")) {
            completeAddTaskAction(details);
        } else if (action2.equalsIgnoreCase("Send Email")) {
            completeSendEmailAction();
        } else if (action2.equalsIgnoreCase("Send SMS")) {
            type("Appointment Status - SMS Test For Trigger Rules", textAreaMessageNotes);
        } else if (action2.equalsIgnoreCase("Send Voice")) {
            completeSendVoiceAction(details);
        }
    }

    public void completeTwoAppointmentStatusActions(String action1, String action2, String details) {
        waitUntileElementIsVisible(actionDropDown);
        selectFromDropDown(action1, actionDropDown);
        switch (action1) {
            case "Add Alert":
                type("Appointment Status - Alert Test For Trigger Rules", textAreaMessageNotes);
                completeSecondAction(action2, details);
                break;
            case "Add Task":
                completeAddTaskAction(details);
                completeSecondAction(action2, details);
                break;
            case "Send Email":
                completeSendEmailAction();
                completeSecondAction(action2, details);
                break;
            case "Send SMS":
                type("Appointment Status - SMS Test For Trigger Rules", textAreaMessageNotes);
                completeSecondAction(action2, details);
                break;
            case "Send Voice":
                completeSendVoiceAction(details);
                completeSecondAction(action2, details);
                break;
        }
    }

    public void clickSaveButton() {
        scrollToElementJS(saveTriggerButton);
        click(saveTriggerButton);
    }

    public String getActionNotes(String action) {
        if (action.equalsIgnoreCase("Add Task")) {
            return "Appointment Status - Task Test For Trigger Rules";
        } else if (action.equalsIgnoreCase("Add Alert")) {
            return "Appointment Status - Alert Test For Trigger Rules";
        }
        return null;
    }

    public String getActionCategory(String action) {
        if(action.equalsIgnoreCase("Add Task")) {
            return getSelectedOptionFromDropDown(addTaskCategoryDropDown);
        }
        return null;
    }
}