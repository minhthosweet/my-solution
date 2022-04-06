package automation.PestRoutes.Controller.Reporting;

import java.util.ArrayList;
import java.util.List;

import automation.PestRoutes.Utilities.Data.*;
import org.testng.annotations.Test;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.ReportingPage.ReportingMainPage;
import automation.PestRoutes.PageObject.ReportingPage.Inventory.InventoryTab;
import automation.PestRoutes.Utilities.Report.AssertException;
import automation.PestRoutes.Utilities.Report.Reporter;

public class Inventory {

	Header header = new Header();
	ReportingMainPage reportingMainPage = new ReportingMainPage();
	InventoryTab inventory = new InventoryTab();
	public List list = new ArrayList<String>();
	String productName = "BNNF";

	String addedStockValue = Double.toString((GetData.generateRandomInteger(3)));
	String updatedStockValue = Double
			.toString(Double.parseDouble(addedStockValue) - (GetData.generateRandomInteger(2)));
	String removedStockvalue = Double.toString(Double.parseDouble(updatedStockValue) / 2);
	String finalStockValue = Double.toString(Double.parseDouble(addedStockValue) - Double.parseDouble(updatedStockValue)
			- Double.parseDouble(removedStockvalue));

	@Test
	public void validateProduct() throws InterruptedException {
		searchProduct();
		addAssertFluid();
		insertAssertFluid();
		removeAssertFluid_Alert();
		removeEntireStock();
		assertProductAvailability();
	}

	//Search Product
	public void searchProduct() {
		header.navigateTo(header.reportingTab);
		reportingMainPage.navigateTo(reportingMainPage.inventory);
		inventory.selectInventory(inventory.allProductsInventory);
		inventory.selectVisibility(inventory.allProductsVisibility);
		inventory.enterProductname(productName);
		inventory.updateInventory();
	}

	//Assert added fluid
	public void addAssertFluid() {
		inventory.inventoryChange(addedStockValue);
		inventory.inventoryChangeOption(inventory.inventoryChangeAdd, productName);
		inventory.updateInventory();
		if(AssertException.result(addedStockValue, inventory.getInStockValue(productName), "Validate Added Stock Value").size()>0) {
		list.add(AssertException.result(addedStockValue, inventory.getInStockValue(productName),
				"Validate Added Stock Value"));
		}
	}

	public void insertAssertFluid() {
		inventory.inventoryChange_Inventory(updatedStockValue, productName);
		inventory.inventoryChangeOption(inventory.inventoryChangeUpdate, productName);
		inventory.updateInventory();
		Double updatedExpectedValue = Double.parseDouble(addedStockValue) + Double.parseDouble(updatedStockValue);
		if(AssertException.result(Double.toString(updatedExpectedValue), inventory.getInStockValue(productName), "Validate Inserted Stock Value").size()>0) {
		list.add(AssertException.result(Double.toString(updatedExpectedValue), inventory.getInStockValue(productName),
				"Validate Inserted Stock Value"));
		}
	}

	public void removeAssertFluid_Alert() throws InterruptedException {
		inventory.inventoryChange_Inventory(addedStockValue, productName);
		inventory.inventoryChangeOption(inventory.inventoryChangeRemove, productName);
		System.out.println(inventory.removeAlertText());
		inventory.removeAlertAccept();
		inventory.inventoryChange_Inventory(removedStockvalue, productName);
		inventory.inventoryChangeOption(inventory.inventoryChangeRemove, productName);
		inventory.updateInventory();
		if(AssertException.result(updatedStockValue, inventory.getInStockValue(productName), "Validate Inserted Stock Value").size()>0) {
		list.add(AssertException.result(updatedStockValue, inventory.getInStockValue(productName),
				"Validate Inserted Stock Value"));
		}
		Reporter.status("Validate Inserted Stock Value", updatedStockValue, inventory.getInStockValue(productName), "Removed Fluid");
	}

	public void removeEntireStock() {
		inventory.inventoryChangeOption(inventory.inventoryChangeRemove, productName);
		inventory.inventoryChange_Inventory(removedStockvalue, productName);
		inventory.updateInventory();
	}
	
	public void assertProductAvailability() {
		inventory.locationOfProduct(productName);
	}

}
