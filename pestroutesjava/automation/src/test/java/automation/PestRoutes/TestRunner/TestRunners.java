package automation.PestRoutes.TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.*;

@CucumberOptions(
 features = {"src/test/java/automation/PestRoutes/Controller"},
 glue={"PageObject.CreateCustomer"}
 )

public class TestRunners extends AbstractTestNGCucumberTests{

}
