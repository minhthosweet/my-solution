package automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.GLAccounts;

import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.OfficeSettingsObjects;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab.ServiceTypes;
import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;

public class GLAccountCreation extends BasePage {
    TriggerRules triggerAdmin;
    OfficeSettingsObjects officeSettingsObjects;
    ServiceTypes serviceTypes;

    public String plusGLAccount = "//div[text()='+ GL Account']";
    public String glAccountNumberTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='glNumber']";
    public String glTitleTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='title']";
    public String glDescriptionTextBox = "//div[@id='preferenceHeader']/following-sibling::form//input[@name='description']";
    public String randomGLAccountNumber = Double.toString(GetData.generateRandomInteger(9)) + "$" + GetData.generateRandomString(5) + Double.toString(GetData.generateRandomInteger(9)) + "-" + Double.toString(GetData.generateRandomInteger(9));
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
        Legacy.waitVisible(triggerAdmin.officeSettingsText);
        Legacy.clickElement(officeSettingsObjects.gLAccounts);
    }

    public void clickCreateGLAccount() {
        Legacy.waitVisible(plusGLAccount);
        Legacy.clickElement(plusGLAccount);
    }

    public void setGLAccountNumber(String glAccountNumber) {
        Legacy.waitVisible(glAccountNumberTextBox);
        Legacy.locate(glAccountNumberTextBox).sendKeys(glAccountNumber);
    }

    public void setGLAccountTitle(String glTitle) {
        Legacy.waitVisible(glTitleTextBox);
        Legacy.locate(glTitleTextBox).sendKeys(glTitle);
    }

    public void setGLAccountDescription(String glDescription) {
        Legacy.waitVisible(glDescriptionTextBox);
        Legacy.locate(glDescriptionTextBox).sendKeys(glDescription);
        getCorrectedGLAccountNumber();
    }

    public String getCorrectedGLAccountNumber() {
        String validaGLAccountNumber = randomGLAccountNumber.replaceAll("[a-zA-Z]", "").replace("$", "").substring(0, 20);
        return validaGLAccountNumber;
    }

    public void clickSaveButton() {
        Legacy.waitVisible(saveButton);
        Legacy.clickElement(saveButton);
    }

    public void searchGLAccountByDescription(String descriptionText) {
        Legacy.waitVisible(searchBox);
        Legacy.locate(searchBox).sendKeys(descriptionText);
    }

    public String getGLAccountNumber() {
        return Legacy.getElementTextValue(glAccountNumberFieldValue).trim();
    }

    public String getGLAccountNumberInServiceType() {
        // The GL Account Element Is Located In Different Places Depending On The Office.
        // try - catch Statement Is Flexible To Select From Either Place
        try {
            if(Utilities.isVisible(glAccountHeader)) {
                return Legacy.getElementTextValue(glAccountNumberInServiceType).trim();
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        editService("Automation Renewal");
        return Utilities.locate(originalOffice_glAccountDropDown).getAttribute("placeholder");
    }

    public String getGLTitle() {
        return Legacy.getElementTextValue(titleFieldValue);
    }

    public String getGLDescription() {
        return Legacy.getElementTextValue(descriptionFieldValue);
    }

    public void editService(String needDescription) {
        serviceTypes = new ServiceTypes();
        serviceTypes.clickEditButton(needDescription);
    }

    public void clickGLAccountOnServiceType(String needServiceType) {
        Legacy.waitVisible("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']");
        Legacy.clickElement("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']");
    }

    public void selectGLAccountFromDropDown(String needServiceType, String needGLNumber) {
        Legacy.waitVisible("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']/option[text()='" + needGLNumber + "']");
        Legacy.clickElement("//input[@value='" + needServiceType + "']/following-sibling::select[@placeholder='GL Account']/option[text()='" + needGLNumber + "']");
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
            Utilities.selectByText(originalOffice_glAccountDropDown, glNumber);
        }
    }
}