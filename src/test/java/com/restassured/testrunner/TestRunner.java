/**
 * 
 */
package com.restassured.testrunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author gslab
 *
 */
@CucumberOptions(features="src/test/java/com/restassured/features",plugin="json:target/jsonReports/cucumber_report.json",glue= {"com/restassured/StepDefinations"})
public class TestRunner extends AbstractTestNGCucumberTests {
//    private TestNGCucumberRunner testNGCucumberRunner;
//    
//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() throws Exception {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
	
	
}