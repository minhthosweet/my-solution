package automation.PestRoutes.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
		Date date = new SimpleDateFormat("M/dd/yyyy").parse(input);
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate yearLater = localDate.plusYears(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
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

	public static String minusGenericDayToDate(String needDate, int days) throws Exception {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusDays(days);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

	public static int getMonth(String needDate) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date = needDate;
		LocalDate localDate = LocalDate.parse(date, formatter);
		int month = localDate.getMonthValue();
		return month;
	}

	public static int getYear(String needDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date = needDate;
		LocalDate localDate = LocalDate.parse(date, formatter);
		int year = localDate.getYear();
		return year;
	}
}
