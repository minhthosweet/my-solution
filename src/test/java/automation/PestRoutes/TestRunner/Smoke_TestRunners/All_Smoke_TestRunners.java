package automation.PestRoutes.TestRunner.Smoke_TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = { "src/test/resources/features" }, plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "pretty"}, glue = {
        "automation.PestRoutes"},tags="@AccountReceivable or @PaymentCC or @PaymentACH or @CustomerReports or @CustomAppointmentDate or @Equipment or @Transfer or @VerifyAppointmentInfoOnAppointmentsTab or @Renewal")
public class All_Smoke_TestRunners extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
