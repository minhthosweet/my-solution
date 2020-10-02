package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SchedulingTab extends AppData {

    CustomerViewDialog_Header overviewHeader;

    public String scheduleDay = "//div[@style='border:1px solid;']/following-sibling::div[1]";
    public String scheduleSameDay = "//div[@style='border:1px solid;']";
    public String scheduleButtonInDialog = "//span[text()='Schedule']";

    public void clickScheduleDay() {
        String date = Utilities.currentDate("M/d/yyyy");
        Utilities.clickElement("//div[@date='"+ date +"']", ElementType.XPath);
    }

    public void clickScheduleSameDay() {
        Utilities.clickElement(scheduleSameDay, ElementType.XPath);
    }

    public void clickScheduleDaysBefore(String days) {
        Utilities.clickElement("//div[@style='border:1px solid;']/preceding-sibling::div[" + days + "]", ElementType.XPath);
    }

    public void clickScheduleDaysAfter(String days) {
        Utilities.clickElement("//div[@style='border:1px solid;']/following-sibling::div[" + days + "]", ElementType.XPath);
    }

    public void addScheduleDateToProperties() throws Exception {
        String date = Utilities.currentDate("M/d/yyyy");
        String currentDay = "//div[@date='"+ date +"']";
        String scheduleDate = Utilities.getAttributeValue(currentDay, "date");
        addData("scheduleDate", scheduleDate, generalData);
    }

    public void clickScheduleButton() throws InterruptedException {
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.subscriptionTabInDialog);
        Utilities.jsClickElement(scheduleButtonInDialog, ElementType.XPath);
    }

    public void selectServiceType(String serviceType) {
        Utilities.waitUntileElementIsVisible("//optgroup[@label='Subscriptions']/option[contains(text(),'" + serviceType + "')]");
        Utilities.clickElement("//optgroup[@label='Subscriptions']/option[contains(text(),'" + serviceType + "')]", ElementType.XPath);
    }

}

