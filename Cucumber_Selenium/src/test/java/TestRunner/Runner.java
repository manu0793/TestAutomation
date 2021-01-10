package TestRunner;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="Features",
		glue= {"StepDefinition"},
		plugin="html:target/myreports",
		monochrome = true,
		dryRun = false,
		strict = true
		)
public class Runner {

}
