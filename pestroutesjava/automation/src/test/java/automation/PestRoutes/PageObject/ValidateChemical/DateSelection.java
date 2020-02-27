package automation.PestRoutes.PageObject.ValidateChemical;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class DateSelection {
	Header mainHeader;
	public String currentDate = "//a[contains(@class,'ui-state-default') and contains(text(),'24')]";
	public String currentMonth = "//div[@id=\"calendarLink\"]/img";
	public String appointment = "//div[@id=\"routesView\"]/div[3]/div[2]/div[1]/div/div[1]/div[11]/div[2]/div[2]";
	public String customerCard = "//div[@id=\"appointmentOptions\"]/div[2]/p";
	public String info = "//a[@class='ui-tabs-anchor' and contains(text(),'Info')]";
	public String unitType = "//select[@id=\"isMultiUnit\"]";
	public String multiUnit = "//select[@id=\"isMultiUnit\"]/option[2]";
	public String save = "//button[@id=\"globalCustomerSaveButton\"]/span";
	public String units= "//*[@class='ui-tabs-anchor' and contains(text(),'Units')]";
	
	
	// Date Selection
	public void DateSelected() {
		Utilities.waitUntileElementIsVisible(currentMonth);
		Utilities.clickElement(currentMonth, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(currentDate);
		Utilities.clickElement(currentDate, ElementType.XPath);
		 
	}

	// Customer Selection
	public void Appointment() throws InterruptedException {
		Utilities.waitUntileElementIsVisible(appointment);
		Utilities.clickElement(appointment, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(customerCard);
		Utilities.clickElement(customerCard, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(info);
		Utilities.clickElement(info, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(unitType);
		Utilities.clickElement(unitType, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(multiUnit);
		Utilities.clickElement(multiUnit, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(save);
		Utilities.clickElement(save, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(units);
		Utilities.clickElement(units, ElementType.XPath);
	}

}
