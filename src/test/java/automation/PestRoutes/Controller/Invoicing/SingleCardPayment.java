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
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.Then;

import static automation.PestRoutes.Utilities.Report.AssertException.result;

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
        String gateWay = Legacy.getElementTextValue(merchant.defaultPaymentGatewayValue);
        customerCard.searchCustomer();
        customerCardHeader.navigateTo(customerCardHeader.invoicesTabInDialog);
        Legacy.waitVisible(invoiceRoutesTab.addNewInvoice);
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Legacy.waitVisible(payment.paymentAmountField);
        String confirmAmount = Legacy.getAttribute(payment.paymentAmountField, "value");
        Legacy.clickElement(payment.confirmAmountField);
        Legacy.locate(payment.confirmAmountField).sendKeys(confirmAmount);

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

    public void makeSingleCardPayment(String gateWay, String needCC, String needNmiCC) throws Exception {
        invImplementation.clickInitialInvoice();
        invoiceRoutesTab.clickAddPayment();
        invoiceHeader.navigate(invoiceHeader.creditCard);
        Legacy.waitVisible(payment.paymentAmountField);
        String confirmAmount = Legacy.getAttribute(payment.paymentAmountField, "value");
        Legacy.clickElement(payment.confirmAmountField);
        Legacy.locate(payment.confirmAmountField).sendKeys(confirmAmount);

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
    public void chargeSignleBrainTreeCc(String needBrainTreeCC) {
        int i=0;
        while(i++<5)
        {
            try
            {
                Utilities.switchToIframe(payment.brainTreeCcIframe);
                Legacy.locate(payment.ccNumberField).sendKeys(needBrainTreeCC);
                GetWebDriver.switchBackToDom();
                break;
            }
            catch(Exception e)
            {
                Utilities.delay(1000);
                GetWebDriver.switchBackToDom();
                Legacy.clickElement(payment.payingWithCardButton);
                continue;
            }
        }
        Utilities.switchToIframe(payment.brainTreeExpIframe);
        Legacy.locate(payment.ccExpField).sendKeys("0228");
        GetWebDriver.switchBackToDom();
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert(10);
        Legacy.waitVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Legacy.getElementTextValue(confirmationPage.singleCardPaymentResult);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }

    public void chargeSignleBrainTreeCc(String needBrainTreeCC, String expirationMthYr, String cvv) {
        String  expMonthAndYr = expirationMthYr.replace("/", "");

        int i=0;
        while(i++<5)
        {
            try
            {
                Legacy.locate(payment.ccNumberField).sendKeys(needBrainTreeCC);
                GetWebDriver.switchBackToDom();
                break;
            }
            catch(Exception e)
            {
                Utilities.delay(500);
                GetWebDriver.switchBackToDom();
                Legacy.clickElement(payment.payingWithCardButton);
                continue;
            }
        }
        Utilities.switchToIframe(payment.brainTreeExpIframe);
        Legacy.locate(payment.ccExpField).sendKeys(expMonthAndYr);
        GetWebDriver.switchBackToDom();
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert();
        Legacy.isVisible(confirmationPage.singleCardPaymentResult);
     }//chargeSignleBrainTreeCc()

    //***Author Aarbi
    @Then("I charge cc with element gateway {string}")
    public void chargeSingleElementCc(String needElementCC) {
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.switchToIframe(payment.elementIframe);
        Legacy.locate(payment.elementCcInputField).sendKeys(needElementCC);
        Legacy.selectByText(payment.elementExpMonthDropdown, "02");
        Legacy.selectByText(payment.elementExpYearDropdown, "2028");
        Legacy.locate(payment.elementCvvInputField).sendKeys("123");
        Legacy.clickElement(payment.elementProcessTransactionButton);
        GetWebDriver.switchBackToDom();
        Utilities.acceptAlert(10);
        Legacy.waitVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Legacy.getElementTextValue(confirmationPage.singleCardPaymentResult);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }

    public void chargeSingleElementCc(String needElementCC, String expirationMthYr, String cvv) {
        String[] separateMonthYear = expirationMthYr.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];

        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.switchToIframe(payment.elementIframe);
        Legacy.locate(payment.elementCcInputField).sendKeys(needElementCC);
        Legacy.selectByText(payment.elementExpMonthDropdown, month);
        Legacy.selectByText(payment.elementExpYearDropdown, year);
        Legacy.locate(payment.elementCvvInputField).sendKeys(cvv);
        Legacy.clickElement(payment.elementProcessTransactionButton);
        GetWebDriver.switchBackToDom();
        Utilities.acceptAlert();
        Legacy.isVisible(confirmationPage.singleCardPaymentResult);
    }//chargeSingleElementCc()

    //***Author Aarbi
    @Then("I charge cc with spreedly gateway {string}")
    public void chargeSignleSpreedlyCc(String needSpreedlyCC) {
        String spreedlyIframe = Legacy.getAttribute(payment.spreedlyOneTimeCcNumberIframe, "id");
        Utilities.switchToIframe(spreedlyIframe);
        Legacy.locate(payment.spreedlyOneTimeCardNumberInputField).sendKeys(needSpreedlyCC);
        GetWebDriver.switchBackToDom();
        String spreedlyCvvIframe = Legacy.getAttribute(payment.spreedlyOneTimeCvvIframe, "id");
        Utilities.switchToIframe(spreedlyCvvIframe);
        Legacy.locate(payment.spreedlyOneTimeCvvInputField).sendKeys("123");
        GetWebDriver.switchBackToDom();
        Legacy.selectByText(payment.spreedlyOneTimeExpMonthDropdown, "February");
        Legacy.selectByText(payment.spreedlyOneTimeExpYearDropdown, "2028");
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert(10);
        Legacy.waitVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Legacy.getElementTextValue(confirmationPage.singleCardPaymentResult);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }

    public void chargeSignleSpreedlyCc(String needSpreedlyCC, String expirationMthYr, String cvv) {
        String[] separateMonthYear = expirationMthYr.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];

        String spreedlyIframe = Legacy.getAttribute(payment.spreedlyOneTimeCcNumberIframe, "id");
        Utilities.switchToIframe(spreedlyIframe);
        Legacy.locate(payment.spreedlyOneTimeCardNumberInputField).sendKeys(needSpreedlyCC);
        GetWebDriver.switchBackToDom();
        String spreedlyCvvIframe = Legacy.getAttribute(payment.spreedlyOneTimeCvvIframe, "id");
        Utilities.switchToIframe(spreedlyCvvIframe);
        Legacy.locate(payment.spreedlyOneTimeCvvInputField).sendKeys(cvv);
        GetWebDriver.switchBackToDom();
        Legacy.selectByText(payment.spreedlyOneTimeExpMonthDropdown, month);
        Legacy.selectByText(payment.spreedlyOneTimeExpYearDropdown, year);
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert();
        Legacy.waitVisible(confirmationPage.singleCardPaymentResult);
       }// chargeSignleSpreedlyCc()


    //***Author Aarbi
    @Then("I charge cc with nmi gateway {string}")
    public void chargeSingleNmiCc(String needNmiCC) {
        Legacy.clickElement(payment.nmiChargeSingleCardButton);
        Utilities.switchToIframe(payment.nmiIframe);
        Legacy.locate(payment.nmiCcNumberInputField).sendKeys(needNmiCC);
        Legacy.locate(payment.nmiCcExpInputField).sendKeys("0228");
        Legacy.locate(payment.nmiCvvInputField).sendKeys("123");
        Legacy.clickElement(payment.nmiSubmitPaymentButton);
        GetWebDriver.switchBackToDom();
        Utilities.acceptAlert(10);
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
        String actualResult = Legacy.getElementTextValue(confirmationPage.confirmationMessage);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }

    public void chargeSingleNmiCc(String needNmiCC, String expirationMthYr,String cvv) {
        String expMthYr = expirationMthYr.replace("/", "");

        Legacy.clickElement(payment.nmiChargeSingleCardButton);
        Utilities.switchToIframe(payment.nmiIframe);
        Legacy.locate(payment.nmiCcNumberInputField).sendKeys(needNmiCC);
        Legacy.locate(payment.nmiCcExpInputField).sendKeys(expMthYr);
        Legacy.locate(payment.nmiCvvInputField).sendKeys(cvv);
        Legacy.clickElement(payment.nmiSubmitPaymentButton);
        GetWebDriver.switchBackToDom();
        Utilities.acceptAlert();
        Legacy.waitVisible(confirmationPage.paymentResultTitle);
    }// chargeSingleNmiCc()

    //***Author Aarbi
    @Then("I charge cc with payrix gateway {string}")
    public void chargeSinglePayrixCc(String needPayrixCC) {
        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixCcIframe);
        Legacy.locate(payment.payrixCcNumberInputField).sendKeys(needPayrixCC);
        GetWebDriver.switchBackToDom();
        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixExpIframe);
        Legacy.locate(payment.payrixExpInputField).sendKeys("0228");
        GetWebDriver.switchBackToDom();
        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixCvvIframe);
        Legacy.locate(payment.payrixCvvInputField).sendKeys("123");
        GetWebDriver.switchBackToDom();
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert(10);
        Legacy.waitVisible(confirmationPage.singleCardPaymentResult);
        String actualResult = Legacy.getElementTextValue(confirmationPage.singleCardPaymentResult);
        String expectedConfirmation = "Successfully Charged Credit Card!";
        result(expectedConfirmation, actualResult, "Credit Card Confirmation", "Card on file payment");
    }

    public void chargeSinglePayrixCc(String needPayrixCC, String expriationMthYr, String cvv) {
       String expMthYr = expriationMthYr.replace("/", "");

        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixCcIframe);
        Legacy.locate(payment.payrixCcNumberInputField).sendKeys(needPayrixCC);
        GetWebDriver.switchBackToDom();
        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixExpIframe);
        Legacy.locate(payment.payrixExpInputField).sendKeys(expMthYr);
        GetWebDriver.switchBackToDom();
        Utilities.switchToIframe(payment.singlePayrixIframe);
        Utilities.switchToIframe(payment.payrixCvvIframe);
        Legacy.locate(payment.payrixCvvInputField).sendKeys(cvv);
        GetWebDriver.switchBackToDom();
        Legacy.clickElement(payment.chargeSingleCard);
        Utilities.acceptAlert();
    }//chargeSinglePayrixCc()

}
