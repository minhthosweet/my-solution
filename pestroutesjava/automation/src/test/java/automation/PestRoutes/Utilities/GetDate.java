package automation.PestRoutes.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

public class GetDate {
	
	public void getDateByDay() {
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_WEEK, 2);
//		Date futureDateTime = calendar.getTime();
		int futureDateTime = calendar.getFirstDayOfWeek();
		System.out.println(futureDateTime);
	}
	public static String addOneYearToDate(String needDate) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "M/dd/uuuu" );
		LocalDate localDate = LocalDate.parse ( input , formatter );
		LocalDate yearLater = localDate.plusYears ( 1 );
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

}
