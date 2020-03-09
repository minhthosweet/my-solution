package automation.PestRoutes.Controller.Reporting_and_Inventory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.AddRemove_Chemical_Inventory.CreatingProduct_Admin;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.PreferencesPage;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;

public class Inventory_ChemicalValidation extends BaseClass {
	
	WebDriver driver = GetWebDriver.getInstance();
	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	PreferencesPage preferences = new PreferencesPage();
	CreatingProduct_Admin productAdmin = new CreatingProduct_Admin();

	private String productName = Utilities.generateRandomString(4);
	private String productLabel = Utilities.generateRandomString(4);

	@Test
	public void validateInventory() {
		createProduct();
	}

	public void createProduct() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		preferences.navigateToProductsPage();
		productAdmin.clickAddProductButton();
		productAdmin.enterProductName(productName);
		productAdmin.enterProductLabel(productLabel);
		preferences.selectFromDropdown(preferences.globalDropDown, "Specific to this office");
		preferences.selectFromDropdown(preferences.visibilityDropDown, "Visible");
		productAdmin.setConcentratedUnit(productAdmin.fluidOunces);
		productAdmin.setDefaultDilution("10%");
		productAdmin.setDilutedUnit(productAdmin.fluidOunces);
		productAdmin.setMeasurementType(productAdmin.volume);
		productAdmin.setManufacturer(Utilities.generateRandomString(4));
		productAdmin.setSentriconBait(productAdmin.no);
		productAdmin.setInventoryUnit(productAdmin.fluidOunces);
		productAdmin.setNumerator("75", productAdmin.fluidOunces);
		productAdmin.setDenominator("25", productAdmin.fluidOunces);
		productAdmin.setApplicationMethod("Aerosol");
		productAdmin.chemicalType(productAdmin.chemical);
		productAdmin.setTargetArea();
		productAdmin.setTargetIssues();
	}
}
