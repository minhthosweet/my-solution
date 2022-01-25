package automation.PestRoutes.TestRunner.FeatureFiles;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"},
                plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty", "pretty"},
                glue = {"automation.PestRoutes"},
                tags = "@Scheduling")
public class RunScheduling extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider//(parallel = true) Commented Because Most Browser Instances Load A Blank Page When Executing testng.xml
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
