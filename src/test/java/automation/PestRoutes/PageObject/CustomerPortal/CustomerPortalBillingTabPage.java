package automation.PestRoutes.PageObject.CustomerPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        return getOptionsFromDropDown(showNumberEntriesDropDown);
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
        if (find(searchField).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isShowingNumberOfEntriesDisplayed(){
        if (find(showingNumberOfEntriesText).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isPreviousLinkDisplayed(){
        if (find(previousLink).isDisplayed()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNextLinkDisplayed(){
        if (find(nextLink).isDisplayed()) {
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
        scrollToElementJS(find(payNowButton));
        click(payNowButton);
    }

    public boolean isPayNowButtonDisplayed() {
        if(elementIsVisible(payNowButton)) {
            scrollToElementJS(find(payNowButton));
            return true;
        }
        return false;
    }

    public void clickMakePaymentButton() {
        if (elementIsVisible(makePaymentButton)) {
            scrollToElementJS(find(makePaymentButton));
            delay(1000);
            click(makePaymentButton);
        }
    }

    public void clickPayAnotherAmount(){
        click(payAnotherAmountButton);
    }

    public boolean isUpdatePhoneImageDisplayed(){
        if (find(updatePhoneImage).isDisplayed()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isMakePaymentImageDisplayed(){
        if (find(makePaymentImage).isDisplayed()){
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
        if (elementIsVisible(enterCreditCardButton)){
            click(enterCreditCardButton);
            switchToIframeByXpath("elementSingleFrame");
            click(elementProcessTransactionButton);
        }
        return elementIsVisible(elementErrorMessage);
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
        if (elementIsVisible(By.xpath("//iframe[@id='payFields-iframe-number']"))) {
            switchToIframeByXpath("payFields-iframe-number");
            if (!elementIsVisible(pestRoutesPaymentsCardNumber)) {
                refreshPage();
                delay(2000);
                clickPayNowButton();
                clickUseOneTimeCard();
                clickMakePaymentButton();
                switchToIframeByXpath("payFields-iframe-number");
            }
            return elementIsVisible(pestRoutesPaymentsCardNumberErrorMessage);
        }
        return elementIsVisible(pestRoutesPaymentsCardNumberErrorMessage);
    }

    public boolean isPestRoutesPaymentsExpirationErrorDisplayed() {
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payFields-iframe-expiration");
        return elementIsVisible(pestRoutesPaymentsExpirationDateErrorMessage);
    }

    public boolean isPestRoutesPaymentsCVVErrorDisplayed() {
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payFields-iframe-cvv");
        return elementIsVisible(pestRoutesPaymentsCVVErrorMessage);
    }

    public boolean isPayTotalCardNumberErrorDisplayed(String gateway){
        switch(gateway) {
            case "Braintree":
                return elementIsVisible(cardNumberErrorMessage);
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
                return elementIsVisible(expirationDateErrorMessage);
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
                return elementIsVisible(checkYourInfoTryAgainErrorMessage);
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
        switchToIframeByXpath("braintree-hosted-field-number");
        scrollToElementJS(find(braintreeCardNumberField));
        type(cardNumber, braintreeCardNumberField);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("braintree-hosted-field-expirationDate");
        type(expirationDate, braintreeExpirationDateField);
        driver.switchTo().defaultContent();
    }

    public void enterElementNewCardInformation(String cardNumber, String expirationDate, String cvv){
        if (find(enterCreditCardButton).isDisplayed()){
            click(enterCreditCardButton);
        }
        switchToIframeByXpath("elementSingleFrame");
        type(cardNumber, elementCardNumberField);
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        selectFromDropDown(month, elementExpirationMonth);
        selectFromDropDown("20"+ year, elementExpirationYear);
        type(cvv, elementCVVField);
        click(elementProcessTransactionButton);
        driver.switchTo().defaultContent();
    }

    public void enterNMINewCardInformation(String cardNumber, String expirationDate, String cvv) {
        refreshPage();
        clickPayNowButton();
        delay(1000);
        clickUseOneTimeCard();
        switchToIframeByXpath("CollectJSInlineccnumber");
        scrollToElementJS(find(nmiCardNumberField));
        type(cardNumber, nmiCardNumberField);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("CollectJSInlineccexp");
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        String expirationMonthYear = month + "/20" + year;
        type(expirationMonthYear, nmiExpirationDateField);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("CollectJSInlinecvv");
        type(cvv, nmiCVVField);
        driver.switchTo().defaultContent();
    }

    public void enterSpreedlyNewCardInformation(String cardNumber, String expirationDate, String cvv){
        WebElement iFrameCardNumber = find(By.xpath("//iframe[contains(@id,'spreedly-number-frame')]"));
        driver.switchTo().frame(iFrameCardNumber);
        scrollToElementJS(find(spreedlyCardNumber));
        type(cardNumber, spreedlyCardNumber);
        driver.switchTo().defaultContent();
        String[] separateMonthYear = expirationDate.split("/");
        String month = separateMonthYear[0];
        String year = separateMonthYear[1];
        Select findDropDown = new Select(find(spreedlyExpirationMonth));
        findDropDown.selectByValue(month);
        selectFromDropDown("20"+ year, spreedlyExpirationYear);
        WebElement iFrameCVV = find(By.xpath("//iframe[contains(@id,'spreedly-cvv-frame')]"));
        driver.switchTo().frame(iFrameCVV);
        type(cvv, spreedlyCVVField);
        driver.switchTo().defaultContent();
    }

    public void enterPestRoutesPaymentsNewCardInformation(String cardNumber, String expirationDate, String cvv) {
        refreshPage();
        clickPayNowButton();
        delay(1000);
        clickUseOneTimeCard();
        switchToIframeByXpath("payFields-iframe-number");
        scrollToElementJS(find(pestRoutesPaymentsCardNumber));
        type(cardNumber, pestRoutesPaymentsCardNumber);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payFields-iframe-expiration");
        type(expirationDate, pestRoutesPaymentsExpirationDate);
        driver.switchTo().defaultContent();
        switchToIframeByXpath("payFields-iframe-cvv");
        type(cvv, pestRoutesPaymentsCVVField);
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