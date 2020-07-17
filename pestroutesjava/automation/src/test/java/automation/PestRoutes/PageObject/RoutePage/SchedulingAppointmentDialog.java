package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SchedulingAppointmentDialog {

    //******************************Page objects******************************

    //*****Scheduling tab objects*****
    public String dialogTitle = "//span[@id = 'ui-id-14']";
    public String serviceTypeDropdown = "//option[text() = 'Select Service Type']/parent::select";
    public String subscriptionTypeDropdown = "//option[text() = 'Please Select Subscription']/parent::select";
    public String appointmentDurationDropdown = "//option[text() = 'Select Appointment Duration']/parent::select";
    public String flexibilityDropdown = "//option[text() = 'Fixed to Spot']/parent::select";
    public String callAheadDropdown = "//option[text() = 'Please Select']/parent::select/parent::div/preceding-sibling::div/select";
    public String interiorNeededDropdown = "//option[text() = 'Please Select']/parent::select";
    public String targetPestsDropdown = "//select[@id = 'appointmentProblemPests']";
    public String apptFlagsDropdown = "//select[@id = 'appointmentGenericFlags']";
    public String appointmentNotesInputField = "//div[contains (text(), 'Appointment Notes')]/following-sibling::textarea";
    public String scheduleButton = "//div[@id= 'completeAppointment']/following-sibling::div//span";

    //*****Unit tab objects*****
    public String unitsTab = "//a[text() = 'Units']";
    public String billingTab = "//ul[@id = 'appointmentTabs']//a[text() = 'Billing']";
    public String schedulingTab = "//ul[@id = 'appointmentTabs']//a[text() = 'Scheduling']";
    public String addUnitOption_UnitsTab = "//div[@id = 'apptUnitsPanel']/div";
    public String unitOption = "//div[@id='select2-result-label-16']";
    public String unitDetailButton = "//span[text()='details']";
    public String addProductForUnitButton = "//div[@class='chemicalUnitNotes']/following-sibling::div[text()='+ add product']";


    //******************************Functions by objects******************************

    public String getTitle() {
        return Utilities.getElementTextValue(dialogTitle, ElementType.XPath);
    }

    public void selectServiceType(String needServiceType) {
        Utilities.waitUntileElementIsVisible(serviceTypeDropdown);
        Utilities.selectValueFromDropDownByValue(serviceTypeDropdown, needServiceType);
    }

    public void selectSubscriptionType(String needSubscriptionType) {
        Utilities.selectValueFromDropDownByValue(subscriptionTypeDropdown, needSubscriptionType);
    }

    public void selectDuration(String needDuration) {
        Utilities.selectValueFromDropDownByValue(appointmentDurationDropdown, needDuration);
    }

    public void selectFlexibility(String needFlexibility) {
        Utilities.selectValueFromDropDownByValue(flexibilityDropdown, needFlexibility);
    }

    public void selectCallAheadOption(String needOption) {
        Utilities.selectValueFromDropDownByValue(callAheadDropdown, needOption);
    }

    public void selectInteriorNeededOption(String needOption) {
        Utilities.selectValueFromDropDownByValue(interiorNeededDropdown, needOption);
    }

    public void selectTargetPestsOption(String needOption) {
        Utilities.selectValueFromDropDownByValue(targetPestsDropdown, needOption);
    }

    public void selectApptFlagsOption(String needOption) {
        Utilities.selectValueFromDropDownByValue(appointmentNotesInputField, needOption);
    }

    public void insertAppointmentNotes(String needNotes) {
        FindElement.elementByAttribute(appointmentNotesInputField, InputType.XPath).sendKeys(needNotes);
    }

    public void clickScheduleButton() {
        Utilities.clickElement(schedulingTab, ElementType.XPath);
        Utilities.clickElement(scheduleButton, ElementType.XPath);
    }

    public void navigateToUnitTab() {
        Utilities.clickElement(unitsTab, ElementType.XPath);
    }

    public void navigateToSchedulingTab() {
        Utilities.clickElement(schedulingTab, ElementType.XPath);
    }

    public void selectUnit() {
        Utilities.clickElement(addUnitOption_UnitsTab, ElementType.XPath);
        Utilities.clickElement(unitOption, ElementType.XPath);
    }

    public void clickAddProduct() {
        Utilities.waitUntileElementIsVisible(addProductForUnitButton);
        Utilities.clickElement(addProductForUnitButton, ElementType.XPath);
    }

    public void clickDetailLink() {
        Utilities.clickElement(unitDetailButton, ElementType.XPath);
    }

}
