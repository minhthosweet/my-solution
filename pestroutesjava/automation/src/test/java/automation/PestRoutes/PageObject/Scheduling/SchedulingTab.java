package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.AppData;
import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SchedulingTab extends AppData {

	public String scheduleDay = "//div[@style='border:1px solid;']/following-sibling::div[1]";
	public String scheduleSameDay = "//div[@style='border:1px solid;']";

	public void clickScheduleDay() {
		Utilities.clickElement(scheduleDay, ElementType.XPath);
	}

	public void clickScheduleSameDay() {
		Utilities.clickElement(scheduleSameDay, ElementType.XPath);
	}

	public void addScheduleDateToProperties() throws Exception {
		String scheduleDate = Utilities.getAttributeValue(scheduleDay, "date");
		addData("scheduleDate", scheduleDate, generalData);
	}

}
