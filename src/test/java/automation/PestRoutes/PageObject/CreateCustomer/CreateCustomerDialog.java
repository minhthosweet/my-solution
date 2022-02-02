package automation.PestRoutes.PageObject.CreateCustomer;

import automation.PestRoutes.PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import static automation.PestRoutes.Utilities.Utilities.*;
import org.openqa.selenium.WebElement;

public class CreateCustomerDialog extends BasePage {

    // **********Objects**********
    public String dialogTitle = "//span[@id = 'ui-id-11']";
    public String newCustTitle = "//div[@id= 'customerPanel']/h3";
    public String firstNameInputField = "//input[@name= 'fname']";
    private By firstNameField = By.xpath("//input[@name= 'fname']");
    public String lastNameInputField = "//input[@name= 'lname']";
    private By lastNameField = By.xpath("//input[@name= 'lname']");
    public String cellPhoneInputField = "//input[@name= 'phone1']";
    public String homePhoneInputField = "//input[@name= 'phone2']";
    public String emailAddressInputField = "//input[@name= 'email']";
    private By emailAddressField = By.xpath("//input[@name= 'email']");
    public String propertyTypeDropDown = "//select[@name= 'commercialAccount']";
    public String unitTypeDropDown = "//select[@name= 'isMultiUnit']";
    public String selectSourceDropDown = "//div[@id='customerPanel']/div[@class='contactInfo']//select[@name='sourceID']";
    public String companyNameInputField = "//input[@name= 'companyName']";
    public String specialHandlingInputField = "//textarea[@name= 'specialScheduling']";
    public String smsCheckBox = "//input[@name= 'smsReminders']";
    public String emailCheckBox = "//input[@name= 'emailReminders']";
    public String voiceCheckBox = "//input[@name= 'phoneReminders']";
    public String smsSelectedCheckBox = "//input[@checked and @name = 'smsReminders']";
    public String emailSelectedCheckBox = "//input[@checked and @name = 'emailReminders']";
    public String voiceSelectedCheckBox = "//input[@checked and @name = 'phoneReminders']";
    public String genericFlagsDropDown = "//select[@id= 'customerGenericFlags']";
    public String paidInFullCheckBox = "//input[@name= 'paidInFull']";
    public String switchOverCheckBox = "//input[@name= 'switchOver']";
    public String salesRepAPayCheckBox = "//input[@name= 'salesmanAPay']";
    public String pendingCancelCheckBox = "//input[@name= 'pendingCancellation']";
    public String prefersPaperCheckBox = "//input[@name= 'prefersPaper']";
    public String purpleDragonCheckBox = "//input[@name= 'purpleDragon']";
    public String addressInputField = "//input[@name= 'address']";
    private By propertyAddressField = By.xpath("//input[@name= 'address']");

    public String zipCodeInputField = "//input[@name= 'zip']";
    private By zipCodeField = By.xpath("//input[@name= 'zip']");
    public String cityInputField = "//input[@name= 'city']";
    private By cityField = By.xpath("//input[@name='city']");
    public String stateDropDown = "//select[@name= 'state']";
    private By stateDropdown = By.xpath("//select[@name='state']");
    public String countyDropDown = "//select[@name= 'county']";
    public String countryDropDown = "//select[@name= 'countryID']";
    public String taxPercentageInputField = "//input[@name= 'taxRate']";
    public String taxPercentageOverrideCheckbox = "//input[@id='overrideTax']";
    public String mainTaxPercentage = "//input[@name='taxRate']";
    public String stateTaxPercentage = "//input[@name='stateTax']";
    public String cityTaxPercentage = "//input[@name='cityTax']";
    public String countyTaxPercentage = "//input[@name='countyTax']";
    public String customTaxPercentage = "//input[@name='customTax']";
    public String district1TaxPercentage = "//input[@name='districtTax1']";
    public String district2TaxPercentage = "//input[@name='districtTax2']";
    public String district3TaxPercentage = "//input[@name='districtTax3']";
    public String district4TaxPercentage = "//input[@name='districtTax4']";
    public String district5TaxPercentage = "//input[@name='districtTax5']";
    public String divisionDropDown = "//select[@name= 'divisionID']";
    public String info = "//a[text()= 'Info']";
    public String mapCode = "//input[@name= 'mapCode']";
    public String clickAddFlag = "//span[text()='Add Flag']";

    // Property Types
    public String residentialProperty = "Residential Property";
    public String commercialProperty = "Commercial Property";

    // Constructors
    CustomerViewDialog_Admin customerAdmin;

