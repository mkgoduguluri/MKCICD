package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//this should be above class
@CucumberOptions(features="src/test/java/cucumber", glue="daily.stepDefinitions", 
			monochrome= true, tags= "@Regression", plugin= {"html:target/cucumber.html"})
	
	public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	//cannot run  cucumber directly, it will provide all the features as TestNg
	//to run we will depend on the TestNG.
	
	
		//mvn test -PCucumberTests
	
	//add in Jenkins too..
}
	