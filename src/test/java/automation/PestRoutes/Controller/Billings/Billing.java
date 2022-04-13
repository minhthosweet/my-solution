package automation.PestRoutes.Controller.Billings;

import automation.PestRoutes.PageObject.CustomerOverview.BillingPage;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Data.AppData;
import automation.PestRoutes.Utilities.Legacy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Random;

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
	@And("I add a CC payment option {string} and {string}")
	public void addPaymentCC(String needRegularCC, String needNMICC) throws InterruptedException {
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardBillingTab = new BillingPage();
		customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
		customerCardBillingTab.clickElement(customerCardBillingTab.addPaymentMethodButton);
		String paymentMethod = billing.getTextValue(billing.paymentMethodTitle);
		if (paymentMethod.equals("Add Payment Method")){
			billing.clickElement(billing.addCreditCardButton);
		}
		String gatewayType = Legacy.jsGetText(billing.paymentGatewayType);
		if (gatewayType.equals("Vault: brain")){
			String[] iFrame = {billing.brainCcIframe, billing.brainExpMonthIframe, billing.brainExpYearIframe, billing.brainCvvIframe};
			String[] fields = {billing.brainCcNumberInputField, billing.brainExpMonthInputField, billing.brainExpYearInputField, billing.brainCvvInputField};
			String[] input = {needRegularCC, "02", "28", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Utilities.switchToIframe(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				GetWebDriver.switchBackToDom();
			}
			billing.clickElement(billing.savePaymentMethodButton);
			Legacy.waitVisible(billing.nmiSavedCc);
		}else if (gatewayType.equals("Vault: PestRoutes Payments")){
			String[] iFrame = {billing.pestRoutesIframeCc, billing.pestRoutesIframeExp, billing.pestRoutesIframeCvv};
			String[] fields = {billing.pestRoutesCcNumberInputField, billing.pestRoutesCcExpirationInputField, billing.pestRoutesCcCvvInputField};
			String[] input = {needRegularCC, "0228", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Legacy.isPresent("//iframe[@id='payrixAddIFrame']");
				Utilities.switchToIframe(billing.payrixIframe);
				Utilities.switchToIframe(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				GetWebDriver.switchBackToDom();
			}
			Utilities.switchToIframe(billing.payrixIframe);
			billing.clickElement(billing.savePaymentMethodButton);
			GetWebDriver.switchBackToDom();
			Legacy.waitVisible(billing.nmiSavedCc);
		}else if (gatewayType.equals("Vault: element")){
			billing.clickElement(billing.elementEnterCcButton);
			Utilities.switchToIframe(billing.elementIframe);
			billing.setInputField(billing.elementCcNumberInputField, needRegularCC);
			billing.selectDropdown(billing.elementExpMonthDropdown, "02");
			billing.selectDropdown(billing.elementExpYearDropdown, "2028");
			billing.clickElement(billing.elementSaveCcButton);
			GetWebDriver.switchBackToDom();
			Legacy.waitVisible(billing.nmiSavedCc);
		} else if (gatewayType.equals("Vault: spreedly")){
			String ccIframe = billing.getAttributeValue(billing.spreedlyCcNumberIframe, "id");
			String cvvIframe = billing.getAttributeValue(billing.spreedlyCvvIframe, "id");
			Utilities.switchToIframe(ccIframe);
			billing.setInputField(billing.spreedlyCcInputField, needRegularCC);
			GetWebDriver.switchBackToDom();
			billing.selectDropdown(billing.spreedlyExpMonthDropdown, "February");
			billing.selectDropdown(billing.spreedlyExpYearDropdown, "2028");
			Utilities.switchToIframe(cvvIframe);
			billing.setInputField(billing.spreedlyCvvInputField, "123");
			GetWebDriver.switchBackToDom();
			billing.clickElement(billing.savePaymentMethodButton);
			Legacy.waitVisible(billing.nmiSavedCc);
		} else if (gatewayType.equals("Vault: nmi")){
			String[] iFrame = {billing.nmiCcNumberIframe, billing.nmiExpIframe, billing.nmiCvvIframe};
			String[] fields = {billing.nmiCcNumberInputField, billing.nmiExpInputField, billing.nmiCvvInputField};
			String[] input = {needNMICC, "022028", "123"};
			for (int i = 0; i < iFrame.length; i++){
				Utilities.switchToIframe(iFrame[i]);
				billing.setInputField(fields[i], input[i]);
				GetWebDriver.switchBackToDom();
			}
			billing.clickElement(billing.savePaymentMethodButton);
			Legacy.waitVisible(billing.nmiSavedCc);

		}
	}
	//***Author Aarbi
	@And("I add an ACH payment option")
	public void addBankAccount() throws Exception {
		String[] routingNumber = {"111000614", "051000017", "122101706"};
		String[] accountNumber = {"111222333", "222333444", "333444555"};
		Random random = new Random();
		int selectRouting = random.nextInt(routingNumber.length);
		int selectAccount = random.nextInt(accountNumber.length);
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardBillingTab = new BillingPage();
		customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
		customerCardHeader.navigateTo(customerCardHeader.billingTabInDialog);
		customerCardBillingTab.clickElement(customerCardBillingTab.addPaymentMethodButton);
		billing.clickElement(billing.addBankAccountButton);
		billing.setInputField(billing.bankAccountBankNameInputField, "JP Morgan");
		billing.setInputField(billing.bankAccountRoutingNumberInputField, routingNumber[selectRouting]);
		billing.setInputField(billing.bankAccountAccountNumberInputField, accountNumber[selectAccount]);
		billing.selectDropdown(billing.bankAcountAccountTypeDropdown, "Checking Account");
		billing.selectDropdown(billing.bankAccountCheckTypeDropdown, "Personal Account");
		billing.clickElement(billing.saveBankAccountButton);
		Legacy.waitVisible(billing.savedBankName);
	}
	//Author:Aarbi
	@When("I add an customer in auto pay with credit card")
	public void addCustomerOnAutoPay() throws InterruptedException {
		customerCardBillingTab = new BillingPage();
		customerCardBillingTab.clickElement(customerCardBillingTab.billingInfoButton);
		Legacy.selectByIndex(customerCardBillingTab.autoPayDropdown, 1);
		customerCardBillingTab.clickElement(customerCardBillingTab.saveBillingInfoButton);
	}
	//Author Aarbi
	//needAutoPayType value can be "CC" or "ACH" or last four of CC or ACH
	@When("I add an customer in auto pay with {string} and max limit {string}")
	public void addCustomerOnAutoPayCCWithMaxLimit(String needAutoPayType, String needMaxLimit) throws InterruptedException {
		customerCardBillingTab = new BillingPage();
		customerCardBillingTab.clickElement(customerCardBillingTab.billingInfoButton);
		Legacy.clickElement(customerCardBillingTab.autoPayDropdown);
		Legacy.clickElement("//div[@id='billingSwitches']//select[@name='autoPayPaymentProfileID']//option[contains(text(),'"+needAutoPayType+"')]");
        customerCardBillingTab.setInputField(customerCardBillingTab.maxMonthlyChargeInputField,needMaxLimit);
		customerCardBillingTab.clickElement(customerCardBillingTab.saveBillingInfoButton);
	}

}
