package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import static automation.PestRoutes.Utilities.Deprecated.*;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;
import java.util.Random;

import static automation.PestRoutes.Utilities.Element.WebSelect.*;
import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

public class BillingPage extends BasePage {

	Random random = new Random();
	String[] routingNumber = {"111000614", "051000017", "122101706"};
	String[] accountNumber = {"111222333", "222333444", "333444555"};

	public String paymentMethodTitle = "//div[@id='billingInfoContent']//h3";
	public By  textBillingInfoUsedMsg = By.xpath("//div[contains(text(),'This billing information is used')]");

	//**********Left navigation objects**********
	public String billingInfoButton = "//li[text()='Billing Info']";
	public String addPaymentMethodButton = "//li[text()='+ Add Payment Method']";
	private By addPaymentMethod = By.xpath("//div[@id='billingInfoPanel']//li[text()='+ Add Payment Method']");
	public String creditCardButton = "//li[text()='Credit Card ']";
	public String bankAccountButton = "//li[text()='Bank Account ']";

	//**********Billing information objects**********

	// Default Bill To Section
	private By selectBillToField = By.xpath("//div[@name='billingAccountDisplayBtn']");

	//***Check Boxes***
	private By additionalContactSmsCheckBox = By.xpath("//div[@class='row no-gutters']//input[@name='additional-smsReminders']");
	private By additionalContactEmailCheckBox = By.xpath("//div[@class='row no-gutters']//input[@name='additional-emailReminders']");
	private By additionalContactVoiceCheckBox = By.xpath("//div[@class='row no-gutters']//input[@name='additional-phoneReminders']");
	private By additionalContactBusinessContactCheckBox = By.xpath("//div[@class='row no-gutters']//input[@name='additional-saveToBusinessContacts']");

	//***Buttons***
	private By autopayNoButton = By.xpath("//div[text()='No']");
	private By autopayCreditCardButton = By.xpath("//div[text()='CC']");
	private By autopayAchButton = By.xpath("//div[text()='ACH']");
	private By copyBillingInfoButton = By.xpath("//div[text()='Copy Billing Info']");
	private By assignNewBillingAccountButton = By.xpath("//div[text()='Assign New Billing Account']");
	public String saveBillingInfoButton = "//div[text()='Save Billing Info']";
	private By btnSaveBillingInfo = By.xpath("//div[text()='Save Billing Info']");
	private By addAdditionalContactButton = By.xpath("//h3[text()='Additional Contacts']/following-sibling::div[text()='+ Add Contact']");
	private By ccRemoveAccountButton = By.xpath("//*[@id='paymentProfileForm']//button[contains(text(),'Remove Account')]");
	private By ccRemovePaymentMethodButton = By.xpath("//*[@id='paymentProfileForm']//button[contains(text(),'Remove Payment Method')]");

	//***Input Fields***
	private By firstNameInputField = By.xpath("//input[@name='billingFName']");
	private By lastNameInputField = By.xpath("//input[@name='billingLName']");
	private By streetAddressInputField = By.xpath("//input[@placeholder='Billing Address']");
	private By cityInputField = By.xpath("//input[@placeholder='Billing City']");
	private By zipcodeInputField = By.xpath("//input[@placeholder='Billing Zip']");
	private By billingPhoneInputField = By.xpath("//input[@placeholder='Billing Phone']");
	private By billingEmailInputField = By.xpath("//input[@placeholder='Billing Email']");
	private By billingCompanyNameInputField = By.xpath("//input[@placeholder='Billing Company Name']");
	private By paymentHoldDateInputField = By.xpath("//input[@name='paymentHoldDate']");
	public String maxMonthlyChargeInputField = "//div[@id='billingSwitches']//input[@name='maxMonthlyCharge']";

	//***Drop downs***
	public String autoPayDropdown = "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']";
	private By dropdwnAutoPay = By.xpath( "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']");

	private By stateDropdown = By.xpath("//select[@name='billingState']");
	private By countryDropdown = By.xpath("//select[@name='billingCountryID']");
	private By preferredBillingDateDropdown = By.xpath("//select[@name='preferredBillingDate']");
	private By collectionStateDropdown = By.xpath("//select[@name='collectionsStage']");
	public String autoPaySetValue = "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']/option[@selected='selected']";

	private By ccMonthDropdown = By.xpath("//select[@name='expMonth']");
	private By ccYearDropdown = By.xpath("//select[@name='expYear']");

