package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	  private WebDriver driver;
	  protected final Log log;
	  
	  // Constructor
	  protected BaseTest() {
		  log = LogFactory.getLog(getClass());
	  }
	 private enum BROWSER {
		  CHROME, FIREFOX, ID, SAFARI, EDGE_LEG, CHEADLESS;
	  }
	
	 public WebDriver getBrowserDriver( String browserName) {
		 
		 BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		 
		 
		  if (browser ==BROWSER.FIREFOX) {
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
		  } //else if(browser ==BROWSER.CHROME) {
		 // WebDriverManager.chromedriver().setup();
			 // driver = new ChromeDriver();
		  //} //else if(browser ==BROWSER.EDGE_CHROMIUM) {
		  //WebDriverManager.edgedriver().setup();
			 // driver = new EdgeDriver();
		 // } //else {
			 // throw new RuntimeException("Please enter correct browser name!");
		 // }
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  return driver;
	}
	
	 public WebDriver getBrowserDriver( String browserName, String appUrl) {
		 
		 BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		 
		 
		  if (browser ==BROWSER.FIREFOX) {
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
		  } //else if(browser ==BROWSER.CHROME) {
		 // WebDriverManager.chromedriver().setup();
			 // driver = new ChromeDriver();
		  //} //else if(browser ==BROWSER.EDGE_CHROMIUM) {
		  //WebDriverManager.edgedriver().setup();
			 // driver = new EdgeDriver();
		 // } //else {
			 // throw new RuntimeException("Please enter correct browser name!");
		 // }
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  return driver;
	}
	  
	 
	 public String getRandomEmail() {
		  Random rand = new Random();
		  return "Testing" + rand.nextInt(9999) + "@live.com";
	  }
	 
	private boolean checkTrue(boolean condition) {
			boolean pass = true;
			try {
				if (condition == true) {
					log.info(" -------------------------- PASSED -------------------------- ");
				} else {
					log.info(" -------------------------- FAILED -------------------------- ");
				}
				Assert.assertTrue(condition);
			} catch (Throwable e) {
				pass = false;

				// Add l???i v??o ReportNG
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
				Reporter.getCurrentTestResult().setThrowable(e);
			}
			return pass;
		}

		protected boolean verifyTrue(boolean condition) {
			return checkTrue(condition);
		}

		private boolean checkFailed(boolean condition) {
			boolean pass = true;
			try {
				if (condition == false) {
					log.info(" -------------------------- PASSED -------------------------- ");
				} else {
					log.info(" -------------------------- FAILED -------------------------- ");
				}
				Assert.assertFalse(condition);
			} catch (Throwable e) {
				pass = false;
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
				Reporter.getCurrentTestResult().setThrowable(e);
			}
			return pass;
		}

		protected boolean verifyFalse(boolean condition) {
			return checkFailed(condition);
		}

		private boolean checkEquals(Object actual, Object expected) {
			boolean pass = true;
			try {
				Assert.assertEquals(actual, expected);
				log.info(" -------------------------- PASSED -------------------------- ");
			} catch (Throwable e) {
				pass = false;
				log.info(" -------------------------- FAILED -------------------------- ");
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
				Reporter.getCurrentTestResult().setThrowable(e);
			}
			return pass;
		}

		protected boolean verifyEquals(Object actual, Object expected) {
			return checkEquals(actual, expected);
		}
	 
	 
}
