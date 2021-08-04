package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerCommunicationTab;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Given;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebElement;

public class CustomerCommunicationTab {

    Header header;
    AdminMainPage admin;
    PreferencesPage preferences;

    public String editECAButton = "//div[contains(text(),'Require Electronic Consent Agreement')]/preceding-sibling::div[text()='edit']";
    public String serviceFollowUpEmail = "//span[text()='Service Follow-up Email:']";
    public String clickSaveECA = "//div[@id='requireElectronicConsent' and text()='save' and @style='display: block;']";
    public String checkBox = "//input[@type='checkbox' and @name='requireElectronicConsent']";
    public String uncheckedECA = "//input[@name='requireElectronicConsent' and @value='1' and not((@checked))]";
    public String checkedECA = "//input[@name='requireElectronicConsent' and @checked]";
    public String checkedDefaultSMSValue = "//select[@name='defaultSMS' and @value ='1']";
    public String editCustomerPreferences = "//form[@id='customerPreferences']//div[text()='edit']";
    public String enableDefaultSMSDropDown = "//select[@name='defaultSMS']";
    public String clickSaveCustomerPreferences = "//form[@id='customerPreferences']//div[text()='save']";

    @Given("I have disabled ECA")
    public void disablingECA() throws InterruptedException {
        navigateToCustomerCommunication();
        disableECA();
    }

    public void navigateToCustomerCommunication() throws InterruptedException {
        admin = new AdminMainPage();
        header = new Header();
        preferences = new PreferencesPage();
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.customerCommunication);
    }

    //edit ECA
    public void clickEditECA() {
        Utilities.waitUntileElementIsVisible(editECAButton);
        Utilities.scrollToElementJS(editECAButton);
        Utilities.jsClickElement(editECAButton, Utilities.ElementType.XPath);
    }

    public void disableECA() {
        clickEditECA();
        try {
            WebElement elm = FindElement.elementByAttribute(uncheckedECA, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                System.out.println("ECA is disabled");
            }
        } catch (Exception e) {
            Utilities.clickElement(checkBox, Utilities.ElementType.XPath);
        }
        clickECASaveButton();
    }

    public void enableECA() {
        clickEditECA();
        try {
            WebElement elm = FindElement.elementByAttribute(checkedECA, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                System.out.println("ECA Enabled");
            }
        } catch (Exception e) {
            Utilities.clickElement(checkBox, Utilities.ElementType.XPath);
        }
        clickECASaveButton();
    }

    public void clickECASaveButton() {
        if (SystemUtils.IS_OS_LINUX) {
            Utilities.acceptAlertLinux();
        }
        Utilities.scrollToElement(serviceFollowUpEmail);
        Utilities.waitUntileElementIsVisible(clickSaveECA);
        Utilities.clickElement(clickSaveECA, Utilities.ElementType.XPath);
    }

    public void saveCustomerPreferences() {
        if (SystemUtils.IS_OS_LINUX) {
            Utilities.acceptAlertLinux();
        }
        Utilities.waitUntileElementIsVisible(clickSaveCustomerPreferences);
        Utilities.clickElement(clickSaveCustomerPreferences, Utilities.ElementType.XPath);
    }

    public void clickEditCustomerPreferences(){
        Utilities.waitUntileElementIsVisible(editCustomerPreferences);
        Utilities.jsClickElement(editCustomerPreferences, Utilities.ElementType.XPath);
    }

    public void enableDefaultSMS(){
        try {
            WebElement elm = FindElement.elementByAttribute(checkedDefaultSMSValue, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                System.out.println("Default SMS CheckBox is Enabled");
            }
        } catch (Exception e) {
            Utilities.selectValueFromDropDownByValue(enableDefaultSMSDropDown, "Yes");
        }
        saveCustomerPreferences();
    }
}
