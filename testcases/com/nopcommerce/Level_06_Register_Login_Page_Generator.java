package com.nopcommerce;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

public class Level_06_Register_Login_Page_Generator  extends BaseTest {
	WebDriver driver;
	String emailAddress, password;

  @Parameters({"browser", "url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
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
	  
	  registerPage = homePage.clickToRegisterLink();
	  
	  
	  registerPage.ClickToGenderMaleRadioButton();
	  
	  registerPage.enterToFirstnameTextbox("huong");
	  
	  registerPage.enterToLastnameTextbox("dang");
	  
	  registerPage.enterToEmailTextbox(emailAddress);
	  
	  registerPage.enterToPasswordTextbox(password);
	  
	  registerPage.enterToConfirmPasswordTextbox(password);
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	  
	  homePage = registerPage.clickToLogoutLink();
	  
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  
	  loginPage = homePage.clickToLoginLink();
	  
	  
	  loginPage.enterToEmailTextbox(emailAddress);
	  
	  
	  loginPage.enterToPasswordTextbox(password);
	  
	  homePage = loginPage.clickToLoginButton();
	  
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());

  }

  @AfterClass
  public void afterClass() {
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

}