	//**********Add Payment method objects**********
	public String addCreditCardButton = "//div[@id='billingInfoContent']//div[text()='Credit Card']";
	private By creditCardPaymentMethod = By.xpath("//div[@id='billingInfoContent']//div[text()='Credit Card']");
	public String addBankAccountButton = "//div[@id='billingInfoContent']//div[text()='Bank Account']";

	public String ccSecurelyEnterCardInfoButton = "//div[text()='Securely Enter Card Info']";
	private By ccCopyAccountButton = By.xpath("//div[text()='Copy Account']");

	private By ccCopyAccountLink = By.xpath("//*[@id='paymentProfileForm']/button[contains(text(),'Copy Account')]");

	//private By ccEditCardDetailsLnk = By.xpath("//*[@id='showDuplicatePaymentProfileWarning' and contains(text(),'Edit Card Details')]");
	private By ccEditCardDetailsLnk = By.xpath("//button[contains(text(),'Edit Card Details')]");

	public String ccSaveCardButton = "//button[@id='submit-payment']";
	private By cc_X_ButtonInAddCreditCardDialog = By.xpath("//form[@id='lightbox']//div[@class='x-container']");
	public String tokenValue = "//b[text()='Token:']/parent::td/following-sibling::td";
	public String ccOptionOnLeft = "//div[@id='billingInfoPanel']//span[text()='Credit Card']";
	public String ACHOptionOnLeft = "//div[@id='billingInfoPanel']//span[text()='Bank Account']";
	private By lnkBillingInfo = By.xpath("//*[@id='billingInfoPanel']//div/ul/li[contains(text(),'Billing Info')]");

	//**********Bank Account objects**********
	//***Buttons***
	public String saveBankAccountButton = "//button[@id='ach_saving_button']";
	private By bankAccountSaveAddressAndNameButton = By.xpath("//div[text()='Save Address / Name']");
	private By bankAccountUpdateAccountNumberButton = By.xpath("//div[text()='Update Account Numbers']");

	//***Input Fields***
	public String bankAccountBankNameInputField = "//input[@name='bankName']";
	public String bankAccountRoutingNumberInputField = "//input[@name='routingNumber']";
	public String bankAccountAccountNumberInputField = "//input[@name='accountNumber']";
	//***Drop downs***
	public String bankAcountAccountTypeDropdown = "//select[@name='accountType']";
	public String bankAccountCheckTypeDropdown = "//select[@name='checkType']";

	//***Saved ACH
	public String savedBankName = "//input[@name='displayBankName']";

