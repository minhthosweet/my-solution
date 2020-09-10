package automation.PestRoutes.PageObject.Admin.Preferences;

import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Given;

public class ECA {

    Header header;
    AdminMainPage admin;
    PreferencesPage preferences;

    public String editECAButton = "//div[contains(text(),'Require Electronic Consent Agreement')]/preceding-sibling::div[text()='edit']";
    public String ECAEnabledCheckBox = "//div[contains(text(),'Require Electronic Consent Agreement')]/following-sibling::div[@style='display: block;']";
    public String ECADisabledCheckBox = "//div[contains(text(),'Require Electronic Consent Agreement')]/following-sibling::div[@style='display: none;']";
    public String agreementSignatureEmailSubject = "//span[contains(text(),'Agreement Signature Email Subject:')]";
    public String clickSave = "//div[@id='requireElectronicConsent' and text()='save']";
    public String checkBox = "//input[@type='checkbox' and @name='requireElectronicConsent']";

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
        Utilities.scrollToElement(agreementSignatureEmailSubject);
        Utilities.waitUntileElementIsVisible(editECAButton);
        Utilities.clickElement(editECAButton, Utilities.ElementType.XPath);
    }

    public void disableECA() {
        clickEditECA();
        try {
            Utilities.waitUntileElementIsVisible(ECAEnabledCheckBox);
            Utilities.clickElement(checkBox, Utilities.ElementType.XPath);
        } catch (Exception e) {
            System.out.println("ECA Disabled /n" + e);
        }
        clickSave();
    }

    public void enableECA() {
        clickEditECA();
        try {
            Utilities.waitUntileElementIsVisible(ECADisabledCheckBox);
            Utilities.clickElement(checkBox, Utilities.ElementType.XPath);
        } catch (Exception e) {
            System.out.println("ECA Enabled /n" + e);
        }
        clickSave();
    }

    public void clickSave() {
        Utilities.waitUntileElementIsVisible(clickSave);
        Utilities.clickElement(clickSave, Utilities.ElementType.XPath);
    }
}
