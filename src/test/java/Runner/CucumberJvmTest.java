package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features = {"src/test/java/Tests/Features"},
        glue = {"Tests"}
)

public class CucumberJvmTest extends AbstractTestNGCucumberTests {
}