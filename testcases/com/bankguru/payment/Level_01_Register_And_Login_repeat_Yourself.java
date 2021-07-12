package com.bankguru.payment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_And_Login_repeat_Yourself {
	WebDriver driver;
	String username, password, loginPageUrl;

  @BeforeClass
  public void initBrowser() {
	  driver = new FirefoxDriver();
	  driver.get("http://demo.guru99.com/V4/index.php");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  loginPageUrl = driver.getCurrentUrl();
	  
  }
  
  @Test
  public void Login_01_Register_To_System() {
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(getRandomEmail());
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  username =  driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	  password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	  
	  
  }
  
  @Test
  public void Login_02_Login_To_System() {
	  driver.get(loginPageUrl);
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  String welcomeMessage = driver.findElement(By.cssSelector("marquee.heading3")).getText();
	  
	  Assert.assertEquals(welcomeMessage, "Welcome To Manager's Page of Guru99 Bank");

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
