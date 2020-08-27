package automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated;

import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.FindElement;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.Preferences.EquipmentTypes;
import automation.PestRoutes.PageObject.Admin.Preferences.PreferencesPage;
import automation.PestRoutes.PageObject.Admin.Preferences.ServiceTypes;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class EquipmentTypesValidations extends BaseClass {
	AdminMainPage main;
	PreferencesPage preferences;
	EquipmentTypes equipment;
	ServiceTypes service;
	Header header;
	List list = new ArrayList<String>();
	@Test

	@Given("^I add equipment type \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void addEquipment(String prefix, String description, String barcodeRequiredOption) throws InterruptedException {
		main = new AdminMainPage();
		preferences = new PreferencesPage();
		equipment = new EquipmentTypes();
		service = new ServiceTypes();
		header = new Header();
		
		header.NavigateTo(header.adminTab);
		main.navigateTo(main.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.equipmentTypesText);
		try {
			WebElement elm = FindElement.elementByAttribute("//div[contains(text(), '"+prefix+"')]", FindElement.InputType.XPath);
		} catch(Exception e ) {
			equipment.clickAddEquipmentButton();
			equipment.setID_Prefix(prefix);
			equipment.setDescription(description);
			equipment.selectOptionFromDropdown(equipment.barcodeRequiredDropdown, barcodeRequiredOption);
			service.clickSave();
			service.setSearch(prefix);
			String expectedPrefix = prefix;
			String actualPrefix = equipment.getIDPrefix();
			list.add(AssertException.result(expectedPrefix, actualPrefix, "Validate added equipment1"));
			System.out.println(actualPrefix);
			String expectedDescription = description;
			String actualDescription = equipment.getDescription();
			list.add(AssertException.result(expectedDescription, actualDescription, "Validate added equipment"));
		}
		
	}
	
	

}
