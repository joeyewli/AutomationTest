package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// @CucumberOptions(plugin = {"pretty"})
//@CucumberOptions(features ="src/test/resources/projectSauceDemo/loginTest.feature")
@CucumberOptions(
        features = {"src/test/resources/Feature"}
        ,plugin = "pretty"
        ,glue = "stepDefinitions"
//        ,tags = "@logout")
        ,tags = {"~@donttest"})
public class RunCucumberTest {
}
