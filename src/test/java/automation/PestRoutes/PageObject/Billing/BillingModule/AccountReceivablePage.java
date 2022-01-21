package automation.PestRoutes.PageObject.Billing.BillingModule;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;

public class AccountReceivablePage {

    //Buttons
    public String refreshButton = "//div[@id= 'balanceFilterWrapper']//div[contains (text(), 'Refresh')]";
    public String basicFilterLink ="//div[@id= 'balanceFilterWrapper']//div[@id='advancedFilterToggleButton']";
    public String advanceFilterLink = "//div[@id= 'balanceFilterWrapper']//div[@id='advancedFilterToggleButton']";
    public String thisYear = "//li[text()='This Year']";
    public String thisMonth = "//li[text()='This Month']";
    public String lastMonth = "//li[text()='Last Month']";
    public String lastYear = "//li[text()='Last Year']";
    public String lastWeek = "//li[text()='Last Week']";

    //Actions buttons
    public String actionsButton = "//div[@id = 'balancesTableActions']";
    public String sendMessageButton_UnderActions = "//div[@id = 'balancesTableActions']//div[text() = 'Send Message']";
    public String exportToExcelButton_UnderActions = "//div[@id = 'balancesTableActions']//div[text() = 'Export to Excel']";
    public String printStatementsButton_UnderActions = "//div[@id = 'balancesTableActions']//div[text() = 'Print Statements']";
    public String snailMailButton_UnderActions = "//div[@id = 'balancesTableActions']//div[text() = 'Snail Mail Statements']";
    public String emailStatementsButton_UnderActions = "//div[@id = 'balancesTableActions']//div[text() = 'Email Statements']";

    //Send Message buttons
    public String SMSradioButton = "//input[@id = 'smsMessageType']";
    public String voiceRadioButton = "//input[@id = 'voiceMessageType']";
    public String emailRadioButton = "//input[@id = 'emailMessageType']";
    public String sendMessagesButton = "//span[text() = 'Send Messages']";
    public String closeMessageButton = "//div[@id='customerMessageDialog']/following-sibling::div//span[text()='Close']";
    public String ignoreContactPreferencesCheckBox = "//input[@name='ignorePreferences']";
    public String getIgnoreMaxPerMinuteCheckBox = "//input[@name='ignoreMaxPerMinute']";

    //Send Message fields
    public String emailSubjectField = "//input[@name='subject']";
    public String emailCategoryField = "//select[@name='emailCategoryID']";
    public String emailTextField = "//textarea[@name='customerMessage']/preceding-sibling::div";

    //Toggle buttons
    public String toggleButton = "//div[@id = 'balanceToggleWrapper']";
    public String toggleAllPrintStatementsOnButton_UnderToggle = "//div[@id = 'balanceToggleWrapper']//div[text() = 'Toggle All Print Statements On']";
    public String toggleAllEmailStatementsOnButton_UnderToggle = "//div[@id = 'balanceToggleWrapper']//div[text() = 'Toggle All Email Statements On']";
    public String toggleAllVoiceOnButton_UnderToggle = "//div[@id = 'balanceToggleWrapper']//div[text() = 'Toggle All Voice On']";
    public String toggleAllSmsOnButton_UnderToggle = "//div[@id = 'balanceToggleWrapper']//div[text() = 'Toggle All SMS On']";

    //Input fields
    public String asOfDateInputField = "//input[@name = 'asOfDate']";
    public String balanceInputField = "//form[@id= 'arFilterParams']//input[@name='balance']";
    public String searchInputField = "//div[@id = 'balancesTable_filter']//input[@type= 'search']";

    //Dropdowns
    public String accountStatusDropdown = "//form[@id= 'arFilterParams']//select[@name='accountStatus']";
    public String autoPayDropdown = "//form[@id= 'arFilterParams']//select[@name='aPay']";
    public String statusDropdown = "//form[@id= 'arFilterParams']//select[@name='paymentAccountStatus']";
    public String propertyDropdown = "//form[@id= 'arFilterParams']//select[@name='propertyType']";
    public String balanceAgeDropdown = "//form[@id= 'arFilterParams']//select[@name='balanceAge']";
    public String paymentDueDropdown = "//form[@id= 'arFilterParams']//input[@name='paymentDueDate-arFilterParams']";
    public String daysOverDueDropdown = "//form[@id= 'arFilterParams']//select[@name='customerDueBetween']";
    public String prefPaperDropdown = "//form[@id= 'arFilterParams']//select[@name='prefersPaper']";
    public String hasEmailDropdown = "//form[@id= 'arFilterParams']//select[@name='hasEmail']";
    public String maxMonthlyDropdown = "//form[@id= 'arFilterParams']//select[@name='hasMaxMonthly']";
    public String groupByDropdown = "//form[@id= 'arFilterParams']//select[@name='balancesGroupBy']";
    public String inclCollectionDropdown = "//form[@id= 'arFilterParams']//select[@name='includeCollectionsStages']";
    public String inclFlagsDropdown = "//form[@id= 'arFilterParams']//select[@name='includeGenericFlags']";
    public String exclFlagsDropdown = "//form[@id= 'arFilterParams']//select[@name='excludeGenericFlags']";


    public void click(String needButton){
        Utilities.clickElement(needButton, Utilities.ElementType.XPath);
    }

    public void clickButtonsInTable(String needColumnNumber){
        Utilities.clickElement("//table[@id='balancesTable']/tbody//tr[1]/td['" + needColumnNumber + "']", Utilities.ElementType.XPath);
    }

    public void select(String needDropDown, String needText){
        Utilities.selectValueFromDropDownByValue(needDropDown, needText);
    }

    public void insert(String needField, String needValue){
        Utilities.waitUntileElementIsVisible(needField);
        Utilities.highLight(needField);
        Utilities.waitUntileElementIsVisible(needField);
        FindElement.elementByAttribute(needField, FindElement.InputType.XPath).sendKeys(needValue);
    }

    public void setInputSubjectText(String needField, String needText){
        Utilities.waitUntileElementIsVisible(needField);
        FindElement.elementByAttribute(needField, FindElement.InputType.XPath).sendKeys(needText);
    }

    public void setInputEmailText(String needField, String needText){
        Utilities.waitUntileElementIsVisible(needField);
        Utilities.clearField(needField);
        Utilities.highLight(needField);
        FindElement.elementByAttribute(needField, FindElement.InputType.XPath).sendKeys(needText);
    }

    public String getValueFromTable(String needColumnNumber){
        String value = Utilities.getElementTextValue("//table[@id='balancesTable']/tbody/tr[1]/td[" + needColumnNumber + "]", Utilities.ElementType.XPath);
        return value;
    }

}
