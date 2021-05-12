package StepDefinition;

import java.util.IllegalFormatFlagsException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class Ecommerce {
	WebDriver driver;
	String driverPath ="D:\\TestLink+Selenium\\JAR Files\\geckodriver-v0.29.1-win64\\geckodriver.exe";

	public static String DEV_KEY = "30573049bbaf451d47542cdb588b10a8";// API Project KEY
	public static String SERVER_URL ="http://127.0.0.1/testlink/lib/api/xmlrpc/v1/xmlrpc.php";// TestLink server url
	public static String PROJECT_NAME ="TestProject01";
	public static String PLAN_NAME="TestPlan01";
	public static String BUILD_NAME="TestBuild001";

	String result = "";
	String exception = null;

	@Given("Launch the application")
	public void launch_the_application () throws Exception {
		try {
			//Launch Fire-fox driver
			System.setProperty("webdriver.gecko.driver",driverPath );
			driver = new FirefoxDriver();

			driver.navigate().to("https://qentinelqi.github.io/shop/");

			result = TestLinkAPIResults.TEST_PASSED;
			updateTestLinkResult("AP-3", null, result);
		} catch (Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-3", exception, result);
		}
	}

	@When("I navigate to contact page")
	public void i_navigate_to_contact_page() throws Exception{
		try {
			WebElement contact =driver.findElement(By.linkText("Contact"));
			boolean flag = contact.isDisplayed();
			if(flag) {
				contact.click();
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("AP-3", null, result);
			}

		}catch(Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-3", exception, result);			   
		}

	}

	@And("I enter all data")
	public void i_enter_all_data() throws Exception {
		try {
			WebElement fullName =driver.findElement(By.id("name"));
			WebElement email =driver.findElement(By.id("email"));
			WebElement message =driver.findElement(By.id("message"));
			WebElement submit = driver.findElement(By.xpath("//input[5]"));
			boolean flag = fullName.isDisplayed();
			if(flag) {
				fullName.sendKeys("This is a first name");
				email.sendKeys("email@email.com");
				message.sendKeys("This is a demo message");
				submit.click();
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("AP-3", null, result);
			}

		}catch(Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-3", exception, result);			   
		}

	}

	@Then("Verify message is sent")
	public void verify_message_is_sent() throws Exception{
		try {
			String expectedTitle = "We'll be in touch";
			String actualTitle =driver.findElement(By.xpath("//section[@class='hero']//h2")).getText();
			if(expectedTitle.equalsIgnoreCase(actualTitle)) {
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("AP-2", null, result);
				driver.quit();
			}

		}catch(Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-3", exception, result);			   
		}

	}

	@When("I navigate to product page")
	public void i_navigate_to_product_page() throws Exception{
		try {
			WebElement product =driver.findElement(By.linkText("Products"));
			boolean flag = product.isDisplayed();
			if(flag) {
				product.click();
				result = TestLinkAPIResults.TEST_PASSED;
				updateTestLinkResult("AP-4", null, result);
			}

		}catch(Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-4", exception, result);			   
		}

	}

	@Then("I add product to the cart")
	public void i_add_product_to_the_cart() throws Exception{
		try {
			WebElement firstProduct = driver.findElement(By.xpath("//*[@id='products']//li"));
			firstProduct.click();

			WebElement addToCart = driver.findElement(By.xpath("//*[@id='add']/div/button"));
			addToCart.click();
			
			result = TestLinkAPIResults.TEST_PASSED;
			updateTestLinkResult("AP-4", null, result);
			driver.quit();

		}catch(Exception ex) {
			result = TestLinkAPIResults.TEST_FAILED;
			exception = ex.getMessage();
			updateTestLinkResult("AP-4", exception, result);			   
		}

	}

	public void updateTestLinkResult(String testCase, String exception, String result)throws TestLinkAPIException {
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,
				SERVER_URL);
		testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,
				testCase, BUILD_NAME, exception, result);
	}
}



