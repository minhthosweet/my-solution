package automation.PestRoutes.PageObject.DocumentsTab;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.*;

import static automation.PestRoutes.Utilities.GetWebDriver.*;
import static automation.PestRoutes.Utilities.Utilities.*;

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
		Utilities.waitClickable(By.xpath(needAttribute), 10);
		Deprecated.clickElement(needAttribute);
	}
	public void selectAgreement(String needFormName) {
		Deprecated.clickElement("//div[text() = '"+needFormName+"']");
	}
	public void selectForm(String needFormName) {
		Deprecated.clickElement("//div[text() = '"+needFormName+"']");
	}
	public void sign(String needAttribute) {
		delay(1000);
		Deprecated.waitVisible(needAttribute);
		WebElement elem = Deprecated.locate(needAttribute);
		Actions builder = new Actions(driver);
		Action drawAction = builder
				.click(elem)
				.moveToElement(elem, 8, 8)
				.clickAndHold(elem)
				.moveByOffset(60, 70)
				.moveByOffset(-120, -120)
				.moveByOffset(70, 80)
				.moveByOffset(-80, -80)
				.release(elem)
				.build();
		drawAction.perform();
	}
	
	public String getMessage(String needAttribute) {
		return Deprecated.getElementTextValue(needAttribute);
	}

}
