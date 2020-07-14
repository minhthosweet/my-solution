package automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated;

import java.util.List;

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
	public List list;
	@Test
	public void addEquipment() throws InterruptedException {
		main = new AdminMainPage();
		preferences = new PreferencesPage();
		equipment = new EquipmentTypes();
		service = new ServiceTypes();
		header = new Header();
		String prefix = Utilities.generateRandomString(3);
		String description = Utilities.generateRandomString(5);
		
		header.NavigateTo(header.adminTab);
		main.navigateTo(main.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.equipmentTypesText);
		equipment.clickAddEquipmentButton();
		equipment.setID_Prefix(prefix);
		equipment.setDescription(description);
		service.clickSave();
		service.setSearch(prefix);
		String expectedPrefix = prefix;
		String actualPrefix = equipment.getIDPrefix();
		list.add(AssertException.result(expectedPrefix, actualPrefix, "Validate added equipment1"));
		System.out.println(actualPrefix);
		String expectedDescription = description;
		String actualDescription = equipment.getDescription();
		list.add(AssertException.result(expectedDescription, actualDescription, "Validate added equipment"));
		AssertException.asserFailure(list);
		
	}
	
	

}
