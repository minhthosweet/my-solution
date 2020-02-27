package automation.PestRoutes.Controller.Admin;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesPage;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class Service extends BaseClass{
	Header header;
	AdminMainPage admin;
	PreferencesPage preferences;
	
	@Test
	
	public void workWithService() throws Exception {
		//Utilities.navigateToUrl(getData("betaUrl", generalData));
		navigateToServiceType();
		searchService(getData("serviceDescription", generalData));
		try {
			preferences = new PreferencesPage();
			WebElement elm = preferences.getDescription(getData("serviceDescription", generalData));
			if(elm.isDisplayed()) {
				editService(getData("serviceDescription", generalData));
			}
			
		} catch(Exception e){
			
			
		} finally {
			addServiceType();
		}
	}
	
	public void navigateToServiceType() {
		admin = new AdminMainPage();
		header = new Header();
		preferences = new PreferencesPage();
		header.NavigateTo(header.adminTab);
		admin.navigateTo(admin.preferences);
		preferences.navigateToServiceTypePage();
	}
	public void searchService(String needServiceDescription) {
		preferences = new PreferencesPage();
		preferences.setSearch(needServiceDescription);
	}
	public void addServiceType() throws Exception {
		preferences = new PreferencesPage();
		preferences.clickAddServiceButton();
		preferences.setDescription(getData("serviceDescription", generalData));
		preferences.setCategory(getData("serviceCategory", generalData));
		preferences.setAbbreviation(getData("serviceAbbreviation", generalData));
		preferences.selectFromDropdown(preferences.globalDropDown, "Specific to this office");
		preferences.selectFromDropdown(preferences.visibilityDropDown, "Visible");
		preferences.selectFromDropdown(preferences.appointmentFrequencyDropDown, "OneTime");
		preferences.selectFromDropdown(preferences.defaultFollowUpDelayDropDown, "No Followup");
		preferences.setAppointLegnth("60");
		preferences.selectFromDropdown(preferences.defaultContractLengthDropDown, "No Contract");
		preferences.selectFromDropdown(preferences.minimumContractLengthDropDown, "No Contract");
		preferences.selectFromDropdown(preferences.displayRenewalDateDropDown, "Yes");
		preferences.selectFromDropdown(preferences.renewalFrequencyDropDown, "Annually");
		preferences.selectFromDropdown(preferences.setRenewalDateDropDown, "On Initial Service Completion");
		preferences.setServiceCharge("0");
		preferences.setServiceCommission("0");
		preferences.setMinInitialCharge("0.00");
		preferences.clickSave();
	}
	
	public void editService(String needDescription) {
		preferences = new PreferencesPage();
		preferences.clickEditButton(needDescription);
		preferences.selectFromDropdown(preferences.setRenewalDateDropDown, "On Initial Service Completion");
		preferences.clickSave();
	}
	

}
