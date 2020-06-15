package automation.PestRoutes.PageObject;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class Footer {

	public String history = "History";
	public String tasks = "Tasks";
	public String phone = "Phone";
	public String marketing = "Marketing";
	public String news = "News";
	public String guides = "Guides";
	public String helpWizard = "Help Wizard";
	public String clock = "Clock";
	public String mapCodeWizard = "Map Code Wizard";

	public String logout = "//div[@class='dockLogoutButton']";

	public void NavigateTo(String chooseTabFromConst) {
		Utilities.waitUntileElementIsVisible("//div[@id='dock']//p[text()='" + chooseTabFromConst + "']");
		Utilities.clickElement("//div[@id='dock']//p[text()='" + chooseTabFromConst + "']", ElementType.XPath);
	}

	public void logoutPortal() {
		Utilities.waitUntileElementIsVisible(logout);
		Utilities.clickElement(logout, ElementType.XPath);
	}

	public void mapCodeWizard(String mapCode) {
		Utilities.waitUntileElementIsVisible("//input[@notext='"+mapCodeWizard+"']");
		FindElement.elementByAttribute("//input[@notext='"+mapCodeWizard+"']", InputType.XPath).sendKeys(mapCode);
	}
}
