package com.nopcommerce;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopcommerce.HomePageObject;
import pageFactory.nopcommerce.LoginPageObject;
import pageFactory.nopcommerce.RegisterPageObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import org.testng.Assert;

public class Level_05_Register_Login_Page_Factory  extends BaseTest {
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
  }
  
  @Test
  public void Login_01_Register_To_System() {
	  driver.get("https://demo.nopcommerce.com/");
	  homePage = new HomePageObject(driver);
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
	  homePage.clickToRegisterLink();
	  registerPage = new RegisterPageObject(driver);
	  
	  registerPage.ClickToGenderMaleRadioButton();
	  
	  registerPage.enterToFirstnameTextbox("huong");
	  
	  registerPage.enterToLastnameTextbox("dang");
	  
	  registerPage.enterToEmailTextbox(emailAddress);
	  
	  registerPage.enterToPasswordTextbox(password);
	  
	  registerPage.enterToConfirmPasswordTextbox(password);
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	  
	  registerPage.clickToLogoutLink();
	  homePage = new HomePageObject(driver);
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  
	  homePage.clickToLoginLink();
	  loginPage = new LoginPageObject(driver);
	  
	  loginPage.enterToEmailTextbox(emailAddress);
	  
	  
	  loginPage.enterToPasswordTextbox(password);
	  
	  loginPage.clickToLoginButton();
	  
	  homePage = new HomePageObject(driver);
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());

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

}