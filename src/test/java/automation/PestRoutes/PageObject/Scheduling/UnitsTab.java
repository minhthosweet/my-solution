package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.FindElement;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.FindElement.InputType;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class UnitsTab {

	public String newUnitClick = "//li[text()='+ New Unit']";
	public String buildingDetails = "//*[@id=\"customerCardUnitWrapper\"]/div[2]/form/div[2]/div[1]/div[1]/div/input";
	public String unitDetails = "//div[@id=\"customerCardUnitWrapper\"]//input[@name=\"description\"]";
	public String barcodeDetails = "//div[@id=\"customerCardUnitWrapper\"]//input[@name=\"barcode\"]";
	public String availableUnit = "//*[@id=\"unitsPanel\"]//div[@class=\"historyType aLeft\"]";
	public String unitType = "//select[@id=\"isMultiUnit\"]";
	public String saveTopRight = "//div[@id=\"customerCardUnitWrapper\"]//span[@class =\"saveItem grayButton right\"]";
	public String unitsSchedule = "//ul[@id='appointmentTabs']//a[text()='Units']";
	public String unitsScheduleApt = "//div[@id=\"s2id_appointmentUnits\"]/ul";
	public String addUnitsSchApt = "//div[@id=\"s2id_appointmentUnits\"]/i[2]";

	// Unit Details
	private String details = "//div[@id=\"apptUnitsPanel\"]//span[text()='details']";
	private String clickXSchedulingButton = "//div[@id=\"schedulingNotice\"]//span[text()='X']";
	private String customMask = "//div[@id=\"select2-custom-mask\"]";

	public void selectUnit(String needUnit) {
		Utilities.clickElement(unitType, ElementType.XPath);
		Utilities.clickElement("//option[text()='" + needUnit + "']", ElementType.XPath);
	}

	public void newUnitClick() {
		Utilities.waitUntileElementIsVisible(newUnitClick);
		Utilities.clickElement(newUnitClick, ElementType.XPath);
	}

	public void setupUnit(String building, String unit, String barcode) {
		try {
			if (Utilities.getElementCount(availableUnit) == 0) {
				Utilities.waitUntileElementIsVisible(buildingDetails);
				FindElement.elementByAttribute(buildingDetails, InputType.XPath).sendKeys(building);
				Utilities.waitUntileElementIsVisible(unitDetails);
				FindElement.elementByAttribute(unitDetails, InputType.XPath).sendKeys(unit);
				Utilities.waitUntileElementIsVisible(barcodeDetails);
				FindElement.elementByAttribute(barcodeDetails, InputType.XPath).sendKeys(barcode);
				Utilities.waitUntileElementIsVisible(saveTopRight);
				Utilities.clickElement(saveTopRight, ElementType.XPath);
			}
		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}

	}

	public void clickUnitsScheduleApt() {
		Utilities.clickElement(unitsSchedule, ElementType.XPath);
	}

	public void AddUnitsSchApt() {
		Utilities.waitUntileElementIsVisible(unitsScheduleApt);
		Utilities.clickElement(unitsScheduleApt, ElementType.XPath);
		Utilities.waitUntileElementIsVisible(addUnitsSchApt);
		Utilities.clickElement(addUnitsSchApt, ElementType.XPath);
	}

	public void clickDetails() {

		if (Utilities.getElementCount(clickXSchedulingButton) > 0) {

			Utilities.clickElement(clickXSchedulingButton, ElementType.XPath);
			Utilities.clickElement(customMask, ElementType.XPath);
		}
		Utilities.waitUntileElementIsVisible(details);
		Utilities.clickElement(details, ElementType.XPath);
	}

}
