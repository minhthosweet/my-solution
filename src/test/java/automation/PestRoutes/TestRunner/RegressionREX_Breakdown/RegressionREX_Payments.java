package automation.PestRoutes.TestRunner.RegressionREX_Breakdown;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/*
This TestRunner Includes Payments For All Gateways
    1. Customer Portal Billing Tab Payment Scenarios
    2. VerifyApplicationPerformsRenewalTaskAfterSingleUseCC Scenario
 */

@CucumberOptions(features = { "src/test/resources/features" }, plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "pretty"}, glue = {
        "automation.PestRoutes"},tags="@RegressionREX_Payments")
public class RegressionREX_Payments extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}