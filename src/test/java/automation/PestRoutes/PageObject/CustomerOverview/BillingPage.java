package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.PageObject.BasePage;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class BillingPage extends BasePage {
	//Page title objects
	public String paymentMethodTitle = "//div[@id='billingInfoContent']//h3";

	//**********Left navigation objects**********
	public String billingInfoButton = "//li[text()='Billing Info']";
	public String addPaymentMethodButton = "//li[text()='+ Add Payment Method']";
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
	private By addAdditionalContactButton = By.xpath("//h3[text()='Additional Contacts']/following-sibling::div[text()='+ Add Contact']");

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
	public String maxMonthlyChargeInputField = "//input[@name='maxMonthlyCharge']";

	//***Drop downs***
	public String autoPayDropdown = "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']";
	private By stateDropdown = By.xpath("//select[@name='billingState']");
	private By countryDropdown = By.xpath("//select[@name='billingCountryID']");
	private By preferredBillingDateDropdown = By.xpath("//select[@name='preferredBillingDate']");
	private By collectionStateDropdown = By.xpath("//select[@name='collectionsStage']");
	public String autoPaySetValue = "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']/option[@selected='selected']";
	private By ccMonthDropdown = By.xpath("//select[@name='expMonth']");
	private By ccYearDropdown = By.xpath("//select[@name='expYear']");
	
	//**********Add Payment method objects**********
	public String addCreditCardButton = "//div[@id='billingInfoContent']//div[text()='Credit Card']";
	public String addBankAccountButton = "//div[@id='billingInfoContent']//div[text()='Bank Account']";

	public String ccSecurelyEnterCardInfoButton = "//div[text()='Securely Enter Card Info']";
	private By ccRemoveAccountButton = By.xpath("//div[text()='Remove Account']");
	private By ccCopyAccountButton = By.xpath("//div[text()='Copy Account']");
	public String ccSaveCardButton = "//button[@id='submit-payment']";
	private By cc_X_ButtonInAddCreditCardDialog = By.xpath("//form[@id='lightbox']//div[@class='x-container']");
	public String tokenValue = "//b[text()='Token:']/parent::td/following-sibling::td";
	public String ccOptionOnLeft = "//div[@id='billingInfoPanel']//li[text()='Credit Card ']";
	public String ACHOptionOnLeft = "//div[@id='billingInfoPanel']//li[text()='Bank Account ']";

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
	//Brain tree
	public String brainCcIframe = "braintree-hosted-field-number";
	public String brainExpMonthIframe = "braintree-hosted-field-expirationMonth";
	public String brainExpYearIframe = "braintree-hosted-field-expirationYear";
	public String brainCvvIframe = "braintree-hosted-field-cvv";
	public String brainCcNumberInputField = "//input[@id='credit-card-number']";
	public String brainExpMonthInputField = "//input[@id='expiration-month']";
	public String brainExpYearInputField = "//input[@id='expiration-year']";
	public String brainCvvInputField = "//input[@id='cvv']";
	//PestRoutes Payments
	public String payrixIframe = "payrixAddIFrame";
	public String pestRoutesIframeCc = "payFields-iframe-number";
	public String pestRoutesIframeExp = "payFields-iframe-expiration";
	public String pestRoutesIframeCvv = "payFields-iframe-cvv";
	public String pestRoutesCcNumberInputField = "//input[@id='payment_number']";
	public String pestRoutesCcExpirationInputField = "//input[@id='expiration']";
	public String pestRoutesCcCvvInputField = "//input[@id='payment_cvv']";
	public String savePaymentMethodButton = "//button[@id='secureCardSaveButton']";
	//Element
	private By elementOneTimeCcIframe = By.xpath("elementSingleChargeWrapper");
	private By elementOneTimeCvvInputField = By.xpath("//input[@id='CVV']");
	public String elementEnterCcButton = "//button[@id='renderSecureCardFormButton']";
	public String elementIframe = "elementFrame";
	public String elementCcNumberInputField = "//input[@id='cardNumber']";
	public String elementExpMonthDropdown = "//select[@id='ddlExpirationMonth']";
	public String elementExpYearDropdown = "//select[@id='ddlExpirationYear']";
	public String elementSaveCcButton = "//a[@id='submit']";
	//Spreedly
	public String spreedlyCcNumberIframe = "//div[@id='spreedly-card-number']/iframe";
	public String spreedlyCvvIframe = "//div[@id='spreedly-cvv']/iframe";
	public String spreedlyCcInputField = "//input[@id='card_number']";
	public String spreedlyExpMonthDropdown = "//select[@name='expMonth']";
	public String spreedlyExpYearDropdown = "//select[@name='expYear']";
	public String spreedlyCvvInputField = "//input[@id='cvv']";
	//NMI
	public String nmiOneTimeCcIframe = "CollectJSIframe";
	private By nmiOneTimeCcNumberInputField = By.xpath("//input[@id='cc-number']");
	private By nmiOneTimeExpInputField = By.xpath("//input[@id='cc-exp']");
	private By nmiOneTimeCvvInputField = By.xpath("//input[@id='cc-cvv']");
	private By nmiOneTimeSubmitButton = By.xpath("//button[@id='submit-payment']");
	public String nmiCcNumberIframe = "CollectJSInlineccnumber";
	public String nmiExpIframe = "CollectJSInlineccexp";
	public String nmiCvvIframe = "CollectJSInlinecvv";
	public String nmiCcNumberInputField = "//input[@id='ccnumber']";
	public String nmiExpInputField = "//input[@id='ccexp']";
	public String nmiCvvInputField = "//input[@id='cvv']";
	public String nmiSavedCc = "//input[@name='accountNumber']";

	
	/* 
	 * Below methods click or select element
	 */
	
	public void clickElement(String needElement) throws InterruptedException {
		Utilities.clickElement(needElement, ElementType.XPath);
		Thread.sleep(100);
	}
	
	public void selectDropdown(String needDropdown, String needValue) throws InterruptedException {
		Thread.sleep(100);
		Utilities.waitUntileElementIsVisible(needDropdown);
		Utilities.selectValueFromDropDownByValue(needDropdown, needValue);
	}
	
	/*
	 * Below method set value in an input field
	 */
	
	public void setInputField(String needInputField, String needValue) {
		Utilities.waitUntileElementIsVisible(needInputField);
		Utilities.clickElement(needInputField, ElementType.XPath);
		if (SystemUtils.IS_OS_MAC_OSX){
			Utilities.doubleClick(needInputField);
		}else {
			FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(Keys.CONTROL, "a");
		}
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(needValue);
	}
	
	/*
	 * Below methods gets the text value or attribute value of an element
	 */
	
	public String getTextValue(String needElement) {
		return Utilities.getElementTextValue(needElement, ElementType.XPath);
	}
	
	public String getAttributeValue(String needElement, String needAttribute) {
		return Utilities.getAttributeValue(needElement, needAttribute);
	}

	public String getAutoPayValue(){
		return Utilities.getElementTextValue(autoPaySetValue, ElementType.XPath);
	}

	//Author : Aditya
	public String getTokenValue(String paymentOption, String tokenNumber) {
		Utilities.waitUntileElementIsVisible(paymentOption);
		Utilities.clickElement(paymentOption, Utilities.ElementType.XPath);
		Utilities.waitUntileElementIsVisible(tokenNumber);
		String entireToken = Utilities.getElementTextValue(tokenNumber, Utilities.ElementType.XPath);
		int startingIndexOfToken = entireToken.indexOf("/", entireToken.indexOf("/") + 1);
		return entireToken.substring(startingIndexOfToken + 1, entireToken.length());
	}

	public String getCustomerAccountID() {
		String customerBillInformation = getText(selectBillToField);
		String customerAccountID = customerBillInformation.replaceAll(
				"\\s.*", "");
		return customerAccountID;
	}
}
