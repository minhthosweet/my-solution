package automation.PestRoutes.PageObject.Admin.Preferences;


import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class PreferencesPage {
	
	//Office settings navigation
	public String officeSettingsRelatedNav = "//h2[@id='office']";
	
	//Partner Sited/Apps navigation
	public String partnerSites_AppsRelatedNav = "//h2[@id='partner']";
	
	//Customer preferences navigation
	public String customerPreferencesRelatedNav = "//h2[@id='customer']";
	public String formTemplate = "//li[text()='Form Templates']";
	public String genericFlags = "//li[text()='Generic Flags']";
	public String customerCommunication = "//li[text()='Customer Communication']";

	//Service related navigation
	public String serviceRelatedNav = "//h2[@id='service']";
	public String addOnsText = "//li[text() = 'Add Ons']";
	public String applicationMethodsText = "//li[text() = 'Application Methods']";
	public String serviceTypesText = "//li[text() = 'Service Types']";
	public String equipmentTypesText = "//li[text() = 'Equipment Types']";
	public String productsText = "//li[text() = 'Products']";
	
	
	//Mobile navigation
	public String mobileRelatedNav = "//h2[@id='mobile']";
	
	/*
	 * Actions
	 * Below methods perform actions such as selecting from drop drown or click an object
	 */
	public void navigateTo(String needMenuArea, String needPage) throws InterruptedException {
		Utilities.clickElement("//h2[@id='office']", ElementType.XPath);
		Thread.sleep(500);
		Utilities.waitUntileElementIsVisible(needMenuArea);
		Utilities.scrollToElement(needMenuArea);
		Utilities.waitUntileElementIsVisible(needMenuArea);
		Utilities.clickElement(needMenuArea, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(needPage);
		Utilities.clickElement(needPage, ElementType.XPath);
	}

	


}
