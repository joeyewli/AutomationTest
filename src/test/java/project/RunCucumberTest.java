package project;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// @CucumberOptions(plugin = {"pretty"})
//@CucumberOptions(features ="src/test/resources/project/loginTest.feature")
@CucumberOptions(features = {"src/test/resources/project/loginTest.feature"}, tags = {"@untested"})
public class RunCucumberTest {
}