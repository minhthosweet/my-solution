package automation.PestRoutes.Controller.Billings;

import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import io.cucumber.java.en.When;

public class Billing extends AppData {
	
	BillingPage billing = new BillingPage();
	CustomerViewDialog_Header customerCardHeader;
	BillingPage customerCardBillingTab;
	
	//***Navigations***
	public void navigateToBillingInfo() throws InterruptedException {
		billing.clickElement(billing.billingInfoButton);
	}
	public void navigateToCC() throws InterruptedException {
		billing.clickElement(billing.creditCardButton);
	}
	public void navigateToBankAccount() throws InterruptedException {
		billing.clickElement(billing.bankAccountButton);
	}
	
	//********
	
	public void editBillingInfo() {
		
	}
	
	public void addCC() throws Exception {
		billing.clickElement(billing.ccSecurelyEnterCardInfoButton);
		billing.setInputField(billing.pestRoutesCcNumberInputField, getData("creditCardNumber", generalData));
		billing.setInputField(billing.pestRoutesCcExpirationInputField, getData("ccExpireDate", generalData));
		billing.setInputField(billing.pestRoutesCcCvvInputField, getData("ccCvv", generalData));
		billing.clickElement(billing.ccSaveCardButton);
	}
	//**Author Aarbi
	public void addPaymentCC() throws InterruptedException {
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardBillingTab = new BillingPage();
		customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
		customerCardBillingTab.clickElement(customerCardBillingTab.addPaymentMethodButton);
		String paymentMethod = billing.getTextValue(billing.addPaymentMethodTitle);
		if (paymentMethod.equals("Add Payment Method")){
			billing.clickElement(billing.addCreditCardButton);
		}
		String gatewayType = Utilities.getInnerText(billing.paymentGatewayType);
		if (gatewayType.equals("Vault: brain")){
			String[] iFrame = {billing.brainCcIframe, billing.brainExpMonthIframe, billing.brainExpYearIframe, billing.brainCvvIframe};
			String[] fields = {billing.brainCcNumberInputField, billing.brainExpMonthInputField, billing.brainExpYearInputField, billing.brainCvvInputField};
			String[] input = {"4111111111111111", "02", "28", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Utilities.switchToIframeByXpath(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				Utilities.switchBackToDom();
			}
			billing.clickElement(billing.savePaymentMethodButton);
			Utilities.waitUntileElementIsVisible(billing.nmiSavedCc);
		}else if (gatewayType.equals("Vault: PestRoutes Payments")){
			String[] iFrame = {billing.pestRoutesIframeCc, billing.pestRoutesIframeExp, billing.pestRoutesIframeCvv};
			String[] fields = {billing.pestRoutesCcNumberInputField, billing.pestRoutesCcExpirationInputField, billing.pestRoutesCcCvvInputField};
			String[] input = {"4111111111111111", "0228", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Utilities.switchToIframeByXpath(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				Utilities.switchBackToDom();
			}
			billing.clickElement(billing.savePaymentMethodButton);
			Utilities.waitUntileElementIsVisible(billing.nmiSavedCc);
		}else if (gatewayType.equals("Vault: element")){
			billing.clickElement(billing.elementEnterCcButton);
			Utilities.switchToIframeByXpath(billing.elementIframe);
			billing.setInputField(billing.elementCcNumberInputField, "4111111111111111");
			billing.selectDropdown(billing.elementExpMonthDropdown, "02");
			billing.selectDropdown(billing.elementExpYearDropdown, "2028");
			billing.clickElement(billing.elementSaveCcButton);
			Utilities.switchBackToDom();
			Utilities.waitUntileElementIsVisible(billing.nmiSavedCc);
		} else if (gatewayType.equals("Vault: spreedly")){
			String ccIframe = billing.getAttributeValue(billing.spreedlyCcNumberIframe, "id");
			String cvvIframe = billing.getAttributeValue(billing.spreedlyCvvIframe, "id");
			Utilities.switchToIframeByXpath(ccIframe);
			billing.setInputField(billing.spreedlyCcInputField, "4111111111111111");
			Utilities.switchBackToDom();
			billing.selectDropdown(billing.spreedlyExpMonthDropdown, "February");
			billing.selectDropdown(billing.spreedlyExpYearDropdown, "2028");
			Utilities.switchToIframeByXpath(cvvIframe);
			billing.setInputField(billing.spreedlyCvvInputField, "123");
			Utilities.switchBackToDom();
			billing.clickElement(billing.savePaymentMethodButton);
			Utilities.waitUntileElementIsVisible(billing.nmiSavedCc);
		} else if (gatewayType.equals("Vault: nmi")){
			String[] iFrame = {billing.nmiCcNumberIframe, billing.nmiExpIframe, billing.nmiCvvIframe};
			String[] fields = {billing.nmiCcNumberInputField, billing.nmiExpInputField, billing.nmiCvvInputField};
			String[] input = {"4111111111111111", "022028", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Utilities.switchToIframeByXpath(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				Utilities.switchBackToDom();
			}
			billing.clickElement(billing.savePaymentMethodButton);
			Utilities.waitUntileElementIsVisible(billing.nmiSavedCc);

		}
	}
	
	public void addBankAccount() throws Exception {
		billing.setInputField(billing.bankAccountBankNameInputField, getData("bankAccountName", generalData));
		billing.setInputField(billing.bankAccountRoutingNumberInputField, getData("routingNumber", generalData));
		billing.setInputField(billing.bankAccountAccountNumberInputField, getData("accountNumber", generalData));
		billing.selectDropdown(billing.bankAcountAccountTypeDropdown, "Checking Account");
		billing.selectDropdown(billing.bankAccountCheckTypeDropdown, "Personal Account");
		billing.clickElement(billing.bankAccountUpdateAccountNumberButton);
	}
	//Author:Aarbi
	@When("I add an customer in auto pay with credit card")
	public void addCustomerOnAutoPayCC() throws InterruptedException {
		customerCardBillingTab = new BillingPage();
		customerCardBillingTab.clickElement(customerCardBillingTab.billingInfoButton);
		Utilities.selectValueFromDropDownByIndex(customerCardBillingTab.autoPayDropdown, 1);
		customerCardBillingTab.clickElement(customerCardBillingTab.saveBillingInfoButton);
	}
	//Author Aarbi
	@When("I add an customer in auto pay with credit card and max limit")
	public void addCustomerOnAutoPayCCWithMaxLimit(String needMaxLimit) throws InterruptedException {
		customerCardBillingTab = new BillingPage();
		customerCardBillingTab.clickElement(customerCardBillingTab.billingInfoButton);
		Utilities.selectValueFromDropDownByIndex(customerCardBillingTab.autoPayDropdown, 1);
		customerCardBillingTab.setInputField(customerCardBillingTab.maxMonthlyChargeInputField,"400");
		customerCardBillingTab.clickElement(customerCardBillingTab.saveBillingInfoButton);
	}

}
