package automation.PestRoutes.PageObject.CreateCustomer;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
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
        Deprecated.selectByText(propertyTypeDropDown, needPropertyType);
    }

    public void selectUnit(String needUnit) {
        Deprecated.selectByText(unitTypeDropDown, needUnit);
    }

    public void selectSource(String needSource) {
        Deprecated.selectByText(selectSourceDropDown, needSource);
    }

    public void selectGenericFlag(String needFlag) {
        Deprecated.locate(genericFlagsDropDown).sendKeys(needFlag);
    }

    public void selectState(String needState) {
        Deprecated.selectByText(stateDropDown, needState);
    }

    public void selectCounty(String needCounty) {
        Deprecated.selectByText(countyDropDown, needCounty);
    }

    public void selectCountry(String needCountry) {
        Deprecated.selectByText(countryDropDown, needCountry);
    }

    public void selectDivision(String needDivision) {
        Deprecated.selectByText(divisionDropDown, needDivision);
    }

    // Click or Unclick Check boxes
    public void clickSmsCheckBox() {
        try {
            WebElement elm = Deprecated.locate("//input[@name= 'smsReminders' and @checked]");
            if (!elm.isDisplayed()) {
                Deprecated.clickElement(smsCheckBox);
            }
        } catch (Exception e) {
        }
    }

    public void clickEmailCheckBox() {
        try {
            WebElement elm = Deprecated.locate(emailSelectedCheckBox);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Deprecated.clickElement(emailCheckBox);
        }
    }

    public void clickVoiceCheckBox() {
        try {
            WebElement elm = Deprecated.locate(voiceSelectedCheckBox);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            Deprecated.clickElement(voiceCheckBox);
        }
    }

    public void unClickSmsCheckBox() {
        try {
            WebElement elm = Deprecated.locate(smsSelectedCheckBox);
            if (elm.isDisplayed()) {
                Deprecated.clickElement(smsSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("SMS checkBox unselected");
        }
    }

    public void unClickEmailCheckBox() {
        try {
            WebElement elm = Deprecated.locate(emailSelectedCheckBox);
            if (elm.isDisplayed()) {
                Deprecated.clickElement(emailSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("Email checkBox unselected");
        }
    }

    public void unClickVoiceCheckBox() {
        try {
            WebElement elm = Deprecated.locate(voiceSelectedCheckBox);
            if (elm.isDisplayed()) {
                Deprecated.clickElement(voiceSelectedCheckBox);
            }
        } catch (Exception e) {
            System.out.println("Voice Checkbox unselected");
        }
    }

    public void clickPaidInFullCheckBox() {
        Deprecated.clickElement(paidInFullCheckBox);
    }

    public void clickSwitchOverCheckBox() {
        Deprecated.clickElement(switchOverCheckBox);
    }

    public void clickSalesRepAPayCheckBox() {
        Deprecated.clickElement(salesRepAPayCheckBox);
    }

    public void clickPendingCancelCheckBox() {
        Deprecated.clickElement(pendingCancelCheckBox);
        customerAdmin = new CustomerViewDialog_Admin();
        customerAdmin.cancellationCategory(2);
        customerAdmin.cancellationNotes();
        clickAddFlag();
    }

    public void clickPrefersPaperCheckBox() {
        Deprecated.clickElement(prefersPaperCheckBox);
    }

    public void clickPurpleDragonCheckBox() {
        Deprecated.clickElement(purpleDragonCheckBox);
    }

    public void clickOverrideTaxCheckBox() {
        Deprecated.clickElement(taxPercentageOverrideCheckbox);
    }

    /*
     * Setter methods Below methods insert text in the input fields
     */
    public void setFirstName(String needFirstName) {
        Deprecated.waitVisible(firstNameInputField);
        Deprecated.locate(firstNameInputField).sendKeys(needFirstName);
    }
    public void typeFirstName (String firstName) {
        Deprecated.type(firstName, firstNameField);
    }

    public void setLastName(String needLastName) {
        Deprecated.locate(lastNameInputField).sendKeys(needLastName);
    }
    
    public void typeLastName (String lastName) {
        Deprecated.type(lastName, lastNameField);
    }

    public void setCellPhone(String needCellPhoneNumber) {
        Deprecated.locate(cellPhoneInputField).sendKeys(needCellPhoneNumber);
    }

    public void setHomePhone(String needHomePhoneNumber) {
        Deprecated.locate(homePhoneInputField).sendKeys(needHomePhoneNumber);
    }

    public void setEmailAddress(String needEmail) {
        Deprecated.locate(emailAddressInputField).sendKeys(needEmail);
    }

    public void setCompanyName(String needCompanyName) {
        Deprecated.locate(companyNameInputField).sendKeys(needCompanyName);
    }

    public void setSpecialHandling(String needSpecialHandlingNotes) {
        Deprecated.locate(specialHandlingInputField).sendKeys(needSpecialHandlingNotes);
    }

    public void setAddress(String needAddress) {
        try {
            Deprecated.locate(addressInputField).sendKeys(needAddress);
        } catch (ElementNotInteractableException notInteractableException){
            System.out.println("Element is not intractable, trying again ");
            Deprecated.locate(addressInputField).sendKeys(needAddress);
        }
    }

    public void setZipCode(String needZipCode) throws InterruptedException {
        Deprecated.locate(zipCodeInputField).sendKeys(needZipCode);
        Thread.sleep(200);
    }

    public void typeZipCode(String zipCode) { Deprecated.type(zipCode, zipCodeField); }

    public void setCity(String needCity) throws InterruptedException {
        Thread.sleep(200);
        Deprecated.locate(cityInputField).sendKeys(Keys.CONTROL, "a");
        Deprecated.locate(cityInputField).sendKeys(needCity);
    }

    public void typeCity(String city) {
        Deprecated.type(city, cityField);
    }

    public void setTaxPercentage(String needTaxPercentage) {
        Deprecated.locate(taxPercentageInputField).sendKeys(needTaxPercentage);
    }

    public void setMapCode(String needMapCode) {
        Deprecated.locate(mapCode).sendKeys(needMapCode);
    }

    public void clickInfo() {
        Deprecated.waitVisible(info);
        Deprecated.clickElement(info);
    }

    public void clickAddFlag() {
        Deprecated.clickElement(clickAddFlag);
    }

    public void typePhone1(String phone) {
        Deprecated.type(phone, Utilities.locate(By.xpath(cellPhoneInputField)));
    }

    /*
     * Getter methods Below methods get text value from an object
     */
    public String getDialogTitle() {
        return Deprecated.getElementTextValue(dialogTitle);
    }

    public String getNewCustTitle() {
        return Deprecated.getElementTextValue(newCustTitle);
    }

    public String getTaxRate(String needTaxField) {
        return Deprecated.getAttribute(needTaxField, "value");
    }

    public String getAddress() {
        return Deprecated.getAttribute(addressInputField, "value");
    }

    public String getZipCode() {
        return Deprecated.getAttribute(zipCodeInputField, "value");
    }

    public String getCity() {
        return Deprecated.getAttribute(cityInputField, "value");
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
        Deprecated.type(propertyAddress, propertyAddressField);
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
        Deprecated.type(emailAddress, emailAddressField);
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
