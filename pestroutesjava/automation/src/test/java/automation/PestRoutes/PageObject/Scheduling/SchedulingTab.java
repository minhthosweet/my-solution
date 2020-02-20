package automation.PestRoutes.PageObject.Scheduling;

import automation.PestRoutes.Utilities.Utilities;
import automation.PestRoutes.Utilities.Utilities.ElementType;

public class SchedulingTab {
	
	public String scheduleDay = "//div[@style='border:1px solid;']/following-sibling::div[1]";
	
	
	public void clickScheduleDay() {
		Utilities.clickElement(scheduleDay, ElementType.XPath);
	}

}
