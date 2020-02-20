package automation.PestRoutes.Utilities;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

public class GetDate {
	@Test
	public void getDateByDay() {
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_WEEK, 2);
//		Date futureDateTime = calendar.getTime();
		int futureDateTime = calendar.getFirstDayOfWeek();
		System.out.println(futureDateTime);
	}

}
