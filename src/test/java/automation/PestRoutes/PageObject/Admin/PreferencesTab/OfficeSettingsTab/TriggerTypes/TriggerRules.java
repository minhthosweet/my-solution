package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Data.AppData.*;
import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Deprecated;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TriggerRules extends PreferencesPage {

    OfficeSettingsObjects officeSettingsObjects;

    By selectedOffice = By.xpath("//select[@id='officeSwitcher']/option[@selected='SELECTED']");
    // Search Trigger
    public String searchTrigger = "//input[@id='triggerSearch']";

    // Trigger Types
    public String triggerType_AR = "AR";
    public String triggerType_Renewal = "Renewal";
    public String triggerType_Reminders = "Reminders";
    public String triggerType_SubscriptionStatus = "Subscription Status";
    public String triggerType_CustomerStatus = "Customer Status";
    public String triggerType_AppointmentStatus = "Appointment Status";
    public String triggerType_SubscriptionDueforService = "Subscription Due for Service";

    // Global Types
    public String globalType = "//select[@name='officeID']";
    public String global_SpecificToThisOffice = "Specific To This Office";
    public String global_AvailableToAllOffices = "Available To All Offices";

    // Global Types
    public String activeType = "//select[@name='active']";
    public String activeType_Active = "Active";
    public String activeType_NotActive = "Not Active";

    // Filter Objects
    public String officeSettingsText = "//div[@id='OfficeLogo']//preceding-sibling::h2";
    public String addTriggerButton = "//div[text() = '+ Trigger']";
    public String triggerTypeDropdown = "//select[@name='triggerEventID']";
    public String descriptionInputField = "//form//input[@name='description']";
    public String globalDropdown = "//select[@name='officeID']";
    public String startDateInputField = "//input[@name='startDate']";
    public String endDateInputField = "//input[@name='endDate']";
    public String isActiveDropdown = "//select[@name='active']";
    public String daysAfterChange = "//input[@data-ruleitemtype='triggerWhenValue']";

    // Buttons
    public String saveButton = "//div[@id='triggerRulesTable']//span[text()='save']";
    private By greenAddTriggerButton= By.xpath("//div[@id='newTrigger' and text()='+ Trigger']");
    private By editTriggerButton = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[6]//following::span");
    private By saveTriggerButton = By.xpath("//span[text()='save']");
    private By removeActionButton = By.xpath("//form[@id='triggerRuleForm']//div[text()='Remove']");

    private By descriptionColumnValues = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[2]");
    private By activeColumnValues = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[6]");
    private By triggerTypeDropDown = By.xpath("//form[@id='triggerRuleForm']//select[@name='triggerEventID']");
    private By descriptionField = By.xpath("//form[@id='triggerRuleForm']//input[@name='description']");
    private By startDateField = By.xpath("//form[@id='triggerRuleForm']//input[@name='startDate']");
    private By activeDropDown = By.xpath("//form[@id='triggerRuleForm']//select[@name='active']");

    public void clickSaveButton() {
        Deprecated.scrollToElement(addTriggerButton);
        Deprecated.waitVisible(saveButton);
        Deprecated.clickElement(saveButton);
    }

    // Trigger Filter Actions
    public void navigateToTriggerRules() {
        officeSettingsObjects= new OfficeSettingsObjects();
        Deprecated.waitVisible(officeSettingsText);
        Deprecated.clickElement(officeSettingsObjects.triggerRules);
    }

    public void clickAddTrigerButton() {
        Deprecated.clickElement(addTriggerButton);
    }

    public void clickEditTrigger(String descriptionName) {
        Deprecated.clickElement("//div[text() = '" + descriptionName + "']//parent::div//span[text()='edit']"
        );
    }

    public void searchTrigger(String description) {
        Deprecated.waitVisible(searchTrigger);
        Deprecated.locate(searchTrigger).sendKeys(description);
    }

    public void selectDropdown(String needObject, String needTextValue) {
        Deprecated.waitVisible(needObject);
        Deprecated.selectByText(needObject, needTextValue);
    }

    // Get Description text value from Landing page
    public String getDescriptionText(String descriptionSet) {
        return Deprecated.getElementTextValue(
                "//div[@id= 'triggerRulesHeader']/following-sibling::div//div[text()='" + descriptionSet + "']"
        );
    }

    // Setter methods
    public void setDescription(String needDescription) {
        Deprecated.locate(descriptionInputField).sendKeys(needDescription);
    }

    public void setStartDate(String needStartDate) {
        Deprecated.waitVisible(startDateInputField);
        Deprecated.clickElement(startDateInputField);
        Deprecated.locate(startDateInputField).clear();
        Deprecated.locate(startDateInputField).sendKeys(needStartDate);
    }

    public void setEndDate(String needEndDate) throws InterruptedException {
        Deprecated.waitVisible(endDateInputField);
        Deprecated.clickElement(endDateInputField);
        Deprecated.locate(endDateInputField).clear();
        Deprecated.locate(endDateInputField).sendKeys(needEndDate);
    }

    public void setDaysAfterChange(String days) {
        Deprecated.waitVisible(daysAfterChange);
        Deprecated.locate(daysAfterChange).sendKeys(days);
    }

    // trigger Queue query
    @When("I execute the trigger queue script")
    public void hitTriggerQueue() {
        GetWebDriver.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
    }

    //getter method
    public WebElement getDescription(String needText) {
        return Deprecated.locate("//div[text()='" + needText + "']");
    }

    public void clickAddTriggerButton() {
        Deprecated.scrollToElementJS(Utilities.locate(greenAddTriggerButton));
        click(greenAddTriggerButton);
    }

    public void selectTriggerType(String triggerType) {
        waitVisible(triggerTypeDropDown);
        Utilities.selectByText(triggerTypeDropDown, triggerType);
    }

    public void typeTriggerDescription(String description) {
        delay(500);
        Deprecated.type(description, descriptionField);
    }

    public void typeStartDate(String date) {
        isVisible(startDateField);
        Deprecated.type(date, Utilities.locate(startDateField));
    }

    public void setAllTriggersToNotActive(String active, String notActive){
        List<WebElement> listOfActiveValues = locateAll(activeColumnValues);
        for(WebElement activeValue : listOfActiveValues) {
            if (activeValue.getText().equals(active)) {
                String activeDataValue = activeValue.getText();
                System.out.println(activeDataValue);
                Utilities.locate(editTriggerButton).click();
                Utilities.selectByText(activeDropDown, notActive);
                click(saveTriggerButton);
            }
        }
    }

    public void clickToRemoveAction() {
        List<WebElement> allRemoveActionButtons = locateAll(removeActionButton);
        for (WebElement removeButton : allRemoveActionButtons) {
            if (removeButton.isDisplayed()) {
                Deprecated.scrollToElementJS(removeButton);
                click(removeActionButton);
            }
        }
    }

    public boolean isExistingTriggerAvailable(String description, String date){
        List<WebElement> listOfDescriptionValues = locateAll(descriptionColumnValues);
        By editButton = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[text()='"+ description +"']//following::span");
        for(WebElement descriptionValue : listOfDescriptionValues) {
            Deprecated.scrollToElementJS(descriptionValue);
            if (descriptionValue.getText().equals(description)) {
                delay(1000);
                Deprecated.scrollToElementJS(editButton);
                Utilities.locate(editButton).click();
                typeStartDate(date);
                Utilities.selectByText(activeDropDown, "Active");
                clickToRemoveAction();
                return true;
            }
        }
        return false;
    }

    public void addActiveTrigger(String triggerType, String description, String date) {
       if(isExistingTriggerAvailable(description, date) == false) {
           clickAddTriggerButton();
           selectTriggerType(triggerType);
           typeTriggerDescription(description);
           Utilities.selectByText(activeDropDown, "Active");
           delay(1000);
           typeStartDate(date);
       }
    }

    public String resetMostRecentDateTrigger() {
        return getData("url", environment) + "resources/scripts/internal/resetMostRecentDateTriggered.php";
    }

    public String getTriggerURL(String triggerName) {
        String officeID = Utilities.locate(selectedOffice).getAttribute("value");
        return getData("url", environment) + "resources/scripts/" + triggerName +
                ".php?debug=1&office=" + officeID + "&testing=1";

   /*
   Plan To Use Remove The Above return Statement And
   Implement The Below return Statement
   When Trigger Rule Gets Merged Into StagingDemo
   Currently, Only A Few Trigger Rule Scenarios Pass In StagingDemo

        return getData("url", environment) + "resources/scripts/" + triggerName +
              ".php";
    */
    }

    public void editExistingTrigger(String description) {
        List<WebElement> listOfDescriptionValues = locateAll(descriptionColumnValues);
        By editButton = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[text()='"+ description +"']//following::span");
        for(WebElement descriptionValue : listOfDescriptionValues) {
            Deprecated.scrollToElementJS(descriptionValue);
            if (descriptionValue.getText().equals(description)) {
                delay(1000);
                Deprecated.scrollToElementJS(editButton);
                Utilities.locate(editButton).click();
                break;
            }
        }
    }
}