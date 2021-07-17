package com.bankguru.login;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Register_Login_Base_Page_2 {
	WebDriver driver;
	String username, password, loginPageUrl;
	BasePage basePage;

  @BeforeClass
  public void initBrowser() {
	  driver = new FirefoxDriver();
	  driver.get("http://demo.guru99.com/V4/index.php");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  
	  // Che giau, An giau su khoi tao 
	  basePage = BasePage.getBasePage();
	  
  }
  
  @Test
  public void Login_01_Register_To_System() {
	  loginPageUrl = basePage.getPageUrl(driver);

	  basePage.clickToElement(driver, "//a[text()='here']");
	  basePage.sendkeyToElement(driver, "//input[@name='emailid']", getRandomEmail());
	  basePage.clickToElement(driver, "//input[@name='btnLogin']");
	  username = basePage.getElemetText(driver, "//td[text()='User ID :']/following-sibling::td");
	  password = basePage.getElemetText(driver, "//td[text()='Password :']/following-sibling::td");

	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  basePage.openPageUrl(driver, loginPageUrl);
	  basePage.sendkeyToElement(driver, "//input[@name='uid']", username);
	  basePage.sendkeyToElement(driver, "//input[@name='password']", password);
	  basePage.clickToElement(driver, "//input[@name='btnLogin']");
	  Assert.assertEquals(basePage.getElemetText(driver, "//marquee[@class='heading3']"), "Welcome To Manager's Page of Guru99 Bank");

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

}