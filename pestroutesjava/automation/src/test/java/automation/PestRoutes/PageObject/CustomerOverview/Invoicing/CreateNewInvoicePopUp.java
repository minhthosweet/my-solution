package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.apache.commons.lang3.SystemUtils;

public class CreateNewInvoicePopUp {

    public String dateField = "//input[@name='date']";
    public String amountInputField = "//input[@name='subTotal']";
    public String serviceTypeDropdown = "//select[@name='serviceID']";
    public String billToInputField = "//input[@id='displayBillToAccountIDSearch4']";
    public String cancelButton = "//form[@id='newInvoiceParams']/ancestor::div//span[text()='Cancel']";
    public String createButton = "//form[@id='newInvoiceParams']/ancestor::div//span[text()='Create']";


    public void set(String needInputField, String needValue){
        Utilities.waitUntileElementIsVisible(needInputField);
        Utilities.highLight(needInputField);
        if (SystemUtils.IS_OS_MAC_OSX){
            FindElement.elementByAttribute(needInputField, FindElement.InputType.XPath).clear();
            Utilities.clickElement(needInputField, Utilities.ElementType.XPath);
        }
        FindElement.elementByAttribute(needInputField, FindElement.InputType.XPath).sendKeys(needValue);
    }

    public void select(String needDropDown, String needValue){
        Utilities.waitUntileElementIsVisible(needDropDown);
        Utilities.selectValueFromDropDownByValue(needDropDown, needValue);
    }

    public void click(String needButton){
        Utilities.waitUntileElementIsVisible(needButton);
        Utilities.clickElement(needButton, Utilities.ElementType.XPath);
    }
}
