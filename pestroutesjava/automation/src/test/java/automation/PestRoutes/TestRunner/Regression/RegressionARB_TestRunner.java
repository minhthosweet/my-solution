package automation.PestRoutes.TestRunner.Regression;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = { "src/test/resources/features" }, plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "pretty"}, glue = {
        "automation.PestRoutes"},tags="@Renewal")
public class RegressionARB_TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
