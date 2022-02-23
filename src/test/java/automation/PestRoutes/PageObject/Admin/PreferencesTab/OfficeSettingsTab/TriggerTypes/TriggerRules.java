package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import static automation.PestRoutes.Utilities.Utilities.*;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TriggerRules extends PreferencesPage {

    OfficeSettingsObjects officeSettingsObjects;
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
        Utilities.scrollToElement(addTriggerButton);
        Utilities.waitUntileElementIsVisible(saveButton);
        Utilities.clickElement(saveButton, ElementType.XPath);
    }

    // Trigger Filter Actions
    public void navigateToTriggerRules() {
        officeSettingsObjects= new OfficeSettingsObjects();
        Utilities.waitUntileElementIsVisible(officeSettingsText);
        Utilities.clickElement(officeSettingsObjects.triggerRules, ElementType.XPath);
    }

    public void clickAddTrigerButton() {
        Utilities.clickElement(addTriggerButton, ElementType.XPath);
    }

    public void clickEditTrigger(String descriptionName) {
        Utilities.clickElement("//div[text() = '" + descriptionName + "']//parent::div//span[text()='edit']",
                ElementType.XPath);
    }

    public void searchTrigger(String description) {
        Utilities.waitUntileElementIsVisible(searchTrigger);
        FindElement.elementByAttribute(searchTrigger, InputType.XPath).sendKeys(description);
    }

    public void selectDropdown(String needObject, String needTextValue) {
        Utilities.waitUntileElementIsVisible(needObject);
        Utilities.selectValueFromDropDownByValue(needObject, needTextValue);
    }

    // Get Description text value from Landing page
    public String getDescriptionText(String descriptionSet) {
        return Utilities.getElementTextValue(
                "//div[@id= 'triggerRulesHeader']/following-sibling::div//div[text()='" + descriptionSet + "']",
                ElementType.XPath);
    }

    // Setter methods
    public void setDescription(String needDescription) {
        FindElement.elementByAttribute(descriptionInputField, InputType.XPath).sendKeys(needDescription);
    }

    public void setStartDate(String needStartDate) {
        Utilities.waitUntileElementIsVisible(startDateInputField);
        Utilities.clickElement(startDateInputField, ElementType.XPath);
        FindElement.elementByAttribute(startDateInputField, InputType.XPath).clear();
        FindElement.elementByAttribute(startDateInputField, InputType.XPath).sendKeys(needStartDate);
    }

    public void setEndDate(String needEndDate) throws InterruptedException {
        Utilities.waitUntileElementIsVisible(endDateInputField);
        Utilities.clickElement(endDateInputField, ElementType.XPath);
        FindElement.elementByAttribute(endDateInputField, InputType.XPath).clear();
        FindElement.elementByAttribute(endDateInputField, InputType.XPath).sendKeys(needEndDate);
    }

    public void setDaysAfterChange(String days) {
        Utilities.waitUntileElementIsVisible(daysAfterChange);
        FindElement.elementByAttribute(daysAfterChange, InputType.XPath).sendKeys(days);
    }

    // trigger Queue query
    @When("I execute the trigger queue script")
    public void hitTriggerQueue() {
        Utilities.navigateToUrl("https://adityam.pestroutes.com/resources/scripts/triggerQueue.php");
    }

    //getter method
    public WebElement getDescription(String needText) {
        return FindElement.elementByAttribute("//div[text()='" + needText + "']", InputType.XPath);
    }

    public void clickAddTriggerButton() {
        scrollToElementJS(find(greenAddTriggerButton));
        click(greenAddTriggerButton);
    }

    public void selectTriggerType(String triggerType) {
        waitUntileElementIsVisible(triggerTypeDropDown);
        selectFromDropDown(triggerType, triggerTypeDropDown);
    }

    public void typeTriggerDescription(String description) {
        // The Following 3 Methods (waitUntileElementIsVisible, elementIsVisible, isPresent) Did Not Work
        // However delay Worked Every Time
        // waitUntileElementIsVisible(descriptionField);
        // elementIsVisible(descriptionField);
        // isPresent(descriptionField);
        delay(500);
        type(description, descriptionField);
    }

    public void typeStartDate(String date) {
        waitUntileElementIsVisible(startDateField);
        type(date, find(startDateField));
    }

    public void setAllTriggersToNotActive(String active, String notActive){
        List<WebElement> listOfActiveValues = findElements(activeColumnValues);
        for(WebElement activeValue : listOfActiveValues) {
            if (activeValue.getText().equals(active)) {
                String activeDataValue = activeValue.getText();
                System.out.println(activeDataValue);
                find(editTriggerButton).click();
                selectFromDropDown(notActive, activeDropDown);
                click(saveTriggerButton);
            }
        }
    }

    public void clickToRemoveAction() {
        if (elementIsVisible(removeActionButton)) {
            scrollToElementJS(removeActionButton);
            click(removeActionButton);
        }
    }

    public boolean isExistingTriggerAvailable(String description, String date){
        List<WebElement> listOfDescriptionValues = findElements(descriptionColumnValues);
        By editButton = By.xpath("//div[@id='triggerRulesTable']/div[2]//div[text()='"+ description +"']//following::span");
        for(WebElement descriptionValue : listOfDescriptionValues) {
            scrollToElementJS(descriptionValue);
            if (descriptionValue.getText().equals(description)) {
                delay(1000);
                scrollToElementJS(editButton);
                find(editButton).click();
                typeStartDate(date);
                selectFromDropDown("Active", activeDropDown);
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
           selectFromDropDown("Active", activeDropDown);
           typeStartDate(date);
       }
    }
}