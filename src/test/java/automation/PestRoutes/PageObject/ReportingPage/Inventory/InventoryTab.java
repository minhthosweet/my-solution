package automation.PestRoutes.PageObject.ReportingPage.Inventory;

import automation.PestRoutes.Utilities.*;
import automation.PestRoutes.Utilities.Deprecated;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static automation.PestRoutes.Utilities.GetWebDriver.*;

public class InventoryTab {
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
		Deprecated.waitVisible(inInventory);
		Deprecated.clickElement(inInventory);
		Deprecated.waitVisible(
				"//select[@name = 'chemicalInventoryFilter']/option[text() = '" + inventoryType + "']");
		Deprecated.clickElement("//select[@name = 'chemicalInventoryFilter']/option[text() = '" + inventoryType + "']"
		);
	}

	public void selectVisibility(String visibilityType) {
		Deprecated.waitVisible(visibility);
		Deprecated.clickElement(visibility);
		Deprecated.waitVisible(clickUpdateInventory);
		Deprecated.clickElement("//select[@name = 'chemicalVisibilityFilter']/option[text() = '" + visibilityType + "']"
		);
	}

	public void enterProductname(String productName) {
		Deprecated.waitVisible(productsName);
		Deprecated.clickElement(productsName);
		Deprecated.locate(productsName).sendKeys(productName);
		Deprecated.waitVisible(
				"//span[@role='status']/following-sibling::div//span[text()='" + productName + "']");
		Deprecated.clickElement("//span[@role='status']/following-sibling::div//span[text()='" + productName + "']"
		);
	}

	public void updateInventory() {
		WebElement elm = Deprecated.locate(clickUpdateInventory);
		Actions actions = new Actions(driver);
		actions.moveToElement(elm).click().click().perform();
		Deprecated.waitVisible(clickUpdateInventory);
	}

	public void inventoryChange(String amount) {
		Deprecated.waitVisible(inventoryChange);
		Deprecated.clickElement(inventoryChange);
		Deprecated.locate(inventoryChange).sendKeys(amount);
	}

	public void inventoryChange_Inventory(String amount, String productName) {
		Deprecated.waitVisible(
				"//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']");
		Deprecated.clickElement("//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']"
		);
		Deprecated
				.locate("//td[text()='" + productName + "']/parent::tr//input[@class = 'inventoryAmount']"
                )
				.sendKeys(amount);
	}

	public void inventoryChangeOption(String option, String productName) {
		Deprecated.waitVisible(
				"//td[text()='" + productName + "']/parent::tr//option[text()='" + option + "']");
		Deprecated.clickElement("//td[text()='" + productName + "']/parent::tr//option[text()='" + option + "']"
		);
	}

	public String getInStockValue(String productName) {
		Deprecated.waitVisible(
				"//td[text()='" + productName + "']/parent::tr//span[@class = 'currentAmount']");
		return Deprecated.getElementTextValue(
				"//td[text()='" + productName + "']/parent::tr//span[@class = 'currentAmount']");
	}

	public String removeAlertText() {
		return Utilities.getAlertText();
	}

	public void removeAlertAccept() throws InterruptedException {
		Utilities.acceptAlert();
	}

	// Xpath to find the product in Inventory
	public String productname = "//table[@id='inventoryTable']//td[contains(text(),'BNNF')]";

	public boolean locationOfProduct(String productName) {
		selectInventory(productsWithoutInventory);
		updateInventory();
		if (Deprecated
				.countElements("//table[@id='inventoryTable']//td[contains(text(),'" + productName + "')]") == 1) {
			System.out.println("Product available in Products without Inventory");
		}

		else {
			selectInventory(productsWithInventory);
			updateInventory();
			if (Deprecated.countElements(
					"//table[@id='inventoryTable']//td[contains(text(),'" + productName + "')]") == 1) {
				System.out.println("Products available in Products with Inventory");
			}else {
			System.out.println("Error");
			}
		}
		return true;
	}

}
