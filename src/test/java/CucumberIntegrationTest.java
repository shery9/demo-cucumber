import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:build/reports/tests/cucumber/cucumber-report.html"},
        features = "src/test/resources/features/",
        glue = {"com.demo.cucumbertest.steps"}
)
public class CucumberIntegrationTest {

    @Test
    public void runAllFeatures() {
        // No need to put any code here, Cucumber will automatically run the features
    }

}
