package automation.PestRoutes.PageObject.DocumentsTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class DocumentsPage {
	
	//submission message object
	public String message = "//div[@id = 'customerDocumentUploadPanel']/preceding-sibling::div[1]";
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
	public String signButton = "//div[text() = 'Sign']";
	
	//New form objects
	public String iframe = "//iframe[@id='documentFrame']";
	public String actionsButton = "//div[text()='Actions']";
	public String employeeSignatureButton = "//div[text()=' Employee ']/following-sibling::div[@for='employee']";
	public String customerSignatureButton = "//div[text()=' Customer ']/following-sibling::div[@for='customer']";
	public String formSignatureBox = "//canvas[@id='singleSignatureInput']";
	public String formSignatureBoxSignButton = "//span[text() = 'Sign']";
	

	public void clickButton(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		Utilities.clickElement(needAttribute, ElementType.XPath); 
	}
	public void selectAgreement(String needFormName) {
		Utilities.clickElement("//div[text() = '"+needFormName+"']", ElementType.XPath);
	}
	public void selectForm(String needFormName) {
		Utilities.clickElement("//div[text() = '"+needFormName+"']", ElementType.XPath);
	}
	public void sign(String needAttribute) {
		Utilities.waitUntileElementIsVisible(needAttribute);
		WebElement elem = FindElement.elementByAttribute(needAttribute, InputType.XPath);
		Utilities.sign(elem);
	}
	
	public String getMessage(String needAttribute) {
		return Utilities.getElementTextValue(needAttribute, ElementType.XPath);
	}

}
