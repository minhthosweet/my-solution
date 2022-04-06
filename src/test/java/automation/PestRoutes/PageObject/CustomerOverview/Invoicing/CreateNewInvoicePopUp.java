package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;

public class CreateNewInvoicePopUp extends BasePage {

    public String dateField = "//input[@name='date']";
    public By invoiceDateField = By.xpath("//input[@name='date']");
    public String amountInputField = "//input[@name='subTotal']";
    private By invoiceSubTotal = By.xpath("//input[@name='subTotal']");
    public String serviceTypeDropdown = "//select[@name='serviceID']";
    private By serviceTypeDropDown = By.xpath("//select[@name='serviceID']");
    public String billToInputField = "//input[@id='displayBillToAccountIDSearch4']";
    public String cancelButton = "//form[@id='newInvoiceParams']/ancestor::div//span[text()='Cancel']";
    public String createButton = "//form[@id='newInvoiceParams']/ancestor::div//span[text()='Create']";
    private By createInvoiceButton = By.xpath("//form[@id='newInvoiceParams']/ancestor::div//span[text()='Create']");

    public void set(String needInputField, String needValue){
        Deprecated.waitVisible(needInputField);
        Deprecated.highLight(needInputField);
        if (SystemUtils.IS_OS_MAC_OSX){
            Deprecated.locate(needInputField).clear();
            Deprecated.clickElement(needInputField);
        }
        Deprecated.locate(needInputField).sendKeys(needValue);
    }

    public void select(String needDropDown, String needValue){
        Deprecated.waitVisible(needDropDown);
        Deprecated.selectByText(needDropDown, needValue);
    }

    public void click(String needButton){
        Deprecated.waitVisible(needButton);
        Deprecated.clickElement(needButton);
    }

    public void typeSubTotal(String subTotal) {
        Deprecated.type(subTotal, invoiceSubTotal);
    }

    public String getSubTotal() {
        return Utilities.locate(invoiceSubTotal).getAttribute("value");
    }

    public void selectServiceType(String serviceType)  {
        Utilities.selectByText(serviceTypeDropDown, serviceType);
    }

    public void clickCreateButton() {
        Utilities.click(createInvoiceButton);
    }

    public void typeInvoiceDate(String invoiceDate) {
        Deprecated.type(invoiceDate, invoiceDateField, "ENTER");
    }//typeInvoiceDate()
}
