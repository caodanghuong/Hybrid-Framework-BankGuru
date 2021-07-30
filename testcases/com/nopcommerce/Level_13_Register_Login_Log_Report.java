package com.nopcommerce;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrderPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class Level_13_Register_Login_Log_Report  extends BaseTest {
	WebDriver driver;
	String emailAddress, password;

  @Parameters({"browser", "url"})
  @BeforeClass
  public void initBrowser(String browserName, String appUrl) {
	  
	  log.info("Pre-Condition - Step 01: Open Browser '" + browserName + "' and navigate to '" + appUrl + "'" );
	  
	  driver = getBrowserDriver(browserName, appUrl);

	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  
	  emailAddress = getRandomEmail();
	  password = "123456";
	  
	  driver.get("https://demo.nopcommerce.com/");
	  homePage = PageGeneratorManager.getHomePage(driver);
  }
  
  @Test
  public void Login_01_Register_To_System() {
	  log.info("User_01_Register - Step 01: Verify Home Page is displayed");
	  verifyTrue(homePage.isHomePageLogoDisplayed());
	  
	  registerPage = PageGeneratorManager.getRegisterPage(driver);
	  
	  log.info("User_01_Register - Step 02: Click to Register link");
	  registerPage = homePage.clickToRegisterLink();
	  
	  log.info("User_01_Register - Step 03: Click to Male radio button ");
	  registerPage.ClickToGenderMaleRadioButton();
	  
	  log.info("User_01_Register - Step 04: Enter to FirstName textbox");
	  registerPage.enterToFirstnameTextbox("huong");
	  
	  log.info("User_01_Register - Step 05: Enter to LastName textbox");
	  registerPage.enterToLastnameTextbox("dang");
	  
	  log.info("User_01_Register - Step 06: Enter to Email textbox with value" + emailAddress);
	  registerPage.enterToEmailTextbox(emailAddress);
	  
	  log.info("User_01_Register - Step 07: Enter to Password textbox with value" + password);
	  registerPage.enterToPasswordTextbox(password);
	  
	  log.info("User_01_Register - Step 08: Enter to Confirm Password textbox with value" + password);
	  registerPage.enterToConfirmPasswordTextbox(password);
	  
	  log.info("User_01_Register - Step 09: Click to Register button ");
	  registerPage.clickToRegisterButton();
	  
	  
	  log.info("User_01_Register - Step 10: Verify success message is displayed");
	  verifyTrue(registerPage.isSuccessMessageDisplayed());
	  
	  
	  log.info("User_01_Register - Step 11: Click to Logout link");
	  //homePage = PageGeneratorManager.getHomePage(driver);
	  homePage = registerPage.clickToLogoutLink();
	  
	  
	  log.info("User_01_Register - Step 12: Verify Home Page is displayed");
	  verifyTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  log.info("User_01_Login - Step 01: Click to Login link");
	  loginPage = homePage.clickToLoginLink();
	  
	  log.info("User_01_Login - Step 02: Enter to Email textbox with value" + emailAddress);
	  loginPage.enterToEmailTextbox(emailAddress);
	  
	  log.info("User_01_Login - Step 03: Enter to Email textbox with value" + password);
	  loginPage.enterToPasswordTextbox(password);
	  
	  log.info("User_01_Login - Step 04: click to Logi button");
	  homePage = loginPage.clickToLoginButton();
	  
	  log.info("User_01_Login - Step 05: Verify Home Page is displayed");
	  verifyTrue(homePage.isHomePageLogoDisplayed());

  }
  
 
  @AfterClass
  public void cleanBrowser() {
	  log.info("Post-condition: Close browser");
	  driver.quit();
  }
	  
  HomePageObject homePage;
  LoginPageObject loginPage;
  RegisterPageObject registerPage;
  SearchPageObject searchPage;
  MyAccountPageObject myAccountPage;
  OrderPageObject orderPage;
  

}