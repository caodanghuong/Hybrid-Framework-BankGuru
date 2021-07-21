package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	  private WebDriver driver;
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
}
