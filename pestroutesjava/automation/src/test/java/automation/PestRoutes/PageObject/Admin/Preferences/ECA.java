package automation.PestRoutes.PageObject.Admin.Preferences;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

public class ECA {

    Header header;
    AdminMainPage admin;
    PreferencesPage preferences;

    public String editECAButton = "//div[contains(text(),'Require Electronic Consent Agreement')]/preceding-sibling::div[text()='edit']";
    public String serviceFollowUpEmail = "//span[text()='Service Follow-up Email:']";
    public String clickSave = "//div[@id='requireElectronicConsent' and text()='save' and @style='display: block;']";
    public String checkBox = "//input[@type='checkbox' and @name='requireElectronicConsent']";
    public String uncheckedECA = "//input[@name='requireElectronicConsent' and @value='1' and not((@checked))]";
    public String checkedECA = "//input[@name='requireElectronicConsent' and @checked]";

    @Given("I have disabled ECA")
    public void disablingECA() throws InterruptedException {
        navigateToCustomerCommunication();
        disableECA();
    }

    public void navigateToCustomerCommunication() throws InterruptedException {
        admin = new AdminMainPage();
        header = new Header();
        preferences = new PreferencesPage();
        header.NavigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.customerCommunication);
    }

    //edit ECA
    public void clickEditECA() {
        Utilities.scrollToElement(serviceFollowUpEmail);
        Utilities.waitUntileElementIsVisible(editECAButton);
        Utilities.clickElement(editECAButton, Utilities.ElementType.XPath);
    }

    public void disableECA() {
        clickEditECA();
        try {
            WebElement elm = FindElement.elementByAttribute(uncheckedECA, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
                System.out.println("ECA Disabled");
            }
        } catch (Exception e) {
            Utilities.clickElement(checkBox, Utilities.ElementType.XPath);
        }
        clickSave();
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
        clickSave();
    }

    public void clickSave() {
        Utilities.waitUntileElementIsVisible(clickSave);
        Utilities.clickElement(clickSave, Utilities.ElementType.XPath);
    }
}