	//**********Credit card objects*************
	//***Gateway Types
	public String paymentGatewayType = "//form[@id='paymentProfileForm']//i";
	private By buttonSavePaymentMethod = By.xpath("//button[@id='secureCardSaveButton']");
	//Brain tree
	public String brainCcIframe = "braintree-hosted-field-number";
	public String brainExpMonthIframe = "braintree-hosted-field-expirationMonth";
	public String brainExpYearIframe = "braintree-hosted-field-expirationYear";
	public String brainCvvIframe = "braintree-hosted-field-cvv";
	public String brainCcNumberInputField = "//input[@id='credit-card-number']";
	private By braintreeCardNumberField = By.xpath("//input[@id='credit-card-number']");
	public String brainExpMonthInputField = "//input[@id='expiration-month']";
	private By braintreeExpirationMonth = By.xpath("//input[@id='expiration-month']");
	public String brainExpYearInputField = "//input[@id='expiration-year']";
	private By braintreeExpirationYear = By.xpath("//input[@id='expiration-year']");
	public String brainCvvInputField = "//input[@id='cvv']";
	private By braintreeCVVField = By.xpath("//input[@id='cvv']");
	//PestRoutes Payments
	public String payrixIframe = "payrixAddIFrame";
	public String pestRoutesIframeCc = "payFields-iframe-number";
	public String pestRoutesIframeExp = "payFields-iframe-expiration";
	public String pestRoutesIframeCvv = "payFields-iframe-cvv";
	public String pestRoutesCcNumberInputField = "//input[@id='payment_number']";
	private By pestRoutesPaymentsCardNumber = By.xpath("//input[@id='payment_number']");
	public String pestRoutesCcExpirationInputField = "//input[@id='expiration']";
	private By pestRoutesPaymentsExpirationDate = By.xpath("//input[@id='expiration']");
	public String pestRoutesCcCvvInputField = "//input[@id='payment_cvv']";
	private By pestRoutesPaymentsCVVField = By.xpath("//input[@id='payment_cvv']");
	public String savePaymentMethodButton = "//button[@id='secureCardSaveButton']";
	//Element
	public String elementEnterCcButton = "//button[@id='renderSecureCardFormButton']";
	private By enterCreditCardButton = By.xpath("//button[@id='renderSecureCardFormButton']");
	public String elementIframe = "elementFrame";
	public String elementCcNumberInputField = "//input[@id='cardNumber']";
	private By elementCardNumberField = By.xpath("//input[@id='cardNumber']");
	public String elementExpMonthDropdown = "//select[@id='ddlExpirationMonth']";
	private By elementExpirationMonth = By.xpath("//select[@id='ddlExpirationMonth']");
	public String elementExpYearDropdown = "//select[@id='ddlExpirationYear']";
	private By elementExpirationYear = By.xpath("//select[@id='ddlExpirationYear']");
	public String elementSaveCcButton = "//a[@id='submit']";
	private By elementProcessTransactionButton = By.xpath("//a[@id='submit']");
	//Spreedly
	public String spreedlyCcNumberIframe = "//div[@id='spreedly-card-number']/iframe";
	public String spreedlyCvvIframe = "//div[@id='spreedly-cvv']/iframe";
	public String spreedlyCcInputField = "//input[@id='card_number']";
	private By spreedlyCardNumber = By.xpath("//input[@id='card_number']");
	public String spreedlyExpMonthDropdown = "//select[@name='expMonth']";
	private By spreedlyExpirationMonth = By.xpath("//select[@name='expMonth']");
	public String spreedlyExpYearDropdown = "//select[@name='expYear']";
	private By spreedlyExpirationYear = By.xpath("//select[@name='expYear']");
	public String spreedlyCvvInputField = "//input[@id='cvv']";
	private By spreedlyCVVField = By.xpath("//input[@id='cvv']");
	//NMI
	private By nmiCardNumberField = By.xpath("//input[@id='ccnumber']");
	private By nmiExpirationDateField = By.xpath("//input[@id='ccexp']");
	private By nmiCVVField = By.xpath("//input[@id='cvv']");
	public String nmiCcNumberIframe = "CollectJSInlineccnumber";
	public String nmiExpIframe = "CollectJSInlineccexp";
	public String nmiCvvIframe = "CollectJSInlinecvv";
	public String nmiCcNumberInputField = "//input[@id='ccnumber']";
	public String nmiExpInputField = "//input[@id='ccexp']";
	public String nmiCvvInputField = "//input[@id='cvv']";
	public String nmiSavedCc = "//input[@name='accountNumber']";

	public final String sharedBillingInfoMsg = "This billing information is used in " +
			"more than one account and cannot be updated. If you need to make a change you can " +
			"remove this payment method and create a new one.";

	/*
	 * Below methods click or select element
	 */

	public void clickElement(String needElement) throws InterruptedException {
		Deprecated.clickElement(needElement);
		Thread.sleep(100);
	}

	public void selectDropdown(String needDropdown, String needValue) throws InterruptedException {
		Thread.sleep(100);
		Deprecated.waitVisible(needDropdown);
		Deprecated.selectByText(needDropdown, needValue);
	}

	/*
	 * Below method set value in an input field
	 */

	public void setInputField(String needInputField, String needValue) {
		Deprecated.waitVisible(needInputField);
		Deprecated.clickElement(needInputField);
		if (SystemUtils.IS_OS_MAC_OSX){
			Deprecated.doubleClick(needInputField);
		}else {
			Deprecated.locate(needInputField).sendKeys(Keys.CONTROL, "a");
		}
		Deprecated.locate(needInputField).sendKeys(needValue);
	}
	/* **************** End of Setters() ******************************/

	/*
	 * Below methods gets the text value or attribute value of an element
	 */

	public String getTextValue(String needElement) {
		return Deprecated.getElementTextValue(needElement);
	}

	public String getAttributeValue(String needElement, String needAttribute) {
		return Deprecated.getAttribute(needElement, needAttribute);
	}

	public String getAutoPayValue(){
		return Deprecated.getElementTextValue(autoPaySetValue);
	}

