package automation.PestRoutes.Utilities;

import org.openqa.selenium.WebElement;

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
	
	
	public static void status(String testStep, String expectedResult, String actualResult, String testName) {
		if(actualResult.contains(expectedResult)) {
			getInstance(testName).log(Status.PASS, "Validate "+testStep+ "=> Actual result  => "+ actualResult+ " matched with expected result  => " +expectedResult);
		} else {
			getInstance(testName).log(Status.FAIL, "Validate "+testStep+ "=> Actual result  => "+ actualResult+ " did not match with expected result  => " +expectedResult);
		}
		
	}

	public static void doubleStatus(String testStep, double expectedResult, double actualResult, String testName) {
		if(actualResult == expectedResult) {
			getInstance(testName).log(Status.PASS, "Validate "+testStep+ "=> Actual result  => "+ actualResult+ " matched with expected result  => " +expectedResult);
		} else {
			getInstance(testName).log(Status.FAIL, "Validate "+testStep+ "=> Actual result  => "+ actualResult+ " did not match with expected result  => " +expectedResult);
		}

	}
	
	public static void conditionStatus(WebElement needElement, String testStep, String testName) {
		if(needElement.isDisplayed()) {
			getInstance(testName).log(Status.PASS, "Validate "+testStep+ "=> " + needElement + " Displayed");
		} else {
			getInstance(testName).log(Status.FAIL, "Validate "+testStep+ "=> " + needElement + " did not displayed");
		}
	}
	
	public static void flushReport() {
		extent.flush();
	}

}
