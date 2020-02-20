package automation.PestRoutes.PageObject.RoutePage;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class RoutePage {
	public String addRoutesButton = "//li[@id= 'addRoutesButton']";
	public String jobPoolButton = "//p[text()= 'Job Pool']";
	public String remindersButton = "//p[text()= 'Reminders']";
	public String mapButton = "//p[text()= 'Map']";
	public String printButton = "//p[text()= 'Print']";
	public String viewButton = "//p[text()= 'Views']";
	
	public void clickButton(String chooseButton) {
		Utilities.clickElement(chooseButton, ElementType.XPath);
	}
	
	public void addRoutesByQuantity(String insertQuantity) {
		Utilities.waitUntileElementIsVisible("//p[text()= 'Add "+insertQuantity+" Route']");
		Utilities.clickElement("//p[text()= 'Add "+insertQuantity+" Route']", ElementType.XPath);
	}
	
//	public void ScheduleAppointment(String needRouteID, String needTime) {
//		Utilities.clickElement("//div[@routeid="+needRouteID+"]//div[text() = "+needTime+"]/following-sibling::div", ElementType.XPath);
//	}
	
	public void scheduleAppointment(String needRouteSlotNumber, String needTime) {
		Utilities.scrollToElement("//div[@class='routes']/div["+needRouteSlotNumber+"]//div[text()='06:30']/following-sibling::div");
		Utilities.waitUntileElementIsVisible("//div[@class='routes']/div["+needRouteSlotNumber+"]"
				+ "//div[text()='"+needTime+"']/following-sibling::div");
		
		Utilities.clickElement("//div[@class='routes']/div["+needRouteSlotNumber+"]"
				+ "//div[text()='"+needTime+"']/following-sibling::div", ElementType.XPath);
	}
	
	public void clickCreatedAppt(String needRouteSlotNumber, String needCustomerID) throws Exception {
		Utilities.waitUntileElementIsVisible("//div[@class='routes']/div["+needRouteSlotNumber+"]//div[@customerid = '"+needCustomerID+"']");
		Utilities.clickElement("//div[@class='routes']/div["+needRouteSlotNumber+"]//div[@customerid = '"+needCustomerID+"']", ElementType.XPath);
		Thread.sleep(5000);
	}
	
	public void clickCustomerCard(String needCustomerID) {
		Utilities.waitUntileElementIsVisible("//div[@customerid = '"+needCustomerID+"']//p[text() = 'Customer Card']");
		Utilities.clickElement("//div[@customerid = '"+needCustomerID+"']//p[text() = 'Customer Card']", ElementType.XPath);
	}

}