	//Author : Aditya
	public String getTokenValue(String paymentOption, String tokenNumber) {
		Deprecated.waitVisible(paymentOption);
		Deprecated.clickElement(paymentOption);
		Deprecated.waitVisible(tokenNumber);
		String entireToken = Deprecated.getElementTextValue(tokenNumber);
		int startingIndexOfToken = entireToken.indexOf("/", entireToken.indexOf("/") + 1);
		return entireToken.substring(startingIndexOfToken + 1, entireToken.length());
	}

	public String getCustomerAccountID() {
		String customerBillInformation = getText(selectBillToField);
		String customerAccountID = customerBillInformation.replaceAll(
				"\\s.*", "");
		return customerAccountID;
	}
	public String getSharedBillingInfoMsg(){
		return getText(textBillingInfoUsedMsg);
	}//getSharedBillingInfoMsg()

	/* **************** End of Getters() ******************************/

	/*
	 ****************** General Methods and Functions ********************
	 */
	public void clickAddPaymentMethod() {
		//delay(3000);
		Utilities.isVisible(addPaymentMethod);
		click(addPaymentMethod);
	}

	public void clickCreditCardButton(){
		click(creditCardPaymentMethod);
	}

	public void clickSavePaymentMethodButton(){
		click(buttonSavePaymentMethod);
	}

	public void clickCopyAccountLnk(){
		delay(500);
		click(ccCopyAccountLink);
	}//clickCopyAccountLnk()

	public void clickEditCardDetails(){
		delay(500);
		click(ccEditCardDetailsLnk);
	}//clickEditCardDetails()

    public void clickEnterCreditCardButton(){
        Utilities.isVisible(enterCreditCardButton);
        click( enterCreditCardButton);
    }//clickEnterCreditCardButton()

	public void enterBraintreeNewCardInformation(String cardNumber, String expirationDate, String cvv){
		driver.switchTo().defaultContent();
		switchToIframe(brainCcIframe);
		type(cardNumber, braintreeCardNumberField);
		driver.switchTo().defaultContent();
		String[] separateMonthYear = expirationDate.split("/");
		String month = separateMonthYear[0];
		String year = separateMonthYear[1];
		switchToIframe(brainExpMonthIframe);
		type(month, braintreeExpirationMonth);
		driver.switchTo().defaultContent();
		switchToIframe(brainExpYearIframe);
		type(year, braintreeExpirationYear);
		driver.switchTo().defaultContent();
		switchToIframe(brainCvvIframe);
		type(cvv, braintreeCVVField);
		driver.switchTo().defaultContent();
		clickSavePaymentMethodButton();
	}

	public void enterElementNewCardInformation(String cardNumber, String expirationDate){
		click(enterCreditCardButton);
		switchToIframe(elementIframe);
		type(cardNumber, elementCardNumberField);
		String[] separateMonthYear = expirationDate.split("/");
		String month = separateMonthYear[0];
		String year = separateMonthYear[1];
		Utilities.selectByText(elementExpirationMonth, month);
		Utilities.selectByText(elementExpirationYear, "20"+ year);
		click(elementProcessTransactionButton);
		driver.switchTo().defaultContent();
	}

	public void enterNMINewCardInformation(String cardNumber, String expirationDate, String cvv) {
		switchToIframe(nmiCcNumberIframe);
		type(cardNumber, nmiCardNumberField);
		driver.switchTo().defaultContent();
		switchToIframe(nmiExpIframe);
		String[] separateMonthYear = expirationDate.split("/");
		String month = separateMonthYear[0];
		String year = separateMonthYear[1];
		String expirationMonthYear = month + "/20" + year;
		type(expirationMonthYear, nmiExpirationDateField);
		driver.switchTo().defaultContent();
		switchToIframe(nmiCvvIframe);
		type(cvv, nmiCVVField);
		driver.switchTo().defaultContent();
		clickSavePaymentMethodButton();
	}

	public void enterSpreedlyNewCardInformation(String cardNumber, String expirationDate, String cvv){
		WebElement iFrameCardNumber = Utilities.locate(By.xpath("//iframe[contains(@id,'spreedly-number-frame')]"));
		driver.switchTo().frame(iFrameCardNumber);
		type(cardNumber, spreedlyCardNumber);
		driver.switchTo().defaultContent();
		String[] separateMonthYear = expirationDate.split("/");
		String month = separateMonthYear[0];
		String year = separateMonthYear[1];
		Select findDropDown = new Select(Utilities.locate(spreedlyExpirationMonth));
		findDropDown.selectByValue(month);
		Utilities.selectByText(spreedlyExpirationYear, "20"+ year);
		WebElement iFrameCVV = Utilities.locate(By.xpath("//iframe[contains(@id,'spreedly-cvv-frame')]"));
		driver.switchTo().frame(iFrameCVV);
		type(cvv, spreedlyCVVField);
		driver.switchTo().defaultContent();
		clickSavePaymentMethodButton();
	}

