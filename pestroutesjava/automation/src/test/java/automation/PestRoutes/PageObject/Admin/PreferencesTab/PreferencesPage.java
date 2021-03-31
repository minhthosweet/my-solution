package automation.PestRoutes.PageObject.Admin.PreferencesTab;


import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class PreferencesPage {
	
	//Office settings navigation
	public String officeSettingsRelatedNav = "//h2[@id='office']";

	
	//Partner Sited/Apps navigation
	public String partnerSites_AppsRelatedNav = "//h2[@id='partner']";
	
	//Customer preferences navigation
	public String customerPreferencesRelatedNav = "//h2[@id='customer']";
	public String additionalContactTypes = "//li[text()='Additional Contact Types']";
	public String cancellationReasons = "//li[text()='Cancellation Reasons']";
	public String contractTemplates = "//li[text()='Contract Templates']";
	public String customerCommunication = "//li[text()='Customer Communication']";
	public String customerSources = "//li[text()='Customer Sources']";
	public String divisions = "//li[text()='Divisions']";
	public String emailCategories = "//li[text()='Email Categories']";
	public String emailTemplates = "//li[text()='Email Templates']";
	public String formTemplate = "//li[text()='Form Templates']";
	public String genericFlags = "//li[text()='Generic Flags']";
	public String leadStages = "//li[text()='Lead Stages']";
	public String leadLostReasons = "//li[text()='Lead Lost Reasons']";
	public String noteCategories = "//li[text()='Note Categories']";
	public String noteTypes = "//li[text()='Note Types']";
	public String renewalNotices = "//li[text()='Renewal Notices']";
	public String routeRegions = "//li[text()='Route Regions']";
	public String structureTemplates = "//li[text()='Structure Templates']";
	public String subPropertyTypes = "//li[text()='Sub-Property Types']";
	public String voiceMessages = "//li[text()='Voice Messages']";
	public String wdoFindings = "//li[text()='WDO Findings']";
	public String wdoRecommendations = "//li[text()='WDO Recommendations']";

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
