package automation.PestRoutes.PageObject.CustomerOverview;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;


public class CustomerViewDialog_Header {
	public String overviewTabInDialog = "Overview";
	public String infoTabInDialog = "Info";
	public String subscriptionTabInDialog = "Subscription";
	public String leadsTabInDialog = "Leads";
	public String billingTabInDialog = "Billing";
	public String notesTabInDialog = "Notes";
	public String documentsTabInDIalog = "Documents";
	public String appointmentsTabInDialog = "Appointments";
	public String invoicesTabInDialog = "Invoices";
	public String propertiesTabInDialog = "Properties";
	public String unitsTabInDialog = "Units";
	public String equipmentTabInDialog = "Equipment";
	public String adminTabInDialog = "Admin";
	public String structuresTabInDialog = "Structures";
	public String scheduleButtonInDialog = "//span[text()='Schedule']";
	public String tranferButtonInDialog = "//span[text()='Transfer']";
	public String closeButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Close']";
	public String saveButton = "//div[@id= 'customerWindow']/following-sibling::div//span[text()='Save']";
	public String closeXButton = "//span[@id= 'ui-id-11']/parent::div/button/span";
	
	public void NavigateTo(String chooseTabFromConst) {
		Utilities.waitUntileElementIsVisible("//a[text() = '"+chooseTabFromConst+"']");
		Utilities.clickElement("//a[text() = '"+chooseTabFromConst+"']", ElementType.XPath);
	}
	
	public void ClickScheduleButton() throws Exception {
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
