package automation.PestRoutes.Utilities;

import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.Before;

public class CucumberBaseClass{

	@Before
	public void beforeTest(){
		GetWebDriver.getInstance();
	}
}
