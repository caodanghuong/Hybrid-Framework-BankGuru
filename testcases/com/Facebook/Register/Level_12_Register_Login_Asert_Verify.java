package com.Facebook.Register;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.facebook.PageGeneratorManager;
import pageObject.facebook.RegisterPageObject;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;



public class Level_12_Register_Login_Asert_Verify  extends BaseTest {
	WebDriver driver;
	String emailAddress, password;
	RegisterPageObject registerPage;

  @Parameters({"browser", "url"})
  @BeforeClass
  public void initBrowser(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get(appUrl);
	  
	  registerPage = PageGeneratorManager.getRegisterPage(driver);
  }
  
  @Test
  public void Register_01_Assert() {
	  Assert.assertTrue(registerPage.isEmailTextboxDisplayed());
	  registerPage.enterToEmailTextbox("huong@gmail.com");
	  registerPage.sleepInSecond(3);
	  Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
	  registerPage.enterToEmailTextbox("");
	  registerPage.sleepInSecond(3);
	  
	  Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
	  
	  Assert.assertTrue(registerPage.isLoginButtonUnDisplayed());
  }
  @Test
  public void Register_02_Verify() {

  }
  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }
}