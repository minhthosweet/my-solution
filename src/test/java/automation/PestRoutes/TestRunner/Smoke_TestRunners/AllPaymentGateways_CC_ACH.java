package automation.PestRoutes.TestRunner.Smoke_TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = { "src/test/resources/features" }, plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "pretty"}, glue = {
        "automation.PestRoutes"},tags="@PaymentCC or @PaymentACH")
public class AllPaymentGateways_CC_ACH extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
