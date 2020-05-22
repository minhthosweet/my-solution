package automation.PestRoutes.Controller.Documents;

import org.testng.annotations.Test;

import automation.PestRoutes.Controller.CustomerCreation.CreateNewCustomer;
import automation.PestRoutes.Controller.Renewal.ValidateRenewal;
import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.DocumentsTab.DocumentsPage;
import automation.PestRoutes.Utilities.BaseClass;

public class DocumentsValidations extends BaseClass {
	
	DocumentsPage documents;
	Header header;
	CustomerViewDialog_Header customerCardHeader;
	CreateNewCustomer newCustomer;
	ValidateRenewal subscription;
	@Test
	public void signDocument() throws Exception{
		documents = new DocumentsPage();
		header = new Header();
		customerCardHeader = new CustomerViewDialog_Header();
		newCustomer = new CreateNewCustomer();
		subscription = new ValidateRenewal();
		subscription.renewalFieldsValidation();
		subscription.createRenewalSubscription();
		customerCardHeader.NavigateTo(customerCardHeader.documentsTabInDIalog);
		documents.clickButton(documents.newAgreementButton);
		documents.clickButton(documents.subscriptionButton);
		documents.clickButton(documents.createSignedAgreementButton);
		documents.sign(documents.agreementSignBox);
		
		
	}

}
