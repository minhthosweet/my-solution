package automation.PestRoutes.Utilities;

import java.io.IOException;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberBaseClass{

	@Before
	public void beforeTest() throws IOException {

		GetWebDriver.getInstance();

		

	}

	@After
	public void afterTest(final Scenario scenario) {
		Reporter.flushReport();
		GetWebDriver.quitCurrentDriver();
		
		
		
	}

}
