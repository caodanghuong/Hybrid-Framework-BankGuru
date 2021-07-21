package com.LiveGuru.Login;

import org.testng.annotations.Test;


import commons.BaseTest;
import pageObject.LiveGuru.HomePageObject;
import pageObject.LiveGuru.LoginPageObject;
import pageObject.LiveGuru.MyDashboardPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Register_Login_Mutilple_Browser  extends BaseTest {
	WebDriver driver;
	String emailAddress, password;
	
	
  @Parameters({"browser", "url"})
  @BeforeClass
  public void beforeClass(String browserName, String appUrl) {
	   driver = getBrowserDriver(browserName, appUrl);
  }
  
  @Test
  public void Login_01_Empty_Email_And_Pasword() {
	  //Step 1: mo usrl ra - ? Home Page
	  driver.get("http://live.demoguru99.com/index.php/");

	  homePage = new HomePageObject(driver);
	  
	  //Step 2: click vao My Account Header -> Login Page
	  homePage.clickToMyAccountFooter();
	  loginPage = new LoginPageObject(driver);
	  
	  
	  loginPage.enterToEmailTextbox("");
	  loginPage.enterToPasswordTextbox("");
	  loginPage.clickToLoginButton();
	  
	  
	  
	  Assert.assertEquals(loginPage.getEmptyEmailErrorMessage(), "This is a required field.");
	  Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "This is a required field.");
  }
  
  @Test
  public void Login_02_Invalid_Email() {
	  loginPage.refreshCurrentPage(driver);
	  
	  
	  loginPage.enterToEmailTextbox("123@456.789");
	  loginPage.enterToPasswordTextbox("123456");
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getInvalidEmailErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");


  }
  @Test(description = "Password less than 6 chars")
  public void Login_03_Invalid_Password() {
	  loginPage.refreshCurrentPage(driver);
	  
	  
	  loginPage.enterToEmailTextbox("dam@gamil.com");
	  loginPage.enterToPasswordTextbox("123");
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getIvalidPasswordErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");

  }
  @Test(description = "Email not exist in System")
  public void Login_04_Incorrect_Email() {
	  loginPage.refreshCurrentPage(driver);
	  
	  
	  loginPage.enterToEmailTextbox(getRandomEmail());
	  loginPage.enterToPasswordTextbox("123456");
	  loginPage.clickToLoginButton();
	  
	  
	  Assert.assertEquals(loginPage.getIvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");

  }
  @Test
  public void Login_05_Incorrect_Password() {
	  loginPage.refreshCurrentPage(driver);
	  
	  
	  loginPage.enterToEmailTextbox("dam@gmail.com");
	  loginPage.enterToPasswordTextbox("1312134");
	  loginPage.clickToLoginButton();
	  
	  
	  Assert.assertEquals(loginPage.getIvalidEmailOrPasswordErrorMessage(), "Invalid login or password.");


  }
  @Test
  public void Login_06_Valid_Email_And_Password() {
	  loginPage.refreshCurrentPage(driver);
	  
	  
	  loginPage.enterToEmailTextbox("dam@gmail.com");
	  loginPage.enterToPasswordTextbox("123456");
	  loginPage.clickToLoginButton();

	  
	  // LoginPage -> My Dashboard
	  myDashboardPage = new MyDashboardPageObject(driver);
	  
	  
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
  MyDashboardPageObject myDashboardPage;
  

}