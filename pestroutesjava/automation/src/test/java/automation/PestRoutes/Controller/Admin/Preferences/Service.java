package automation.PestRoutes.Controller.Admin.Preferences;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.Preferences.PreferencesPage;
import automation.PestRoutes.PageObject.Admin.Preferences.ServiceTypes;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

public class Service extends BaseClass{
	Header header;
	AdminMainPage admin;
	PreferencesPage preferences;
	ServiceTypes service;
	
	@Test
	
	public void workWithService() throws Exception {
		//Utilities.navigateToUrl(getData("betaUrl", generalData));
		navigateToServiceType();
		searchService(getData("serviceDescription", generalData));
		try {
			service = new ServiceTypes();
			WebElement elm = service.getDescription(getData("serviceDescription", generalData));
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
		service = new ServiceTypes();
		header.NavigateTo(header.adminTab);
		admin.navigateTo(admin.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.serviceTypesText);
	}
	public void searchService(String needServiceDescription) {
		service = new ServiceTypes();
		service.setSearch(needServiceDescription);
	}
	public void addServiceType() throws Exception {
		service = new ServiceTypes();
		service.clickAddServiceButton();
		service.setDescription(getData("serviceDescription", generalData));
		service.setCategory(getData("serviceCategory", generalData));
		service.setAbbreviation(getData("serviceAbbreviation", generalData));
		service.selectFromDropdown(service.globalDropDown, "Specific to this office");
		service.selectFromDropdown(service.visibilityDropDown, "Visible");
		service.selectFromDropdown(service.appointmentFrequencyDropDown, "OneTime");
		service.selectFromDropdown(service.defaultFollowUpDelayDropDown, "No Followup");
		service.setAppointLegnth("60");
		service.selectFromDropdown(service.defaultContractLengthDropDown, "No Contract");
		service.selectFromDropdown(service.minimumContractLengthDropDown, "No Contract");
		service.selectFromDropdown(service.displayRenewalDateDropDown, "Yes");
		service.selectFromDropdown(service.renewalFrequencyDropDown, "Annually");
		service.selectFromDropdown(service.setRenewalDateDropDown, "On Initial Service Completion");
		service.setServiceCharge("0");
		service.setServiceCommission("0");
		service.setMinInitialCharge("0.00");
		service.clickSave();
	}
	
	public void editService(String needDescription) {
		service = new ServiceTypes();
		service.clickEditButton(needDescription);
		service.selectFromDropdown(service.setRenewalDateDropDown, "On Initial Service Completion");
		service.clickSave();
	}
	

}
