package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;

import java.util.HashMap;
import java.util.Map;

public class SchedulingTab extends BasePage {

    AppData app = new AppData();
    CustomerViewDialog_Header overviewHeader;

    public String scheduleDay = "//div[@style='border:1px solid;']/following-sibling::div[1]";
    public String scheduleSameDay = "//div[@style='border:1px solid;']";
    public String scheduleButtonInDialog = "//span[text()='Schedule']";
    public String closeRecommendedRoutes = "//span[text()='Recommended Routes']/following-sibling::button[@title='close']";
    public String jobPool = "//p[text()='Job Pool']";
    public String linkFillRoutes = "//*[@id='calendarNav']//li[@for='fillRoutes']/div/p[contains(text(),'Fill Routes')]";
    public String recommendedRoutesRefreshButton = "//div[text()='Refresh']";
    public Map<String, String> filter_Types = new HashMap<String, String>();
    public String filterTypes(String key) {
        filter_Types.put("sortByClosest", "//select[@name='sortBy']");
        filter_Types.put("includeFullRoutes", "//select[@name='fillCapacity']");
        filter_Types.put("filterTechs", "//select[@name='techFilter']");
        filter_Types.put("filterGroups", "//select[@name='groupFilter']");
        filter_Types.put("maxMiles", "//input[@name='maxDistance']");
        filter_Types.put("startDateRange", "//input[@name='startDate']");
        filter_Types.put("endDateRange", "//input[@name='endDate']");
        return filter_Types.get(key);
    }

    public void clickScheduleDay() {
        String date = GetDate.currentDate("M/d/yyyy");
        Legacy.jsClickElement("//div[@date='"+ date +"']");
    }

    public void clickScheduleSameDay() {
        Legacy.jsClickElement(scheduleSameDay);
    }

    public void clickScheduleDaysBefore(String days) {
        Legacy.jsClickElement("//div[@style='border:1px solid;']/preceding-sibling::div[" + days + "]");
    }

    public void clickScheduleDaysAfter(String days) {
        Legacy.jsClickElement("//div[@style='border:1px solid;']/following-sibling::div[" + days + "]");
    }

    public void clickJobPool() {
        Legacy.waitVisible(jobPool);
        Legacy.clickElement(jobPool, true, true);
    }

    public void addScheduleDateToProperties() {
        String date = GetDate.currentDate("M/d/yyyy");
        String currentDay = "//div[@date='"+ date +"']";
        Legacy.waitVisible(currentDay);
        String scheduleDate = Legacy.getAttribute(currentDay, "date");
        app.addData("scheduleDate", scheduleDate, app.generalData);
    }

    public void clickScheduleButton() {
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.subscriptionTabInDialog);
        Legacy.jsClickElement(scheduleButtonInDialog);
        Utilities.delay(3000);
        //Utilities.waitUntileElementIsVisible(closeRecommendedRoutes);
        Legacy.jsClickElement(closeRecommendedRoutes);
    }

    public void clickOnlyScheduleButton() throws InterruptedException {
        overviewHeader = new CustomerViewDialog_Header();
        overviewHeader.navigateTo(overviewHeader.subscriptionTabInDialog);
        Legacy.jsClickElement(scheduleButtonInDialog);
    }

    public void closeRemmendedRoutesDialog() {
        Legacy.waitVisible(closeRecommendedRoutes);
        Legacy.jsClickElement(closeRecommendedRoutes);
    }

    public void selectServiceType(String serviceType) {
        Legacy.waitVisible("//optgroup[@label='Subscriptions']/option[contains(text(),'" + serviceType + "')]");
        Legacy.clickElement("//optgroup[@label='Subscriptions']/option[contains(text(),'" + serviceType + "')]");
    }

    public void clickButton(String needElement) throws InterruptedException {
        Legacy.scrollToElementJS(needElement);
        Legacy.waitVisible(needElement);
        Legacy.clickElement(needElement);
    }

    public void selectFirstOptionFromDropDown(String needElement) {
        Legacy.waitVisible(needElement);
        Legacy.selectByIndex(needElement, 1);
    }

     //Author: F. White
    public void clickFillRoutesLink() throws InterruptedException {
        Legacy.waitVisible(linkFillRoutes, 45);
        Thread.sleep(3000);
        Legacy.clickElement(linkFillRoutes);
    }

}

