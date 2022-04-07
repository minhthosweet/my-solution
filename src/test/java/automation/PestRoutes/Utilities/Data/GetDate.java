package automation.PestRoutes.Utilities.Data;

import org.apache.commons.lang3.time.*;

import java.text.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

    public static String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("MMMMMMMMMMMMMM").format(calendar.getTime());
    }

    public static String addYearstoCurrentYear(String needFormat, int addNumberOfMonths) {
        DateFormat dateFormat = new SimpleDateFormat(needFormat);
        Date date = new Date();
        date = DateUtils.addYears(date, addNumberOfMonths);
        String date1 = dateFormat.format(date);
        return date1;
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String date = new SimpleDateFormat("dd").format(calendar.getTime());
        if (date.startsWith("0")) {
            date = date.substring(1);
        }
        return date;
    }

    public static String getMonthsInFuture(int monthsInFuture) {
        Calendar currentMonth = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        currentMonth.add(Calendar.MONTH, monthsInFuture);
        return dateFormat.format(currentMonth.getTime());
    }

    public static String currentDate(String needFormat) {
        DateFormat dateFormat = new SimpleDateFormat(needFormat);
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public static String currentDateWithZeroDelimiterOnDate(String needFormat) {
        DateFormat dateFormat = new SimpleDateFormat(needFormat);
        Date date = new Date();
        String date1 = dateFormat.format(date);
        if (date1.startsWith("0") && (needFormat.startsWith("d") || needFormat.startsWith("M"))) {
            date1 = date1.substring(1);
            if (date1.charAt(4) == '0') {
                date1 = date1.substring(0, 3) + date1.substring(4);
            }
        }
        return date1;
    }

    public void getDateByDay() {
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_WEEK, 2);
//		Date futureDateTime = calendar.getTime();
		int futureDateTime = calendar.getFirstDayOfWeek();
		System.out.println(futureDateTime);
	}

	public static String addOneYearToDate(String needDate) {
		try {
			Date date = new SimpleDateFormat("M/dd/yyyy").parse(needDate);
			Instant instant = date.toInstant();
			LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate yearLater = localDate.plusYears(1);
			String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
			return formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String minusOneYearToDate(String needDate) {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusYears(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		return formattedDate;

	}

	public static String minusOneWeekToDate(String needDate) {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusWeeks(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		return formattedDate;

	}

	public static String addOneDayToDate(String needDate) {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.plusDays(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		return formattedDate;

	}
	
	public static String minusOneDayToDate(String needDate) {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusDays(1);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		return formattedDate;

	}

	public static String minusGenericDayToDate(String needDate, int days) {
		String input = needDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uuuu");
		LocalDate localDate = LocalDate.parse(input, formatter);
		LocalDate yearLater = localDate.minusDays(days);
		String formattedDate = yearLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
		System.out.println(formattedDate);
		return formattedDate;

	}

	public static int getMonth(String needDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
		String date = needDate;
		LocalDate localDate = LocalDate.parse(date, formatter);
		int month = localDate.getMonthValue();
		return month;
	}

	public static int getYear(String needDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
		String date = needDate;
		LocalDate localDate = LocalDate.parse(date, formatter);
		int year = localDate.getYear();
		return year;
	}

	public static String convert_4DigitYear_2DigitMonth_2DigitDay(String date) {
		// Incoming Date Is Formatted As YYYY-MM-DD
		// Return Date Is Formatted As M/DD/YYYY

		String[] separateYearMonthDay = date.split("-");
		String year = separateYearMonthDay[0];
		String month = separateYearMonthDay[1].replaceFirst("^0*", "");
		String day = separateYearMonthDay[2].replaceFirst("^0*", "");
		return month + "/" + day + "/" + year;
	}

	public static String convert_2DigitMonth_2DigitDay_2DigitYear(String date) {
		// Incoming Date Is Formatted As MM/dd/yy
		// Return Date Is Formatted As M/DD/YYYY

		String[] separateYearMonthDay = date.split("/");
		String month = separateYearMonthDay[0].replaceFirst("^0*", "");
		String day = separateYearMonthDay[1].replaceFirst("^0*", "");
		String year = "20" + separateYearMonthDay[2];
		return month + "/" + day + "/" + year;
	}

	public static String convert_2DigitMonth_2DigitDay_4DigitYear(String date) {
		// Incoming Date Is Formatted As MM/dd/YYYY
		// Return Date Is Formatted As M/DD/YYYY

		String[] separateYearMonthDay = date.split("/");
		String month = separateYearMonthDay[0].replaceFirst("^0*", "");
		String day = separateYearMonthDay[1].replaceFirst("^0*", "");
		String year = separateYearMonthDay[2];
		return month + "/" + day + "/" + year;
	}

	public static String display_DayOfWeek_Date(String date, String dateFormat) throws ParseException {
		// Pass In The Date & Format
		// Return Day of Week & Date (i.e., Sunday, May 8)

		Date convertDate = new SimpleDateFormat(dateFormat).parse(date);
		String dayOfWeek = new SimpleDateFormat("EEEE").format(convertDate);

		SimpleDateFormat month_date = new SimpleDateFormat("MMM d");
		Calendar calendar = Calendar.getInstance();
		String month_day = month_date.format(calendar.getTime());
		return dayOfWeek + ", " + month_day;
	}
}
