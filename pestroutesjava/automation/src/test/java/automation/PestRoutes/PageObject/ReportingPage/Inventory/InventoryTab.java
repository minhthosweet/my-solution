package automation.PestRoutes.PageObject.ReportingPage.Inventory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class InventoryTab {
	WebDriver driver = GetWebDriver.getInstance();
	public String inInventory = "//div[@id='inventoryBody']//select[@name = 'chemicalInventoryFilter']";
	public String visibility = "//div[@id='inventoryBody']//select[@name = 'chemicalVisibilityFilter']";
	public String productsName = "//label[text()= 'Products: ']/following-sibling::div//input";
	// Update XPath for clickAddproduct
	public String clickUpdateInventory = "//div[text() = 'Update Inventory']";

	// In Inventory
	public String productsWithInventory = "Products With Inventory";
	public String productsWithoutInventory = "Products Without Inventory";
	public String allProductsInventory = "All Products";

	// Visibility
	public String visibleProducts = "Visible Products";
	public String hiddenProducts = "Hidden Products";
	public String allProductsVisibility = "All Products";

	// Inventory Change
	public String inventoryChange = "//table[@id='inventoryTable']//input[@class='inventoryAmount']";
	public String updateType = "//table[@id='inventoryTable']//select[@class='updateType']";

	// Inventory Change Options
	public String inventoryChangeAdd = "Add";
	public String inventoryChangeRemove = "Remove";
	public String inventoryChangeUpdate = "Update";

	public void selectInventory(String inventoryType) {
		Utilities.waitUntileElementIsVisible(inInventory);
		Utilities.clickElement(inInventory, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(
				"//select[@name = 'chemicalInventoryFilter']/option[text() = '" + inventoryType + "']");
		Utilities.clickElement("//select[@name = 'chemicalInventoryFilter']/option[text() = '" + inventoryType + "']",
				ElementType.XPath);
	}

	public void selectVisibility(String visibilityType) {
		Utilities.waitUntileElementIsVisible(visibility);
		Utilities.clickElement(visibility, ElementType.XPath);
		// Utilities.scrollToElement("//select[@name =
		// 'chemicalVisibilityFilter']/option[text() = '" + visibilityType + "']");
		Utilities.waitUntileElementIsVisible(clickUpdateInventory);
		Utilities.clickElement("//select[@name = 'chemicalVisibilityFilter']/option[text() = '" + visibilityType + "']",
				ElementType.XPath);
	}

	public void enterProductname(String productName) {
		Utilities.waitUntileElementIsVisible(productsName);
		Utilities.clickElement(productsName, ElementType.XPath);
		FindElement.elementByAttribute(productsName, InputType.XPath).sendKeys(productName);
		Utilities.waitUntileElementIsVisible(
				"//span[@role='status']/following-sibling::div//span[text()='" + productName + "']");
		Utilities.clickElement("//span[@role='status']/following-sibling::div//span[text()='" + productName + "']",
				ElementType.XPath);
	}

	public void updateInventory() {
		WebElement elm = FindElement.elementByAttribute(clickUpdateInventory, InputType.XPath);
		Actions actions = new Actions(driver);
		actions.moveToElement(elm).click().click().perform();
		Utilities.waitUntileElementIsVisible(clickUpdateInventory);
	}

	public void inventoryChange(String amount) {
		Utilities.waitUntileElementIsVisible(inventoryChange);
		Utilities.clickElement(inventoryChange, ElementType.XPath);
		FindElement.elementByAttribute(inventoryChange, InputType.XPath).sendKeys(amount);
	}

	public void inventoryChange_Inventory(String amount, String productName) {
		Utilities.waitUntileElementIsVisible(
				"//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']");
		Utilities.clickElement("//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']",
				ElementType.XPath);
		FindElement
				.elementByAttribute("//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']",
						InputType.XPath)
				.sendKeys(amount);
	}

	public void inventoryChangeOption(String option, String productName) {
		Utilities.waitUntileElementIsVisible(
				"//td[text()='" + productName + "']/parent::tr//option[text()='" + option + "']");
		Utilities.clickElement("//td[text()='" + productName + "']/parent::tr//option[text()='" + option + "']",
				ElementType.XPath);
	}

	public String getInStockValue(String productName) {
		Utilities.waitUntileElementIsVisible(
				"//td[text()='" + productName + "']/parent::tr//span[@class = 'currentAmount']");
		return Utilities.getElementTextValue(
				"//td[text()='" + productName + "']/parent::tr//span[@class = 'currentAmount']", ElementType.XPath);
	}

	public String removeAlertText() {
		return Utilities.getAlertText();
	}

	public void removeAlertAccept() {
		Utilities.acceptAlert();
	}

	// Xpath to find the product in Inventory
	public String productname = "//table[@id='inventoryTable']//td[contains(text(),'BNNF')]";

	public boolean locationOfProduct(String productName) {
		selectInventory(productsWithoutInventory);
		updateInventory();
		if (Utilities
				.getElementCount("//table[@id='inventoryTable']//td[contains(text(),'" + productName + "')]") == 1) {
			System.out.println("Product available in Products without Inventory");
		}

		else {
			selectInventory(productsWithInventory);
			updateInventory();
			if (Utilities.getElementCount(
					"//table[@id='inventoryTable']//td[contains(text(),'" + productName + "')]") == 1) {
				System.out.println("Products available in Products with Inventory");
			}else {
			System.out.println("Error");
			}
		}
		return true;
	}

}
