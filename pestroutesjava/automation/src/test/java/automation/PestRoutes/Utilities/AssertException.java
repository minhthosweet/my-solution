package automation.PestRoutes.Utilities;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertException {
	public static List list = new ArrayList<String>();
	public static List<String> result(String expected, String actual, String testName){
		
		try{
			assertTrue(actual.contains(expected));
		}catch(AssertionError e) {
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}
	
	public static List<String> conditionResult(WebElement element){
		try {
			if(element==null) {
				Assert.fail();
			}
		}catch(AssertionError e) {
			list.add(e.getMessage());
		}
		return list;
	}
	
	public static void asserFailure(List needListName) {
		//list.removeIf(Objects::isNull);
		if (needListName.size()>0) {
			System.out.println(needListName.size());
			System.out.println(needListName);
			throw new AssertionError();
			
		}
	}

}
