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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.plusYears(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

	public static String minusOneYearToDate(String needDate) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusYears(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

	public static String minusOneWeekToDate(String needDate) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusWeeks(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

	public static String addOneDayToDate(String needDate) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.plusDays(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}
	
	public static String minusOneDayToDate(String needDate) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusDays(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}
}
