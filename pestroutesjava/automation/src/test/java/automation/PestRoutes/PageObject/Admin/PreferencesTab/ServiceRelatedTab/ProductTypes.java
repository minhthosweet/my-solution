package automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class ProductTypes {
	// add Product
	public String addProduct = "//div[text() = '+ Product']";

	// Description of Product
	private String productName = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder= 'product name']";
	private String productLabel = "//div[@id='preferenceHeader']/following-sibling::form//input[@placeholder= 'product label']";

	// Unit Types
	public String concentratedUnit = "//select[@placeholder='concentrated unit']";
	public String manufacturer = "//input[@placeholder='manufacturer']";
	public String inventoryUnit = "//select[@placeholder='inventory unit']";
	public String defaultDilution = "//input[@placeholder='default dilution']";
	public String dilutedUnit = "//select[@placeholder='inventory unit']";
	public String isSentriconBait = "//select[@placeholder='is sentricon bait']";
	public String measurementType = "//select[@placeholder='measurement type']";
	public String mixRatioNumerator = "//input[@placeholder='Numerator']";
	public String mixRatioNumeratorUnit = "//select[@placeholder='Numerator Unit']";
	public String mixRatioDenominator = "//input[@placeholder='Denominator']";
	public String mixRatioDenominatorUnit = "//select[@placeholder='Denominator Unit']";
	public String applicationMethod = "//select[@placeholder='application method']";
	public String isChemicalType = "//select[@placeholder='type']";
	public String targetAreas = "//div[text()='Target Areas']/following-sibling::div//input";
	public String addTargetArea = "//div[text()='Target Areas']/following-sibling::div//i[2]";
	public String targetIssue = "//div[text()='Target Issues']/following-sibling::div//input";
	public String addTargetIssue = "//div[text()='Target Issues']/following-sibling::div//i[2]";

	// Meausrement Units
	public String cubicCentimete = "Cubic Centimete (Volume)";
	public String cups = "Cups (Volume)";
	public String fluidOunces = "Fluid Ounces (Volume)";
	public String gallons = "Gallons (Volume)";
	public String Liters = "Liters (Volume)";
	public String milliliter = "Milliliter (Volume)";
	public String pail = "Pail (Volume)";
	public String pints = "Pints (Volume)";
	public String quarts = "Quarts (Volume)";
	public String tablespoon = "Tablespoon (Volume)";
	public String teaspoon = "teaspoon (Volume)";
	public String grams = "Grams (Weight)";
	public String kilograms = "Kilograms (Weight)";
	public String milligrams = "Milligrams (Weight)";
	public String ounces = "Ounces (Weight)";
	public String pounds = "Pounds (Weight)";
	public String tons = "Tons (Weight)";
	public String bag = "Bag (Unit)";
	public String bottle = "Bottle (Unit)";
	public String box = "Box (Unit)";
	public String can = "Can (Unit)";
	public String cartridge = "Cartridge (Unit)";
	public String each = "Each (Unit)";
	public String jug = "Jug (Unit)";
	public String packet = "Packet (Unit)";
	public String pallet = "Pallet (Unit)";
	public String tube = "Tube (Unit)";
	public String units = "Units (Unit)";
	public String feet = "Feet (Length)";
	public String inches = "Inches (Length)";

	// Measurement Type
	public String weight = "Weight";
	public String unit = "Unit";
	public String volume = "Volume";
	public String length = "Length";

	// Sentricon Bait Options
	public String yes = "Yes";
	public String no = "No";

	// Chemical Type
	public String chemical = "Chemical";
	public String nonChemical = "Non-Chemical";

	// Placeholdernames
	public String concentratedUnit_placeHolder = "concentrated unit";
	public String dilutedUnit_placeHolder = "diluted unit";
	public String inventoryUnit_placeHolder = "inventory unit";

	// Edit Product
	public void clickEdit(String productName) {
		Utilities.waitUntileElementIsVisible(
				"//input[@value='" + productName + "']/following-sibling::div[text()='edit']");
		Utilities.clickElement("//input[@value='" + productName + "']/following-sibling::div[text()='edit']",
				ElementType.XPath);
	}

	public void clickAddProductButton() {
		Utilities.waitUntileElementIsVisible(addProduct);
		Utilities.clickElement(addProduct, ElementType.XPath);
	}

	// Product Details
	public void enterProductName(String productsName) {
		Utilities.waitUntileElementIsVisible(productName);
		FindElement.elementByAttribute(productName, InputType.XPath).sendKeys(productsName);
	}

	public void enterProductLabel(String productsLabel) {
		Utilities.waitUntileElementIsVisible(productLabel);
		FindElement.elementByAttribute(productLabel, InputType.XPath).sendKeys(productsLabel);
	}

	// Setter methods for Unit
	// Product Values
	public void setUnit(String placeHolderName, String units) {
		Utilities.clickElement(concentratedUnit, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='" + placeHolderName + "']//option[text()='" + units + "']",
				ElementType.XPath);
	}

	public void setDefaultDilution(String defaultDilutionsQuantity) {
		FindElement.elementByAttribute(defaultDilution, InputType.XPath).clear();
		FindElement.elementByAttribute(defaultDilution, InputType.XPath).sendKeys(defaultDilutionsQuantity);

	}

	public void setMeasurementType(String unit) {
		Utilities.clickElement(measurementType, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='measurement type']/option[text()='" + unit + "']",
				ElementType.XPath);
	}

	public void setManufacturer(String manufacturers) {
		FindElement.elementByAttribute(manufacturer, InputType.XPath).clear();
		FindElement.elementByAttribute(manufacturer, InputType.XPath).sendKeys(manufacturers);

	}

	public void setSentriconBait(String yesORno) {
		Utilities.clickElement(measurementType, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='is sentricon bait']/option[text()='" + yesORno + "']",
				ElementType.XPath);
	}

	public void setNumerator(String numeratorRatio, String unit) {
		FindElement.elementByAttribute(mixRatioNumerator, InputType.XPath).clear();
		FindElement.elementByAttribute(mixRatioNumerator, InputType.XPath).sendKeys(numeratorRatio);
		Utilities.clickElement(mixRatioNumeratorUnit, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='Numerator Unit']//option[text()='" + unit + "']",
				ElementType.XPath);
	}

	public void setDenominator(String denominatorRatio, String unit) {
		FindElement.elementByAttribute(mixRatioDenominator, InputType.XPath).clear();
		FindElement.elementByAttribute(mixRatioDenominator, InputType.XPath).sendKeys(denominatorRatio);
		Utilities.clickElement(mixRatioDenominatorUnit, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='Denominator Unit']//option[text()='" + unit + "']",
				ElementType.XPath);
	}

	public void setApplicationMethod(String appMethod) {
		Utilities.clickElement(applicationMethod, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='application method']/option[text()='" + appMethod + "']",
				ElementType.XPath);
	}

	public void chemicalType(String chemicalOption) {
		Utilities.clickElement(isChemicalType, ElementType.XPath);
		Utilities.clickElement("//select[@placeholder='type']/option[text()='" + chemicalOption + "']",
				ElementType.XPath);
	}

	public void setTargetArea() {
		Utilities.clickElement(targetAreas, ElementType.XPath);
		Utilities.clickElement(addTargetArea, ElementType.XPath);
	}

	public void setTargetIssues() {
		Utilities.clickElement(targetIssue, ElementType.XPath);
		Utilities.clickElement(addTargetIssue, ElementType.XPath);
	}

	public String getProductName(String setProductName) {
		Utilities.waitUntileElementIsVisible(
				"//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductName + "')]");
		return Utilities
				.getElementTextValue("//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductName + "')]",
						ElementType.XPath)
				.substring(0, 4);
	}

	public String getProductLabel(String setProductLabel) {
		Utilities.waitUntileElementIsVisible(
				"//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductLabel + "')]");
		return Utilities
				.getElementTextValue("//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductLabel + "')]",
						ElementType.XPath)
				.substring(0, 4);
	}

	public String getUnitData(String placeHolderName) {
		return Utilities.getElementTextValue(
				"//select[@placeholder='" + placeHolderName + "']//option[@selected='selected']", ElementType.XPath);
	}

	public String getNumeratorValue() {
		return Utilities.getAttributeValue(mixRatioNumerator, "value");
	}

	public String getDenominatorValue() {
		return Utilities.getAttributeValue(mixRatioDenominator, "value");
	}

}