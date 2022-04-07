package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
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
    private By includeServiceTypesMultiDropDown = By.xpath("//label[text()='Include Service Types']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By includeDivisionsMultiDropDown = By.xpath("//label[text()='Include Divisions']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By excludeSubscriptionFlagsDropDown = By.xpath("//label[text()='Exclude Subscription Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//input");
    private By notificationBufferDropDown = By.xpath("//select[@data-ruleitemtype='rescheduleTriggerWhen']");
    private By incrementField = By.xpath("//input[@data-ruleitemtype='rescheduleTriggerWhenValue']");
    private By isInitialServiceDropDown = By.xpath("//label[text()='Is Initial Service']//following::div//select[@name='filterItemValue']");

    public void selectStatusChangedTo(String changeStatus) {
        Deprecated.scrollToElementJS(statusChangedToDropDown);
        Utilities.selectByText(statusChangedToDropDown, changeStatus);
        System.out.println("Appointment Status Changed To " + changeStatus);
    }

    public void selectWhenToTrigger(String whenToTrigger) {
        Deprecated.scrollToElementJS(whenToTriggerDropDown);
        Utilities.selectByText(whenToTriggerDropDown, whenToTrigger);
    }

    public boolean typeIncludeCustomerFlag(String flagCode) {
        List<WebElement> allFlags = locateAll(By.xpath("//label[text()='Include Customer Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement includeCustomerFlagsMultiField = Utilities.locate(includeCustomerFlagsMultiDropDown);
        for (WebElement flag : allFlags) {
            if (flag.getText().contains(flagCode)) {
                return true;
            }
        }
        Deprecated.scrollToElementJS(includeCustomerFlagsMultiField);
        Deprecated.type(flagCode, includeCustomerFlagsMultiField);
        System.out.println("Customer Flag: " + flagCode);
        return false;
    }

    public boolean typeIncludeServiceType(String serviceType) {
        List<WebElement> allServiceTypes = locateAll(By.xpath("//label[text()='Include Service Types']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement includeServiceTypesMultiField = locate(includeServiceTypesMultiDropDown);
        for (WebElement service : allServiceTypes) {
            if (service.getText().contains(serviceType)) {
                return true;
            }
        }
        Deprecated.scrollToElementJS(includeServiceTypesMultiField);
        Deprecated.type(serviceType, includeServiceTypesMultiField);
        System.out.println("Service Type: " + serviceType);
        return false;
    }

    public boolean typeIncludeDivisions(String division) {
        List<WebElement> allDivisions = locateAll(By.xpath("//label[text()='Include Divisions']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement includeDivisionsMultiField = locate(includeDivisionsMultiDropDown);
        for (WebElement tempDivision : allDivisions) {
            if (tempDivision.getText().contains(division)) {
                return true;
            }
        }
        Deprecated.scrollToElementJS(includeDivisionsMultiField);
        Deprecated.type(division, includeDivisionsMultiField);
        System.out.println("Division: " + division);
        return false;
    }

    public boolean typeExcludeSubscriptionFlag(String subscriptionFlag) {
        List<WebElement> allExcludedSubscriptionFlags = locateAll(By.xpath("//label[text()='Exclude Subscription Flags']/parent::div[@class='col-6']//following-sibling::div/div//ul//div"));
        WebElement excludeSubscriptionFlagsMultiField = locate(excludeSubscriptionFlagsDropDown);
        for (WebElement excludeFlag : allExcludedSubscriptionFlags) {
            if (excludeFlag.getText().contains(subscriptionFlag)) {
                return true;
            }
        }
        Deprecated.scrollToElementJS(excludeSubscriptionFlagsMultiField);
        Deprecated.type(subscriptionFlag, excludeSubscriptionFlagsMultiField);
        System.out.println("Subscription Flag: " + subscriptionFlag);
        return false;
    }

    public void selectAppointmentType(String appointmentType) {
        Deprecated.scrollToElementJS(appointmentTypeDropDown);
        Utilities.selectByText(appointmentTypeDropDown, appointmentType);
    }

    public void clickAddActionButton() {
        isVisible(greenActionButton);
        jsScrollTo(greenActionButton);
        click(greenActionButton);
    }

    public void completeAddTaskAction(String assignTo) {
        String addTaskEmployee = "//select[@id='observerItem' and @data-observeritemtype='employeeID']";

        waitVisible(addTaskAssignToDropDown);
        Utilities.selectByText(addTaskAssignToDropDown, assignTo);
        if(assignTo.equalsIgnoreCase("Specific Employee")) {
            Deprecated.selectByIndex(addTaskEmployee, 0);
        }
        Deprecated.type("1", addTaskDaysTillDueField);
        Deprecated.scrollToElementJS(textAreaMessageNotes);
        Deprecated.type("Status - Task Test For Trigger Rules", textAreaMessageNotes);
        Utilities.selectByText(addTaskCategoryDropDown, "Appt Status");
    }

    public void completeSendVoiceAction(String voiceType) {
        String voiceMessage = "//select[@name='observerItemValue' and @data-observeritemtype='recordedMessages']";
        if (voiceType.equalsIgnoreCase("New Message")) {
            Utilities.selectByText(voiceTypeDropDown, voiceType);
            isVisible(textAreaMessageNotes);
            Deprecated.scrollToElementJS(textAreaMessageNotes);
            Deprecated.type("Status - Voice Test For Trigger Rules", textAreaMessageNotes);
        } else if (voiceType.equalsIgnoreCase("Pre-recorded Message")) {
            Utilities.selectByText(voiceTypeDropDown, voiceType);
            Deprecated.selectByIndex(voiceMessage, 0);
        }
    }

    public void completeSendEmailAction() {
        Deprecated.type("Automation Trigger Rule Test", emailTitleField);
        Deprecated.scrollToElementJS(emailTitleField);
        Deprecated.type("Status - Email Test For Trigger Rules", textArea_EmailMessage);
    }

     public void completeStatusAction(String action, String details) {
        waitVisible(actionDropDown);
        Utilities.selectByText(actionDropDown, action);
        switch(action) {
            case "Add Alert":
                delay(2000);
                Deprecated.type("Status - Alert Test For Trigger Rules", textAreaMessageNotes);
                break;
            case "Add Task":
                completeAddTaskAction(details);
                break;
            case "Send Email":
                completeSendEmailAction();
                break;
            case "Send SMS":
                Deprecated.type("Status - SMS Test For Trigger Rules", textAreaMessageNotes);
                break;
            case "Send Voice":
                completeSendVoiceAction(details);
                break;
        }
    }

    public void completeSecondAction(String action2, String details) {
        By secondActionDropDown = By.xpath("//form[@id='triggerRuleForm']//div[2]/div/label//following::select[@name='eventObserverID']");
        clickAddActionButton();
        waitVisible(secondActionDropDown);
        Utilities.selectByText(secondActionDropDown, action2);
        if (action2.equalsIgnoreCase("Add Alert")) {
            Deprecated.type("Status - Alert Test For Trigger Rules", textAreaMessageNotes);
        } else if (action2.equalsIgnoreCase("Add Task")) {
            completeAddTaskAction(details);
        } else if (action2.equalsIgnoreCase("Send Email")) {
            completeSendEmailAction();
        } else if (action2.equalsIgnoreCase("Send SMS")) {
            Deprecated.type("Status - SMS Test For Trigger Rules", textAreaMessageNotes);
        } else if (action2.equalsIgnoreCase("Send Voice")) {
            completeSendVoiceAction(details);
        }
    }

    public void completeTwoAppointmentStatusActions(String action1, String action2, String details) {
        waitVisible(actionDropDown);
        selectByText(actionDropDown, action1);
        switch (action1) {
            case "Add Alert":
                Deprecated.type("Status - Alert Test For Trigger Rules", textAreaMessageNotes);
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
                Deprecated.type("Status - SMS Test For Trigger Rules", textAreaMessageNotes);
                completeSecondAction(action2, details);
                break;
            case "Send Voice":
                completeSendVoiceAction(details);
                completeSecondAction(action2, details);
                break;
        }
    }

    public void clickSaveButton() {
        Deprecated.scrollToElementJS(saveTriggerButton);
        click(saveTriggerButton);
    }

    public String getActionNotes(String action) {
        if (action.equalsIgnoreCase("Add Task")) {
            return "Status - Task Test For Trigger Rules";
        } else if (action.equalsIgnoreCase("Add Alert")) {
            return "Status - Alert Test For Trigger Rules";
        }
        return null;
    }

    public String getActionCategory(String action) {
        if(action.equalsIgnoreCase("Add Task")) {
            return getFirstSelected(addTaskCategoryDropDown);
        }
        return null;
    }

    public void selectNotificationBuffer(String notificationBuffer) {
        selectByText(notificationBufferDropDown, notificationBuffer);
    }

    public void typeIncrement(String increment) {
        Deprecated.type(increment, incrementField);
    }

    public void selectIsInitialService(String isInitialService) {
        selectByText(isInitialServiceDropDown, isInitialService);
    }
}