package automation.PestRoutes.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty" }, 
		glue = { "automation.PesRoutes.Controller" },
		features = {"resources/features"},
		tags = {"@CustomerCart"})

public class TestRunners extends AbstractTestNGCucumberTests {

}
