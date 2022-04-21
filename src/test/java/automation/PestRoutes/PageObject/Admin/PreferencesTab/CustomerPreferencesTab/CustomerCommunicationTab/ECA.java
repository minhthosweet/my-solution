package automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.CustomerCommunicationTab;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.PreferencesPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.Given;
import org.apache.commons.lang3.SystemUtils;
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
        header.navigateTo(header.adminTab);
        admin.navigateTo(admin.preferences);
        preferences.navigateTo(preferences.customerPreferencesRelatedNav, preferences.customerCommunication);
    }

    //edit ECA
    public void clickEditECA() {
        Legacy.waitVisible(editECAButton);
        Legacy.scrollToElementJS(editECAButton);
        Legacy.jsClickElement(editECAButton);
    }

    public void disableECA() {
        clickEditECA();
        try {
            WebElement elm = Legacy.locate(uncheckedECA);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Legacy.clickElement(checkBox);
        }
        clickSave();
    }

    public void enableECA() {
        clickEditECA();
        try {
            WebElement elm = Legacy.locate(checkedECA);
            if (elm.isDisplayed()) {
                System.out.println("ECA Enabled");
            }
        } catch (Exception e) {
            Legacy.clickElement(checkBox);
        }
        clickSave();
    }

    public void clickSave() {
        if (SystemUtils.IS_OS_LINUX) {
            Utilities.acceptAlert();
        }
        Legacy.scrollToElement(serviceFollowUpEmail);
        Legacy.waitVisible(clickSave);
        Legacy.clickElement(clickSave);
    }
}
