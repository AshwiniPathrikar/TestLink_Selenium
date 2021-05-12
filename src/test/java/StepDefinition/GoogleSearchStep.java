package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class GoogleSearchStep {
	WebDriver driver;
	String driverPath ="D:\\TestLink+Selenium\\JAR Files\\geckodriver-v0.29.1-win64\\geckodriver.exe";
	
	public static String DEV_KEY = "30573049bbaf451d47542cdb588b10a8";// API Project KEY
	public static String SERVER_URL ="http://127.0.0.1/testlink/lib/api/xmlrpc/v1/xmlrpc.php";// TestLink server url
	public static String PROJECT_NAME ="TestProject01";
	public static String PLAN_NAME="TestPlan01";
	public static String BUILD_NAME="TestBuild001";
	
	String result = "";
    String exception = null;
    
	@Given("Launch the browser")
	public void launch_the_browser() throws Exception {
        
        try {
        	//Launch Fire-fox driver
    		System.setProperty("webdriver.gecko.driver",driverPath );
    		driver = new FirefoxDriver();
           
            result = TestLinkAPIResults.TEST_PASSED;
            updateTestLinkResult("AP-2", null, result);
       } catch (Exception ex) {
            result = TestLinkAPIResults.TEST_FAILED;
            exception = ex.getMessage();
            updateTestLinkResult("AP-2", exception, result);
       }
		
		//driver.get("http://www.google.com");
		//driver.get("http://127.0.0.1/index.php");
		
		
		System.out.print("Launch the browser");
	    
	}

	@Then("Enter google search url")
	public void enter_google_search_url() throws Exception {
	    
		try {
            driver.navigate().to("http://www.google.com");
            boolean flag = driver.findElement(By.name("btnK")).isDisplayed();
            if(flag) {
            result = TestLinkAPIResults.TEST_PASSED;
            updateTestLinkResult("AP-2", null, result);
            }
       } catch (Exception ex) {
            result = TestLinkAPIResults.TEST_FAILED;
            exception = ex.getMessage();
            updateTestLinkResult("AP-2", exception, result);
       }
		System.out.print("Enter google search url");
		
	  
	}
	
	//@AfterSuite
	public void tearDown() throws Exception {
        driver.quit();                                                      
   }
  
   public void updateTestLinkResult(String testCase, String exception, String result)throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,
                               SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,
                               testCase, BUILD_NAME, exception, result);
   }
   
}