package automation.PestRoutes.PageObject.CreateCustomer;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Admin;
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
        Legacy.selectByText(propertyTypeDropDown, needPropertyType);
    }

    public void selectUnit(String needUnit) {
        Legacy.selectByText(unitTypeDropDown, needUnit);
    }

    public void selectSource(String needSource) {
        Legacy.selectByText(selectSourceDropDown, needSource);
    }

    public void selectGenericFlag(String needFlag) {
        Legacy.locate(genericFlagsDropDown).sendKeys(needFlag);
    }

    public void selectState(String needState) {
        Legacy.selectByText(stateDropDown, needState);
    }

    public void selectCounty(String needCounty) {
        Legacy.selectByText(countyDropDown, needCounty);
    }

    public void selectCountry(String needCountry) {
        Legacy.selectByText(countryDropDown, needCountry);
    }

    public void selectDivision(String needDivision) {
        Legacy.selectByText(divisionDropDown, needDivision);
    }

    // Click or Unclick Check boxes
    public void clickSmsCheckBox() {
        try {
            WebElement elm = Legacy.locate("//input[@name= 'smsReminders' and @checked]");
            if (!elm.isDisplayed()) {
                Legacy.clickElement(smsCheckBox);
            }
        } catch (Exception e) {
        }
    }

    public void clickEmailCheckBox() {
        try {
            WebElement elm = Legacy.locate(emailSelectedCheckBox);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Legacy.clickElement(emailCheckBox);
        }
    }

    public void clickVoiceCheckBox() {
        try {
            WebElement elm = Legacy.locate(voiceSelectedCheckBox);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Legacy.clickElement(voiceCheckBox);
        }
    }

    public void unClickSmsCheckBox() {
        try {
            WebElement elm = Legacy.locate(smsSelectedCheckBox);
            if (elm.isDisplayed()) {
                Legacy.clickElement(smsSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("SMS checkBox unselected");
        }
    }

    public void unClickEmailCheckBox() {
        try {
            WebElement elm = Legacy.locate(emailSelectedCheckBox);
            if (elm.isDisplayed()) {
                Legacy.clickElement(emailSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("Email checkBox unselected");
        }
    }

    public void unClickVoiceCheckBox() {
        try {
            WebElement elm = Legacy.locate(voiceSelectedCheckBox);
            if (elm.isDisplayed()) {
                Legacy.clickElement(voiceSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("Voice Checkbox unselected");
        }
    }

    public void clickPaidInFullCheckBox() {
        Legacy.clickElement(paidInFullCheckBox);
    }

    public void clickSwitchOverCheckBox() {
        Legacy.clickElement(switchOverCheckBox);
    }

    public void clickSalesRepAPayCheckBox() {
        Legacy.clickElement(salesRepAPayCheckBox);
    }

    public void clickPendingCancelCheckBox() {
        Legacy.clickElement(pendingCancelCheckBox);
        customerAdmin = new CustomerViewDialog_Admin();
        customerAdmin.cancellationCategory(2);
        customerAdmin.cancellationNotes();
        clickAddFlag();
    }

    public void clickPrefersPaperCheckBox() {
        Legacy.clickElement(prefersPaperCheckBox);
    }

    public void clickPurpleDragonCheckBox() {
        Legacy.clickElement(purpleDragonCheckBox);
    }

    public void clickOverrideTaxCheckBox() {
        Legacy.clickElement(taxPercentageOverrideCheckbox);
    }

    /*
     * Setter methods Below methods insert text in the input fields
     */
    public void setFirstName(String needFirstName) {
        Legacy.waitVisible(firstNameInputField);
        Legacy.locate(firstNameInputField).sendKeys(needFirstName);
    }
    public void typeFirstName (String firstName) {
        Utilities.type(firstNameField, firstName);
    }

    public void setLastName(String needLastName) {
        Legacy.locate(lastNameInputField).sendKeys(needLastName);
    }
    
    public void typeLastName (String lastName) {
        Utilities.type(lastNameField, lastName);
    }

    public void setCellPhone(String needCellPhoneNumber) {
        Legacy.locate(cellPhoneInputField).sendKeys(needCellPhoneNumber);
    }

    public void setHomePhone(String needHomePhoneNumber) {
        Legacy.locate(homePhoneInputField).sendKeys(needHomePhoneNumber);
    }

    public void setEmailAddress(String needEmail) {
        Legacy.locate(emailAddressInputField).sendKeys(needEmail);
    }

    public void setCompanyName(String needCompanyName) {
        Legacy.locate(companyNameInputField).sendKeys(needCompanyName);
    }

    public void setSpecialHandling(String needSpecialHandlingNotes) {
        Legacy.locate(specialHandlingInputField).sendKeys(needSpecialHandlingNotes);
    }

    public void setAddress(String needAddress) {
        try {
            Legacy.locate(addressInputField).sendKeys(needAddress);
        } catch (ElementNotInteractableException notInteractableException){
            System.out.println("Element is not intractable, trying again ");
            Legacy.locate(addressInputField).sendKeys(needAddress);
        }
    }

    public void setZipCode(String needZipCode) throws InterruptedException {
        Legacy.locate(zipCodeInputField).sendKeys(needZipCode);
        Thread.sleep(200);
    }

    public void typeZipCode(String zipCode) { Utilities.type(zipCodeField, zipCode); }

    public void setCity(String needCity) throws InterruptedException {
        Thread.sleep(200);
        Legacy.locate(cityInputField).sendKeys(Keys.CONTROL, "a");
        Legacy.locate(cityInputField).sendKeys(needCity);
    }

    public void typeCity(String city) {
        Utilities.type(cityField, city);
    }

    public void setTaxPercentage(String needTaxPercentage) {
        Legacy.locate(taxPercentageInputField).sendKeys(needTaxPercentage);
    }

    public void setMapCode(String needMapCode) {
        Legacy.locate(mapCode).sendKeys(needMapCode);
    }

    public void clickInfo() {
        Legacy.waitVisible(info);
        Legacy.clickElement(info);
    }

    public void clickAddFlag() {
        Legacy.clickElement(clickAddFlag);
    }

    public void typePhone1(String phone) {
        Legacy.type(phone, Utilities.locate(By.xpath(cellPhoneInputField)));
    }

    /*
     * Getter methods Below methods get text value from an object
     */
    public String getDialogTitle() {
        return Legacy.getElementTextValue(dialogTitle);
    }

    public String getNewCustTitle() {
        return Legacy.getElementTextValue(newCustTitle);
    }

    public String getTaxRate(String needTaxField) {
        return Legacy.getAttribute(needTaxField, "value");
    }

    public String getAddress() {
        return Legacy.getAttribute(addressInputField, "value");
    }

    public String getZipCode() {
        return Legacy.getAttribute(zipCodeInputField, "value");
    }

    public String getCity() {
        return Legacy.getAttribute(cityInputField, "value");
    }

    public String getCustomerFullName() {
        String customerFullName = Utilities.locate(firstNameField).getAttribute("value") + " " +
                              Utilities.locate(lastNameField).getAttribute("value");
        System.out.println("Full Name: " + customerFullName);
        return customerFullName;
    }

    public String getCustomerFirstName() {
        String firstName = Utilities.locate(firstNameField).getAttribute("value");
        System.out.println("First Name: " + firstName);
        return firstName;
    }

    public String getCustomerLastName() {
        String lastName = Utilities.locate(lastNameField).getAttribute("value");
        System.out.println("Last Name: " + lastName);
        return lastName;
    }

    public void typePropertyAddress(String propertyAddress) {
        Utilities.type(propertyAddressField, propertyAddress);
    }

    public String getPropertyAddress(){
        String propertyAddress = Utilities.locate(propertyAddressField).getAttribute("value");
        System.out.println("Address: " + propertyAddress);
        return propertyAddress;
    }

    public String getCityStateZip() {
        String city = Utilities.locate(cityField).getAttribute("value");
        String state = Utilities.locate(stateDropdown).getAttribute("value");
        String zipCode = Utilities.locate(zipCodeField).getAttribute("value");
        String cityStateZip = city + ", " + state + " " + zipCode;
        System.out.println("City, State Zip: " + cityStateZip);
        return cityStateZip;
    }

    public void typeEmailAddress(String emailAddress) {
        Utilities.type(emailAddressField, emailAddress);
    }

    public String getEmailAddress() {
        String email = Utilities.locate(emailAddressField).getAttribute("value");
        System.out.println("Email: " + email);
        return email;
    }

    public String getPhone1() {
        String phone1 = Utilities.locate(By.xpath(cellPhoneInputField)).getAttribute("value");
        System.out.println("Phone 1: " + phone1);
        return phone1;
    }
}
