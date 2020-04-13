package automation.PestRoutes.Controller.Billings;

import java.io.IOException;

import automation.PestRoutes.PageObject.Billing.BillingPage;
import automation.PestRoutes.Utilities.BaseClass;

public class Billing extends BaseClass{
	
	BillingPage billing = new BillingPage();
	
	//***Navigations***
	public void navigateToBillingInfo() {
		billing.clickElement(billing.billingInfoButton);
	}
	public void navigateToCC() {
		billing.clickElement(billing.creditCardButton);
	}
	public void navigateToBankAccount() {
		billing.clickElement(billing.bankAccountButton);
	}
	
	//********
	
	public void editBillingInfo() {
		
	}
	
	public void addCC() throws Exception {
		billing.clickElement(billing.ccSecurelyEnterCardInfoButton);
		billing.setInputField(billing.ccCardNumberInputField, getData("creditCardNumber", generalData));
		billing.setInputField(billing.ccExpirationInputField, getData("ccExpireDate", generalData));
		billing.setInputField(billing.ccCvvInputField, getData("ccCvv", generalData));
		billing.clickElement(billing.ccSaveCardButton);
	}
	
	public void addBankAccount() throws Exception {
		billing.setInputField(billing.bankAccountBankNameInputField, getData("bankAccountName", generalData));
		billing.setInputField(billing.bankAccountRoutingNumberInputField, getData("routingNumber", generalData));
		billing.setInputField(billing.bankAccountAccountNumberInputField, getData("accountNumber", generalData));
		billing.selectDropdown(billing.bankAcountAccountTypeDropdown, "Checking Account");
		billing.selectDropdown(billing.bankAccountCheckTypeDropdown, "Personal Account");
		billing.clickElement(billing.bankAccountUpdateAccountNumberButton);
	}

}
