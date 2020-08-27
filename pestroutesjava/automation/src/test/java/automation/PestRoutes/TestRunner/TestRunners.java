package automation.PestRoutes.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, plugin = { "pretty" }, glue = {
		"features" }, tags = "@Sales")
public class TestRunners extends AbstractTestNGCucumberTests {
	
}
