package automation.PestRoutes.PageObject.Admin.PreferencesTab;


import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.CustomerPreferencesTab.GenericFlagsPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.MerchantInfoTab.MarchantInfoPage;
import automation.PestRoutes.PageObject.Admin.PreferencesTab.OfficeSettingsTab.TriggerTypes.TriggerRules;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;
import org.openqa.selenium.By;

import static automation.PestRoutes.Utilities.Utilities.*;

public class PreferencesPage extends AdminMainPage {
	
	//Office Settings Navigation
	private By officeSettingsRelatedNav = By.xpath("//h2[@id='office']");
	private By merchantInfo = By.xpath("//ul[@id='preferencesMenu']//li[text()='Merchant Info']");
	private By triggerRules = By.xpath("//ul[@id='preferencesMenu']//li[text()='Trigger Rules']");

	//Partner Sited/Apps Navigation
	private By partnerSites_AppsRelatedNav = By.xpath("//h2[@id='partner']");
	
	//Customer Preferences Navigation
	public String customerPreferencesRelatedNav = "//h2[@id='customer']";
	private By customerPreferences = By.xpath("//h2[@id='customer' and text()='Customer Preferences']");
	private By additionalContactTypes = By.xpath("//li[text()='Additional Contact Types']");
	private By cancellationReasons = By.xpath("//li[text()='Cancellation Reasons']");
	private By contractTemplates = By.xpath("//li[text()='Contract Templates']");
	public String customerCommunication = "//li[text()='Customer Communication']";
	public String customerSources = "//li[text()='Customer Sources']";
	public String divisions = "//li[text()='Divisions']";
	private By emailCategories = By.xpath("//li[text()='Email Categories']");
	private By emailTemplates = By.xpath("//li[text()='Email Templates']");
	public String formTemplate = "//li[text()='Form Templates']";
	public String genericFlags = "//li[text()='Generic Flags']";
	private By genericFlagsListItem = By.xpath("//ul[@id='preferencesMenu']//li[text()='Generic Flags']");
	private By leadStages = By.xpath("//li[text()='Lead Stages']");
	private By leadLostReasons = By.xpath("//li[text()='Lead Lost Reasons']");
	private By noteCategories = By.xpath("//li[text()='Note Categories']");
	private By noteTypes = By.xpath("//li[text()='Note Types']");
	private By renewalNotices = By.xpath("//li[text()='Renewal Notices']");
	public String routeRegions = "//li[text()='Route Regions']";
	private By structureTemplates = By.xpath("//li[text()='Structure Templates']");
	private By subPropertyTypes = By.xpath("//li[text()='Sub-Property Types']");
	private By voiceMessages = By.xpath("//li[text()='Voice Messages']");
	private By wdoFindings = By.xpath("//li[text()='WDO Findings']");
	private By wdoRecommendations = By.xpath("//li[text()='WDO Recommendations']");

	//Service Related Navigation
	public String serviceRelatedNav = "//h2[@id='service']";
	private By addOnsText = By.xpath("//li[text() = 'Add Ons']");
	private By applicationMethodsText = By.xpath("//li[text() = 'Application Methods']");
	public String serviceTypesText = "//li[text() = 'Service Types']";
	public String equipmentTypesText = "//li[text() = 'Equipment Types']";
	public String productsText = "//li[text() = 'Products']";

	//Billing Preferences
	public String billingPreferencesSectionTitle = "//*[@id='billingLetterPrefs']//div[contains(text(), 'Billing Preferences')]";
	private By lnkSave_BillingPreferences = By.xpath("//*[@id='enclosure05']");
	private By lnkCancel_BillingPreferences = By.xpath("//*[@id='enclosure05']//following-sibling::div[contains(text(),'cancel')]");
	private By lnkEdit_BillingPreferences = By.xpath("//*[@id='enclosure05']//preceding-sibling::div[contains(text(),'edit')]");

	private By useConsolidatedInvoicing= By.xpath("//*[@id='billingLetterPrefs']//div/select[@name ='useConsolidatedInvoicing']");

	//Mobile navigation
	private By mobileRelatedNav = By.xpath("//h2[@id='mobile']");

	/*
	 * Actions
	 * Below methods perform actions such as selecting from drop drown or click an object
	 */
	public void navigateTo(String needMenuArea, String needPage) throws InterruptedException {
		Utilities.clickElement("//h2[@id='office']", ElementType.XPath);
		Thread.sleep(300);
		//Utilities.scrollToElement("//li[text()='WDO Findings']");
		Utilities.waitUntileElementIsVisible(needMenuArea);
		Utilities.clickElement(needMenuArea, ElementType.XPath);
		Thread.sleep(300);
		Utilities.scrollToElementJS(needPage);
		Utilities.waitUntileElementIsVisible(needPage);
		Utilities.clickElement(needPage, ElementType.XPath);
	}

	//*************** Setters **************


	//*********** Getters ******************

	//*************** General Methods and Functions ***************

	public void clickMerchantinfolink(){
		click(merchantInfo);
	}
	public MarchantInfoPage clickMerchantInfo(){
		click(merchantInfo);
		return new MarchantInfoPage();
	}

	public void clickCustomerPreferences() {
		delay(2000);
		scrollToElementJS(find(customerPreferences));
		click(customerPreferences);
	}

	public GenericFlagsPage clickGenericFlags() {
		elementIsVisible(genericFlagsListItem);
		scrollToElementJS(genericFlagsListItem);
		click(genericFlagsListItem);
		return new GenericFlagsPage();
	}

	public TriggerRules clickTriggerRules() {
		delay(2000);
		scrollToElementJS(find(triggerRules));
		click(triggerRules);
		return new TriggerRules();
	}

	public void clickEdit_BillingPreferences(){
		scrollToElementJS(find(lnkEdit_BillingPreferences));
		click(lnkEdit_BillingPreferences);
	}//clickEdit_BillingPreferences()

	public void clickCancel_BillingPreferences(){
		click(lnkCancel_BillingPreferences);
	}//clickCancel_BillingPreferences()

	public void clickSave_BillingPreferences(){
		click(lnkSave_BillingPreferences);
	}//clickSave_BillingPreferences()

	public void selectUseConsolidatedInvoicing(String option){
		selectFromDropDown(option, useConsolidatedInvoicing);
	}//clickSave_BillingPreferences()
}
