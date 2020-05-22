package automation.PestRoutes.PageObject.DocumentsTab;

import org.openqa.selenium.WebElement;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class DocumentsPage {
	
	//Main buttons
	public String uploadDocumentsButton = "//li[contains (text(), 'Upload Document')]";
	public String newAgreementButton = "//li[contains (text(), 'New Agreement')]";
	public String newFormButton = "//li[contains (text(), 'New Form')]";
	
	//Upload documents page's objects
	public String documentDescription = "//input[@placeholder='Document Description']";
	// need upload document path
	public String visibleToCustomerCheckbox = "//input[@id='uploadShowCustomer']";
	public String visibleToTechCheckbox = "//input[@id='uploadShowTech']";
	public String uploadButton = "//div[@id='customerDocumentUploadButton']";
	
	//New agreement page's objects
	public String subscriptionButton = "//h3[text()='Generate new agreement for which subscription:']/following-sibling::div[1]";
	
	//Service Agreement objects
	public String createSignedAgreementButton = "//div[text()='Create Signed Agreement']";
	public String agreementSignBox= "//canvas[@id='formSignature']";
	
	
	public void clickButton(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		Utilities.clickElement(needAttribute, ElementType.XPath);
	}
	public void sign(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		WebElement elem = FindElement.elementByAttribute(needAttribute, InputType.XPath);
		Utilities.sign(elem);
	}

}
