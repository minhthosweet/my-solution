package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.*;

import static automation.PestRoutes.Utilities.Data.AppData.*;
import static automation.PestRoutes.Utilities.Utilities.*;

import automation.PestRoutes.Utilities.Legacy;
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
    private By allMultiDropDownSelections = By.xpath("//form[@id='triggerRuleForm']/div[2]//a");

    public void clickSaveButton() {
        Legacy.scrollToElement(addTriggerButton);
        Legacy.waitVisible(saveButton);
        Legacy.clickElement(saveButton);
    }

    // Trigger Filter Actions
    public void navigateToTriggerRules() {
        officeSettingsObjects= new OfficeSettingsObjects();
        Legacy.waitVisible(officeSettingsText);
        Legacy.clickElement(officeSettingsObjects.triggerRules);
    }

    public void clickAddTrigerButton() {
        Legacy.clickElement(addTriggerButton);
    }

    public void clickEditTrigger(String descriptionName) {
        Legacy.clickElement("//div[text() = '" + descriptionName + "']//parent::div//span[text()='edit']"
        );
    }

    public void searchTrigger(String description) {
        Legacy.waitVisible(searchTrigger);
        Legacy.locate(searchTrigger).sendKeys(description);
    }

    public void selectDropdown(String needObject, String needTextValue) {
        Legacy.waitVisible(needObject);
        Legacy.selectByText(needObject, needTextValue);
    }

    // Get Description text value from Landing page
    public String getDescriptionText(String descriptionSet) {
        return Legacy.getElementTextValue(
                "//div[@id= 'triggerRulesHeader']/following-sibling::div//div[text()='" + descriptionSet + "']"
        );
    }

    // Setter methods
    public void setDescription(String needDescription) {
        Legacy.locate(descriptionInputField).sendKeys(needDescription);
    }

    public void setStartDate(String needStartDate) {
        Legacy.waitVisible(startDateInputField);
        Legacy.clickElement(startDateInputField);
        Legacy.locate(startDateInputField).clear();
        Legacy.locate(startDateInputField).sendKeys(needStartDate);
    }

    public void setEndDate(String needEndDate) throws InterruptedException {
        Legacy.waitVisible(endDateInputField);
        Legacy.clickElement(endDateInputField);
        Legacy.locate(endDateInputField).clear();
        Legacy.locate(endDateInputField).sendKeys(needEndDate);
    }

    public void setDaysAfterChange(String days) {
        Legacy.waitVisible(daysAfterChange);
        Legacy.locate(daysAfterChange).sendKeys(days);
    }

    // trigger Queue query
    @When("I execute the trigger queue script")
    public void hitTriggerQueue() {
        GetWebDriver.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
    }

    //getter method
    public WebElement getDescription(String needText) {
        return Legacy.locate("//div[text()='" + needText + "']");
    }

    public void clickAddTriggerButton() {
        Legacy.scrollToElementJS(Utilities.locate(greenAddTriggerButton));
        click(greenAddTriggerButton);
    }

    public void selectTriggerType(String triggerType) {
        waitVisible(triggerTypeDropDown);
        Utilities.selectByText(triggerTypeDropDown, triggerType);
    }

    public void typeTriggerDescription(String description) {
        delay(500);
        type(descriptionField, description);
    }

    public void typeStartDate(String date) {
        isVisible(startDateField);
        Legacy.type(date, Utilities.locate(startDateField));
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
                Legacy.scrollToElementJS(removeButton);
                click(removeActionButton);
            }
        }
    }

    public boolean isExistingTriggerAvailable(String description, String date){
        List<WebElement> listOfDescriptionValues = locateAll(descriptionColumnValues);
        By editButton = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[text()='"+ description +"']//following::span");
        for(WebElement descriptionValue : listOfDescriptionValues) {
            Legacy.scrollToElementJS(descriptionValue);
            if (descriptionValue.getText().equals(description)) {
                delay(1000);
                Legacy.scrollToElementJS(editButton);
                Utilities.locate(editButton).click();
                typeStartDate(date);
                selectByText(activeDropDown, "Active");
                removeAllMultiDropDownSelections();
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
        delay(3000);
        return getData("url", environment) + "resources/scripts/internal/resetMostRecentDateTriggered.php";
    }

    public String getTriggerURL(String triggerName) {
        delay(3000);
        String officeID = getAttribute(selectedOffice, "value");
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
            Legacy.scrollToElementJS(descriptionValue);
            if (descriptionValue.getText().equals(description)) {
                delay(1000);
                Legacy.scrollToElementJS(editButton);
                Utilities.locate(editButton).click();
                break;
            }
        }
    }

    public void removeAllMultiDropDownSelections() {
        List<WebElement> allMultiSelections = locateAll(allMultiDropDownSelections);
        for (WebElement selection : allMultiSelections) {
            if (selection.isDisplayed()) {
                Legacy.scrollToElementJS(selection);
                selection.click();
            }
        }
    }
}