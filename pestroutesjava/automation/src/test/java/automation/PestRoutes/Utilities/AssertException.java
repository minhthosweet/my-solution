package automation.PestRoutes.Utilities;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

public class AssertException {
	
	
	public static List<String> result(String expected, String actual, String testName){
		List list = new ArrayList<String>();
		try{
			assertTrue(actual.contains(expected));
		}catch(AssertionError e) {
			list.add(testName + ":" + e.getMessage());
		}
		return list;
	}
	
	public static void asserFailure(List needListName) {
		if (needListName.size()>0) {
			throw new AssertionError();
	}
	}

}
