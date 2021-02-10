package automation.PestRoutes.Controller.Admin.Preferences.ServiceRelated;

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

	Header header = new Header();
	AdminMainPage adminMainPage = new AdminMainPage();
	PreferencesPage preferences = new PreferencesPage();
	ProductTypes productAdmin = new ProductTypes();
	ServiceTypes serviceTypes = new ServiceTypes();
	public List list = new ArrayList<String>();

	private String productName = Utilities.generateRandomString(4).toUpperCase();
	private String productLabel = Utilities.generateRandomString(4).toUpperCase();

	@Test
	public void validateProduct() throws InterruptedException {
		createProduct();
		assertCreatedProduct();
		editProductUnit();
		productUnit_edit_assert();
	}

	//Create product
	public void createProduct() throws InterruptedException {
		header.navigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.productsText);
		productAdmin.clickAddProductButton();
		productAdmin.enterProductName(productName);
		productAdmin.enterProductLabel(productLabel);
		serviceTypes.selectFromDropdown(serviceTypes.globalDropDown, "Specific to this office");
		serviceTypes.selectFromDropdown(serviceTypes.visibilityDropDown, "Visible");
		productAdmin.setUnit(productAdmin.concentratedUnit_placeHolder, productAdmin.fluidOunces);
		productAdmin.setDefaultDilution("10%");
		productAdmin.setUnit(productAdmin.dilutedUnit_placeHolder, productAdmin.fluidOunces);
		productAdmin.setMeasurementType(productAdmin.volume);
		productAdmin.setManufacturer(Utilities.generateRandomString(4));
		productAdmin.setSentriconBait(productAdmin.no);
		productAdmin.setUnit(productAdmin.inventoryUnit_placeHolder, productAdmin.fluidOunces);
		productAdmin.setNumerator("75", productAdmin.fluidOunces);
		productAdmin.setDenominator("25", productAdmin.fluidOunces);
		productAdmin.setApplicationMethod("Aerosol");
		productAdmin.chemicalType(productAdmin.chemical);
		productAdmin.setTargetArea();
		productAdmin.setTargetIssues();
		serviceTypes.clickSave();
	}

	//Assert the product created
	private void assertCreatedProduct() throws InterruptedException {
		header.navigateTo(header.adminTab);
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

	//Edit Product
	private void editProductUnit() throws InterruptedException {
		header.navigateTo(header.adminTab);
		adminMainPage.navigateTo(adminMainPage.preferences);
		preferences.navigateTo(preferences.serviceRelatedNav, preferences.productsText);
		serviceTypes.setSearch(productName);
		productAdmin.clickEdit(productName);
		productAdmin.setUnit(productAdmin.concentratedUnit_placeHolder, productAdmin.inches);
		productAdmin.setUnit(productAdmin.dilutedUnit_placeHolder, productAdmin.inches);
		productAdmin.setUnit(productAdmin.inventoryUnit_placeHolder, productAdmin.inches);
		productAdmin.setNumerator("50", productAdmin.inches);
		productAdmin.setDenominator("50", productAdmin.inches);
		serviceTypes.clickSave();
	}

	//Assert Another Edit
	private void productUnit_edit_assert() {
		String expectedMixRatio = "50";
		String expectedProductUnit = "Grams(Weight)";
		productAdmin.clickEdit(productName);
		productAdmin.setUnit(productAdmin.concentratedUnit_placeHolder, productAdmin.grams);
		productAdmin.setUnit(productAdmin.dilutedUnit_placeHolder, productAdmin.grams);
		productAdmin.setUnit(productAdmin.inventoryUnit_placeHolder, productAdmin.grams);
		productAdmin.setNumerator("50", productAdmin.grams);
		productAdmin.setDenominator("50", productAdmin.grams);
		serviceTypes.clickSave();
		productAdmin.clickEdit(productName);
		String actualConcentratedProductUnit = productAdmin.getUnitData(productAdmin.concentratedUnit_placeHolder);
		String actualInventoryProductUnit = productAdmin.getUnitData(productAdmin.inventoryUnit_placeHolder);
		String actualDilutedProductUnit = productAdmin.getUnitData(productAdmin.dilutedUnit_placeHolder);
		String actualNumeratorMixRatio = productAdmin.getNumeratorValue();
		String actualDenominatorMiXRatio = productAdmin.getDenominatorValue();
		list.add(AssertException.result(expectedProductUnit, actualConcentratedProductUnit, "Validate Conc Unit"));
		list.add(AssertException.result(expectedProductUnit, actualInventoryProductUnit, "Validate Inventory Unit"));
		list.add(AssertException.result(expectedProductUnit, actualDilutedProductUnit, "Validate Diluted Unit"));
		list.add(AssertException.result(expectedMixRatio, actualDenominatorMiXRatio, "Validate Denominator Mix Ratio"));
		list.add(AssertException.result(expectedMixRatio, actualNumeratorMixRatio, "Validate Numerator Mix Ratio"));
	}

}
