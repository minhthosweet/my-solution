package automation.PestRoutes.Controller.Invoicing;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Admin.Preferences.OfficeSettings.MerchantInfo.AddOrEditMerchantInfo;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.CreditCardConfirmationPage;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.CreditCard.SingleUseCardPage;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.InvoiceImplementation;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.Invoice_Header;
import automation.PestRoutes.PageObject.CustomerOverview.Invoicing.RoutePageInvoicing;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import static automation.PestRoutes.Utilities.AssertException.result;

public class SingleCardPayment {
    SingleUseCardPage payment = new SingleUseCardPage();
    Invoice_Header invoiceHeader = new Invoice_Header();
    RoutePageInvoicing invoiceRoutesTab = new RoutePageInvoicing();
    CreditCardConfirmationPage confirmationPage = new CreditCardConfirmationPage();
    InvoiceImplementation invImplementation = new InvoiceImplementation();
    AddOrEditMerchantInfo merchantInfo = new AddOrEditMerchantInfo();
    CreateNewCustomer customerCard = new CreateNewCustomer();
    MarchantInfoPage merchant = new MarchantInfoPage();
    CustomerViewDialog_Header customerCardHeader = new CustomerViewDialog_Header();

    //***Author Aarbi
    @Then("I make payment with single credit card {string} and {string}")
    public void makeSingleCardPayment(String needCC, String needNmiCC) throws Exception {
        customerCard.closeCustomerCard();
        merchantInfo.navigateToMerchantInfo();
        String gateWay = Utilities.getElementTextValue(merchant.defaultPaymentGatewayValue, Utilities.ElementType.XPath);
        customerCard.searchCustomer();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Utilities.waitUntileElementIsVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Utilities.waitUntileElementIsVisible(payment.paymentAmountField);
        String confirmAmount = Utilities.getAttributeValue(payment.paymentAmountField, "value");
        FindElement.elementByAttribute(payment.confirmAmountField, FindElement.InputType.XPath).sendKeys(confirmAmount);

        if (gateWay.contains("Braintree")){
            chargeSignleBrainTreeCc(needCC);
        }else if (gateWay.contains("Element")){
            chargeSingleElementCc(needCC);
        } else if(gateWay.contains("Spreedly")){
            chargeSignleSpreedlyCc(needCC);
        } else if(gateWay.contains("NMI")){
            chargeSingleNmiCc(needNmiCC);
        } else if (gateWay.contains("PestRoutes Payments")){
            chargeSinglePayrixCc(needCC);
        }
    }
    //***Author Aarbi
    @Then("I charge cc with braintree gateway {string}")
    public void chargeSignleBrainTreeCc(String needBrainTreeCC) throws InterruptedException {
        int i=0;
        while(i++<5)
        {
            try
            {
                Utilities.switchToIframeByXpath(payment.brainTreeCcIframe);
                FindElement.elementByAttribute(payment.ccNumberField, FindElement.InputType.XPath).sendKeys(needBrainTreeCC);
                Utilities.switchBackToDom();
                break;
            }
            catch(Exception e)
            {
                Thread.sleep(1000);
                Utilities.switchBackToDom();
                Utilities.clickElement(payment.payingWithCardButton, Utilities.ElementType.XPath);
                continue;
            }
        }
        Utilities.switchToIframeByXpath(payment.brainTreeExpIframe);
        FindElement.elementByAttribute(payment.ccExpField, FindElement.InputType.XPath).sendKeys("0228");
        Utilities.switchBackToDom();
        Utilities.clickElement(payment.chargeSingleCard, Utilities.ElementType.XPath);
        Utilities.acceptAlert();
        Utilities.waitUntileElementIsVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Utilities.getElementTextValue(confirmationPage.singleCardPaymentResult, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }
    //***Author Aarbi
    @Then("I charge cc with element gateway {string}")
    public void chargeSingleElementCc(String needElementCC) throws InterruptedException {
        Utilities.clickElement(payment.chargeSingleCard, Utilities.ElementType.XPath);
        Utilities.switchToIframeByXpath(payment.elementIframe);
        FindElement.elementByAttribute(payment.elementCcInputField, FindElement.InputType.XPath).sendKeys(needElementCC);
        Utilities.selectValueFromDropDownByValue(payment.elementExpMonthDropdown, "02");
        Utilities.selectValueFromDropDownByValue(payment.elementExpYearDropdown, "2028");
        FindElement.elementByAttribute(payment.elementCvvInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(payment.elementProcessTransactionButton, Utilities.ElementType.XPath);
        Utilities.switchBackToDom();
        Utilities.acceptAlert();
        Utilities.waitUntileElementIsVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Utilities.getElementTextValue(confirmationPage.singleCardPaymentResult, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }
    //***Author Aarbi
    @Then("I charge cc with spreedly gateway {string}")
    public void chargeSignleSpreedlyCc(String needSpreedlyCC) throws InterruptedException {
        String spreedlyIframe = Utilities.getAttributeValue(payment.spreedlyOneTimeCcNumberIframe, "id");
        Utilities.switchToIframeByXpath(spreedlyIframe);
        FindElement.elementByAttribute(payment.spreedlyOneTimeCardNumberInputField, FindElement.InputType.XPath).sendKeys(needSpreedlyCC);
        Utilities.switchBackToDom();
        String spreedlyCvvIframe = Utilities.getAttributeValue(payment.spreedlyOneTimeCvvIframe, "id");
        Utilities.switchToIframeByXpath(spreedlyCvvIframe);
        FindElement.elementByAttribute(payment.spreedlyOneTimeCvvInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.switchBackToDom();
        Utilities.selectValueFromDropDownByValue(payment.spreedlyOneTimeExpMonthDropdown, "February");
        Utilities.selectValueFromDropDownByValue(payment.spreedlyOneTimeExpYearDropdown, "2028");
        Utilities.clickElement(payment.chargeSingleCard, Utilities.ElementType.XPath);
        Utilities.acceptAlert();
        Utilities.waitUntileElementIsVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Utilities.getElementTextValue(confirmationPage.singleCardPaymentResult, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }
    //***Author Aarbi
    @Then("I charge cc with nmi gateway {string}")
    public void chargeSingleNmiCc(String needNmiCC) throws InterruptedException {
        Utilities.clickElement(payment.nmiChargeSingleCardButton, Utilities.ElementType.XPath);
        Utilities.switchToIframeByXpath(payment.nmiIframe);
        FindElement.elementByAttribute(payment.nmiCcNumberInputField, FindElement.InputType.XPath).sendKeys(needNmiCC);
        FindElement.elementByAttribute(payment.nmiCcExpInputField, FindElement.InputType.XPath).sendKeys("0228");
        FindElement.elementByAttribute(payment.nmiCvvInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.clickElement(payment.nmiSubmitPaymentButton, Utilities.ElementType.XPath);
        Utilities.switchBackToDom();
        Utilities.acceptAlert();
        Utilities.waitUntileElementIsVisible(confirmationPage.paymentResultTitle);
        String actualResult = Utilities.getElementTextValue(confirmationPage.confirmationMessage, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }
    //***Author Aarbi
    @Then("I charge cc with payrix gateway {string}")
    public void chargeSinglePayrixCc(String needPayrixCC) throws InterruptedException {
        Utilities.switchToIframeByXpath(payment.singlePayrixIframe);
        Utilities.switchToIframeByXpath(payment.payrixCcIframe);
        FindElement.elementByAttribute(payment.payrixCcNumberInputField, FindElement.InputType.XPath).sendKeys(needPayrixCC);
        Utilities.switchBackToDom();
        Utilities.switchToIframeByXpath(payment.singlePayrixIframe);
        Utilities.switchToIframeByXpath(payment.payrixExpIframe);
        FindElement.elementByAttribute(payment.payrixExpInputField, FindElement.InputType.XPath).sendKeys("0228");
        Utilities.switchBackToDom();
        Utilities.switchToIframeByXpath(payment.singlePayrixIframe);
        Utilities.switchToIframeByXpath(payment.payrixCvvIframe);
        FindElement.elementByAttribute(payment.payrixCvvInputField, FindElement.InputType.XPath).sendKeys("123");
        Utilities.switchBackToDom();
        Utilities.clickElement(payment.chargeSingleCard, Utilities.ElementType.XPath);
        Utilities.acceptAlert();
        Utilities.waitUntileElementIsVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Utilities.getElementTextValue(confirmationPage.singleCardPaymentResult, Utilities.ElementType.XPath);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }
}
