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

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import org.testng.Assert;

public class Level_08_Register_Login_Page_Dynamic_Locator  extends BaseTest {
	WebDriver driver;
	String emailAddress, password;

  @Parameters({"browser", "url"})
  @BeforeClass
  public void initBrowser(String browserName, String appUrl) {
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

	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
	  registerPage = PageGeneratorManager.getRegisterPage(driver);
	  registerPage = homePage.clickToRegisterLink();
	  registerPage.ClickToGenderMaleRadioButton();
	  registerPage.enterToFirstnameTextbox("huong");
	  registerPage.enterToLastnameTextbox("dang");
	  registerPage.enterToEmailTextbox(emailAddress);
	  registerPage.enterToPasswordTextbox(password);
	  registerPage.enterToConfirmPasswordTextbox(password);
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	  
	  //homePage = PageGeneratorManager.getHomePage(driver);
	  homePage = registerPage.clickToLogoutLink();
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  loginPage = homePage.clickToLoginLink();
	  loginPage.enterToEmailTextbox(emailAddress);
	  loginPage.enterToPasswordTextbox(password);
	  homePage = loginPage.clickToLoginButton();
	  //homePage = PageGeneratorManager.getHomePage(driver);
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());

  }
  
  @Test
  public void Login_03_Sưitch_Page_At_Footer() {
	  //Home Page --> Search Page
	  searchPage = (SearchPageObject) homePage.getFooterPageByName(driver, "Search");
	  
	  //Search Page --> My Account Page 
	  myAccountPage = (MyAccountPageObject) searchPage.getFooterPageByName(driver, "My account");
	  
	  
	  
	  // My Account Page --> Order Page 
	  orderPage = (OrderPageObject) myAccountPage.getFooterPageByName(driver, "Orders");
	  
	  
	  
	  // Order Page --> My Account Page 
	  myAccountPage = (MyAccountPageObject) orderPage.getFooterPageByName(driver, "My account");
	  
	  // My Account Page --> Search Page
	  
	  searchPage = (SearchPageObject) myAccountPage.getFooterPageByName(driver, "Search");
	  
	  
	  // Search --> Order
	  orderPage = (OrderPageObject) searchPage.getFooterPageByName(driver, "Orders");
	  
  }
  
  @Test
  public void Login_04_Sưitch_Page_At_Footer() {

	  // Order Page --> My Account Page 
	  orderPage.openFooterPageByName(driver, "My account");
	  myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
	  // My Account Page --> Search Page
	  myAccountPage.openFooterPageByName(driver, "Search");
	  searchPage = PageGeneratorManager.getsearchPage(driver);
	  
	  // Search --> Order
	  searchPage.openFooterPageByName(driver, "Orders");
	  orderPage = PageGeneratorManager.getOrderPage(driver);
	  
  }

  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }
  public String getRandomEmail() {
	  Random rand = new Random();
	  return "Testing" + rand.nextInt(9999) + "@live.com";
  }
  public void sleepInSecond(long timeout) {
	  try {
		Thread.sleep( timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  

  }
  HomePageObject homePage;
  LoginPageObject loginPage;
  RegisterPageObject registerPage;
  SearchPageObject searchPage;
  MyAccountPageObject myAccountPage;
  OrderPageObject orderPage;
  

}