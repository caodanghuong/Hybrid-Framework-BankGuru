package com.jquery.datatale;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.PageGeneratorManager;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class Level_09_DataTable  extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;


  @Parameters({"browser", "url"})
  @BeforeClass
  public void initBrowser(String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  homePage = PageGeneratorManager.getHomePage(driver);
	  //homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
  }
  
  //@Test
  public void Table_01_Paging() {
	  homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
	  
	  homePage.openPageNumber("15");
	  Assert.assertTrue(homePage.isPageActiveByNumber("15"));
	  
	  
	  homePage.openPageNumber("5");
	  Assert.assertTrue(homePage.isPageActiveByNumber("5"));
	  
	  
	  homePage.openPageNumber("20");
	  Assert.assertTrue(homePage.isPageActiveByNumber("20"));
	  
	  
	  
  }


  //@Test
  public void Table_02_Input_Header_Textbox() {
	  // Input to Textbox
	  homePage.inputToHeaderTextboxByName("Females","283821");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
	  
	  homePage.inputToHeaderTextboxByName("Males","802948");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
	  
	  homePage.inputToHeaderTextboxByName("Country","New Zealand");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
  }
  //@Test
  public void Table_03_Click_Icon() {
	  
	  //click ti Icon
	  homePage.clickToIconByCountryName("Angola", "remove");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
	  
	  homePage.clickToIconByCountryName("AFRICA", "remove");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
	  homePage.clickToIconByCountryName("Afghanistan", "edit");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
	  homePage.clickToIconByCountryName("Armenia", "edit");
	  homePage.sleepInSecond(3);
	  homePage.refreshCurrentPage(driver);
	  
  }
 // @Test
  public void Table_04_Verify_Row_Values() {
		  homePage.inputToHeaderTextboxByName("Country","Afghanistan");
		  Assert.assertTrue(homePage.isRowValueDispalyed("384187", "Afghanistan", "407124", "791312"));
		  homePage.sleepInSecond(3);
		  homePage.refreshCurrentPage(driver);
		  
		  homePage.inputToHeaderTextboxByName("Country","Austria");
		  Assert.assertTrue(homePage.isRowValueDispalyed("44241", "Austria", "45685", "89926"));
		  homePage.sleepInSecond(3);
		  homePage.refreshCurrentPage(driver);
	  }
 
  //@Test
  public void Table_05_Input_To_Row_Textbox() {
	  homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

	  homePage.inputToTextboxByRowNumber("Contact Person", "3", "Huong cao");
	  homePage.sleepInSecond(3);
	  
	  homePage.inputToTextboxByRowNumber("Order Placed", "1", "5");
	  homePage.sleepInSecond(3);
	  
	  
	  homePage.inputToTextboxByRowNumber("Company", "2", "Apple");
	  homePage.sleepInSecond(3);
  
  }
  
  @Test
  public void Table_06_Click_Icon_At_Row() {
	  homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
	  homePage.clickToIconByRowNumber("2", "Move Up");
	  homePage.sleepInSecond(3);
	  
	  homePage.clickToIconByRowNumber("3", "Remove Current Row");
	  homePage.sleepInSecond(3);
	  
	  homePage.clickToIconByRowNumber("2", "Remove Current Row");
	  homePage.sleepInSecond(3);
	  
	  homePage.clickToIconByRowNumber("1", "Remove Current Row");
	  homePage.sleepInSecond(3);
  }
  @AfterClass
  public void cleanBrowser() {
	  driver.quit();
  }

}