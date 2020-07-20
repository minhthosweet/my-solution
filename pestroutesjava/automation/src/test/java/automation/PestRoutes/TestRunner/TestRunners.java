package automation.PestRoutes.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, plugin = { "pretty" }, glue = {
		"automation.PestRoutes" }, tags = "@Test")
public class TestRunners extends AbstractTestNGCucumberTests {
	
}
