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



public class Level_11_Register_Login_Element_Undisplayed  extends BaseTest {
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
  public void Register_01_Element_Displayed() {
	  //Displayed: Visible on UI + Exist in DOM
	  // WaitVisible
	  //isDisplayed
	  Assert.assertTrue(registerPage.isEmailTextboxDisplayed());
	  
	  // Business: Input to Email textbox
	  //Confirm email dispalyed
	  registerPage.enterToEmailTextbox("huong@gmail.com");
	  registerPage.sleepInSecond(3);
	  
	  Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
	  
  }
  
  @Test
  public void Register_02_Elelemnt_Undisplayed_In_DOM() {
	  //Undisplayed: Invisible on UI + Exist in DOM
	  // isDisplayed
	  registerPage.enterToEmailTextbox("");
	  registerPage.sleepInSecond(3);
	  
	  // Confirm email undisplayed
	  Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
  }
  
  @Test
  public void Register_03_Element_Undisplayed_Not_In_DOM() {
	  // Undisplayed : Invisible on UI + Not Exist in DOM
	  //isDisplayed --> False (Try - catch)
	  // Wait Maximum timeout implicit
	  Assert.assertFalse(registerPage.isLoginButtonDisplayed());
	  
	  // khẳng định 
	  Assert.assertFalse(registerPage.isLoginButtonDisplayed());

	  
	  
	  
  }
  
  @Test
  public void Register_04_Element_Undisplayed_Not_In_DOM() {
	  // Undisplayed : Invisible on UI + Not Exist in DOM
	  //FindElements
	  
	  // Phủ định 
	  Assert.assertTrue(registerPage.isLoginButtonUnDisplayed());

	  // Overide implicit timeout
	  
  }
  
  
  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }
}