	public void  enterPestRoutesPaymentsNewCardInformation(String cardNumber, String expirationDate, String cvv) {
		switchToIframe(payrixIframe);
		switchToIframe(pestRoutesIframeCc);
		type(cardNumber, pestRoutesPaymentsCardNumber);
		driver.switchTo().defaultContent();
		switchToIframe(payrixIframe);
		switchToIframe(pestRoutesIframeExp);
		type(expirationDate, pestRoutesPaymentsExpirationDate);
		driver.switchTo().defaultContent();
		switchToIframe(payrixIframe);
		switchToIframe(pestRoutesIframeCvv);
		type(cvv, pestRoutesPaymentsCVVField);
		driver.switchTo().defaultContent();
		switchToIframe(payrixIframe);
		clickSavePaymentMethodButton();
		driver.switchTo().defaultContent();
	}

	public void enterNewCardInformation(String gateway, String cardNumber, String expirationDate, String cvv) {
		switch(gateway) {
			case "Braintree":
				enterBraintreeNewCardInformation(cardNumber, expirationDate, cvv);
				break;
			case "Element":
				enterElementNewCardInformation(cardNumber, expirationDate);
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

	public void clickSaveBillingInfoButton()
	{
		click(btnSaveBillingInfo);
	}//clickSaveBillingInfoButton

	public void selectAutoPayOption(String strOption)
	{
		Utilities.selectByText(dropdwnAutoPay, strOption);
	}//selectAutoPayOption()

	public void clickBillingIfoLink() {
		click(lnkBillingInfo);
	}//clickBillingIfoLink()

	public String shareCustomerCreditCardInfo(String customerName){
		clickCreditCardButton();
		clickCopyAccountLnk();

		//Click on the customer with the  credit card that should be shared
		click(By.xpath("//*[@id='billingInfoContent']//strong[contains(text(),'" + customerName + "')]"));

		acceptAlert(); //Accept the "Confirm Copy" Message'
		delay(3000);
		acceptAlert();//Accept the "Successfully Copied" Message

		return (getTokenValue(ccOptionOnLeft, tokenValue)).toLowerCase(Locale.ROOT);
	}//shareCustomerCreditCardInfo()


	/* Note: This method assumes only 1 payment method exists; and it's a shared credit card*/
	public void clickSharedCardOnFile(){
		delay(500);
		click(By.xpath("//*[@id='billingInfoPanel']//li/span[text()='Credit Card']"));
	}//clickSharedCardOnFile()

	public void clickRemoveAccount() {
		click(ccRemoveAccountButton);
	}//clickRemoveAccount()

	public void clickRemovePaymentMethod() {
		click(ccRemovePaymentMethodButton);
	}//clickRemovePaymentMethod()


	public void removeCCPaymentMethod(){
		click(ccRemoveAccountButton);
		if(isVisible(ccRemovePaymentMethodButton))
			click(ccRemovePaymentMethodButton);
	}//removePaymentMethod()

	public void clickBillingInfo() {
		delay(3000);
		click(By.xpath(billingInfoButton));
	}

	public void clickBankAccountButton() {
		click(By.xpath(addBankAccountButton));
	}

	public void typeBankName(String bankName) {
		type(bankName, By.xpath(bankAccountBankNameInputField));
	}

	public void typeRoutingNumber() {
		int selectRouting = random.nextInt(routingNumber.length);
		type(routingNumber[selectRouting], By.xpath(bankAccountRoutingNumberInputField));
	}

	public void typeAccountNumber() {
		int selectAccount = random.nextInt(accountNumber.length);
		type(accountNumber[selectAccount], By.xpath(bankAccountAccountNumberInputField));
	}

	public void clickSaveBankAccountButton() {
		click(By.xpath(saveBankAccountButton));
	}

	public void selectAutoPay() {
		selectByIndex(By.xpath(autoPayDropdown), 1);
	}

	public void typeMaxMonthlyAmount(String maxAmount) {
		type(By.xpath(maxMonthlyChargeInputField), maxAmount);
	}
}