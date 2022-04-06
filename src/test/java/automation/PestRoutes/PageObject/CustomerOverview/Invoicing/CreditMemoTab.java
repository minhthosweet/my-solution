package automation.PestRoutes.PageObject.CustomerOverview.Invoicing;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
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
        Deprecated.waitVisible(chargeValue_creditMemo);
        return Deprecated.getAttribute(chargeValue_creditMemo, "value");
    }

    public void clickCreateButton_creditMemoTab() {
        Deprecated.waitVisible(createButton_creditMemoTab);
        Deprecated.clickElement(createButton_creditMemoTab);
    }

    public void clickAppliedCharge_invoiceApplications() throws InterruptedException {
//        Utilities.waitUntileElementIsVisible(date_creditMemo);
        Utilities.waitClickable(By.xpath(invoiceApplicationBox), 15);
        Deprecated.clickElement(invoiceApplicationBox);
        if (!Deprecated.isPresent(appointmentDate_creditMemo)){
            Deprecated.clickElement(initialInvoice);
        }
    }

    public String getInvoiceApplicationDate() {
        Deprecated.waitVisible(date_invoiceApplication);
        return Deprecated.getElementTextValue(date_invoiceApplication);
    }

    public String getCreditMemoDate() {
        Deprecated.waitVisible(date_creditMemo);
        return Deprecated.getElementTextValue(date_creditMemo);
    }

    public String getBilledAccount() {
        Deprecated.waitVisible(billedAccount_creditMemo);
        return Deprecated.getAttribute(billedAccount_creditMemo, "value");
    }

    public String getPaymentsBalance() {
        Deprecated.waitVisible(billedAccount_creditMemo);
        return Deprecated.getElementTextValue(paymentsBalance);
    }

    public void clickInitialInvoice() throws InterruptedException {
        Thread.sleep(100);
        Deprecated.waitVisible(initialInvoice);
        Deprecated.clickElement(initialInvoice);
    }

    //Author: Aditya
    public void validateServiceTypeName() throws Exception {
        String serviceTypeName_creditMemoInvoice = "//div[contains(text(),'Actions')]//following-sibling::div[1]//h3";
        try {
            Deprecated.waitVisible(date_creditMemo);
            WebElement elm = Deprecated.locate(serviceTypeName_creditMemoInvoice);
            if (elm.isDisplayed()) {
            }
        } catch (Exception e) {
            System.out.println("Service Type is not displayed on the header on credit memo invoice");
        }
    }

    //Author : Aditya
    public String getTicketID() throws InterruptedException {
        Thread.sleep(100);
        Deprecated.waitVisible(clickCreditMemoInvoice);
        int ticketID1 = Integer.parseInt(Deprecated.getAttribute("//ul[@id='invoiceGroupListContainer']//li[1]", "ticketid"));
        int ticketID2 = Integer.parseInt(Deprecated.getAttribute("//ul[@id='invoiceGroupListContainer']//li[2]", "ticketid"));
        if (ticketID1 > ticketID2) {
            return "//ul[@id='invoiceGroupListContainer']//li[1]";
        } else {
            return "//ul[@id='invoiceGroupListContainer']//li[2]";
        }
    }

    //Setters
    public void setServiceType_creditMemoTab(String serviceType) {
        Deprecated.waitVisible(serviceType_creditMemoTab);
        Deprecated.selectByText(serviceType_creditMemoTab, serviceType);
    }
}
