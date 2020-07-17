package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class CustomerViewDialog_Header {
	public String overviewTabInDialog = "overviewTab";
	public String infoTabInDialog = "infoTab";
	public String subscriptionTabInDialog = "subscriptionTab";
	public String leadsTabInDialog = "leadsTab";
	public String billingTabInDialog = "billingTab";
	public String notesTabInDialog = "notesTab";
	public String documentsTabInDIalog = "documentsTab";
	public String appointmentsTabInDialog = "appointmentsTab";
	public String invoicesTabInDialog = "invoicesTab";
	public String propertiesTabInDialog = "linkedPropertiesTab";
	public String unitsTabInDialog = "unitsTab";
	public String equipmentTabInDialog = "equipmentsTab";
	public String adminTabInDialog = "adminTab";
	public String structuresTabInDialog = "structuresTab";
	public String scheduleButtonInDialog = "//span[text()='Schedule']";
	public String tranferButtonInDialog = "//span[text()='Transfer']";
	public String closeButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Close']";
	public String saveButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']";
	public String closeXButton = "//span[@id= 'ui-id-11']/parent::div/button/span";

	//Notes tab objects
	public String customerContacts_Notes = "//li[text()='Customer Contacts']";
	
	public void clickCustomerContactsInNotesTab() {
		Utilities.waitUntileElementIsVisible(customerContacts_Notes);
		Utilities.clickElement(customerContacts_Notes, ElementType.XPath);
	}
	
	public void NavigateTo(String chooseTabFromConst) {
		Utilities.waitUntileElementIsVisible("//li[@name= '" + chooseTabFromConst + "']");
		Utilities.clickElement("//li[@name = '" + chooseTabFromConst + "']", ElementType.XPath);
	}

	public void ClickScheduleButton() throws Exception {
		Thread.sleep(2000);
		Utilities.clickElement(scheduleButtonInDialog, ElementType.XPath);
		Thread.sleep(2000);
	}

	public void ClickTranferButton() {
		Utilities.clickElement(tranferButtonInDialog, ElementType.XPath);
	}

	public void ClickSaveButton() {
		Utilities.clickElement(saveButton, ElementType.XPath);
	}

	public void ClickCloseButton() {
		Utilities.clickElement(closeButton, ElementType.XPath);
	}

	public void Click_X_Button() {
		Utilities.clickElement(closeXButton, ElementType.XPath);
	}

}
