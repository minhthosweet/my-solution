package automation.PestRoutes.Controller.Admin.Preferences;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.Admin.AdminMainPage;
import automation.PestRoutes.PageObject.Admin.Preferences.ProductTypes;
import automation.PestRoutes.PageObject.Admin.Preferences.PreferencesPage;
import automation.PestRoutes.PageObject.Admin.Preferences.ServiceTypes;
import automation.PestRoutes.Utilities.AssertException;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Utilities;

public class Product extends BaseClass {

	// WebDriver driver = GetWebDriver.getInstance();
	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	PreferencesPage preferences = new PreferencesPage();
	ProductTypes productAdmin = new ProductTypes();
	ServiceTypes serviceTypes = new ServiceTypes();
	public List list = new ArrayList<String>();

	private String productName = Utilities.generateRandomString(4).toUpperCase();
	private String productLabel = Utilities.generateRandomString(4).toUpperCase();

	@Test
	public void validateProduct() {
		createProduct();
		assertCreatedProduct();
	}

	public void createProduct() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.productsText);
		productAdmin.clickAddProductButton();
		productAdmin.enterProductName(productName);
		productAdmin.enterProductLabel(productLabel);
		serviceTypes.selectFromDropdown(serviceTypes.globalDropDown, "Specific to this office");
		serviceTypes.selectFromDropdown(serviceTypes.visibilityDropDown, "Visible");
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
		serviceTypes.clickSave();
	}

	private void assertCreatedProduct() {
		header.NavigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.productsText);
		serviceTypes.setSearch(productName);
		String expectedProductName = productName;
		String actualProductname = productAdmin.getProductName(productName);
		list.add(AssertException.result(expectedProductName, actualProductname, "Validate Product Name"));
		String expectedProductLabel = productLabel;
		String actualProductLabel = productAdmin.getProductLabel(productLabel);
		list.add(AssertException.result(expectedProductLabel, actualProductLabel, "Validate Product Label"));
	}

}
