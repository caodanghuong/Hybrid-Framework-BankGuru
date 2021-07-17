package com.nopcommerce;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Register_Login_Page_Object  extends BasePage {
	WebDriver driver;
	String emailAddress, password;


  @BeforeClass
  public void initBrowser() {
	  driver = new FirefoxDriver();

	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  
	  emailAddress = getRandomEmail();
	  password = "123456";
  }
  
  @Test
  public void Login_01_Register_To_System() {
	  //Step 1: mo usrl ra - ? Home Page
	  driver.get("https://demo.nopcommerce.com/");
	  homePage = new HomePageObject(driver);
	  
	  //Step 2: Verify Home Page Logo displayed
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
	  //Step 3: Click to Register link -> Register Page
	  homePage.clickToRegisterLink();
	  registerPage = new RegisterPageObject(driver);
	  
	  //Step  4: click to Gender male radio
	  registerPage.ClickToGenderMaleRadioButton();
	  
	  //Step 5: Input to Firestname textbox
	  registerPage.enterToFirstnameTextbox("huong");
	  
	  //Step 6: Input to Lastname textbox
	  registerPage.enterToLastnameTextbox("dang");
	  
	  //Step 7: Input to Email textbox
	  registerPage.enterToEmailTextbox(emailAddress);
	  
	  //Step 8: Input to Password textbox
	  registerPage.enterToPasswordTextbox(password);
	  
	  //Step 9: Input to Confirm password textbox
	  registerPage.enterToConfirmPasswordTextbox(password);
	  
	  //Step 10: click to register button
	  registerPage.clickToRegisterButton();
	  
	  //Step 11: Verify Success Message displayed
	  Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
	  
	  //Step 12: click to Logout link -> Home Page
	  registerPage.clickToLogoutLink();
	  homePage = new HomePageObject(driver);
	  
	  //Step 13: Verify Home Page Logo displayed
	  Assert.assertTrue(homePage.isHomePageLogoDisplayed());
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  
	  //Step 1: click to Login link
	  homePage.clickToLoginLink();
	  loginPage = new LoginPageObject(driver);
	  
	  //Step 2: Input to Password textbox
	  loginPage.enterToEmailTextbox(emailAddress);
	  
	  
	  //Step 3:  Input to Email textbox
	  loginPage.enterToPasswordTextbox(password);
	  
	  // Step 4: Click to Login button
	  loginPage.clickToLoginButton();
	  
	  homePage = new HomePageObject(driver);
	  
	  //Step 5: Verify Home Page Logo displayed
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