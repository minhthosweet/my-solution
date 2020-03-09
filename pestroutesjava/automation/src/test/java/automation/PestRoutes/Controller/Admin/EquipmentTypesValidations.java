package automation.PestRoutes.Controller.Admin;

import java.util.List;

import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.EquipmentTypes;
import automation.PestRoutes.PageObject.Admin.PreferencesPage;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class EquipmentTypesValidations extends BaseClass {
	AdminMainPage main;
	PreferencesPage preferences;
	EquipmentTypes equipment;
	Header header;
	public List list;
	@Test
	public void addEquipment() {
		main = new AdminMainPage();
		preferences = new PreferencesPage();
		equipment = new EquipmentTypes();
		header = new Header();
		String prefix = Utilities.generateRandomString(3);
		String description = Utilities.generateRandomString(5);
		
		header.NavigateTo(header.adminTab);
		main.navigateTo(main.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.equipmentTypesText);
		equipment.clickAddEquipmentButton();
		equipment.setID_Prefix(prefix);
		equipment.setDescription(description);
		preferences.clickSave();
		preferences.setSearch(prefix);
		String expectedPrefix = prefix;
		String actualPrefix = equipment.getIDPrefix();
		super.list.add(AssertException.result(expectedPrefix, actualPrefix, "Validate added equipment1"));
		System.out.println(actualPrefix);
		String expectedDescription = description;
		String actualDescription = equipment.getDescription();
		super.list.add(AssertException.result(expectedDescription, actualDescription, "Validate added equipment"));
		AssertException.asserFailure(list);
		
	}
	
	

}
