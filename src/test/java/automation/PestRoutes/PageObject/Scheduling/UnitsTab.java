package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.Deprecated;

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
		Deprecated.clickElement(unitType);
		Deprecated.clickElement("//option[text()='" + needUnit + "']");
	}

	public void newUnitClick() {
		Deprecated.waitVisible(newUnitClick);
		Deprecated.clickElement(newUnitClick);
	}

	public void setupUnit(String building, String unit, String barcode) {
		try {
			if (Deprecated.countElements(availableUnit) == 0) {
				Deprecated.waitVisible(buildingDetails);
				Deprecated.locate(buildingDetails).sendKeys(building);
				Deprecated.waitVisible(unitDetails);
				Deprecated.locate(unitDetails).sendKeys(unit);
				Deprecated.waitVisible(barcodeDetails);
				Deprecated.locate(barcodeDetails).sendKeys(barcode);
				Deprecated.waitVisible(saveTopRight);
				Deprecated.clickElement(saveTopRight);
			}
		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}

	}

	public void clickUnitsScheduleApt() {
		Deprecated.clickElement(unitsSchedule);
	}

	public void AddUnitsSchApt() {
		Deprecated.waitVisible(unitsScheduleApt);
		Deprecated.clickElement(unitsScheduleApt);
		Deprecated.waitVisible(addUnitsSchApt);
		Deprecated.clickElement(addUnitsSchApt);
	}

	public void clickDetails() {

		if (Deprecated.countElements(clickXSchedulingButton) > 0) {

			Deprecated.clickElement(clickXSchedulingButton);
			Deprecated.clickElement(customMask);
		}
		Deprecated.waitVisible(details);
		Deprecated.clickElement(details);
	}

}
