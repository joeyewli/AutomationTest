package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// @CucumberOptions(plugin = {"pretty"})
//@CucumberOptions(features ="src/test/resources/projectSauceDemo/loginTest.feature")
@CucumberOptions(
        features = {"src/test/resources/Feature/loginTest.feature"}
        ,glue = "stepDefinitions"
        ,tags = {"~@donttest"})
public class RunCucumberTest {
}
