package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class CreditMemoTab {

    //Credit Memo Pop up Objects
    public String serviceType_creditMemoTab = "//select[@name='serviceID']";
    public String createButton_creditMemoTab = "//span[text()='Create']";

    //Credit Memo Objects
    private String initialInvoice = "//ul[@id='invoiceGroupListContainer']//li[2]";
    public String clickCreditMemoInvoice = "//ul[@id='invoiceGroupListContainer']//li[1]";
    public String chargeValue_creditMemo = "//input[@name='serviceCharge']";
    public String invoiceApplicationBox = "//div[@paymentID and @charge]";
    public String date_creditMemo = "//div[text()='Credit Memo Date']/following-sibling::div[1]";
    public String date_invoiceApplication = "//div[@onmouseup]//div[contains(text(),'applied')]//text()/following-sibling::div";
    public String billedAccount_creditMemo = "//input[@data-ticketid] ";
    public String paymentsBalance = "//div[text()='Balance']/following-sibling::div";
    public String appointmentDate_creditMemo = "//div[text()='Appointment Date']";

    //Getters
    public String getChargesAmount_creditMemo() {
        Utilities.waitUntileElementIsVisible(chargeValue_creditMemo);
        return Utilities.getAttributeValue(chargeValue_creditMemo, "value");
    }

    public void clickCreateButton_creditMemoTab() {
        Utilities.waitUntileElementIsVisible(createButton_creditMemoTab);
        Utilities.clickElement(createButton_creditMemoTab, Utilities.ElementType.XPath);
    }

    public void clickAppliedCharge_invoiceApplications() throws InterruptedException {
//        Utilities.waitUntileElementIsVisible(date_creditMemo);
        Utilities.waitUntileElementIsClickable(By.xpath(invoiceApplicationBox), 15);
        Utilities.clickElement(invoiceApplicationBox, Utilities.ElementType.XPath);
        if (!Utilities.isPresent(appointmentDate_creditMemo)){
            Utilities.clickElement(initialInvoice, Utilities.ElementType.XPath);
        }
    }

    public String getInvoiceApplicationDate() {
        Utilities.waitUntileElementIsVisible(date_invoiceApplication);
        return Utilities.getElementTextValue(date_invoiceApplication, Utilities.ElementType.XPath);
    }

    public String getCreditMemoDate() {
        Utilities.waitUntileElementIsVisible(date_creditMemo);
        return Utilities.getElementTextValue(date_creditMemo, Utilities.ElementType.XPath);
    }

    public String getBilledAccount() {
        Utilities.waitUntileElementIsVisible(billedAccount_creditMemo);
        return Utilities.getAttributeValue(billedAccount_creditMemo, "value");
    }

    public String getPaymentsBalance() {
        Utilities.waitUntileElementIsVisible(billedAccount_creditMemo);
        return Utilities.getElementTextValue(paymentsBalance, Utilities.ElementType.XPath);
    }

    public void clickInitialInvoice() throws InterruptedException {
        Thread.sleep(100);
        Utilities.waitUntileElementIsVisible(initialInvoice);
        Utilities.clickElement(initialInvoice, Utilities.ElementType.XPath);
    }

    //Author: Aditya
    public void validateServiceTypeName() throws Exception {
        String serviceTypeName_creditMemoInvoice = "//div[contains(text(),'Actions')]//following-sibling::div[1]//h3";
        try {
            Utilities.waitUntileElementIsVisible(date_creditMemo);
            WebElement elm = FindElement.elementByAttribute(serviceTypeName_creditMemoInvoice, FindElement.InputType.XPath);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            System.out.println("Service Type is not displayed on the header on credit memo invoice");
        }
    }

    //Author : Aditya
    public String getTicketID() throws InterruptedException {
        Thread.sleep(100);
        Utilities.waitUntileElementIsVisible(clickCreditMemoInvoice);
        int ticketID1 = Integer.parseInt(Utilities.getAttributeValue("//ul[@id='invoiceGroupListContainer']//li[1]", "ticketid"));
        int ticketID2 = Integer.parseInt(Utilities.getAttributeValue("//ul[@id='invoiceGroupListContainer']//li[2]", "ticketid"));
        if (ticketID1 > ticketID2) {
            return "//ul[@id='invoiceGroupListContainer']//li[1]";
        } else {
            return "//ul[@id='invoiceGroupListContainer']//li[2]";
        }
    }

    //Setters
    public void setServiceType_creditMemoTab(String serviceType) {
        Utilities.waitUntileElementIsVisible(serviceType_creditMemoTab);
        Utilities.selectValueFromDropDownByValue(serviceType_creditMemoTab, serviceType);
    }
}
