package automation.PestRoutes.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "src/test/java/automation/PestRoutes/Controller" }, glue = { "PageObject.CreateCustomer" })

public class TestRunners extends AbstractTestNGCucumberTests {

}
