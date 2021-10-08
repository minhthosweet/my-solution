package automation.PestRoutes.PageObject.ReportingPage;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class ReportingMainPage {
	public String office = "Office";
	public String inventory = "Inventory";
	public String lcoations = "Locations";
	public String technicians = "Technicians";
	public String officeStats = "Office Stats";
	public String formLookup = "Form Lookup";
			
	
	public void navigateTo(String needTab) {
		Utilities.clickElement("//p[text() = '"+needTab+"']", ElementType.XPath);
	}
}
