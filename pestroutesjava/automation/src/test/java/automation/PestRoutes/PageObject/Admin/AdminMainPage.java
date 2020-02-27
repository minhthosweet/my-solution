package automation.PestRoutes.PageObject.Admin;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class AdminMainPage {
	
	public String businessContacts = "Business Contacts";
	public String users = "Users";
	public String teams_Departments = "Teams / Departments";
	public String routeTemplates = "Route Templates";
	public String preferences = "Preferences";
	public String billing = "Billing";
	
	
	public void navigateTo(String needTab) {
		Utilities.clickElement("//p[text() = '"+needTab+"']", ElementType.XPath);
	}

}
