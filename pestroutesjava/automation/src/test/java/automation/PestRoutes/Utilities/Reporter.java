package automation.PestRoutes.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporter {
	static ExtentHtmlReporter htmlReport;
	static ExtentReports extent;
	static ExtentTest test;

	public static ExtentTest getInstance(String testName) {
		if(test == null) {
			htmlReport = new ExtentHtmlReporter("Results.html");
			htmlReport.setAppendExisting(true);
			extent = new ExtentReports();
			extent.attachReporter(htmlReport);
			test = extent.createTest(testName);
		}
		return test;
	}
	
	public static void status(String expectedResult, String actualResult, String testName) {
		if(expectedResult.contains(actualResult)) {
			getInstance(testName).log(Status.PASS, "Actual result  => "+ actualResult+ " matched with expected result  => " +expectedResult);
		} else {
			getInstance(testName).log(Status.FAIL, "Actual result  => "+ actualResult+ " did not match with expected result  => " +expectedResult);
		}
		
	}
	
	public static void flushReport() {
		extent.flush();
	}

}
