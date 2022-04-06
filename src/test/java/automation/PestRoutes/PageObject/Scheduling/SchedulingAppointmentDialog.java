package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;

import java.util.List;

public class SchedulingAppointmentDialog extends BasePage {

    //******************************Page objects******************************

    //*****Scheduling tab objects*****
    public String dialogTitle = "//span[@id = 'ui-id-14']";
    public String serviceTypeDropdown = "//option[text() = 'Select Service Type']/parent::select";
    private By serviceTypeDropDown = By.xpath("//div[@id=\"overviewPanel\"]//select[@name='type']");
    public String subscriptionTypeDropdown = "//option[text() = 'Please Select Subscription']/parent::select";
    private By subscriptionTypeDropDown = By.xpath("//div[@id=\"overviewPanel\"]//select[@name='subscriptionID']");
    public String appointmentDurationDropdown = "//option[text() = 'Select Appointment Duration']/parent::select";
    public String flexibilityDropdown = "//option[text() = 'Fixed to Spot']/parent::select";
    public String callAheadDropdown = "//option[text() = 'Please Select']/parent::select/parent::div/preceding-sibling::div/select";
    public String interiorNeededDropdown = "//option[text() = 'Please Select']/parent::select";
    public String targetPestsDropdown = "//select[@id = 'appointmentProblemPests']";
    public String apptFlagsDropdown = "//select[@id = 'appointmentGenericFlags']";
    public String appointmentNotesInputField = "//div[contains (text(), 'Appointment Notes')]/following-sibling::textarea";
    public String scheduleButton = "//div[@id= 'completeAppointment']/following-sibling::div//span";
    private By buttonSchedule = By.xpath("//div[@id= 'completeAppointment']/following-sibling::div//span[text()='Schedule']");
    public String rescheduleButton = "//span[text()='Reschedule']";

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
        return Deprecated.getElementTextValue(dialogTitle);
    }

    public void selectServiceType(String needServiceType) {
        Deprecated.waitVisible(serviceTypeDropdown);
        Deprecated.selectByText(serviceTypeDropdown, needServiceType);
    }

    public void selectSubscriptionType(String needSubscriptionType) {
        Deprecated.selectByText(subscriptionTypeDropdown, needSubscriptionType);
    }

    public void selectDuration(String needDuration) {
        Deprecated.selectByText(appointmentDurationDropdown, needDuration);
    }

    public void selectFlexibility(String needFlexibility) {
        Deprecated.selectByText(flexibilityDropdown, needFlexibility);
    }

    public void selectCallAheadOption(String needOption) {
        Deprecated.selectByText(callAheadDropdown, needOption);
    }

    public void selectInteriorNeededOption(String needOption) {
        Deprecated.selectByText(interiorNeededDropdown, needOption);
    }

    public void selectTargetPestsOption(String needOption) {
        Deprecated.selectByText(targetPestsDropdown, needOption);
    }

    public void selectApptFlagsOption(String needOption) {
        Deprecated.selectByText(appointmentNotesInputField, needOption);
    }

    public void insertAppointmentNotes(String needNotes) {
        Deprecated.locate(appointmentNotesInputField).sendKeys(needNotes);
    }

    public void clickScheduleButton() {
        Deprecated.clickElement(schedulingTab);
        Deprecated.clickElement(scheduleButton);
    }

    public void clickRescheduleButton() {
        Deprecated.waitVisible(schedulingTab);
        Deprecated.clickElement(schedulingTab);
        Deprecated.clickElement(rescheduleButton);
    }

    public void navigateToUnitTab() {
        Deprecated.clickElement(unitsTab);
    }

    public void navigateToSchedulingTab() {
        Deprecated.clickElement(schedulingTab);
    }

    public void selectUnit() {
        Deprecated.clickElement(addUnitOption_UnitsTab);
        Deprecated.clickElement(unitOption);
    }

    public void clickAddProduct() {
        Deprecated.waitVisible(addProductForUnitButton);
        Deprecated.clickElement(addProductForUnitButton);
    }

    public void clickDetailLink() {
        Deprecated.clickElement(unitDetailButton);
    }

    public void selectTypeOfService (String serviceType) {
        isVisible(serviceTypeDropDown);
        Utilities.selectByText(serviceTypeDropDown, serviceType);
    }

    public void selectSubscription(String subscription) {
        Utilities.selectByText(subscriptionTypeDropDown, subscription);
    }

    public List<String> getSubscription() {
        return Deprecated.getAllSelectedOptionsFromDropDown(subscriptionTypeDropDown);
    }

    public void clickBlueScheduleButton() {
        click(buttonSchedule);
        delay(3000);
    }
}
