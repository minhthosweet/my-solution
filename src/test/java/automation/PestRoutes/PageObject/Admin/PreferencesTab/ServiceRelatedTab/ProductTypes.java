package automation.PestRoutes.PageObject.Admin.PreferencesTab.ServiceRelatedTab;

import automation.PestRoutes.Utilities.Deprecated;

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
		Deprecated.waitVisible(
				"//input[@value='" + productName + "']/following-sibling::div[text()='edit']");
		Deprecated.clickElement("//input[@value='" + productName + "']/following-sibling::div[text()='edit']"
		);
	}

	public void clickAddProductButton() {
		Deprecated.waitVisible(addProduct);
		Deprecated.clickElement(addProduct);
	}

	// Product Details
	public void enterProductName(String productsName) {
		Deprecated.waitVisible(productName);
		Deprecated.locate(productName).sendKeys(productsName);
	}

	public void enterProductLabel(String productsLabel) {
		Deprecated.waitVisible(productLabel);
		Deprecated.locate(productLabel).sendKeys(productsLabel);
	}

	// Setter methods for Unit
	// Product Values
	public void setUnit(String placeHolderName, String units) {
		Deprecated.clickElement(concentratedUnit);
		Deprecated.clickElement("//select[@placeholder='" + placeHolderName + "']//option[text()='" + units + "']"
		);
	}

	public void setDefaultDilution(String defaultDilutionsQuantity) {
		Deprecated.locate(defaultDilution).clear();
		Deprecated.locate(defaultDilution).sendKeys(defaultDilutionsQuantity);

	}

	public void setMeasurementType(String unit) {
		Deprecated.clickElement(measurementType);
		Deprecated.clickElement("//select[@placeholder='measurement type']/option[text()='" + unit + "']"
		);
	}

	public void setManufacturer(String manufacturers) {
		Deprecated.locate(manufacturer).clear();
		Deprecated.locate(manufacturer).sendKeys(manufacturers);

	}

	public void setSentriconBait(String yesORno) {
		Deprecated.clickElement(measurementType);
		Deprecated.clickElement("//select[@placeholder='is sentricon bait']/option[text()='" + yesORno + "']"
		);
	}

	public void setNumerator(String numeratorRatio, String unit) {
		Deprecated.locate(mixRatioNumerator).clear();
		Deprecated.locate(mixRatioNumerator).sendKeys(numeratorRatio);
		Deprecated.clickElement(mixRatioNumeratorUnit);
		Deprecated.clickElement("//select[@placeholder='Numerator Unit']//option[text()='" + unit + "']"
		);
	}

	public void setDenominator(String denominatorRatio, String unit) {
		Deprecated.locate(mixRatioDenominator).clear();
		Deprecated.locate(mixRatioDenominator).sendKeys(denominatorRatio);
		Deprecated.clickElement(mixRatioDenominatorUnit);
		Deprecated.clickElement("//select[@placeholder='Denominator Unit']//option[text()='" + unit + "']"
		);
	}

	public void setApplicationMethod(String appMethod) {
		Deprecated.clickElement(applicationMethod);
		Deprecated.clickElement("//select[@placeholder='application method']/option[text()='" + appMethod + "']"
		);
	}

	public void chemicalType(String chemicalOption) {
		Deprecated.clickElement(isChemicalType);
		Deprecated.clickElement("//select[@placeholder='type']/option[text()='" + chemicalOption + "']"
		);
	}

	public void setTargetArea() {
		Deprecated.clickElement(targetAreas);
		Deprecated.clickElement(addTargetArea);
	}

	public void setTargetIssues() {
		Deprecated.clickElement(targetIssue);
		Deprecated.clickElement(addTargetIssue);
	}

	public String getProductName(String setProductName) {
		Deprecated.waitVisible(
				"//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductName + "')]");
		return Deprecated
				.getElementTextValue("//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductName + "')]"
                )
				.substring(0, 4);
	}

	public String getProductLabel(String setProductLabel) {
		Deprecated.waitVisible(
				"//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductLabel + "')]");
		return Deprecated
				.getElementTextValue("//div[@id='newPreferenceBody']//div[contains(text(),'" + setProductLabel + "')]"
                )
				.substring(0, 4);
	}

	public String getUnitData(String placeHolderName) {
		return Deprecated.getElementTextValue(
				"//select[@placeholder='" + placeHolderName + "']//option[@selected='selected']");
	}

	public String getNumeratorValue() {
		return Deprecated.getAttribute(mixRatioNumerator, "value");
	}

	public String getDenominatorValue() {
		return Deprecated.getAttribute(mixRatioDenominator, "value");
	}

}