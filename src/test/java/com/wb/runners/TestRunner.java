package com.wb.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "classpath:features/TestScenario1.feature", 
				glue = "com.whitebread.steps",
		plugin = { "pretty",
				"html:target/cucumber","json:target/cucumber-report/cucumber.json" },
		monochrome=true
 )
public class TestRunner {

}
