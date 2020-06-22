package automation.PestRoutes.Controller.Documents;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.PestRoutes.Controller.Admin.Preferences.CustomerPreferences.FormTemplates;
import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DocumentsTab.DocumentsPage;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Reporter;
import automation.PestRoutes.Utilities.Utilities;

public class DocumentsValidations extends BaseClass {
	
	DocumentsPage documents;
	Header header;
	CustomerViewDialog_Header customerCardHeader;
	CreateNewCustomer newCustomer;
	ValidateRenewal subscription;
	FormTemplates forms;
	List list = new ArrayList<String>();
	String formName = "Automation Test";
	
	public void signAgreement() throws Exception{
		String expectedSuccessEmailMessage = "The agreement has been successfully signed!";
		documents = new DocumentsPage();
		header = new Header();
		subscription = new ValidateRenewal();
		subscription.renewalFieldsValidation();
		subscription.createRenewalSubscription();
		customerCardHeader = new CustomerViewDialog_Header();
		customerCardHeader.NavigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.uploadDocumentsButton);
		documents.clickButton(documents.newAgreementButton);
		documents.clickButton(documents.subscriptionButton);
		documents.clickButton(documents.createSignedAgreementButton);
		documents.sign(documents.agreementSignBox);
		documents.clickButton(documents.signButton);
		String actualSuccessMessage = documents.getMessage(documents.message);
		result(expectedSuccessEmailMessage, actualSuccessMessage, "New emailed agreement message", "Documents tab validations");
		
		
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
		customerCardHeader.NavigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.newFormButton);
		documents.selectForm(formName);
		documents.clickButton(documents.actionsButton);
		documents.clickButton(documents.signButton);
		//Utilities.clickElementInIframe(documents.employeeSignatureButton);
		WebElement iframe = FindElement.elementByAttribute(documents.iframe, InputType.XPath);
		Utilities.switchToIframeByXpath(iframe);
		documents.clickButton(documents.customerSignatureButton);
		documents.sign(documents.formSignatureBox);
		documents.clickButton(documents.formSignatureBoxSignButton);
		Utilities.switchBackToDom();
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

}
