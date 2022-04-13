package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Legacy;
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
        return Legacy.getElementTextValue(dialogTitle);
    }

    public void selectServiceType(String needServiceType) {
        Legacy.waitVisible(serviceTypeDropdown);
        Legacy.selectByText(serviceTypeDropdown, needServiceType);
    }

    public void selectSubscriptionType(String needSubscriptionType) {
        Legacy.selectByText(subscriptionTypeDropdown, needSubscriptionType);
    }

    public void selectDuration(String needDuration) {
        Legacy.selectByText(appointmentDurationDropdown, needDuration);
    }

    public void selectFlexibility(String needFlexibility) {
        Legacy.selectByText(flexibilityDropdown, needFlexibility);
    }

    public void selectCallAheadOption(String needOption) {
        Legacy.selectByText(callAheadDropdown, needOption);
    }

    public void selectInteriorNeededOption(String needOption) {
        Legacy.selectByText(interiorNeededDropdown, needOption);
    }

    public void selectTargetPestsOption(String needOption) {
        Legacy.selectByText(targetPestsDropdown, needOption);
    }

    public void selectApptFlagsOption(String needOption) {
        Legacy.selectByText(appointmentNotesInputField, needOption);
    }

    public void insertAppointmentNotes(String needNotes) {
        Legacy.locate(appointmentNotesInputField).sendKeys(needNotes);
    }

    public void clickScheduleButton() {
        Legacy.clickElement(schedulingTab);
        Legacy.clickElement(scheduleButton);
    }

    public void clickRescheduleButton() {
        Legacy.waitVisible(schedulingTab);
        Legacy.clickElement(schedulingTab);
        Legacy.clickElement(rescheduleButton);
    }

    public void navigateToUnitTab() {
        Legacy.clickElement(unitsTab);
    }

    public void navigateToSchedulingTab() {
        Legacy.clickElement(schedulingTab);
    }

    public void selectUnit() {
        Legacy.clickElement(addUnitOption_UnitsTab);
        Legacy.clickElement(unitOption);
    }

    public void clickAddProduct() {
        Legacy.waitVisible(addProductForUnitButton);
        Legacy.clickElement(addProductForUnitButton);
    }

    public void clickDetailLink() {
        Legacy.clickElement(unitDetailButton);
    }

    public void selectTypeOfService (String serviceType) {
        isVisible(serviceTypeDropDown);
        Utilities.selectByText(serviceTypeDropDown, serviceType);
    }

    public void selectSubscription(String subscription) {
        Utilities.selectByText(subscriptionTypeDropDown, subscription);
    }

    public List<String> getSubscription() {
        return Legacy.getAllSelectedOptionsFromDropDown(subscriptionTypeDropDown);
    }

    public void clickBlueScheduleButton() {
        click(buttonSchedule);
        delay(3000);
    }

    public void clickReschedule() {
        click(By.xpath(rescheduleButton));
        delay(3000);
    }
}
