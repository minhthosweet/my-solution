package automation.PestRoutes.PageObject.RoutePage;


import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class RoutePage {
	public String addRoutesButton = "//li[@id= 'addRoutesButton']";
	public String jobPoolButton = "//p[text()= 'Job Pool']";
	public String remindersButton = "//p[text()= 'Reminders']";
	public String mapButton = "//p[text()= 'Map']";
	public String printButton = "//p[text()= 'Print']";
	public String viewButton = "//p[text()= 'Views']";
	public String groupName = "//div[@id=\"routesView\"]//h3[contains (text(), 'TestRoutes')]";
	public String addGroup = "//div[@id=\"addGroupButton\"]//h3[contains (text(), 'Add Group')]";
	public String groupTitle = "//form[@id=\"editGroupForm\"]//input[@name=\"title\"]";
	public String groupTemplate = "//form[@id=\"editGroupForm\"]//select[@name=\"templateID\"]";
	public String groupTemplateName = "//form[@id=\"editGroupForm\"]//option[contains (text(), 'TestRoutes')]";
	//Had to use the below XPath as there are multiple Save buttons on the UI page
	public String saveButton = "//body[@id=\"daysPage\"]/div[32]/div[3]/div/button[2]/span";
	public void clickButton(String chooseButton) {
		Utilities.clickElement(chooseButton, ElementType.XPath);
	}

	public void addRoutesByQuantity(String insertQuantity) {
		Utilities.waitUntileElementIsVisible("//p[text()= 'Add " + insertQuantity + " Route']");
		Utilities.clickElement("//p[text()= 'Add " + insertQuantity + " Route']", ElementType.XPath);
	}

//	public void ScheduleAppointment(String needRouteID, String needTime) {
//		Utilities.clickElement("//div[@routeid="+needRouteID+"]//div[text() = "+needTime+"]/following-sibling::div", ElementType.XPath);
//	}

	public void scheduleAppointment(String needRouteSlotNumber, String needTime) {
		Utilities.scrollToElement(
				"//div[@class='routes']/div[" + needRouteSlotNumber + "]"
						+ "//div[text()='" + needTime + "']/following-sibling::div");
		Utilities.waitUntileElementIsVisible("//div[@class='routes']/div[" + needRouteSlotNumber + "]"
				+ "//div[text()='" + needTime + "']/following-sibling::div");

		Utilities.clickElement("//div[@class='routes']/div[" + needRouteSlotNumber + "]" + "//div[text()='" + needTime
				+ "']/following-sibling::div", ElementType.XPath);
	}

	public void clickCreatedAppt(String needRouteSlotNumber, String needCustomerID) throws Exception {
		Utilities.waitUntileElementIsVisible(
				"//div[@class='routes']/div[" + needRouteSlotNumber + "]//div[@customerid = '" + needCustomerID + "']");
		Utilities.clickElement(
				"//div[@class='routes']/div[" + needRouteSlotNumber + "]//div[@customerid = '" + needCustomerID + "']",
				ElementType.XPath);
		Thread.sleep(5000);
	}

	public void clickCustomerCard(String needCustomerID) {
		Utilities.waitUntileElementIsVisible(
				"//div[@customerid = '" + needCustomerID + "']//p[text() = 'Customer Card']");
		Utilities.clickElement("//div[@customerid = '" + needCustomerID + "']//p[text() = 'Customer Card']",
				ElementType.XPath);
	}

	public void addGroup() {
		try {
			//WebElement testGroupname = FindElement.elementByAttribute(groupName, InputType.XPath);
			if (Utilities.getElementCount(groupName)==0) {
				Utilities.clickElement(addGroup, ElementType.XPath);
				FindElement.elementByAttribute(groupTitle, InputType.XPath).sendKeys("TestRoutes");
				Utilities.waitUntileElementIsVisible(groupTemplate);
				Utilities.clickElement(groupTemplate, ElementType.XPath);
				Utilities.waitUntileElementIsVisible(groupTemplateName);
				Utilities.clickElement(groupTemplateName, ElementType.XPath);
				Utilities.waitUntileElementIsVisible(saveButton);
				Utilities.clickElement(saveButton, ElementType.XPath);
			}
		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}

	}

}
