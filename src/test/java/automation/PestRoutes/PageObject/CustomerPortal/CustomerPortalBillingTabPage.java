package automation.PestRoutes.PageObject.CustomerPortal;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Legacy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

public class CustomerPortalBillingTabPage extends CustomerPortalBasePage {

    private By payNowButton = By.xpath("//button[@id='showPaymentOptionsButton']");
    private By payAnotherAmountButton = By.xpath("//input[@id='payDifferent']");
    private By cardOnFileButton = By.xpath("//input[contains(@id,'paymentType')]");
    private By useOneTimeCardButton = By.xpath("//input[@name='paymentType' and @value='singlePayment']");
    private By enterCreditCardButton = By.xpath("//button[@id='renderSecureCardFormButton']");
    private By braintreeCardNumberField = By.xpath("//input[@id='credit-card-number']");
    private By braintreeExpirationDateField = By.xpath("//input[@id='expiration']");
    private By checkYourInfoTryAgainErrorMessage = By.xpath("//div[text()='Please check your information and try again.']");
    private By elementCardNumberField = By.xpath("//input[@id='cardNumber']");
    private By elementExpirationMonth = By.xpath("//select[@id='ddlExpirationMonth']");
    private By elementExpirationYear = By.xpath("//select[@id='ddlExpirationYear']");
    private By elementCVVField = By.xpath("//input[@id='CVV']");
    private By elementProcessTransactionButton = By.xpath("//a[@id='submit']");
    private By elementErrorMessage = By.xpath("//span[contains(text(),'Values must be entered for required fields')]");
    private By nmiCardNumberField = By.xpath("//input[@id='ccnumber']");
    private By nmiExpirationDateField = By.xpath("//input[@id='ccexp']");
    private By nmiCVVField = By.xpath("//input[@id='cvv']");
    private By spreedlyCardNumber = By.xpath("//input[@id='card_number']");
    private By spreedlyExpirationMonth = By.xpath("//select[@name='expMonth']");
    private By spreedlyExpirationYear = By.xpath("//select[@name='expYear']");
    private By spreedlyCVVField = By.xpath("//input[@id='cvv']");
    private By pestRoutesPaymentsCardNumber = By.xpath("//input[@id='payment_number']");
    private By pestRoutesPaymentsCardNumberErrorMessage = By.xpath("//span[@id='payment_number_error']");
    private By pestRoutesPaymentsExpirationDate = By.xpath("//input[@id='expiration']");
    private By pestRoutesPaymentsExpirationDateErrorMessage = By.xpath("//span[@id='expiration_error']");
    private By pestRoutesPaymentsCVVField = By.xpath("//input[@id='payment_cvv']");
    private By pestRoutesPaymentsCVVErrorMessage = By.xpath("//span[@id='cvv_error']");
    private By cardNumberErrorMessage = By.xpath("//div[text()='Please fill out a card number.' and not(contains(@id,'field-description-number'))]");
    private By expirationDateErrorMessage = By.xpath("//div[text()='Please fill out an expiration date.' and not(contains(@id,'field-description-expirationDate'))]");
    private By makePaymentButton = By.xpath("//button[@id='submitPaymentOptionsButton']");
    private By currentSection = By.xpath("//h3[text()='Current']/..");
    private By showNumberEntriesDropDown = By.xpath("//select[@name='transactionHistory_length']");
    private By searchField = By.xpath("//div[@id='transactionHistory_filter']/label//following::input");
    private By newDateColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'new Date')]");
    private By descriptionColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'Description')]");
    private By accountInfoColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'Account Info')]");
    private By chargeColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'Charge')]");
    private By balanceColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'Balance')]");
    private By paymentColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'Payment')]");
    private By ticketNumberColumn = By.xpath("//table[@id='transactionHistory']//th[contains(text(),'#')]");
    private By showingNumberOfEntriesText = By.xpath("//div[@id='transactionHistory_info']");
    private By previousLink = By.xpath("//a[@id='transactionHistory_previous']");
    private By nextLink = By.xpath("//a[@id='transactionHistory_previous']");
    private By updatePhoneImage = By.xpath("//div[@id='welcomeBarInner']//img[contains(@src, 'phones')]");
    private By makePaymentImage = By.xpath("//div[@id='welcomeBarInner']//img[contains(@src, 'balance')]");
    private By paymentMethodErrorMessage = By.xpath("//div[text()='Please choose a method of payment.']");

    public List<String> getValuesFromShowNumberEntriesDropDown(){
        return Legacy.getOptionsFromDropDown(showNumberEntriesDropDown);
    }

    public String getNewDate(){
        return getText(newDateColumn);
    }

    public String getDescription(){
        return getText(descriptionColumn);
    }

    public String getAccountInfo(){
        return getText(accountInfoColumn);
    }

    public String getCharge(){
        return getText(chargeColumn);
    }

    public String getBalance(){
        return getText(balanceColumn);
    }

    public String getPayment(){
        return getText(paymentColumn);
    }

    public String getTicketIDNumber(){
        return getText(ticketNumberColumn);
    }

    public boolean isSearchFieldDisplayed(){
        if (Utilities.locate(searchField).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isShowingNumberOfEntriesDisplayed(){
        if (Utilities.locate(showingNumberOfEntriesText).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isPreviousLinkDisplayed(){
        if (Utilities.locate(previousLink).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNextLinkDisplayed(){
        if (Utilities.locate(nextLink).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public String getCurrentSectionText(){
        return getText(currentSection);
    }

    public void clickPayNowButton(){
        Legacy.scrollToElementJS(Utilities.locate(payNowButton));
        click(payNowButton);
    }

    public boolean isPayNowButtonDisplayed() {
        if(isVisible(payNowButton)) {
            Legacy.scrollToElementJS(Utilities.locate(payNowButton));
            return true;
        }
        return false;
    }

    public void clickMakePaymentButton() {
        if (isVisible(makePaymentButton)) {
            Legacy.scrollToElementJS(Utilities.locate(makePaymentButton));
            String buttonMakePayment = "//button[@id='submitPaymentOptionsButton']";
            Legacy.isPresent(buttonMakePayment);
            click(makePaymentButton);
            delay(5000);
        }
        delay(5000);
    }

    public void clickPayAnotherAmount(){
        click(payAnotherAmountButton);
    }

    public boolean isUpdatePhoneImageDisplayed(){
        if (Utilities.locate(updatePhoneImage).isDisplayed()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isMakePaymentImageDisplayed(){
        if (Utilities.locate(makePaymentImage).isDisplayed()){
            return true;
        }else {
            return false;
        }
    }

    public void clickUseOneTimeCard(){
        delay(3000);
        click(useOneTimeCardButton);
        delay(3000);
    }

    public void clickCardOnFile(){
        click(cardOnFileButton);
    }

    public String getPaymentMethodErrorMessage(){
        return getText(paymentMethodErrorMessage);
    }

    public boolean isElementErrorDisplayed() {
        if (isVisible(enterCreditCardButton)){
            click(enterCreditCardButton);
            switchToIframe("elementSingleFrame");
            click(elementProcessTransactionButton);
        }
        return isVisible(elementErrorMessage);
    }

    public boolean isNMIAlertErrorDisplayed() {
       delay(3000);
       String alertErrorMessage = getAlertText();
       if (alertErrorMessage.contains("Incorrect")) {
           acceptAlert();
           return true;
       }
       return false;
    }

    public boolean isSpreedlyAlertErrorDisplayed () {
        delay(3000);
        String alertErrorMessage = getAlertText();
        if (alertErrorMessage.contains("Invalid")) {
            acceptAlert();
            return true;
        }
        return false;
    }

    public boolean isPestRoutesPaymentsCardNumberErrorDisplayed() {
        if (isVisible(By.xpath("//iframe[@id='payFields-iframe-number']"))) {
            switchToIframe("payFields-iframe-number");
            if (!isVisible(pestRoutesPaymentsCardNumber)) {
                GetWebDriver.refreshPage();
                delay(2000);
                clickPayNowButton();
                clickUseOneTimeCard();
                clickMakePaymentButton();
                switchToIframe("payFields-iframe-number");
            }
            return isVisible(pestRoutesPaymentsCardNumberErrorMessage);
        }
        return isVisible(pestRoutesPaymentsCardNumberErrorMessage);
    }

    public boolean isPestRoutesPaymentsExpirationErrorDisplayed() {
        driver.switchTo().defaultContent();
        switchToIframe("payFields-iframe-expiration");
        return isVisible(pestRoutesPaymentsExpirationDateErrorMessage);
    }

    public boolean isPestRoutesPaymentsCVVErrorDisplayed() {
        driver.switchTo().defaultContent();
        switchToIframe("payFields-iframe-cvv");
        return isVisible(pestRoutesPaymentsCVVErrorMessage);
    }

    public boolean isPayTotalCardNumberErrorDisplayed(String gateway){
        switch(gateway) {
            case "Braintree":
                return isVisible(cardNumberErrorMessage);
            case "Element":
                return isElementErrorDisplayed();
            case "NMI":
                return isNMIAlertErrorDisplayed();
            case "Spreedly":
                return isSpreedlyAlertErrorDisplayed();
            case "PestRoutes Payments":
                return isPestRoutesPaymentsCardNumberErrorDisplayed();
            default: return false;
        }
    }

    public boolean isPayTotalExpirationDateErrorDisplayed(String gateway){
        switch(gateway) {
            case "Braintree":
                return isVisible(expirationDateErrorMessage);
            case "Element":
                return isElementErrorDisplayed();
            case "NMI":
                clickMakePaymentButton();
                return isNMIAlertErrorDisplayed();
            case "Spreedly":
                clickMakePaymentButton();
                return isSpreedlyAlertErrorDisplayed();
            case "PestRoutes Payments":
                return isPestRoutesPaymentsExpirationErrorDisplayed();
            default:
                return false;
        }
    }

   public boolean isPayTotalCVVErrorDisplayed(String gateway){
        switch(gateway) {
            case "Braintree":
                return isVisible(checkYourInfoTryAgainErrorMessage);
            case "Element":
                return isElementErrorDisplayed();
            case "NMI":
                clickMakePaymentButton();
                return isNMIAlertErrorDisplayed();
            case "Spreedly":
                clickMakePaymentButton();
                return isSpreedlyAlertErrorDisplayed();
            case "PestRoutes Payments":
                return isPestRoutesPaymentsCVVErrorDisplayed();
            default: return false;
        }
    }

    public void enterBraintreeNewCardInformation(String cardNumber, String expirationDate){
        driver.switchTo().defaultContent();
        switchToIframe("braintree-hosted-field-number");
        Legacy.scrollToElementJS(Utilities.locate(braintreeCardNumberField));
        Legacy.type(cardNumber, braintreeCardNumberField);
        driver.switchTo().defaultContent();
        switchToIframe("braintree-hosted-field-expirationDate");
        Legacy.type(expirationDate, braintreeExpirationDateField);
        driver.switchTo().defaultContent();
    }

    public void enterElementNewCardInformation(String cardNumber, String expirationDate, String cvv){
        if (Utilities.locate(enterCreditCardButton).isDisplayed()){
            click(enterCreditCardButton);
        }
        switchToIframe("elementSingleFrame");
        Legacy.type(cardNumber, elementCardNumberField);
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        Utilities.selectByText(elementExpirationMonth, month);
        Utilities.selectByText(elementExpirationYear, "20"+ year);
        Legacy.type(cvv, elementCVVField);
        click(elementProcessTransactionButton);
        acceptAlert(10);
        driver.switchTo().defaultContent();
    }

    public void enterNMINewCardInformation(String cardNumber, String expirationDate, String cvv) {
        GetWebDriver.refreshPage();
        clickPayNowButton();
        delay(1000);
        clickUseOneTimeCard();
        switchToIframe("CollectJSInlineccnumber");
        Legacy.scrollToElementJS(Utilities.locate(nmiCardNumberField));
        Legacy.type(cardNumber, nmiCardNumberField);
        driver.switchTo().defaultContent();
        switchToIframe("CollectJSInlineccexp");
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        String expirationMonthYear = month + "/20" + year;
        Legacy.type(expirationMonthYear, nmiExpirationDateField);
        driver.switchTo().defaultContent();
        switchToIframe("CollectJSInlinecvv");
        Legacy.type(cvv, nmiCVVField);
        driver.switchTo().defaultContent();
    }

    public void enterSpreedlyNewCardInformation(String cardNumber, String expirationDate, String cvv){
        WebElement iFrameCardNumber = Utilities.locate(By.xpath("//iframe[contains(@id,'spreedly-number-frame')]"));
        driver.switchTo().frame(iFrameCardNumber);
        Legacy.scrollToElementJS(Utilities.locate(spreedlyCardNumber));
        Legacy.type(cardNumber, spreedlyCardNumber);
        driver.switchTo().defaultContent();
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        Select findDropDown = new Select(Utilities.locate(spreedlyExpirationMonth));
        findDropDown.selectByValue(month);
        Utilities.selectByText(spreedlyExpirationYear, "20"+ year);
        WebElement iFrameCVV = Utilities.locate(By.xpath("//iframe[contains(@id,'spreedly-cvv-frame')]"));
        driver.switchTo().frame(iFrameCVV);
        Legacy.type(cvv, spreedlyCVVField);
        driver.switchTo().defaultContent();
    }

    public void enterPestRoutesPaymentsNewCardInformation(String cardNumber, String expirationDate, String cvv) {
        GetWebDriver.refreshPage();
        clickPayNowButton();
        delay(1000);
        clickUseOneTimeCard();
        switchToIframe("payFields-iframe-number");
        Legacy.scrollToElementJS(Utilities.locate(pestRoutesPaymentsCardNumber));
        Legacy.type(cardNumber, pestRoutesPaymentsCardNumber);
        driver.switchTo().defaultContent();
        switchToIframe("payFields-iframe-expiration");
        Legacy.type(expirationDate, pestRoutesPaymentsExpirationDate);
        driver.switchTo().defaultContent();
        switchToIframe("payFields-iframe-cvv");
        Legacy.type(cvv, pestRoutesPaymentsCVVField);
        driver.switchTo().defaultContent();
    }

    public void enterNewCardInformation(String gateway, String cardNumber, String expirationDate, String cvv) throws InterruptedException {
        switch(gateway) {
            case "Braintree":
                enterBraintreeNewCardInformation(cardNumber, expirationDate);
                break;
            case "Element":
                enterElementNewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "NMI":
                enterNMINewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "Spreedly":
                enterSpreedlyNewCardInformation(cardNumber, expirationDate, cvv);
                break;
            case "PestRoutes Payments":
                enterPestRoutesPaymentsNewCardInformation(cardNumber, expirationDate, cvv);
                break;
        }
    }
}