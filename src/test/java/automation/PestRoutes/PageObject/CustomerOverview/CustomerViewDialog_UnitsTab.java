package automation.PestRoutes.PageObject.CustomerOverview;

public class CustomerViewDialog_UnitsTab {
	
	public String addUnitButton = "//li[@id='addUnit']";
	public String addBulkUnitButton = "//li[@id='addBulkUnit']";
	public String buildingInputField = "//label[text() = 'Building']/following-sibling::div/input[@name='building']";
	public String unitInputField = "//label[text() = 'Unit']/following-sibling::div/input[@name='description']";
	public String barcodeInputField = "//label[text() = 'Barcode']/following-sibling::div/input[@name='barcode']";
	public String notesInputField = "//div[@class = 'row inputSection']//textarea[@name='notes']";
	public String saveButton = "//div[@class = 'unitItemDetail']//span[text() = 'Save']";

}
