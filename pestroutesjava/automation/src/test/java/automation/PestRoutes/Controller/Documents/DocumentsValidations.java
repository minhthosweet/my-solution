package automation.PestRoutes.Controller.Documents;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DocumentsTab.DocumentsPage;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class DocumentsValidations extends BaseClass {
	
	DocumentsPage documents;
	Header header;
	CustomerViewDialog_Header customerCardHeader;
	CreateNewCustomer newCustomer;
	ValidateRenewal subscription;
	List list = new ArrayList<String>();
	@Test
	public void signDocument() throws Exception{
		String expectedSuccessEmailMessage = "The agreement has been successfully signed!";
		documents = new DocumentsPage();
		header = new Header();
		customerCardHeader = new CustomerViewDialog_Header();
		newCustomer = new CreateNewCustomer();
		subscription = new ValidateRenewal();
		subscription.renewalFieldsValidation();
		subscription.createRenewalSubscription();
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
	
	@SuppressWarnings("unchecked")
	private void result(String expected, String actual, String stepName, String testName) {
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

}
