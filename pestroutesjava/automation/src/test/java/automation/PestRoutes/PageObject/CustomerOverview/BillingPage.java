package automation.PestRoutes.PageObject.CustomerOverview;

import org.openqa.selenium.Keys;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.WebElement;

public class BillingPage {
	//Page title objects
	public String addPaymentMethodTitle = "//div[@id='billingInfoContent']//h3";
	//**********Left navigation objects**********
	public String billingInfoButton = "//li[text()='Billing Info']";
	public String addPaymentMethodButton = "//li[text()='+ Add Payment Method']";
	public String creditCardButton = "//li[text()='Credit Card ']";
	public String bankAccountButton = "//li[text()='Bank Account ']";
	
	//**********Billing information objects**********
	//***Check Boxes***
	public String additionalContactSmsCheckBox = "//div[@class='row no-gutters']//input[@name='additional-smsReminders']";
	public String additionalContactEmailCheckBox = "//div[@class='row no-gutters']//input[@name='additional-emailReminders']";
	public String additionalContactVoiceCheckBox = "//div[@class='row no-gutters']//input[@name='additional-phoneReminders']";
	public String additionalContactBusinessContactCheckBox = "//div[@class='row no-gutters']//input[@name='additional-saveToBusinessContacts']";
	//***Buttons***
	public String autopayNoButton = "//div[text()='No']";
	public String autopayCreditCardButton = "//div[text()='CC']";
	public String autopayAchButton = "//div[text()='ACH']";
	public String copyBillingInfoButton = "//div[text()='Copy Billing Info']";
	public String assignNewBillingAccountButton = "//div[text()='Assign New Billing Account']";
	public String saveBillingInfoButton = "//div[text()='Save Billing Info']";
	public String addAdditionalContactButton = "//h3[text()='Additional Contacts']/following-sibling::div[text()='+ Add Contact']";
	public String additionalContactCancelButton = "";
	public String additionalContactSaveButton = "";
	//***Input Fields***
	public String firstNameInputField = "//input[@name='billingFName']";
	public String lastNameInputField = "//input[@name='billingLName']";
	public String streetAddressInputField = "//input[@placeholder='Billing Address']";
	public String cityInputField = "//input[@placeholder='Billing City']";
	public String zipcodeInputField = "//input[@placeholder='Billing Zip']";
	public String billingPhoneInputField = "//input[@placeholder='Billing Phone']";
	public String billingEmailInputField = "//input[@placeholder='Billing Email']";
	public String billingCompanyNameInputField = "//input[@placeholder='Billing Company Name']";
	public String paymentHoldDateInputField = "//input[@name='paymentHoldDate']";
	public String maxMonthlyChargeInputField = "//input[@name='maxMonthlyCharge']";
	public String additionalContactFirstNameInputField = "";
	public String additionalContactLastNameInputField = "";
	public String additionalContactCompanyNameInputField = "";
	public String additionalContactEmailInputField = "";
	public String additionalContactPhoneInputField = "";
	public String additionalContactPhone2InputField = "";
	public String additionalContactAddressInputField = "";
	public String additionalContactCityInputField = "";
	public String additionalContactZipcodeInputField = "";
	//***Drop downs***
	public String autoPayDropdown = "//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']";
	public String stateDropdown = "//select[@name='billingState']";
	public String countryDropdown = "//select[@name='billingCountryID']";
	public String preferredBillingDateDropdown = "//select[@name='preferredBillingDate']";
	public String collectionStateDropdown = "//select[@name='collectionsStage']";
	public String additionalContactStateDropdown = "";
	public String additionalContactTypeDropdown = "";
	public String additionalContactRelationDropdown = "";
	
	//**********Add Payment method objects**********
	public String addCreditCardButton = "//div[@id='billingInfoContent']//div[text()='Credit Card']";
	public String addBankAccountButton = "//div[@id='billingInfoContent']//div[text()='Bank Account']";

	public String ccSecurelyEnterCardInfoButton = "//div[text()='Securely Enter Card Info']";
	public String ccRemoveAccountButton = "//div[text()='Remove Account']";
	public String ccCopyAccountButton = "//div[text()='Copy Account']";
	public String ccSaveCardButton = "//button[@id='submit-payment']";
	public String cc_X_ButtonInAddCreditCardDialog = "//form[@id='lightbox']//div[@class='x-container']";
	//***Input Fields***

	//***Drop downs***
	public String ccMonthDropdown = "//select[@name='expMonth']";
	public String ccYearDropdown = "//select[@name='expYear']";
	
	//**********Bank Account objects**********
	//***Buttons***
	public String saveBankAccountButton = "//button[@id='ach_saving_button']";
	public String bankAccountSaveAddressAndNameButton = "//div[text()='Save Address / Name']";
	public String bankAccountUpdateAccountNumberButton = "//div[text()='Update Account Numbers']";
	//***Input Fields***
	public String bankAccountBankNameInputField = "//input[@name='bankName']";
	public String bankAccountRoutingNumberInputField = "//input[@name='routingNumber']";
	public String bankAccountAccountNumberInputField = "//input[@name='accountNumber']";
	//***Drop downs***
	public String bankAcountAccountTypeDropdown = "//select[@name='accountType']";
	public String bankAccountCheckTypeDropdown = "//select[@name='checkType']";

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
	public String pestRoutesIframeCc = "payFields-iframe-number";
	public String pestRoutesIframeExp = "payFields-iframe-expiration";
	public String pestRoutesIframeCvv = "payFields-iframe-cvv";
	public String pestRoutesCcNumberInputField = "//input[@id='payment_number']";
	public String pestRoutesCcExpirationInputField = "//input[@id='expiration']";
	public String pestRoutesCcCvvInputField = "//input[@id='payment_cvv']";
	public String savePaymentMethodButton = "//button[@id='secureCardSaveButton']";
	//Element
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
		FindElement.elementByAttribute(needInputField, InputType.XPath).sendKeys(Keys.CONTROL,"a");
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

}