    // **********Functions**********

    /******************************************************************************************************************
     * Actions Below methods perform click/select on an object
     */
    // Drop downs
    public void selectProperty(String needPropertyType) {
        Utilities.selectValueFromDropDownByValue(propertyTypeDropDown, needPropertyType);
    }

    public void selectUnit(String needUnit) {
        Utilities.selectValueFromDropDownByValue(unitTypeDropDown, needUnit);
    }

    public void selectSource(String needSource) {
        Utilities.selectValueFromDropDownByValue(selectSourceDropDown, needSource);
    }

    public void selectGenericFlag(String needFlag) {
        FindElement.elementByAttribute(genericFlagsDropDown, InputType.XPath).sendKeys(needFlag);
    }

    public void selectState(String needState) {
        Utilities.selectValueFromDropDownByValue(stateDropDown, needState);
    }

    public void selectCounty(String needCounty) {
        Utilities.selectValueFromDropDownByValue(countyDropDown, needCounty);
    }

    public void selectCountry(String needCountry) {
        Utilities.selectValueFromDropDownByValue(countryDropDown, needCountry);
    }

    public void selectDivision(String needDivision) {
        Utilities.selectValueFromDropDownByValue(divisionDropDown, needDivision);
    }

    // Click or Unclick Check boxes
    public void clickSmsCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute("//input[@name= 'smsReminders' and @checked]", InputType.XPath);
            if (!elm.isDisplayed()) {
                Utilities.clickElement(smsCheckBox, ElementType.XPath);
            }
        } catch (Exception e) {
        }
    }

    public void clickEmailCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute(emailSelectedCheckBox, InputType.XPath);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Utilities.clickElement(emailCheckBox, ElementType.XPath);
        }
    }

    public void clickVoiceCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute(voiceSelectedCheckBox, InputType.XPath);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Utilities.clickElement(voiceCheckBox, ElementType.XPath);
        }
    }

    public void unClickSmsCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute(smsSelectedCheckBox, InputType.XPath);
            if (elm.isDisplayed()) {
                Utilities.clickElement(smsSelectedCheckBox, ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("SMS checkBox unselected");
        }
    }

    public void unClickEmailCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute(emailSelectedCheckBox, InputType.XPath);
            if (elm.isDisplayed()) {
                Utilities.clickElement(emailSelectedCheckBox, ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("Email checkBox unselected");
        }
    }

    public void unClickVoiceCheckBox() {
        try {
            WebElement elm = FindElement.elementByAttribute(voiceSelectedCheckBox, InputType.XPath);
            if (elm.isDisplayed()) {
                Utilities.clickElement(voiceSelectedCheckBox, ElementType.XPath);
            }
        } catch (Exception e) {
            System.out.println("Voice Checkbox unselected");
        }
    }

    public void clickPaidInFullCheckBox() {
        Utilities.clickElement(paidInFullCheckBox, ElementType.XPath);
    }

    public void clickSwitchOverCheckBox() {
        Utilities.clickElement(switchOverCheckBox, ElementType.XPath);
    }

    public void clickSalesRepAPayCheckBox() {
        Utilities.clickElement(salesRepAPayCheckBox, ElementType.XPath);
    }

    public void clickPendingCancelCheckBox() {
        Utilities.clickElement(pendingCancelCheckBox, ElementType.XPath);
        customerAdmin = new CustomerViewDialog_Admin();
        customerAdmin.cancellationCategory(2);
        customerAdmin.cancellationNotes();
        clickAddFlag();
    }

    public void clickPrefersPaperCheckBox() {
        Utilities.clickElement(prefersPaperCheckBox, ElementType.XPath);
    }

    public void clickPurpleDragonCheckBox() {
        Utilities.clickElement(purpleDragonCheckBox, ElementType.XPath);
    }

    public void clickOverrideTaxCheckBox() {
        Utilities.clickElement(taxPercentageOverrideCheckbox, ElementType.XPath);
    }

    /*
     * Setter methods Below methods insert text in the input fields
     */
    public void setFirstName(String needFirstName) {
        Utilities.waitUntileElementIsVisible(firstNameInputField);
        FindElement.elementByAttribute(firstNameInputField, InputType.XPath).sendKeys(needFirstName);
    }
    public void typeFirstName (String firstName) {
        delay(3000);
        type(firstName, firstNameField);
    }

    public void setLastName(String needLastName) {
        FindElement.elementByAttribute(lastNameInputField, InputType.XPath).sendKeys(needLastName);
    }
    
    public void typeLastName (String lastName) {
        type(lastName, lastNameField);
    }

    public void setCellPhone(String needCellPhoneNumber) {
        FindElement.elementByAttribute(cellPhoneInputField, InputType.XPath).sendKeys(needCellPhoneNumber);
    }

    public void setHomePhone(String needHomePhoneNumber) {
        FindElement.elementByAttribute(homePhoneInputField, InputType.XPath).sendKeys(needHomePhoneNumber);
    }

    public void setEmailAddress(String needEmail) {
        FindElement.elementByAttribute(emailAddressInputField, InputType.XPath).sendKeys(needEmail);
    }

    public void setCompanyName(String needCompanyName) {
        FindElement.elementByAttribute(companyNameInputField, InputType.XPath).sendKeys(needCompanyName);
    }

    public void setSpecialHandling(String needSpecialHandlingNotes) {
        FindElement.elementByAttribute(specialHandlingInputField, InputType.XPath).sendKeys(needSpecialHandlingNotes);
    }

    public void setAddress(String needAddress) {
        try {
            FindElement.elementByAttribute(addressInputField, InputType.XPath).sendKeys(needAddress);
        } catch (ElementNotInteractableException notInteractableException){
            System.out.println("Element is not intractable, trying again ");
            FindElement.elementByAttribute(addressInputField, InputType.XPath).sendKeys(needAddress);
        }
    }

    public void setZipCode(String needZipCode) throws InterruptedException {
        FindElement.elementByAttribute(zipCodeInputField, InputType.XPath).sendKeys(needZipCode);
        Thread.sleep(200);
    }

    public void typeZipCode(String zipCode) { type(zipCode, zipCodeField); }

    public void setCity(String needCity) throws InterruptedException {
        Thread.sleep(200);
        FindElement.elementByAttribute(cityInputField, InputType.XPath).sendKeys(Keys.CONTROL, "a");
        FindElement.elementByAttribute(cityInputField, InputType.XPath).sendKeys(needCity);
    }

    public void typeCity(String city) {
        type(city, cityField);
    }

    public void setTaxPercentage(String needTaxPercentage) {
        FindElement.elementByAttribute(taxPercentageInputField, InputType.XPath).sendKeys(needTaxPercentage);
    }

    public void setMapCode(String needMapCode) {
        FindElement.elementByAttribute(mapCode, InputType.XPath).sendKeys(needMapCode);
    }

    public void clickInfo() {
        Utilities.waitUntileElementIsVisible(info);
        Utilities.clickElement(info, ElementType.XPath);
    }

    public void clickAddFlag() {
        Utilities.clickElement(clickAddFlag, ElementType.XPath);
    }

    public void typePhone1(String phone) {
        type(phone, find(By.xpath(cellPhoneInputField)));
    }

    /*
     * Getter methods Below methods get text value from an object
     */
    public String getDialogTitle() {
        return Utilities.getElementTextValue(dialogTitle, ElementType.XPath);
    }

    public String getNewCustTitle() {
        return Utilities.getElementTextValue(newCustTitle, ElementType.XPath);
    }

    public String getTaxRate(String needTaxField) {
        return Utilities.getAttributeValue(needTaxField, "value");
    }

    public String getAddress() {
        return Utilities.getAttributeValue(addressInputField, "value");
    }

    public String getZipCode() {
        return Utilities.getAttributeValue(zipCodeInputField, "value");
    }

    public String getCity() {
        return Utilities.getAttributeValue(cityInputField, "value");
    }

    public String getCustomerFullName() {
        String customerFullName = find(firstNameField).getAttribute("value") + " " +
                              find(lastNameField).getAttribute("value");
        return customerFullName;
    }

    public String getCustomerFirstName() {
        return find(firstNameField).getAttribute("value");
    }

    public void typePropertyAddress(String propertyAddress) {
        type(propertyAddress, propertyAddressField);
    }

    public String getPropertyAddress(){
        return find(propertyAddressField).getAttribute("value");
    }

    public String getCityStateZip() {
        String city = find(cityField).getAttribute("value");
        String state = find(stateDropdown).getAttribute("value");
        String zipCode = find(zipCodeField).getAttribute("value");
        return city + ", " + state + " " + zipCode;
    }

    public void typeEmailAddress(String emailAddress) {
        type(emailAddress, emailAddressField);
    }

    public String getEmailAddress() {
        return find(emailAddressField).getAttribute("value");
    }

    public String getPhone1() {
        return find(By.xpath(cellPhoneInputField)).getAttribute("value");
    }
}
