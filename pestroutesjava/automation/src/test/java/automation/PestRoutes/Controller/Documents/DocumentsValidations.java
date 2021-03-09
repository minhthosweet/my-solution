package automation.PestRoutes.Controller.Documents;

import java.io.IOException;

import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_InfoTab;
import automation.PestRoutes.Utilities.*;
import io.cucumber.java.en.And;
import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences.FormTemplates;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DocumentsTab.DocumentsPage;

public class DocumentsValidations extends AppData {
	
	DocumentsPage documents;
	Header header;
	CustomerViewDialog_Header customerCardHeader;
	CreateNewCustomer newCustomer;
	ValidateRenewal subscription;
	FormTemplates forms;
	CustomerViewDialog_InfoTab customerViewDialog_infoTab;
	String formName = "Automation Test";

	public void signAgreement() throws Exception{
		String expectedSuccessEmailMessage = "The agreement has been successfully signed!";
		documents = new DocumentsPage();
		header = new Header();
		subscription = new ValidateRenewal();
		subscription.renewalFieldsValidation();
		subscription.createRenewalSubscription();
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardHeader.navigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.uploadDocumentsButton);
		documents.clickButton(documents.newAgreementButton);
		documents.clickButton(documents.subscriptionButton);
		documents.clickButton(documents.createSignedAgreementButton);
		documents.sign(documents.agreementSignBox);
		documents.clickButton(documents.signButton);
		String actualSuccessMessage = documents.getMessage(documents.message);
		result(expectedSuccessEmailMessage, actualSuccessMessage, "New emailed agreement message", "Documents tab validations");
		
		
	}

	@And("I sign the agreement for subscription of type After Agreement Signed")
	public void signAgreement_AfterAgreementSignedSubscription() throws Exception{
		customerViewDialog_infoTab = new CustomerViewDialog_InfoTab();
		documents = new DocumentsPage();
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardHeader.navigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.newAgreementButton);
		documents.clickButton(documents.subscriptionButton);
		documents.clickButton(documents.createSignedAgreementButton);
		documents.sign(documents.agreementSignBox);
		documents.clickButton(documents.signButton);
		customerCardHeader.navigateTo(customerCardHeader.infoTabInDialog);
		String emailID = customerViewDialog_infoTab.getEmail();
		String expectedSuccessEmailMessage = "The agreement has been successfully signed! A copy has been emailed to: " + emailID;
		customerCardHeader.navigateTo(customerCardHeader.documentsTabInDIalog);
/*
		String actualSuccessMessage = documents.getMessage(documents.message);
		actualSuccessMessage is not reachable
		result(expectedSuccessEmailMessage, actualSuccessMessage, "New emailed agreement message", "Documents tab validations");
*/
		String[] fields = {documents.message, documents.newAgreementButton, documents.newFormButton, documents.uploadDocumentsButton};
		AssertException.validateFieldEnabled(fields);

	}
	@Test
	public void signEmployeeForm() throws Exception {
		String formName = Utilities.generateRandomString(6);
		forms = new FormTemplates();
		customerCardHeader = new CustomerViewDialog_Header();
		documents = new DocumentsPage();
		newCustomer = new CreateNewCustomer();
		forms.navigateToFormTemplate();
		forms.createEmployeeSignForm(formName);
		newCustomer.createCustomerWithEmail();
		customerCardHeader.navigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.newFormButton);
		documents.selectForm(formName);
		documents.clickButton(documents.actionsButton);
		documents.clickButton(documents.signButton);
		Utilities.clickElementInIframe(documents.employeeSignatureButton);
		//Utilities.clickElementInIframe(documents.employeeSignatureButton);
		//WebElement iframe = FindElement.elementByAttribute(documents.iframe, InputType.XPath);
		//Utilities.switchToIframeByXpath(iframe);
		//documents.clickButton(documents.employeeSignatureButton);
		documents.sign(documents.formSignatureBox);
		documents.clickButton(documents.formSignatureBoxSignButton);
		Utilities.switchBackToDom();
		
		
	}

	@And("I validate the if agreement is created")
	public void validateAgreement() throws InterruptedException, IOException {
		documents = new DocumentsPage();
		String expectedSuccessMessage = getData("serviceDescription", generalData)+ " Agreement";
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardHeader.navigateTo(customerCardHeader.documentsTabInDIalog);
		String actualSuccessMessage = Utilities.getElementTextValue("//ul[@id='documentList']//div[text()='"+getData("serviceDescription", generalData)+" Agreement']", Utilities.ElementType.XPath);
		result(expectedSuccessMessage, actualSuccessMessage, "New emailed agreement message", "Documents tab validations");
	}
	
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			Utilities.list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

}
