package automation.PestRoutes.Utilities;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertException {
	// public static List list = new ArrayList<String>();
	public static List<String> result(String expected, String actual, String testName) {
		List list = new ArrayList<String>();
		try {
			assertTrue(actual.contains(expected));
		} catch (AssertionError e) {
//			if(e.getMessage()!=null) {
//				list.add(testName + ":" + e.getMessage());
//			}
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}

	public static List<String> conditionResult(WebElement element) {
		List list = new ArrayList<String>();
		try {
			if (element == null) {
				Assert.fail();
			}
		} catch (AssertionError e) {
			list.add(e.getMessage());
		}
		return list;
	}

	public static List<String> hardAssert(String expected, String actual, String testName) {
		List list = new ArrayList<String>();
		try {
			assertTrue(actual.equals(expected));
		} catch (AssertionError e) {
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}

	public static void assertFailure(List needListName) {
		List list = new ArrayList<String>();
		System.out.println(needListName);
		if (needListName.size() > 0) {
			System.out.println(needListName.size());
			System.out.println(needListName);
			throw new AssertionError();

		}
	}

	public static void result(String expected, String actual, String stepName, String testName) {
		List list = new ArrayList<String>();
		if (AssertException.result(expected, actual, stepName).size() > 0) {
			list.add(AssertException.result(expected, actual, stepName));
		}
		Reporter.status(stepName, expected, actual, testName);
	}

	public static void validateFieldEnabled(String[] needArray) {
		for (int i = 0; i < needArray.length; i++) {
			WebElement field = FindElement.elementByAttribute(needArray[i], FindElement.InputType.XPath);
			if (field.isEnabled()) {
				Assert.assertTrue(true);
				Reporter.getInstance("Validate fields in account receivable").log(Status.PASS, needArray[i] + " displayed");
			} else {
				Reporter.getInstance("Validate fields in account receivable").log(Status.FAIL, needArray[i] + " is missing");
				Assert.assertFalse(true);
			}
		}

	}
}

