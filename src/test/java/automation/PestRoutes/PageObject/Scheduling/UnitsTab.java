package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.Legacy;

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
		Legacy.clickElement(unitType);
		Legacy.clickElement("//option[text()='" + needUnit + "']");
	}

	public void newUnitClick() {
		Legacy.waitVisible(newUnitClick);
		Legacy.clickElement(newUnitClick);
	}

	public void setupUnit(String building, String unit, String barcode) {
		try {
			if (Legacy.countElements(availableUnit) == 0) {
				Legacy.waitVisible(buildingDetails);
				Legacy.locate(buildingDetails).sendKeys(building);
				Legacy.waitVisible(unitDetails);
				Legacy.locate(unitDetails).sendKeys(unit);
				Legacy.waitVisible(barcodeDetails);
				Legacy.locate(barcodeDetails).sendKeys(barcode);
				Legacy.waitVisible(saveTopRight);
				Legacy.clickElement(saveTopRight);
			}
		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}

	}

	public void clickUnitsScheduleApt() {
		Legacy.clickElement(unitsSchedule);
	}

	public void AddUnitsSchApt() {
		Legacy.waitVisible(unitsScheduleApt);
		Legacy.clickElement(unitsScheduleApt);
		Legacy.waitVisible(addUnitsSchApt);
		Legacy.clickElement(addUnitsSchApt);
	}

	public void clickDetails() {

		if (Legacy.countElements(clickXSchedulingButton) > 0) {

			Legacy.clickElement(clickXSchedulingButton);
			Legacy.clickElement(customMask);
		}
		Legacy.waitVisible(details);
		Legacy.clickElement(details);
	}

}
