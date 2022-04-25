package automation.PestRoutes.Utilities.Data;

import org.apache.commons.lang3.time.*;

import java.text.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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
		LocalDate dayLater = localDate.plusDays(1);
		return dayLater.format(DateTimeFormatter.ofPattern("M/dd/yyyy"));
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

	public static String compareTwoDates(String strDate1, String dateFormat1 , String strDate2, String dateFormat2 ){
		String CheckFormat = "MM/dd/yyyy";
		String date1String;
		String date2String;
		Date D1 = new Date();
		Date D2 = new Date();
		int flagDate1=0;
		int flagDate2 =0;

		try {
			DateFormat date1DF = new SimpleDateFormat(dateFormat1);
			date1DF.setLenient(false);

			Date date1 = date1DF.parse(strDate1);
			date1String = new SimpleDateFormat(CheckFormat).format(date1);
			DateFormat dateDF1 = new SimpleDateFormat(CheckFormat);
			D1=dateDF1.parse(date1String);
		} catch (ParseException | IllegalArgumentException e) {
			flagDate1 = 1;
		}

		try {
			DateFormat date2DF = new SimpleDateFormat(dateFormat2);
			date2DF.setLenient(false);

			Date date2 =date2DF.parse(strDate2);
			date2String = new SimpleDateFormat(CheckFormat).format(date2);
			DateFormat dateDF2 = new SimpleDateFormat(CheckFormat);
			D2=dateDF2.parse(date2String);
		} catch (ParseException | IllegalArgumentException e) {
			flagDate2 = 1;
		}

		if((flagDate1 == 0) &&  (flagDate2==0)) {
			if (D1.equals(D2)) {
				return "0"; // Dates are sane
			}
			else if ( D1.before(D2))
				return "-1";  //Before
			else
				return "1";  //After
		}
		return "";
	}//compareTwoDates()

	public static String formatDate(String strDate, String originalFormat ){
		String CheckFormat = "MM/dd/yyyy";
		String strFormattedDate = "";

		try {
			DateFormat dateDF = new SimpleDateFormat(originalFormat);
			dateDF.setLenient(false);

			Date date = dateDF.parse(strDate);
			strFormattedDate = new SimpleDateFormat(CheckFormat).format(date);
		} catch (ParseException | IllegalArgumentException e) {
			e.printStackTrace();
		}

		return strFormattedDate ;
	}//formatDate()

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
		// Return Date Is Formatted As M/D/YYYY

		String[] separateYearMonthDay = date.split("/");
		String month = separateYearMonthDay[0].replaceFirst("^0*", "");
		String day = separateYearMonthDay[1].replaceFirst("^0*", "");
		String year = separateYearMonthDay[2];
		return month + "/" + day + "/" + year;
	}

	public static String convertTo_4DigitYear_2DigitMonth_2DigitDay(String date) {
		// Incoming Date Is Formatted As MM/dd/YYYY
		// Return Date Is Formatted As YYYY-MM-DD

		String[] separateYearMonthDay = date.split("/");
		String month = separateYearMonthDay[0];
		String day = separateYearMonthDay[1];
		String year = separateYearMonthDay[2];
		return year + "-" + month + "-" + day;
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

	public static boolean isTargetTimeBetweenTimeRange(String targetTime, String startTime, String endTime) {
		LocalTime start = LocalTime.parse("0" + startTime);
		LocalTime end = LocalTime.parse("0" + endTime);
		LocalTime actualTime = LocalTime.parse("0" + targetTime);

		if ((! actualTime.isBefore(start) && ! actualTime.isAfter(end))) {
			return true;
		}
		return false;
	}
}
