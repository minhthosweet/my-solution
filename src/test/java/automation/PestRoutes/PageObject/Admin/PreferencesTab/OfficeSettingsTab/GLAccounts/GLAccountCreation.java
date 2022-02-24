package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.GLAccounts;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab.ServiceTypes;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;

public class GLAccountCreation extends BasePage {
    TriggerRules triggerAdmin;
    OfficeSettingsObjects officeSettingsObjects;
    ServiceTypes serviceTypes;

    public String plusGLAccount = "//div[text()='+ GL Account']";
    public String glAccountNumberTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='glNumber']";
    public String glTitleTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='title']";
    public String glDescriptionTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='description']";
    public String randomGLAccountNumber = Double.toString(Utilities.generateRandomInteger(9)) + "$" + Utilities.generateRandomString(5) + Double.toString(Utilities.generateRandomInteger(9)) + "-" + Double.toString(Utilities.generateRandomInteger(9));
    public String saveButton = "//div[@id='preferenceHeader']/following-sibling::form//div[text()='save']";
    public String searchBox = "//div[@id='newPreferenceBody']//input[@placeholder='Search...']";
    public String glAccountNumberFieldValue = "//li[not(contains(@style,'display: none;'))]//input[@name='glNumber']/preceding-sibling::div[text()]";
    public String titleFieldValue = "//li[not(contains(@style,'display: none;'))]//input[@name='title']/preceding-sibling::span[text()]";
    public String descriptionFieldValue = "//li[not(contains(@style,'display: none;'))]//input[@name='description']/preceding-sibling::span[text()]";
    public String glAccountNumberInServiceType = "//li[not(contains(@style,'display: none;'))]//div[@data-global]/preceding-sibling::div[1]";
    private By originalOffice_glAccountDropDown = By.xpath("//div[@id='serviceTypesItem']//select[@name='glAccountID']");
    private By glAccountHeader = By.xpath("//div[@id='preferenceHeader']/div[text()='GL Account']");

    public void navigateToGLAccount() {
        triggerAdmin = new TriggerRules();
        officeSettingsObjects = new OfficeSettingsObjects();
        Utilities.waitUntileElementIsVisible(triggerAdmin.officeSettingsText);
        Utilities.clickElement(officeSettingsObjects.gLAccounts, Utilities.ElementType.XPath);
    }

    public void clickCreateGLAccount() {
        Utilities.waitUntileElementIsVisible(plusGLAccount);
        Utilities.clickElement(plusGLAccount, Utilities.ElementType.XPath);
    }

    public void setGLAccountNumber(String glAccountNumber) {
        Utilities.waitUntileElementIsVisible(glAccountNumberTextBox);
        FindElement.elementByAttribute(glAccountNumberTextBox, FindElement.InputType.XPath).sendKeys(glAccountNumber);
    }

    public void setGLAccountTitle(String glTitle) {
        Utilities.waitUntileElementIsVisible(glTitleTextBox);
        FindElement.elementByAttribute(glTitleTextBox, FindElement.InputType.XPath).sendKeys(glTitle);
    }

    public void setGLAccountDescription(String glDescription) {
        Utilities.waitUntileElementIsVisible(glDescriptionTextBox);
        FindElement.elementByAttribute(glDescriptionTextBox, FindElement.InputType.XPath).sendKeys(glDescription);
        getCorrectedGLAccountNumber();
    }

    public String getCorrectedGLAccountNumber() {
        String validaGLAccountNumber = randomGLAccountNumber.replaceAll("[a-zA-Z]", "").replace("$", "").substring(0, 20);
        return validaGLAccountNumber;
    }

    public void clickSaveButton() {
        Utilities.waitUntileElementIsVisible(saveButton);
        Utilities.clickElement(saveButton, Utilities.ElementType.XPath);
    }

    public void searchGLAccountByDescription(String descriptionText) {
        Utilities.waitUntileElementIsVisible(searchBox);
        FindElement.elementByAttribute(searchBox, FindElement.InputType.XPath).sendKeys(descriptionText);
    }

    public String getGLAccountNumber() {
        return Utilities.getElementTextValue(glAccountNumberFieldValue, Utilities.ElementType.XPath).trim();
    }

    public String getGLAccountNumberInServiceType() {
        // The GL Account Element Is Located In Different Places Depending On The Office.
        // try - catch Statement Is Flexible To Select From Either Place
        try {
            if(Utilities.elementIsVisible(glAccountHeader)) {
                return Utilities.getElementTextValue(glAccountNumberInServiceType, Utilities.ElementType.XPath).trim();
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        editService("Automation Renewal");
        return find(originalOffice_glAccountDropDown).getAttribute("placeholder");
    }

    public String getGLTitle() {
        return Utilities.getElementTextValue(titleFieldValue, Utilities.ElementType.XPath);
    }

    public String getGLDescription() {
        return Utilities.getElementTextValue(descriptionFieldValue, Utilities.ElementType.XPath);
    }

    public void editService(String needDescription) {
        serviceTypes = new ServiceTypes();
        serviceTypes.clickEditButton(needDescription);
    }

    public void clickGLAccountOnServiceType(String needServiceType) {
        Utilities.waitUntileElementIsVisible("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']");
        Utilities.clickElement("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']", Utilities.ElementType.XPath);
    }

    public void selectGLAccountFromDropDown(String needServiceType, String needGLNumber) {
        Utilities.waitUntileElementIsVisible("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']/option[text()='" + needGLNumber + "']");
        Utilities.clickElement("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']/option[text()='" + needGLNumber + "']", Utilities.ElementType.XPath);
    }

    public void saveService() {
        serviceTypes = new ServiceTypes();
        serviceTypes.clickSave();
    }

    public void selectFromGLAccount(String serviceType, String glNumber) {
        // The GL Account Element Is Located In Different Places Depending On The Office.
        // try - catch Statement Is Flexible To Select From Either Place
        try{
            clickGLAccountOnServiceType(serviceType);
            selectGLAccountFromDropDown(serviceType, glNumber);
        } catch(Exception exc) {
            exc.printStackTrace();
            selectFromDropDown(glNumber, originalOffice_glAccountDropDown);
        }
    }
}