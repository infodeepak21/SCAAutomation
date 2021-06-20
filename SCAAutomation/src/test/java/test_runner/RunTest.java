package test_runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features={"src/test/resfeaturefiles/"},

plugin= {"pretty","html:target/cucumber", "json:target/cucumber.json", "html:target/reprts/cucumber-pretty"},tags={"@test1"},
glue= {"step_definations"},
monochrome=true,snippets=SnippetType.CAMELCASE)

public class RunTest extends AbstractTestNGCucumberTests{

	public static void main(String[] args) {
		

	}

}
