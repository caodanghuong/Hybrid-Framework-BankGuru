package com.nopcommerce;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopcommerce.HomePageObject;
import pageFactory.nopcommerce.LoginPageObject;
import pageFactory.nopcommerce.PageGeneratorManager;
import pageFactory.nopcommerce.RegisterPageObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import org.testng.Assert;

public class Level_07_Register_Login_Page_Switch_Page  extends BaseTest {
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
	  registerPage.ClickToGenderMaleRadioButton();
	  registerPage.enterToFirstnameTextbox("huong");
	  registerPage.enterToLastnameTextbox("dang");
	  registerPage.enterToEmailTextbox(emailAddress);
	  registerPage.enterToPasswordTextbox(password);
	  registerPage.enterToConfirmPasswordTextbox(password);
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	  
	  homePage = PageGeneratorManager.getHomePage(driver);
	  
	  
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  
	  loginPage = PageGeneratorManager.getLoginPage(driver);
	  loginPage.enterToEmailTextbox(emailAddress);
	  loginPage.enterToPasswordTextbox(password);
	  
	  homePage = PageGeneratorManager.getHomePage(driver);
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());

  }
  
  @Test
  public void Login_03_Sưitch_Page_At_Footer() {}

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